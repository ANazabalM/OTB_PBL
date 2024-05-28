package com.registro.usuarios.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Categoria;
import com.registro.usuarios.repositorio.CategoriaRepository;
import com.registro.usuarios.servicio.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria save(Categoria categoria){
        return  categoriaRepository.save(categoria);
    }
}
