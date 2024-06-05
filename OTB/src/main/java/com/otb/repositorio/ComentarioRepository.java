package com.otb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otb.modelo.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long>{ 
    
}