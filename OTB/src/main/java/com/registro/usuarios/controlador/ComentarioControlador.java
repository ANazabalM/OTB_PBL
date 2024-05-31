package com.registro.usuarios.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.registro.usuarios.modelo.Comentario;
import com.registro.usuarios.servicio.ComentarioService;

@Controller
public class ComentarioControlador {

    @Autowired
    private ComentarioService comentarioServicio;

    @GetMapping("/comentario/delete/{comentarioId}") // Tiene que ser PostMapping, pero de momento para probar he puesto GET
    public String eliminarComentario(@PathVariable Long comentarioId, Model model){

        Comentario comentario = comentarioServicio.getComentario(comentarioId);

        comentarioServicio.borrarComentario(comentario);

        return "index";
    }
}
