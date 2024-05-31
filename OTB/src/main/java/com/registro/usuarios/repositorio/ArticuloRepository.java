package com.registro.usuarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository <Articulo, Long>{
    
    @Query(value = "SELECT a.* FROM articulos_leidos al JOIN articulo a ON al.articulo_id = a.articulo_id GROUP BY al.articulo_id ORDER BY COUNT(al.articulo_id) DESC LIMIT 3", nativeQuery = true)
    public List<Articulo> getTopArticulos();

    /*
    @Query(value = "SELECT a.* FROM articulos_leidos al JOIN articulo a ON al.articulo_id = a.articulo_id GROUP BY al.articulo_id ORDER BY COUNT(al.articulo_id) DESC LIMIT 3", nativeQuery = true)
    public List<Articulo> getValoracionArticulo(String articuloId);

    public List<Articulo> getVisualizacionArticulo(String articuloId);
    */
}
