package com.registro.usuarios.controlador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.modelo.Articulo;
import com.registro.usuarios.modelo.Categoria;
import com.registro.usuarios.modelo.Comentario;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.ArticuloService;
import com.registro.usuarios.servicio.CategoriaService;
import com.registro.usuarios.servicio.ComentarioService;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class ArticuloControlador {
    
    @Autowired
    private ArticuloService articuloServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CategoriaService categoriaServicio;
    
    @Autowired
    private ComentarioService comentarioServicio;

    @GetMapping("/articulo/view/{articuloId}")
    private String verArticulo(@PathVariable String articuloId, Model model){

        if(articuloId != null)
        {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String emailAuth = auth.getName();

        //Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);

        if(usuario == null){
            Long idusuarioANONIMO = Long.parseLong("1");
            usuario = usuarioServicio.getUsuario(idusuarioANONIMO);
        }

        Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));

        Categoria catergoriaID = articulo.getCategorias();
        Categoria categoria = categoriaServicio.getCategoria(catergoriaID.getCategoriaId());

        Usuario usuarioA = articulo.getUsuarios();

            if(articulo != null){
                Categoria categoria2 = new Categoria(   categoria.getTitulo(),
                                                        categoria.getColor());
                List<Comentario> comentarios = articulo.getArticuloComentario();

                //articulo.addVisualizacion(usuario,articulo);

                model.addAttribute("autor", usuarioA);
                model.addAttribute("articulo", articulo);
                model.addAttribute("categoria", categoria2);
                model.addAttribute("comentarios", comentarios);
                articuloServicio.guardarArticulo(articulo);
                return "articulo";
            }
        }
        return "error";
    }

    @ModelAttribute("articulo")
	public Articulo retornarNuevoArticulo() {
		return new Articulo();
	}

    @GetMapping("/articulo/create")
    private String verFormularioCreacion(Model model){
        return "crear_articulo";
    }

    @ModelAttribute("comentarioCrear")
	public Comentario retornarNuevoComentario() {
		return new Comentario();
	}


    @PostMapping("/articulo/view/{articuloId}/comentario/create") //Deberia 
    public String crearComentario(@ModelAttribute("comentarioCrear") Comentario comentario, 
                                        @PathVariable String articuloId)
    {
        Usuario usuario = usuarioServicio.buscarPorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));
        Comentario comentarioGuardar = new Comentario(comentario.getContenido(), LocalDate.now(), usuario, articulo);

        if(articulo != null)
        {
            comentarioServicio.save(comentarioGuardar);
            return "redirect:/articulo/view/" + articulo.getArticuloId();
        }

        return "error";
        
    }

    @PostMapping("/articulo/create")
    private String crearArticulo(@ModelAttribute("articulo") Articulo articulo, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String emailAuth = auth.getName();

        Categoria catergoriaID = articulo.getCategorias();
        
        Categoria categoria = categoriaServicio.getCategoria(catergoriaID.getCategoriaId());
        Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);


        Articulo articuloNuevo = new Articulo(  articulo.getTitulo(),
                                                articulo.getAlt_img(),
                                                articulo.getSrc_video(),
                                                articulo.getContenido(),
                                                articulo.getLang(),
                                                usuario,
                                                categoria);

        articuloServicio.save(articuloNuevo);
        return "index";
    }

    @PostMapping("/articulo/delete/{articuloId}")
    private String eliminarArticulo(@PathVariable Long articuloId, Model model){

        articuloServicio.deleteArticulo(articuloId);

        return "index";
    }
}
