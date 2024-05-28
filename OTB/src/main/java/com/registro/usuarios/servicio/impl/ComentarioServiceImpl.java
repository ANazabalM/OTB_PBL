package com.registro.usuarios.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Comentario;
import com.registro.usuarios.repositorio.ComentarioRepository;
import com.registro.usuarios.servicio.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService{
    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario save(Comentario comentario){
        return  comentarioRepository.save(comentario);
    }
}
