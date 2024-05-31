package com.registro.usuarios.controlador;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.registro.usuarios.modelo.Articulo;
import com.registro.usuarios.servicio.ArticuloService;

@Controller
public class LoginControlador {

	@Autowired
	private ArticuloService articuloServicio;

	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	@GetMapping("/")
	public String verPaginaDeInicio(HttpSession session, Model model) {		
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		if(email.equals("anonymousUser"))
		{
			session.setAttribute("email", null);

		}else if(!email.equals("anonymousUser") || session.getAttribute("email") == null){

			session.setAttribute("email", email);
		}
		
		List<Articulo> articulos = articuloServicio.cogerMasVistos();
		model.addAttribute("articulos", articulos);
	
		return "index";
	}
}
