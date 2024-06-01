package com.registro.usuarios.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.registro.usuarios.modelo.Articulo;
import com.registro.usuarios.modelo.Solicitud;
import com.registro.usuarios.modelo.Usuario;
import com.registro.usuarios.servicio.ArticuloService;
import com.registro.usuarios.servicio.SolicitudService;
import com.registro.usuarios.servicio.UsuarioServicio;

@Controller
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ArticuloService articuloServicio;

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping("/administrador")
    public String visualizarAccionesAdmin(Model model)
    {
        List<Usuario> usuarios = usuarioServicio.getAll();
        model.addAttribute("usuarios", usuarios);
        
        return "administrador";
    }

    @GetMapping("/usuario/view/{usuarioId}")
    public String visualizarPerfil(@PathVariable String usuarioId, Model model){
        
        Usuario usuario = usuarioServicio.getUsuario(Long.parseLong(usuarioId));
        Usuario usuarioVista = null;
        Boolean mismo = false;

        if(usuario != null )
        {
            String email = usuario.getEmail();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		    String emailAuth = auth.getName();
            mismo = email.equals(emailAuth);
            
            int numeroArticulos = usuario.getUsuariosArticulo().size();
            //float media = usuarioServicio.calcularMediaUsuario(usuarioId);

            if(!mismo)
            {
                usuarioVista = new Usuario(Long.parseLong(usuarioId), usuario.getNombre(), usuario.getApellido(),
                                    usuario.getUsername(), usuario.getDescripcion(), usuario.getImg_src());
                

            }else{
                usuarioVista = new Usuario(Long.parseLong(usuarioId), usuario.getNombre(), usuario.getApellido(),
                                    usuario.getDescripcion(), usuario.getEmail(), usuario.getUsername(), usuario.getImg_src());
            }

            List <Articulo> listaArticulos = usuario.getUsuariosArticulo();
            model.addAttribute("mismo", mismo);
            model.addAttribute("usuario", usuarioVista);
            model.addAttribute("listaArticulos", listaArticulos);
            model.addAttribute("numeroArticulos", numeroArticulos);
            //model.addAttribute("media", media);

            return "usuario";
        }

        return "error";
    }
    
  
    
    @GetMapping("/usuario/delete/{usuarioId}") // Tiene que ser PostMapping, pero de momento para probar he puesto GET
    public String eliminarPerfil(@PathVariable String usuarioId, Model model){

        if(SecurityContextHolder.getContext().getAuthentication().
        getName().equals("a@a.com"))
        {
            Usuario usuario = usuarioServicio.getUsuario(Long.parseLong(usuarioId));
            if(usuario != null)
            {
                usuarioServicio.eliminarUsuario(Long.parseLong(usuarioId));
                return "index";
            }
        }

        return "error";
    }

    @GetMapping("/usuario/edit/{usuarioId}")
    public String editarPerfil(@PathVariable String usuarioId, Model model){
        
        Usuario usuario = usuarioServicio.getUsuario(Long.parseLong(usuarioId));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String email = auth.getName();        
        if(usuario != null  && usuario.getEmail().equals(email))
        {
            model.addAttribute("usuario", usuario);
            model.addAttribute("mismo", true);

            return "usuario_editar";
        }

        return "error";

    }

    @PostMapping("/usuario/edit/{usuarioId}")
    public String verFormularioEdicionUsuario(@PathVariable String usuarioId, Model model,
                                                @ModelAttribute("usuario") Usuario usuario)
    {
        Usuario usuarioDB = usuarioServicio.getUsuario(Long.parseLong(usuarioId));
        String emailLogged = SecurityContextHolder.getContext().getAuthentication().
            getName();

        if(emailLogged.equals("admin@gmail.com") || 
            usuarioDB.getEmail().equals(emailLogged))
        {   
            if(!usuarioDB.getNombre().equals(usuario.getNombre()))
            {
                usuarioDB.setNombre(usuario.getNombre());
            }

            if(!usuarioDB.getApellido().equals(usuario.getApellido()))
            {
                usuarioDB.setApellido(usuario.getApellido());
            }

            if( usuarioDB.getDescripcion() == null || usuarioDB.getDescripcion().equals(usuario.getDescripcion()))
            {
                usuarioDB.setDescripcion(usuario.getDescripcion());
            }

            if(usuarioDB.getImg_src() == null || usuarioDB.getImg_src().equals(usuario.getImg_src()))
            {
                usuarioDB.setImg_src(usuario.getImg_src());
            }
            usuarioServicio.editarUsuario(usuarioDB);
            return "index";
        }

        return "error";
    }

    @GetMapping("/listaDeFavoritos/{usuarioId}")
    public String listaFavoritos(@PathVariable Long usuarioId, Model model) {
        Usuario usuario = usuarioServicio.getUsuario(usuarioId);
        List<Articulo> favoritos = usuario.getArticulos_favoritos();
        model.addAttribute("favoritos", favoritos);
        return "lista_de_favoritos";
    }

    @PostMapping("/addFavorito/{usuarioId}/{articuloId}")
    public String addFavorito(@PathVariable Long usuarioId, @PathVariable Long articuloId) {
        Usuario usuario = usuarioServicio.getUsuario(usuarioId);
        Articulo articulo = articuloServicio.getArticulo(articuloId);
        usuario.add_articulo_favoritos(articulo);
        return "redirect:/";
    }

    @PostMapping("/removeFavorito/{usuarioId}/{articuloId}")
    public String removeFavorito(@PathVariable Long usuarioId, @PathVariable Long articuloId) {
        Usuario usuario = usuarioServicio.getUsuario(usuarioId);
        Articulo articulo = articuloServicio.getArticulo(articuloId);
        usuario.remove_articulo_favoritos(articulo);
        return "redirect:/";
    }

}
