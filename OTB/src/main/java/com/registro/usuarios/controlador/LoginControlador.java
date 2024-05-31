package com.registro.usuarios.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public String verPaginaDeInicio(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String email = auth.getName();

		if(!email.equals("anonymousUser"))
		{
			model.addAttribute("email", email);
		}else{
			model.addAttribute("email", null);
		}


		List<Articulo> articulos = articuloServicio.cogerMasVistos();
		model.addAttribute("articulos", articulos);
	

		return "index";
	}
}
