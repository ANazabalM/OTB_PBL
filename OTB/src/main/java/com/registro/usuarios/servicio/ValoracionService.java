package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Valoracion;

public interface ValoracionService {
    public Valoracion save(Valoracion valoracion);

    public Valoracion cogerValoracion(Long articuloId,Long usuarioId);
}
