package com.registro.usuarios.controlador;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class UsuarioControlador {
    
    @GetMapping("/usuario/view/{usuarioId}")
    public String visualizarPerfil(@PathVariable String usuarioId, Model model){
        /*
            
         * model.addAtribute("usuario", usuario);
         * return "verPerfilUsuario";
         */

        return "a";
    }
}
