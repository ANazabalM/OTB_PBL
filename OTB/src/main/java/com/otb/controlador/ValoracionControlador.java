package com.otb.controlador;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.otb.modelo.Articulo;
import com.otb.modelo.Usuario;
import com.otb.modelo.Valoracion;
import com.otb.servicio.ArticuloService;
import com.otb.servicio.UsuarioServicio;
import com.otb.servicio.ValoracionService;

@Controller
public class ValoracionControlador {

    @Autowired
    private ArticuloService articuloServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ValoracionService valoracionService;

    @PostMapping("/valoracion/create/{articuloId}")
    public String crearValoracion(@ModelAttribute("valoracionCrear")
                                        Valoracion valoracion, HttpSession session,
                                        @PathVariable("articuloId") String articuloId)
    {
        if(articuloId != null)
        {
        
            Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));
            if(articulo != null)
            {
                Usuario usuario = usuarioServicio.buscarPorEmail((String)session.getAttribute("email"));

                Valoracion valoracionGuardar = new Valoracion(valoracion.getPuntuacion(), usuario, articulo);
        
                valoracionService.save(valoracionGuardar);
                return "redirect:/articulo/view/" + articuloId;
            }
        }
        
        return "error";        

    }

}
