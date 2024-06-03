package com.otb.servicio;

import java.util.List;

import com.otb.modelo.Articulo;

public interface ArticuloService {
    public Articulo save(Articulo articulo);

    public Articulo getArticulo(long articuloId);

    public void deleteArticulo(long articuloId);

    public void editarArticulo(long articuloId, Articulo articuloEditado);

    public void guardarArticulo(Articulo articuloguardado);

    public List<Articulo> cogerTodos();

    public List<Articulo> cogerMasVistos();

    public List<Articulo> cogerArticulosCategoria(String categoriaId);
}
