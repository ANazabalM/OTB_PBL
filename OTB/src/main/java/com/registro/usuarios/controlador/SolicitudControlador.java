package com.registro.usuarios.controlador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/solicitud/view")
    private String listaSolicitudes(Model model){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailAuth = auth.getName();  
        Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);

        if(usuario.getTipo().equals("avanzado") || usuario.getTipo().equals("administrador"))
        {
            List<Solicitud> solicitudes = solicitudServicio.getAll();
            model.addAttribute("solicitudes", solicitudes);
        }

        return "solicitudes";
    }

    @GetMapping("/solicitud/view/{solicitudId}")
    private String verSolicitud(@PathVariable Long solicitudId, Model model){

        if(solicitudId != null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String emailAuth = auth.getName();  
            Usuario usuarioLogged = usuarioServicio.buscarPorEmail(emailAuth);

            if(usuarioLogged.getTipo().equals("avanzado") || usuarioLogged.getTipo().equals("administrador"))
            {
                Solicitud solicitud = solicitudServicio.getSolicitud(solicitudId);

                Usuario usuario = solicitud.getSolicitudUsuarios();

                Solicitud solicitudVista = new Solicitud(solicitud.getSolicitudId(),
                                                solicitud.getTitulo(),
                                                solicitud.getDescripcion(),
                                                solicitud.getFecha_sol());
                model.addAttribute("solicitud", solicitudVista);
                model.addAttribute("usuario", usuario);
                return "solicitud";
            }
        }
        return "error";
    }


    @ModelAttribute("solicitud")
	public Solicitud retornarNuevoSolicitud2() {
		return new Solicitud();
	}

    @GetMapping("/solicitud/create")
    private String verFormularioCreacion(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailAuth = auth.getName();  
        Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);

        if(usuario.getTipo().equals("avanzado") || usuario.getTipo().equals("administrador"))
        {
            return "solicitud_categoria";
        }
        return "index";
    }

    @PostMapping("/solicitud/create")
    private String crearArticulo(@ModelAttribute("solicitud") Solicitud solicitud, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailAuth = auth.getName();  
        Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);
        if(usuario.getTipo().equals("avanzado") || usuario.getTipo().equals("administrador"))
        {
            
            LocalDate date = LocalDate.now();
            Solicitud solicitudCrear = new Solicitud(   solicitud.getTitulo(),
                                                    solicitud.getDescripcion(),
                                                    usuario,
                                                    date
                                                    );
            solicitudServicio.save(solicitudCrear);
        }
        return "index";
    }


    @GetMapping("/solicitud/delete/{solicitudId}") // Tiene que ser PostMapping, pero de momento para probar he puesto GET
    public String eliminarSolicitud(@PathVariable Long solicitudId, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailAuth = auth.getName();  
        Usuario usuario = usuarioServicio.buscarPorEmail(emailAuth);

        if(usuario.getTipo().equals("avanzado") || usuario.getTipo().equals("administrador"))
        {
            Solicitud solicitud = solicitudServicio.getSolicitud(solicitudId);

            solicitudServicio.borrarSolicitud(solicitud);
        }
        
        return "index";
    }
}
