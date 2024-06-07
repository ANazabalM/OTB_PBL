package com.otb.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.otb.excepciones.ResourceNotFoundException;
import com.otb.modelo.Articulo;
import com.otb.modelo.Usuario;
import com.otb.servicio.ArticuloService;
import com.otb.servicio.UsuarioServicio;

@Controller
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ArticuloService articuloServicio;


    @GetMapping("/administrador")
    public String visualizarAccionesAdmin(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailAuth = auth.getName();  
        Usuario usuarioLogged = usuarioServicio.buscarPorEmail(emailAuth);

        if(usuarioLogged.getTipo().equals("administrador"))
        {
            List<Usuario> usuarios = usuarioServicio.getAll();
            model.addAttribute("usuarios", usuarios);
            return "administrador";
        }
        return "redirect:/";
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
                                    usuario.getUsername(), usuario.getDescripcion(), usuario.getImg_src(), usuario.getTipo());
                

            }else{
                usuarioVista = new Usuario(Long.parseLong(usuarioId), usuario.getNombre(), usuario.getApellido(),
                                    usuario.getDescripcion(), usuario.getEmail(), usuario.getUsername(), usuario.getImg_src(), usuario.getFecha_nacimiento(),
                                    usuario.getTipo());
            }

            List <Articulo> listaArticulos = usuario.getUsuariosArticulo();
            model.addAttribute("mismo", mismo);
            model.addAttribute("usuario", usuarioVista);
            model.addAttribute("listaArticulos", listaArticulos);
            model.addAttribute("numeroArticulos", numeroArticulos);
            //model.addAttribute("media", media);

            return "usuario";
        }

        return "redirect:/";
    }
    
  
    
    @PostMapping("/usuario/delete/{usuarioId}") // Tiene que ser PostMapping, pero de momento para probar he puesto GET
    public String eliminarPerfil(@PathVariable String usuarioId, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailAuth = auth.getName();  
        Usuario usuarioLogged = usuarioServicio.buscarPorEmail(emailAuth);

        if(usuarioLogged.getTipo().equals("administrador"))
        {
            Usuario usuario = usuarioServicio.getUsuario(Long.parseLong(usuarioId));
            if(usuario != null)
            {
                usuarioServicio.eliminarUsuario(Long.parseLong(usuarioId));
                return "redirect:/";
            }
        }

        return "redirect:/";
    }

    @GetMapping("/usuario/edit/{usuarioId}")
    public String editarPerfil(@PathVariable String usuarioId, Model model){
        
        Usuario usuario = usuarioServicio.getUsuario(Long.parseLong(usuarioId));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  

        Usuario usuarioLogged = usuarioServicio.buscarPorEmail(email);

        if(usuario != null  && (usuario.getEmail().equals(email) || 
                        usuarioLogged.getTipo().equals("administrador")))
        {
            model.addAttribute("usuario", usuario);
            model.addAttribute("mismo", true);

            return "usuario_editar";
        }

        return "redirect:/";

    }

    @PostMapping("/usuario/edit/{usuarioId}")
    public String verFormularioEdicionUsuario(@PathVariable String usuarioId, Model model,
                                                @ModelAttribute("usuario") Usuario usuarioEditado)
    {
        Usuario usuarioDB = usuarioServicio.getUsuario(Long.parseLong(usuarioId));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();  

        Usuario usuarioLogged = usuarioServicio.buscarPorEmail(email);


        if(usuarioLogged.getTipo().equals("administrador") || 
            usuarioDB.getEmail().equals(email))
        {   
            if(!usuarioDB.getNombre().equals(usuarioEditado.getNombre()))
            {
                usuarioDB.setNombre(usuarioEditado.getNombre());
            }

            if(usuarioDB.getFecha_nacimiento() != usuarioEditado.getFecha_nacimiento())
            {
                usuarioDB.setFecha_nacimiento(usuarioEditado.getFecha_nacimiento());
            }

            if(!usuarioDB.getApellido().equals(usuarioEditado.getApellido()))
            {
                usuarioDB.setApellido(usuarioEditado.getApellido());
            }

            if(!usuarioEditado.getDescripcion().equals("") && (usuarioDB.getDescripcion() == null ||
                                     !usuarioDB.getDescripcion().equals(usuarioEditado.getDescripcion())))
            {
                usuarioDB.setDescripcion(usuarioEditado.getDescripcion());
            }

            if(!usuarioEditado.getImg_src().equals("")  && (usuarioDB.getImg_src() != null ||
                                    usuarioDB.getImg_src().equals(usuarioEditado.getImg_src())))
            {
                usuarioDB.setImg_src(usuarioEditado.getImg_src());
            }
            usuarioServicio.editarUsuario(usuarioDB);
            model.addAttribute("success", "Usuario creado con éxito");
            return "redirect:/usuario/view/" + usuarioId;
        }

        return "redirect:/";
    }

    @GetMapping("/listaDeFavoritos")
    public String listaFavoritos(Model model) {
        String emailLogged = SecurityContextHolder.getContext().getAuthentication().
            getName();

        if(!emailLogged.equals("anonymousUser"))
        {
            List<Articulo> favoritos = usuarioServicio.buscarPorEmail(emailLogged).getArticulos_favoritos();
            model.addAttribute("favoritos", favoritos);
            return "lista_de_favoritos";
        }
        return "redirect:/";
    }

    @PostMapping("/listaDeFavoritos/add/{articuloId}")
    public String addFavorito(@PathVariable Long articuloId) {
        
        String emailLogged = SecurityContextHolder.getContext().getAuthentication().
        getName();

        if(!emailLogged.equals("anonymousUser"))
        {
            Articulo articulo = articuloServicio.getArticulo(articuloId);
            if(articulo != null)
            {
                Usuario usuario = usuarioServicio.buscarPorEmail(emailLogged);
                if(usuario.getArticulos_favoritos().indexOf(articulo) == -1)
                {
                    usuario.add_articulo_favoritos(articulo);
                    usuarioServicio.editarUsuario(usuario);
                }
                return "redirect:/articulo/view/" + articuloId;
            }
        }
        return "redirect:/";

    }

    @PostMapping("/listaDeFavoritos/delete/{articuloId}")
    public String removeFavorito(@PathVariable Long articuloId) {
        
        String emailLogged = SecurityContextHolder.getContext().getAuthentication().
        getName();

        if(!emailLogged.equals("anonymousUser"))
        {
            Articulo articulo = articuloServicio.getArticulo(articuloId);
            if(articulo != null)
            {
                Usuario usuario = usuarioServicio.buscarPorEmail(emailLogged);
                usuario.remove_articulo_favoritos(articulo);
                usuarioServicio.editarUsuario(usuario);
                return "redirect:/listaDeFavoritos";
            }
        }
        return "redirect:/";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "redirect:/";
    }
}
