package com.registro.usuarios.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ComentarioControlador {
    
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
}
