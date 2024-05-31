package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Comentario;

public interface ComentarioService {
    public Comentario save(Comentario comentario);

    public Comentario getComentario(Long id);

    public void borrarComentario(Comentario comentario);

}
