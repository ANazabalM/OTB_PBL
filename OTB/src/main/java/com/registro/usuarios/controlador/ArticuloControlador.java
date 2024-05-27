package com.registro.usuarios.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticuloControlador {
    
    /* 
     * private ArticuloServicio articuloServicio;
    */

    @GetMapping("/articulo/view/{articuloId}")
    private String verArticulo(@PathVariable String articuloId, Model model){
        if(articuloId == null)
        {
            return "error";
        }

        /* 
            Articulo articulo = articuloServicio.cogerArticulo(articuloId);
         * model.addAttribute("articulo", articulo);
            return "articulo";
        */
        return "a";
    }
}
