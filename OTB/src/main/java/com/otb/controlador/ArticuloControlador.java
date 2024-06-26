package com.otb.controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.otb.excepciones.ResourceNotFoundException;
import com.otb.modelo.Articulo;
import com.otb.modelo.Categoria;
import com.otb.modelo.Comentario;
import com.otb.modelo.ComentarioAJAX;
import com.otb.modelo.Usuario;
import com.otb.modelo.Valoracion;
import com.otb.servicio.ArticuloService;
import com.otb.servicio.CategoriaService;
import com.otb.servicio.ComentarioService;
import com.otb.servicio.UsuarioServicio;
import com.otb.servicio.ValoracionService;

@Controller
public class ArticuloControlador {
    
    @Autowired
    private ArticuloService articuloServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CategoriaService categoriaServicio;

    @Autowired
    private ValoracionService valoracionService;

    @Autowired
    private ComentarioService comentarioService;

    
    @GetMapping("/articulo/view/{articuloId}")
    private String verArticulo(@PathVariable String articuloId, Model model){

        Long articuloFavorito= null;
        if(articuloId != null)
        {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String emailAuth = auth.getName();

        Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);

        if(usuario == null){
            Long idusuarioANONIMO = Long.parseLong("1");
            usuario = usuarioServicio.getUsuario(idusuarioANONIMO);
        }
        
        if(usuario != null && articuloId != null){
        articuloFavorito = articuloServicio.cogerArticuloFavorito(usuario.getId(), Long.parseLong(articuloId));
        }
        

        Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));

        Categoria catergoriaID = articulo.getCategorias();
        Categoria categoria = categoriaServicio.getCategoria(catergoriaID.getCategoriaId());

        
            List<Valoracion> listaValoracion = valoracionService.cogerLasValoracion(Long.parseLong(articuloId));
            int mediaValoracion = 0;
            if(listaValoracion.size()!=0){
                int i =0 ;
                for (Valoracion valoracion2 :listaValoracion){
                    i++;
                    mediaValoracion = mediaValoracion + valoracion2.getPuntuacion();
                }
                        mediaValoracion = (mediaValoracion / i);
                        articulo.setValoracionMedia(mediaValoracion);
            }else articulo.setValoracionMedia(0);

        Usuario usuarioA = articulo.getUsuarios();


            if(articulo != null){
                Categoria categoria2 = new Categoria(   categoria.getTitulo(),
                                                        categoria.getColor());
                List<Comentario> comentarios = articulo.getArticuloComentario();

                articulo.addVisualizacion(usuario,articulo);

                if(articuloFavorito == null)model.addAttribute("articuloCorazon", "fa-regular fa-heart card-icon");
                else model.addAttribute("articuloCorazon", "fas fa-heart card-icon favorited");

                model.addAttribute("autor", usuarioA);
                model.addAttribute("articulo", articulo);
                model.addAttribute("categoria", categoria2);
                model.addAttribute("comentarios", comentarios);
                model.addAttribute("currentEmail", emailAuth);
                articuloServicio.guardarArticulo(articulo);
                return "articulo";
            }
        }
        return "redirect:/";
    }

    @ModelAttribute("articulo")
	public Articulo retornarNuevoArticulo() {
		return new Articulo();
	}

    @GetMapping("/articulo/create")
    private String verFormularioCreacion(Model model){
        model.addAttribute("categoriasVisualizar", categoriaServicio.cogerTodas());
        return "crear_articulo";
    }
    
    @ModelAttribute("comentarioCrear")
	public Comentario retornarNuevoComentario() {
		return new Comentario();
	}   

    @PostMapping("/articulo/create")
    private String crearArticulo(@ModelAttribute("articulo") Articulo articulo, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String emailAuth = auth.getName();
        
        Categoria categoria = articulo.getCategorias();
        Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);

        Articulo articuloNuevo = new Articulo(  articulo.getTitulo(),
                                                articulo.getAlt_img(),
                                                articulo.getSrc_video(),
                                                articulo.getSrc_img(),
                                                articulo.getContenido(),
                                                articulo.getLang(),
                                                usuario,
                                                LocalDate.now(),
                                                categoria);

        model.addAttribute("success", "Articulo creado con éxito");
        articuloServicio.save(articuloNuevo);
        return "redirect:/";
    }

    @PostMapping("/articulo/delete/{articuloId}")
    private String eliminarArticulo(@PathVariable String articuloId, HttpSession session,
                                        Model model){

        Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));

        if(articulo != null)
        {
            Usuario usuario = usuarioServicio.buscarPorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            if((usuario.getTipo().equals("administrador")) || (session.getAttribute("email").equals(articulo.getUsuarios().getEmail())))
            {
                List<Valoracion> valoraciones = valoracionService.cogerLasValoracion(Long.parseLong(articuloId));
                for (Valoracion val : valoraciones){
                    valoracionService.borrarValoracion(val);
                }
                List<Comentario> com = comentarioService.cogerLosComentarios(Long.parseLong(articuloId));
                for (Comentario comm : com){
                    comentarioService.borrarComentario(comm);
                }
                Articulo art = articuloServicio.cojerArticulo(Long.parseLong(articuloId));
                articuloServicio.deleteArticulo(art);
                
            }
        }
        
        model.addAttribute("success", "Articulo eliminado con éxito");

        return "redirect:/";
    }

    @GetMapping("/articulo/{articuloId}/comentarios")
    @ResponseBody
    public List<ComentarioAJAX> getComentarios(@PathVariable String articuloId) {
        Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));
        List<Comentario> comentarios = articulo.getArticuloComentario();
        List<ComentarioAJAX> comentariosVisualizar = new ArrayList<>();

        for(Comentario comentario: comentarios)
        {
            comentariosVisualizar.add(new ComentarioAJAX(comentario.getComentarioId(), 
                comentario.getContenido(), comentario.getUsuariosComentarios().getUsername(),
                comentario.getUsuariosComentarios().getEmail()));
        }

        return comentariosVisualizar;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "redirect:/";
    }

    @ModelAttribute("valoracionCrear")
	public Valoracion retornarNuevValoracion() {
		return new Valoracion();
	}
    
    @PostMapping("/valoracion/create/{articuloId}")
    public String crearValoracion(@ModelAttribute("valoracionCrear")
                                        Valoracion valoracion, HttpSession session,
                                        @PathVariable("articuloId") String articuloId)
    {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String emailAuth = auth.getName();

        Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);

        if(usuario != null ){
            if(articuloId != null)
            {
            
                Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));
                if(articulo != null)
                {
                    Usuario usuario2 = usuarioServicio.buscarPorEmail((String)session.getAttribute("email"));

                    Valoracion valoracionGuardar = new Valoracion(valoracion.getPuntuacion(), usuario2, articulo);
            
                    valoracionService.save(valoracionGuardar);
                    return "redirect:/articulo/view/" + articuloId;
                }
            }
        }
        
    return "redirect:/";        

    }

}
