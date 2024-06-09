package com.otb.servicio;

import java.util.List;

import com.otb.modelo.Valoracion;

public interface ValoracionService {
    public Valoracion save(Valoracion valoracion);

    public Valoracion cogerValoracion(Long articuloId,Long usuarioId);

    public List<Valoracion> cogerLasValoracion(Long articuloId);

    public void borrarLasValoracion(Long articuloId);
}
