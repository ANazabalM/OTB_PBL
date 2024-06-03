package com.registro.usuarios.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Valoracion;
import com.registro.usuarios.repositorio.ValoracionRepository;
import com.registro.usuarios.servicio.ValoracionService;

@Service
public class ValoracionServiceImpl implements ValoracionService{

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Override
    public Valoracion save(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    @Override
    public Valoracion cogerValoracion( Long articuloId, Long usuarioId) {
    
        return valoracionRepository.haValorado(articuloId,usuarioId);
    }
    
}
