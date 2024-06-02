package com.registro.usuarios.controlador;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.ArticuloService;
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

    @Autowired
    private ArticuloService articuloServicio;

    @PostMapping("/comentario/create/{articuloId}")
    public String crearComentario(@ModelAttribute("comentarioCrear")
                                        Comentario comentario, HttpSession session,
                                        @PathVariable("articuloId") String articuloId)
    {
        if(articuloId != null)
        {
        
            Articulo articulo = articuloServicio.getArticulo(Long.parseLong(articuloId));
            if(articulo != null)
            {
                Usuario usuario = usuarioServicio.buscarPorEmail((String)session.getAttribute("email"));

                Comentario comentarioGuardar = new Comentario(comentario.getContenido(), LocalDate.now(), usuario, articulo);
        
                comentarioServicio.save(comentarioGuardar);
                return "redirect:/articulo/view/" + articuloId;
            }
        }
        return "error";        
    }

    
    @PostMapping("/comentario/delete/{comentarioId}") // Tiene que ser PostMapping, pero de momento para probar he puesto GET
    public String eliminarComentario(@PathVariable Long comentarioId, HttpSession session){

        Comentario comentario = comentarioServicio.getComentario(comentarioId);

        if(comentario != null)
        {
            Usuario usuario = usuarioServicio.buscarPorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            Articulo articulo = articuloServicio.getArticulo(comentario.getArticulosComentarios().getArticuloId());

            if((usuario.getRol().equals("admin")) || (session.getAttribute("email").equals(comentario.getUsuariosComentarios().getEmail())))
            {
                comentarioServicio.borrarComentario(comentario);
            }
            return "redirect:/articulo/view/" + articulo.getArticuloId();
        }

        return "index";
    }
}
