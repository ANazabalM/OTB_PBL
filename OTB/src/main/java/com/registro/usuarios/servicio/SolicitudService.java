package com.registro.usuarios.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Solicitud;
import com.registro.usuarios.repositorio.SolicitudRepository;

@Service
public class SolicitudService {
    @Autowired
    private SolicitudRepository solicitudRepository;

    public Solicitud save(Solicitud solicitud){
        return  solicitudRepository.save(solicitud);
    }
}
