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
import com.registro.usuarios.modelo.Solicitud;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.SolicitudService;
import com.registro.usuarios.servicio.UsuarioServicio;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

@Controller
public class SolicitudControlador {
    

    @Autowired
    private UsuarioServicio usuarioServicio;


    @Autowired
    private SolicitudService solicitudServicio;

    @GetMapping("/solicitud/view/{solicitudId}")
    private String verArticulo(@PathVariable String articuloId, Model model){

        
        return "error";
    }

    @ModelAttribute("solicitud")
	public Solicitud retornarNuevoArticulo() {
		return new Solicitud();
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
        Solicitud solicitudCrear = new Solicitud(   solicitud.getTitulo(),
                                                    solicitud.getDescripcion(),
                                                    usuario,
                                                    date
                                                    );
        solicitudServicio.save(solicitudCrear);
        return "index";
    }
}