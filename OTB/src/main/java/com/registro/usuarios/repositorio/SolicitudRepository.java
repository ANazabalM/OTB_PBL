package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud,Integer>{
    
}
