package com.registro.usuarios.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Articulo;
import com.registro.usuarios.repositorio.ArticuloRepository;
import com.registro.usuarios.servicio.ArticuloService;

@Service
public class ArticuloServiceImpl implements ArticuloService{
    @Autowired
    private ArticuloRepository articuloRepository;

    public Articulo save(Articulo articulo){
        return  articuloRepository.save(articulo);
    }
}
