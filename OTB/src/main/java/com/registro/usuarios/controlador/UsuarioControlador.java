package com.registro.usuarios.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.UsuarioServicio;

public class UsuarioControlador {
    
    UsuarioServicio usuarioServicio;

    @GetMapping("/usuario/view/{username}")
    public String visualizarPerfil(@PathVariable String username, Model model){
        Usuario usuario = usuarioServicio.getUsuario(username);
        Usuario usuarioRespuesta = null;

        if(usuario != null )
        {
            String email = usuario.getEmail();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		    String emailAuth = auth.getName();

            if(emailAuth.equals(email))
            {
                usuarioRespuesta = new Usuario();

            }else{
                usuarioRespuesta = new Usuario();
            }
            
            model.addAttribute("usuario", usuarioRespuesta);
            return "verPerfilUsuario";
        }

        return "error";
    }
    
    @PostMapping("/usuario/delete/{username}")
    public String eliminarPerfil(@PathVariable String usuarioId, Model model){

        Usuario usuario = usuarioServicio.cogerUsuarioId(Long.parseLong(usuarioId));
        if(usuario != null)
        {
            //usuarioServicio.eliminarUsuario(Integer.parseInt(usuarioId));
            return "index";
        }

        return "error";
    }

    @PostMapping("/usuario/edit/{usuarioId}")
    public String editarPerfil(@PathVariable String usuarioId, Model model){
        /*
        Usuario usuario = usuarioServicio.cogerUsuario(Integer.parseInt(usuarioId));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String email = auth.getName();        
        if(usuario != null  && usuario.getEmai().equals(email))
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
