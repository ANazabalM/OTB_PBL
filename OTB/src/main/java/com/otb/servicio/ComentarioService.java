package com.otb.servicio;

import java.util.List;

import com.otb.modelo.Comentario;

public interface ComentarioService {
    public Comentario save(Comentario comentario);

    public Comentario getComentario(Long id);

    public void borrarComentario(Comentario comentario);

    public void borrarTodosLosComentarios(Long articuloId);

    public List<Comentario> cogerLosComentarios(Long articuloId);

}
