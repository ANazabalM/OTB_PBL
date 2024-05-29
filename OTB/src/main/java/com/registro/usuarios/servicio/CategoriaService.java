package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Categoria;

public interface CategoriaService {
    public Categoria save(Categoria categoria);

    public Categoria findByCategoriaId(Long id);
}
