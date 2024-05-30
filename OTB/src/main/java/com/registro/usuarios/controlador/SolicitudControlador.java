package com.registro.usuarios.controlador;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.modelo.Articulo;
import com.registro.usuarios.modelo.Categoria;
import com.registro.usuarios.modelo.Solicitud;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.ArticuloService;
import com.registro.usuarios.servicio.SolicitudService;
import com.registro.usuarios.servicio.UsuarioServicio;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

@Controller
public class SolicitudControlador {
    
    @Autowired
    private ArticuloService articuloServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;


    @Autowired
    private SolicitudService solicitudServicio;

    @GetMapping("/solicitud/view/{solicitudId}")
    private String verArticulo(@PathVariable String articuloId, Model model){

        if(articuloId != null)
        {

        Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));

        Categoria catergoriaID = articulo.getCategorias();
        Usuario usuarioA = articulo.getUsuarios();
        
            if(articulo != null){
                Articulo articuloVista = new Articulo(  articulo.getTitulo(),
                                                        articulo.getAlt_img(),
                                                        articulo.getSrc_video(),
                                                        articulo.getContenido(),
                                                        usuarioA);

                model.addAttribute("autor", usuarioA);
                model.addAttribute("articulo", articuloVista);
                return "articulo";
            }
        }
        return "error";
    }

    @ModelAttribute("solicitud")
	public Articulo retornarNuevoArticulo() {
		return new Articulo();
	}

    @GetMapping("/solicitud/create")
    private String verFormularioCreacion(Model model){
        return "solicitud_categoria";
    }

    @PostMapping("/solicitud/create")
    private String crearArticulo(@ModelAttribute("solicitud") Solicitud solicitud, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String emailAuth = auth.getName();
        
        Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);

        LocalDate date = LocalDate.now();
        Solicitud solicitudCrear = new Solicitud(   solicitud.getDescripcion(),
                                                    usuario,
                                                    date
                                                    );
        solicitudServicio.save(solicitudCrear);
        return "index";
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
