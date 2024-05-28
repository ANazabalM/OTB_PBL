package com.registro.usuarios.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Comentario;
import com.registro.usuarios.repositorio.ComentarioRepository;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario save(Comentario comentario){
        return  comentarioRepository.save(comentario);
    }
}
