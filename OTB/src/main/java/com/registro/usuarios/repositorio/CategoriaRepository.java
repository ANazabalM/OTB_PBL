package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    

}
