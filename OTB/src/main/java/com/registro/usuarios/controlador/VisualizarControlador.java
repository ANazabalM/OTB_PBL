package com.registro.usuarios.controlador;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.UsuarioServicio;

public class VisualizarControlador {
    
    UsuarioServicio usuarioservicio;
    
    @GetMapping("/visualizar/usuario/{username}")
    public String visulizarUsuario(@PathVariable String username, Model model)
    {
        Usuario usuario = usuarioservicio.getUsuario(username);

        model.addAttribute("usuario", usuario);
        
        return "usuario";
    }
}
