package com.registro.usuarios.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
            Articulo articulo = articuloServicio.cogerArticulo(Integer.parseInt(articuloId));
         *  model.addAttribute("articulo", articulo);
            return "articulo";
        */
        return "a";
    }

    @GetMapping("/articulo/create")
    private String verFormularioCreacion(Model model){
        return "formularioArticulo";
    }

    @PostMapping("/articulo/create")
    private String crearArtiuclo(Model model){

        /* 
            Articulo articuloA = model.getAttribute("articulo");
            articuloService.guardarArticulo(articuloA);
            return "index";
        */
        return "a";
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
