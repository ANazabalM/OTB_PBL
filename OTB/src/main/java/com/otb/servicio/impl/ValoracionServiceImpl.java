package com.otb.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otb.modelo.Valoracion;
import com.otb.repositorio.ValoracionRepository;
import com.otb.servicio.ValoracionService;

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
