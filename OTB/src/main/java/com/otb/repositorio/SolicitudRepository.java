package com.otb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otb.modelo.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud,Long>{
    
}
