package com.registro.usuarios.controlador;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.modelo.Articulo;
import com.registro.usuarios.modelo.Comentario;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.modelo.Valoracion;
import com.registro.usuarios.servicio.ArticuloService;
import com.registro.usuarios.servicio.UsuarioServicio;
import com.registro.usuarios.servicio.ValoracionService;

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
