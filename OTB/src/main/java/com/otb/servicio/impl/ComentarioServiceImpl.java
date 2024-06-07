package com.otb.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otb.excepciones.ResourceNotFoundException;
import com.otb.modelo.Comentario;
import com.otb.repositorio.ComentarioRepository;
import com.otb.servicio.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService{
    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario save(Comentario comentario){
        return  comentarioRepository.save(comentario);
    }

    @Override
    public Comentario getComentario(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comentario no encontrado");
        }
        return comentarioRepository.getById(id);
    }

    @Override
    public void borrarComentario(Comentario comentario) {
        if (!comentarioRepository.existsById(comentario.getComentarioId())) {
            throw new ResourceNotFoundException("Comentario no encontrado");
        }
        comentarioRepository.delete(comentario);
    }

    @Override
    public void borrarTodosLosComentarios(Long articuloId) {
        comentarioRepository.borrarTodosLosComentarios(articuloId);
    }
}
