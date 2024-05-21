package com.example.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.person.model.Usuario;
import com.example.person.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepo;

    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepo.save(usuario);
    }

    public void borrarUsuario(Long id){
        usuarioRepo.deleteById(id);
    }

    public List<Usuario> listarUsuario(){
        return usuarioRepo.findAll();
    }

    public Usuario buscarUsuarioPorId(Long id){
        return usuarioRepo.findById(id).orElse(null);
    }

    public void modificarUsuario(Usuario usuario){
        usuarioRepo.save(usuario);
    }

    public UsuarioDTO login(String usuername, String password){

        Usuario usuario = usuarioRepo.findByUsernameAndPassword(usuername,password);

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNombre(),usuario.getApellido(),usuario.getEmail(),usuario.getTipo(),usuario.getDescripcion());

        return usuarioDTO;
    }



}
