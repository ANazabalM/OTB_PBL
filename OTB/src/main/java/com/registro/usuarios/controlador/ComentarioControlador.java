package com.registro.usuarios.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.modelo.Comentario;

@Controller
public class ComentarioControlador {

    //@Autowired
   // private ComentarioService comentarioServicio;

   // @Autowired
    //private UsuarioServicio usuarioServicio;
    
    @PostMapping("/comentario/create")
    public String crearComentario(Model model)
    {
        /*
         * Articulo articulo = model.getAttribute("articulo", articulo);
         * Comentario comentario = new Comentario(model.getAttribute(comentario), articuloId, model.getAttribute("email"));
         * 
         * comentarioServicio.guardar(comentario);
         * return "articulo";
         */
        return "a";
    }

    @PostMapping("/comentario/delete/{comentarioId}")
    public String crearComentario(@PathVariable String comentarioId, Model model)
    {
        /*
            Comentario comentario = comentarioServicio.cogerComentario(Integer.parseInt(comentarioId));
            if(comentario != null)
            {
                comentarioServicio.deleteComentario(Integer.parseInt(comentarioId));
                return "articulo";
            }
         * return "error";
         */
        return "a";
    }

    @ModelAttribute("comentario")
	public Comentario retornarNuevoComentario() {
		return new Comentario();
	}

    @GetMapping("/comentario/create")
    private String verFormularioCreacion(Model model){
        return "crear_comentario";
    }

    @PostMapping("/articulo/view/{articuloId}")
    private String crearcomentario(@ModelAttribute("comentario") Comentario comentario, Model model){

     //   Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	//	String emailAuth = auth.getName();

    /*     Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);
/* 
        Comentario comentarioNuevo = new Comentario(comentario.getContenido(),
                                                    usuario.getId(),
                                                    usuario.getApellido()
                                                    );

        comentarioServicio.save(comentarioNuevo);
        */
        return "index";
    }
}
