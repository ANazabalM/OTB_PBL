package com.registro.usuarios.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CategoriaControlador {
    
    /*
     * private CategoriaServicio categoriaServicio
     */

    public CategoriaControlador(//CategoriaServicio categoriaServicio
    ) {
		super();
		//this.categoriaServicio = categoriaServicio;
	}

    @GetMapping("/categoria/view/{categoriaId}")
    public String showCategoria(@PathVariable String categoriaId, Model model){
        /*
        if(categoriaId == null)
        {
            List<Categoria> categorias = categoriaServicio.cogerTodas();
            model.addAtribute("categorias", categorias);
            return "categorias";
        }
        Categoria categoria = categoriaServicio.coger(String.parseInt(categoriaId));
        ArticuloServicio articuloServicio = new ArticuloServicio;

        List<Articulo> articulos = articuloServicio.cogerTodos(categoriaId);
        model.addAtribute("articulos", articulos);
        model.addAtribute("categoria", categoria);

        return "categoria";
         */

        return "a";
    }
}
