package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Solicitud;

public interface SolicitudService {
    public Solicitud save(Solicitud solicitud);

    public Solicitud getSolicitud(Long id);

    public void borrarSolicitud(Solicitud solicitud);
}
