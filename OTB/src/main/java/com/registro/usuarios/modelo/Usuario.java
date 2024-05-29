package com.registro.usuarios.modelo;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	@Column
	private String email;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String descripcion;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "usuarios")
    private List<Articulo> usuariosArticulo;

    public void addArticulo(Articulo articulo){
        usuariosArticulo.add(articulo);
        articulo.setUsuarios(this);
    }

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "usuariosComentarios")
    private List<Comentario> usuariosComentario;

    public void addComentario(Comentario comentario){
        usuariosComentario.add(comentario);
        comentario.setUsuariosComentarios(this);
    }

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "solicitudUsuarios")
    private List<Solicitud> usuariosSolicitud;

    public void addSolicitud(Solicitud solicitud){
        usuariosSolicitud.add(solicitud);
        solicitud.setSolicitudUsuarios(this);
    }
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
			)
	private Collection<Rol> roles;

	//@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "articulos_leidos",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "articulo_id",referencedColumnName = "articuloId")
			)
	private List<Articulo> articulos_leidos;

	//@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "articulos_favoritos",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "articulo_id",referencedColumnName = "articuloId")
			)
	private List<Articulo> articulos_favoritos;
	
	public Usuario(Long id, String nombre, String apellido, String email, String password, Collection<Rol> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Usuario(String nombre, String apellido, String email, String password, Collection<Rol> roles) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

    public Usuario(String nombre, String apellido, String username, String descripcion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.descripcion = descripcion;
		this.username = username;
	}

	public Usuario(String nombre, String apellido, String descripcion, String email, String username) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.descripcion = descripcion;
		this.email = email;
		this.username = username;
		
	}
}
