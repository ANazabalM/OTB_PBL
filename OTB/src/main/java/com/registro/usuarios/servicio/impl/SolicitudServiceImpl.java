package com.registro.usuarios.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Solicitud;
import com.registro.usuarios.repositorio.SolicitudRepository;
import com.registro.usuarios.servicio.SolicitudService;

@Service
public class SolicitudServiceImpl implements SolicitudService {
    @Autowired
    private SolicitudRepository solicitudRepository;

    public Solicitud save(Solicitud solicitud){
        return  solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud getSolicitud(Long id) {
        return solicitudRepository.getById(id);
    }

    @Override
    public void borrarSolicitud(Solicitud solicitud) {
        solicitudRepository.delete(solicitud);
    }
}
