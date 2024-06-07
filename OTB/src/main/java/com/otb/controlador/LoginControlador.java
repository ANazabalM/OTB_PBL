package com.otb.controlador;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.otb.modelo.Articulo;
import com.otb.modelo.Usuario;
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
		Usuario usuario = null;

		if(!email.equals("anonymousUser") && session.getAttribute("email") == null){

			session.setAttribute("email", email);
			
			usuario = usuarioServicio.buscarPorEmail(email);
		
			if(usuario.getRol() != null && usuario.getRol().equals("administrador"))
			{
				session.setAttribute("admin", true);
			}else if(usuario.getRol() != null && usuario.getRol().equals("avanzado"))
			{
				session.setAttribute("avanzado", true);

			}
			
			session.setAttribute("userId", String.valueOf(usuario.getId()));
		}

		List<Articulo> articulos = articuloServicio.cogerMasVistos();
		model.addAttribute("articulos", articulos);
		
		return "index";
	}
}
