package com.otb.servicio;

import com.otb.modelo.Valoracion;

public interface ValoracionService {
    public Valoracion save(Valoracion valoracion);

    public Valoracion cogerValoracion(Long articuloId,Long usuarioId);
}
