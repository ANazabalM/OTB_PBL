package com.otb.servicio;

import java.util.List;

import com.otb.modelo.Categoria;

public interface CategoriaService {
    public Categoria save(Categoria categoria);

    public List<Categoria> cogerTodas();

    public Categoria getCategoria(Long categoriaId);

    public void borrarCategoria(Categoria categoria);

}
