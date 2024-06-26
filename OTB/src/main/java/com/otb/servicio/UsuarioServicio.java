package com.otb.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.otb.controlador.dto.UsuarioRegistroDTO;
import com.otb.modelo.Usuario;

public interface UsuarioServicio extends UserDetailsService{
	
	public Usuario guardar(UsuarioRegistroDTO registroDTO);

	public List<Usuario> listarUsuarios();

	public Usuario getUsuario(Long id);

	public void eliminarUsuario(long id);

    public void editarUsuario(Usuario usuario);

	public Usuario buscarPorEmail (String email);

    public List<Usuario> getAll();

    public float calcularMediaUsuario(String usuarioId);


}
