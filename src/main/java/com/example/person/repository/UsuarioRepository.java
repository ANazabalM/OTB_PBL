package com.example.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.person.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{ 

    public Usuario findByUsernameAndPassword(String username, String password);
    
}
