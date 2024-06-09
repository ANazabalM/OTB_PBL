package com.otb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otb.modelo.Valoracion;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion,Long>{

    @Query(value = "SELECT * FROM valoracion where articulo_id = :articuloID AND usuarios_id = :usuarioID", nativeQuery = true)
    public Valoracion haValorado(@Param("articuloID") Long articuloID, @Param("usuarioID") Long usuarioId);
    
    @Query(value = "SELECT * FROM valoracion where articulo_id = :articuloID", nativeQuery = true)
    public List<Valoracion> laValoracion(@Param("articuloID") Long articuloID);

    @Query(value = "DELETE FROM valoracion where articulo_id = :articuloId", nativeQuery = true)
    public Long borrarArticuloValorado(@Param("articuloId") Long articuloId);
}
