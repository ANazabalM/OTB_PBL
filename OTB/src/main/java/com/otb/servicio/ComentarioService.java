package com.otb.servicio;

import com.otb.modelo.Comentario;

public interface ComentarioService {
    public Comentario save(Comentario comentario);

    public Comentario getComentario(Long id);

    public void borrarComentario(Comentario comentario);

}
