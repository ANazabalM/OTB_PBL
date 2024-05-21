package com.example.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.person.model.Usuario;
import com.example.person.service.UsuarioDTO;
import com.example.person.service.UsuarioService;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuario")
    @ResponseBody
    public List<Usuario> listarUsuario(){
        return usuarioService.listarUsuario();
    }

    @PostMapping("/usuario")
    @ResponseBody
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.crearUsuario(usuario);
    }

    @DeleteMapping("/usuario/{id}")
    public void borrarUsuario(@PathVariable Long id){
        usuarioService.borrarUsuario(id);
    }

    @GetMapping("/usuario/{id}")
    @ResponseBody
    public Usuario buscarUsuarioPorId(Long id){
        return usuarioService.buscarUsuarioPorId(id);
    }

    @PutMapping("/usuario")
    public void modificarUsuario(@RequestBody Usuario usuario){
        usuarioService.modificarUsuario(usuario);
    }

    @PostMapping("/login")
    public UsuarioDTO login (@RequestBody Usuario usuario){
        return usuarioService.login(usuario.getUsername(),usuario.getPassword());
    }
    
}
