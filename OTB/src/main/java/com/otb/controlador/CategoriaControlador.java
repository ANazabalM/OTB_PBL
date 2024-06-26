package com.otb.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.otb.excepciones.ResourceNotFoundException;
import com.otb.modelo.Articulo;
import com.otb.modelo.Categoria;
import com.otb.modelo.Usuario;
import com.otb.modelo.Valoracion;
import com.otb.servicio.ArticuloService;
import com.otb.servicio.CategoriaService;
import com.otb.servicio.UsuarioServicio;
import com.otb.servicio.ValoracionService;

@Controller
public class CategoriaControlador {
    
    @Autowired
    private CategoriaService categoriaServicio;
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ArticuloService articuloServicio;

    @Autowired
    private ValoracionService valoracionServicio;

    @GetMapping("/categoria/view/{categoriaId}")
    public String showCategoria(@PathVariable String categoriaId, Model model){
        
        if(categoriaId != null)
        {

            List<Articulo> articulos = articuloServicio.cogerArticulosCategoria(categoriaId);
            Categoria categoria = categoriaServicio.getCategoria(Long.parseLong(categoriaId));
        
            for (Articulo articulo2 :articulos){
                List<Valoracion> listaValoracion = valoracionServicio.cogerLasValoracion(articulo2.getArticuloId());
                int mediaValoracion = 0;
                if(listaValoracion.size()!=0){
                    int i =0 ;
                    for (Valoracion valoracion2 :listaValoracion){
                        i++;
                        mediaValoracion = mediaValoracion + valoracion2.getPuntuacion();
                    }
                            mediaValoracion = (mediaValoracion / i);
                            articulo2.setValoracionMedia(mediaValoracion);
                }else articulo2.setValoracionMedia(0);
                        
                        
            }
            model.addAttribute("articulos", articulos);
            model.addAttribute("categoria", categoria);

            return "tema";
        }
        
        return "redirect:/";
    }

    @GetMapping("/categoria/view")
    public String showCategorias(Model model){
        List<Categoria> categorias = categoriaServicio.cogerTodas();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @ModelAttribute("categoria")
	public Categoria retornarNuevoCategoria() {
		return new Categoria();
	}

    @GetMapping("/categoria/create")
    public String verFormularioCreacionCategoria(){ 
        Usuario usuario = usuarioServicio.buscarPorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(usuario.getTipo() != null && usuario.getTipo().equals("administrador"))
        {
            return "crear_categoria";
        }
        return "redirect:/";
    }

    @PostMapping("/categoria/create")
    public String crearCategoria(@ModelAttribute("categoria") Categoria categoria,
                                    Model model){

        Usuario usuario = usuarioServicio.buscarPorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(usuario.getTipo().equals("administrador"))
        {
            categoriaServicio.save(categoria);
        }
        model.addAttribute("success", "Categoria creada con éxito");

        return "redirect:/";
    }

    @GetMapping("/categoria/delete/{categoriaId}") // Tiene que ser PostMapping, pero de momento para probar he puesto GET
    public String eliminarCategoria(@PathVariable Long categoriaId, Model model){

        Categoria categoria = categoriaServicio.getCategoria(categoriaId);

        if(categoria != null)
        {
            Usuario usuario = usuarioServicio.buscarPorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            if(usuario.getTipo().equals("administrador"))
            {
                categoriaServicio.borrarCategoria(categoria);
            }
        }
        model.addAttribute("success", "Categoria eliminada con éxito");

        return "redirect:/";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "redirect:/";
    }
}
