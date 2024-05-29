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

            if(articulo != null)
            {
                Articulo articuloVista = new Articulo(articulo.getTitulo(), articulo.getFecha_publ(),
                                                        articulo.getContenido(), articulo.getAlt_img(),
                                                        articulo.getSrc_img());
                Usuario usuario = new Usuario(articulo.getUsuarios().getId(),
                                                articulo.getUsuarios().getUsername());
                Categoria categoria = new Categoria(articulo.getCategorias().getTitulo(),
                                        articulo.getCategorias().getColor());
                List<Comentario> comentarios = articulo.getArticuloComentario();

                model.addAttribute("autor", usuario);
                model.addAttribute("articulo", articuloVista);
                model.addAttribute("categoria", categoria);
                model.addAttribute("comentarios", comentarios);

                return "index";
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
    private String crearArticulo(@ModelAttribute("articulo") Articulo articulo){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String emailAuth = auth.getName();


        Categoria categoria = categoriaServicio.findByCategoriaId(null);
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

    @GetMapping("/articulo/edit/{articuloId}")
    private String verFormularioEdicion(@PathVariable String articuloId, Model model){

        /* 
            Articulo articulo = articuloServicio.cogerArticulo(Integer.parseInt(articuloId));
            if(articulo != null)
            {
                model.addAttribute("articulo", articulo);
                return "formularioArticulo";
            }
            return "index";
        */
        return "a";
    }

    @PostMapping("/articulo/edit/{articuloId}")
    private String editarArticulo(@PathVariable String articuloId, Model model){

        /* 
            Articulo articuloA = articuloServicio.cogerArticulo(Integer.parseInt(articuloId));
            Articulo articulo = model.getAttribute("articulo");

            if(articuloA != null)
            {
                articuloServicio.editarArticulo(articulo);
                return "index";
            }

            return "error";
        */
        return "a";
    }

    @PostMapping("/articulo/delete/{articuloId}")
    private String eliminarArticulo(@PathVariable String articuloId, Model model){

        /* 
            Articulo articulo = articuloServicio.cogerArticulo(Integer.parseInt(articuloId));

            if(articulo != null)
            {
                articuloServicio.eliminarArticulo(articulo);
                return "index";
            }
            
            return "error";
        */
        return "a";
    }
}
