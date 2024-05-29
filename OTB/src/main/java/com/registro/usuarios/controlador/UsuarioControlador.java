package com.registro.usuarios.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/usuario/view/{usuarioId}")
    public String visualizarPerfil(@PathVariable String usuarioId, Model model){
        Usuario usuario = usuarioServicio.getUsuario(Long.parseLong(usuarioId));
        Usuario usuarioRespuesta = null;
        Boolean mismo = false;

        if(usuario != null )
        {
            String email = usuario.getEmail();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		    String emailAuth = auth.getName();

            if(emailAuth.equals(email))
            {
                mismo = true;
                usuarioRespuesta = new Usuario(usuario.getNombre(), usuario.getApellido(),
                                    usuario.getUsername(), usuario.getDescripcion());
                

            }else{
                usuarioRespuesta = new Usuario(usuario.getNombre(), usuario.getApellido(),
                                    usuario.getDescripcion(), usuario.getEmail(), usuario.getUsername());
            }
            model.addAttribute("mismo", mismo);
            model.addAttribute("usuario", usuarioRespuesta);
            return "index";
        }

        return "error";
    }
    
    @GetMapping("/usuario/delete/{usuarioId}") // Tiene que ser PostMapping, pero de momento para probar he puesto GET
    public String eliminarPerfil(@PathVariable String usuarioId, Model model){

        if(SecurityContextHolder.getContext().getAuthentication().
        getName().equals("admin@gmail.com"))
        {
            Usuario usuario = usuarioServicio.getUsuario(Long.parseLong(usuarioId));
            if(usuario != null)
            {
                usuarioServicio.eliminarUsuario(Long.parseLong(usuarioId));
                return "index";
            }
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
        
        if(SecurityContextHolder.getContext().getAuthentication().
            getName().equals("admin@gmail.com"))
        {
            Usuario usuario = new Usuario();

            if(usuario != null)
            {
                usuarioServicio.editarUsuario(usuario);
                return "index";
            }
        }
        return "error";
    }

}
