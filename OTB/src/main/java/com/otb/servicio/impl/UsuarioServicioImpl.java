package com.otb.servicio.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.otb.controlador.dto.UsuarioRegistroDTO;
import com.otb.excepciones.ResourceNotFoundException;
import com.otb.modelo.Rol;
import com.otb.modelo.Usuario;
import com.otb.repositorio.UsuarioRepository;
import com.otb.servicio.UsuarioServicio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	
	private UsuarioRepository usuarioRepositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UsuarioServicioImpl(UsuarioRepository usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getNombre(), 
				registroDTO.getApellido(),registroDTO.getEmail(), registroDTO.getUsername(),
				passwordEncoder.encode(registroDTO.getPassword()), "Basico");

		return usuarioRepositorio.save(usuario);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	public Usuario getUsuario(Long id)
	{
		Optional<Usuario> usuario = usuarioRepositorio.findById(id);
		Usuario usuario1 = null;

		if(!usuario.isPresent()) {
			
			throw new ResourceNotFoundException("Usuario no encontrado");
		}
		else{
			usuario1 = usuario.get();
		}

		return usuario1;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public void eliminarUsuario(long id) {
		if (!usuarioRepositorio.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        usuarioRepositorio.deleteById(id);
	}

	@Override
	public void editarUsuario(Usuario usuario)
	{
		if (!usuarioRepositorio.existsById(usuario.getId())) {
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
		usuarioRepositorio.save(usuario);
	}

	public Usuario buscarPorEmail (String email){
		return usuarioRepositorio.getByEmail(email);
	}

	@Override
	public List<Usuario> getAll() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		return usuarios;
	}

	@Override
	public float calcularMediaUsuario(String usuarioId) {
		
		return usuarioRepositorio.calcularMediaUsuario(usuarioId);	
	}

}
