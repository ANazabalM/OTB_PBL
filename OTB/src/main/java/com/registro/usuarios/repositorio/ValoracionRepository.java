package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Valoracion;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion,Long>{

    @Query(value = "SELECT * FROM valoracion where articulo_id = :articuloID AND usuarios_id = :usuarioID", nativeQuery = true)
    public Valoracion haValorado(@Param("articuloID") Long articuloID, @Param("usuarioID") Long usuarioId);
    
}
