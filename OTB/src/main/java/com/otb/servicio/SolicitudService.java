package com.otb.servicio;

import java.util.List;

import com.otb.modelo.Solicitud;

public interface SolicitudService {
    public Solicitud save(Solicitud solicitud);

    public Solicitud getSolicitud(Long id);

    public void borrarSolicitud(Solicitud solicitud);

    public List<Solicitud> getAll();

}
