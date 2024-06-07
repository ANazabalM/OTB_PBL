package com.otb.modelo;

import java.time.LocalDate;
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
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

	@Column
	private String img_src;

	@Column
	private LocalDate fecha_nacimiento;

	@Column
	private String Tipo="normal";

	@Column
	private String rol;

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

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "usuariosValorados")  // 1 --> n el foreing key que mandas a otra tabla
    private List<Valoracion> articuloValorado; // "articuloComentario" hay que cogerlo del MayToOne

    public void addValoracion(Valoracion valoracion){ // añadir comentario, en repository
        articuloValorado.add(valoracion);
        valoracion.setUsuariosValorados(this);
    }
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
			)
	private List<Rol> roles;

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	//@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "articulos_leidos",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "articulo_id",referencedColumnName = "articuloId")
			)
	private List<Articulo> articulos_leidos;

	public void addVisualizacion(Articulo articulo){ 
        articulos_leidos.add(articulo);
    }

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	//@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "articulos_favoritos",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "articulo_id",referencedColumnName = "articuloId")
			)
	private List<Articulo> articulos_favoritos;
	
	
	public void add_articulo_favoritos(Articulo articulo)
	{
		articulos_favoritos.add(articulo);
		articulo.addFavorito(this);
	}

	public void remove_articulo_favoritos(Articulo articulo)
	{
		articulos_favoritos.remove(articulo);
		articulo.removeFavorito(this);
	}

	public Usuario(Long id, String nombre, String apellido, String email, String password, List<Rol> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Usuario(String nombre, String apellido, String email, String username, String password, String rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.username = username;
		this.password = password;
		this.rol = rol;
	}

	public Usuario(String nombre, String apellido, String descripcion, String email, String username) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.descripcion = descripcion;
		this.email = email;
		this.username = username;
		
	}

	public Usuario(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	public Usuario(String nombre, String apellido, String email, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.email = email;
	}

	public Usuario(Long usuarioId, String nombre2, String apellido2, String descripcion2, String email2,
			String username2, String img_src, LocalDate fecha_nacimiento, String tipo) {
		super();
		this.id = usuarioId;
		this.nombre = nombre2;
		this.apellido = apellido2;
		this.descripcion = descripcion2;
		this.email = email2;
		this.username = username2;
		this.img_src = img_src;
		this.fecha_nacimiento = fecha_nacimiento;
		this.Tipo = tipo;
	}

	public Usuario(long id, String nombre2, String apellido2, String username2, 
					String descripcion2, String img_src, String tipo) {
		this.id = id;
		this.nombre = nombre2;
		this.apellido = apellido2;
		this.username = username2;
		this.descripcion = descripcion2;
		this.img_src = img_src;
		this.Tipo = tipo;
	}
}
