package com.otb.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otb.excepciones.ResourceNotFoundException;
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
    
    @Override
    public List<Valoracion> cogerLasValoracion(Long articuloId) {
    
        return valoracionRepository.laValoracion(articuloId);
    }

        @Override
    public void borrarValoracion(Valoracion valoracion) {
        if (!valoracionRepository.existsById(valoracion.getValoracion_id())) {
            throw new ResourceNotFoundException("Valoracion no encontrado");
        }
        valoracionRepository.delete(valoracion);
    }

    @Override
    public void borrarLasValoracion(Long articuloId) {
    
        valoracionRepository.borrarArticuloValorado(articuloId);
    }
}
