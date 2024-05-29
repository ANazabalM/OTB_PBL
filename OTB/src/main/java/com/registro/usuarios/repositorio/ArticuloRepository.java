package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository <Articulo,Long>{
    
}
