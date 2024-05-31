package com.registro.usuarios.controlador;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.ComentarioService;
import com.registro.usuarios.servicio.UsuarioServicio;
import com.registro.usuarios.modelo.Articulo;
import com.registro.usuarios.modelo.Comentario;

@Controller
public class ComentarioControlador {

    @Autowired
    private ComentarioService comentarioServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @ModelAttribute("comentario")
	public Comentario retornarNuevoArticulo() {
		return new Comentario();
	}


    @PostMapping("/comentario/create")
    public String crearComentario(Model model, HttpSession session)
    {
        Comentario comentario = (Comentario) model.getAttribute("comentarioCrear");
        Usuario usuario = usuarioServicio.buscarPorEmail((String)session.getAttribute("email"));
        Articulo articulo = (Articulo) model.getAttribute("articulo");
        Comentario comentarioGuardar = new Comentario(comentario.getContenido(), LocalDate.now(), usuario, articulo);

        if(articulo != null)
        {
            comentarioServicio.save(comentarioGuardar);
            return "redirect:/articulo/view/" + articulo.getArticuloId();
        }

        return "error";
        
    }

    @PostMapping("/comentario/delete/{comentarioId}")
    public String crearComentario(@PathVariable String comentarioId, Model model)
    {
        /*
            Comentario comentario = comentarioServicio.cogerComentario(Integer.parseInt(comentarioId));
            if(comentario != null)
            {
                comentarioServicio.deleteComentario(Integer.parseInt(comentarioId));
                return "articulo";
            }
         * return "error";
         */
        return "a";
    }

}
