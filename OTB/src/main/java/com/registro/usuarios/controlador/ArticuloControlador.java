package com.registro.usuarios.controlador;

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
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class ArticuloControlador {
    
    @Autowired
    private ArticuloService articuloServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CategoriaService categoriaServicio;

    @GetMapping("/articulo/view/{articuloId}")
    private String verArticulo(@PathVariable String articuloId, Model model){

        if(articuloId != null)
        {

        Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));

        Categoria catergoriaID = articulo.getCategorias();
        Categoria categoria = categoriaServicio.getCategoria(catergoriaID.getCategoriaId());
        Usuario usuarioA = articulo.getUsuarios();

            if(articulo != null){
                Categoria categoria2 = new Categoria(   categoria.getTitulo(),
                                                        categoria.getColor());
                List<Comentario> comentarios = articulo.getArticuloComentario();

                model.addAttribute("autor", usuarioA);
                model.addAttribute("articulo", articulo);
                model.addAttribute("categoria", categoria2);
                model.addAttribute("comentarios", comentarios);
                model.addAttribute("comentarioCrear", new Comentario());
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

    /*
     *  @GetMapping("/articulo/edit/{articuloId}")
    private String verFormularioEdicion(@PathVariable String articuloId, Model model){

        return "a";
    }

    @PostMapping("/articulo/edit/{articuloId}")
    private String editarArticulo(@PathVariable String articuloId, Model model){

        Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String email = auth.getName();        
        if(articulo != null  && (articulo.getUsuarios().getEmail()).equals(email))
        {
            model.addAttribute("articulo", articulo);

            return "crear_articulo";
        }

        return "error";
    }
     */
   

    @PostMapping("/articulo/delete/{articuloId}")
    private String eliminarArticulo(@PathVariable String articuloId, Model model){

        if(SecurityContextHolder.getContext().getAuthentication().
        getName().equals("admin@gmail.com"))
        {
            Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));
            if(articulo != null)
            {
                articuloServicio.deleteArticulo(Long.parseLong(articuloId));
                return "index";
            }
        }

        return "error";
    }
}
