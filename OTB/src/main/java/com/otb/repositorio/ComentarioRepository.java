package com.otb.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otb.modelo.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long>{ 

    @Query(value = "DELETE FROM comentario where articulo_id = :articuloId", nativeQuery = true)
    public void borrarTodosLosComentarios(@Param("articuloId") Long articuloId);
}
