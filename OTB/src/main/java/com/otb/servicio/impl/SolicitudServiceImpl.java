package com.otb.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otb.modelo.Solicitud;
import com.otb.repositorio.SolicitudRepository;
import com.otb.servicio.SolicitudService;

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

    @Override
    public List<Solicitud> getAll() {
		List<Solicitud> solicitud = solicitudRepository.findAll();
		return solicitud;
    }

    
}
