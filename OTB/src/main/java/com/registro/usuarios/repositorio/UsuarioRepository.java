package com.registro.usuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.usuarios.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query(value = "SELECT u.* FROM usuarios u WHERE u.email = :email", nativeQuery = true)
	public Usuario getByEmail(@Param("email") String email);
	
	public Usuario findByEmail(String email);

	public Usuario findByUsername(String username);

	@Query(value = "SELECT AVG(v.puntuacion) FROM (usuarios u JOIN articulo a ON a.usuarios_Id = u.id) JOIN valoracion v on v.articuloId = a.articuloId WHERE u.id = 1 GROUP BY u.id", nativeQuery = true)
	public float calcularMediaUsuario(String usuarioId);
}
