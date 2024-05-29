package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long>{ 
    
}
