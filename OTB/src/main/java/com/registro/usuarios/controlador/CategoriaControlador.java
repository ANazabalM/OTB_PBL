package com.registro.usuarios.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.modelo.Articulo;
import com.registro.usuarios.modelo.Categoria;
import com.registro.usuarios.servicio.ArticuloService;
import com.registro.usuarios.servicio.CategoriaService;


@Controller
public class CategoriaControlador {
    
    @Autowired
    private CategoriaService categoriaServicio;
    
    @Autowired
    private ArticuloService articuloServicio;


    @GetMapping("/categoria/view/{categoriaId}")
    public String showCategoria(@PathVariable String categoriaId, Model model){
        
        if(categoriaId != null)
        {
            List<Articulo> articulos = articuloServicio.cogerTodos();
            List<Articulo> articulosVisualizar = new ArrayList<>();
            Categoria categoria = categoriaServicio.getCategoria(Long.parseLong(categoriaId));

            for(Articulo articulo : articulos)
            {
                String articuloId = String.valueOf(articulo.getCategorias().getCategoriaId());
                if(articuloId.equals(categoriaId))
                {
                    articulo.setUsuarios(articulo.getUsuarios());
                    articulosVisualizar.add(articulo);
                }
            }
            model.addAttribute("articulos", articulosVisualizar);
            model.addAttribute("categoria", categoria);
            return "tema";
        }

        return "error";
    }

    @GetMapping("/categoria/view")
    public String showCategorias(Model model){
        
        List<Categoria> categorias = categoriaServicio.cogerTodas();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @PostMapping("/categoria/delete/{categoriaId}")
    public String eliminarCategoria(@PathVariable String categoriaId){
        
        /* 
            Categoria categoria = categoriaService.getCategoria(String.parseInt(categoriaId));

            if(categoria != null)
            {
                categoriaService.eliminarCategoria(categoria.getId());
                return "index";
            }
            
            return "error";
        */
        
        return "a";
    }

    @GetMapping("/categoria/create")
    public String verFormularioCreacionCategoria(){  
        return "formularioCategoria";
    }

    @GetMapping("/categoria")
    public String a(){  
        return "categorias";
    }


    @PostMapping("/categoria/create")
    public String crearCategoria(Model model){
        /*
            Categoria categoria = model.getAttribute("categoria");
            categoriaServicio.guardarCategoria(categoria);
            return "index";
         */
        return "a";
    }


    @GetMapping("/categoria/edit/{categoriaId}")
    public String verFomularioEditarCategoria(@PathVariable String categoriaId){
        
        /*
         * Categoria categoria = categoriaService.cogerCategoria(Integer.parseInt(categoriaId));
         * if(categoria != null)
         * {
         *      model.addAttribute("categoria", categoria);
         *      return "formilarioCategoria";
         * }
         * 
         * return "error";
         */
        return "a";
    }

    @PostMapping("/categoria/edit/{categoriaId}")
    public String editarCategoria(@PathVariable String categoriaId, Model model){
        
        /*
         * Categoria categoriaA = categoriaService.cogerCategoria(Integer.parseInt(categoriaId));
         * Categoria categoria = model.getAttribute("categoria");
         * if(categoriaA != null)
         * {
         *      articuloServicio.editarCategoria(categoria);
         *      return "index";
         * }
         * 
         * return "error";
         */
        return "a";
    }
}
