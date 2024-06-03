package com.otb.controlador;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.otb.modelo.Articulo;
import com.otb.servicio.ArticuloService;
import com.otb.servicio.UsuarioServicio;

@Controller
public class LoginControlador {

	@Autowired
	private ArticuloService articuloServicio;

	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	@GetMapping("/")
	public String verPaginaDeInicio(HttpSession session, Model model) {		
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Long userId = null;

		if(!email.equals("anonymousUser") && session.getAttribute("email") == null){

			session.setAttribute("email", email);
			userId = usuarioServicio.buscarPorEmail(email).getId();
			session.setAttribute("userId", String.valueOf(userId));
		}

		List<Articulo> articulos = articuloServicio.cogerMasVistos();
		model.addAttribute("articulos", articulos);
		
		return "index";
	}
}
