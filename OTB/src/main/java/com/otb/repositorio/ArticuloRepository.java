package com.otb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otb.modelo.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository <Articulo, Long>{
    
    @Query(value = "SELECT a.* FROM articulos_leidos al JOIN articulo a ON al.articulo_id = a.articulo_id GROUP BY al.articulo_id ORDER BY COUNT(al.articulo_id) DESC LIMIT 8", nativeQuery = true)
    public List<Articulo> getTopArticulos();

    @Query(value = "SELECT a.* FROM articulo a WHERE a.categoria_id = :categoriaId", nativeQuery = true)
    public List<Articulo> getArticulosCategoria(@Param("categoriaId") Long categoriaId);

    @Query(value = "SELECT articulo_id FROM articulos_favoritos where usuario_id = :usuarioId and articulo_id = :articuloId", nativeQuery = true)
    public Long getArticulosFavoritos(@Param("usuarioId") Long usuarioId , @Param("articuloId") Long articuloId);

    @Query(value = "DELETE FROM articulo where articulo_id = :articuloId", nativeQuery = true)
    public Long borrarArticulo(@Param("articuloId") Long articuloId);

    @Query(value = "DELETE FROM articulos_leidos where articulo_id = :articuloId", nativeQuery = true)
    public Long borrarArticuloVisto(@Param("articuloId") Long articuloId);

    @Query(value = "DELETE FROM articulos_favoritos where articulo_id = :articuloId", nativeQuery = true)
    public Long borrarArticuloFavorito(@Param("articuloId") Long articuloId);

    



    /*
    @Query(value = "SELECT a.* FROM articulos_leidos al JOIN articulo a ON al.articulo_id = a.articulo_id GROUP BY al.articulo_id ORDER BY COUNT(al.articulo_id) DESC LIMIT 3", nativeQuery = true)
    public List<Articulo> getValoracionArticulo(String articuloId);

    public List<Articulo> getVisualizacionArticulo(String articuloId);
    */
}
