package com.registro.usuarios.servicio;

import java.util.List;

import com.registro.usuarios.modelo.Categoria;

public interface CategoriaService {
    public Categoria save(Categoria categoria);

    public List<Categoria> cogerTodas();

    public Categoria getCategoria(Long categoriaId);
}
