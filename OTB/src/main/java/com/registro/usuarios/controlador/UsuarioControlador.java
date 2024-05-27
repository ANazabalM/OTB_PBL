package com.registro.usuarios.controlador;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class UsuarioControlador {
    
    @GetMapping("/usuario/view/{usuarioId}")
    public String visualizarPerfil(@PathVariable String usuarioId, Model model){
        /*
        Usuario usuario = usuarioServicio.cogerUsuario(Integer.parseInt(usuarioId));
        if(usuario != null)
        {
            model.addAtribute("usuario", usuario);
         * return "verPerfilUsuario";
        }

        return "error";
         * 
         */

        return "a";
    }
    
    @PostMapping("/usuario/delete/{usuarioId}")
    public String eliminarPerfil(@PathVariable String usuarioId, Model model){
        /*
        Usuario usuario = usuarioServicio.cogerUsuario(Integer.parseInt(usuarioId));
        if(usuario != null)
        {
            usuarioServicio.eliminarUsuario(Integer.parseInt(usuarioId));
            return "index";
        }

        return "error";
         * 
         */

        return "a";
    }

    @PostMapping("/usuario/edit/{usuarioId}")
    public String editarPerfil(@PathVariable String usuarioId, Model model){
        /*
        Usuario usuario = usuarioServicio.cogerUsuario(Integer.parseInt(usuarioId));
        if(usuario != null)
        {
            usuarioServicio.eliminarUsuario(Integer.parseInt(usuarioId));
            return "index";
        }

        return "error";
         * 
         */

        return "a";
    }

    @GetMapping("/usuario/edit/{usuarioId}")
    public String verFormularioEdicionUsuario(@PathVariable String usuarioId, Model model)
    {
        /*
         * if(usuario != null)
         * {
         *      usuarioServicio.edit();
         *      return "index";
         * }
         * 
         * return "error";
         */
        return "a";
    }

}
