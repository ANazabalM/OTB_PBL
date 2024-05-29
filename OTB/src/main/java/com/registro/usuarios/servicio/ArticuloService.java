package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Articulo;

public interface ArticuloService {
    public Articulo save(Articulo articulo);

    public Articulo getArticulo(long articuloId);

    public void deleteArticulo(long articuloId);

    public void editarArticulo(long articuloId, Articulo articuloEditado);
}
