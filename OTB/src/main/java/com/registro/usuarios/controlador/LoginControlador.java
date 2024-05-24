package com.registro.usuarios.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControlador {

	
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
		}

		return "index";
	}
}
