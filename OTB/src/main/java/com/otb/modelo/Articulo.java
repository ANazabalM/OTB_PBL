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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                           // Atributo de lombok, que crea getters y setters. Para ahorrar código.                             
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "articulo", uniqueConstraints = @UniqueConstraint(columnNames = "titulo"))         // Para decir que es una tabla 
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long articuloId;
    
    @Column
    private String titulo;
    
    @Column
    private LocalDate fecha_publ;
    
    @Column
    private String src_img;
    
    @Column
    private String alt_img;

    @Column
    private String src_video;
    
    @Column
    private String lang;
    
    @Column
    private String contenido;
    
    @ManyToOne (fetch = FetchType.EAGER)// n --> 1 el foreing key que recibes
    @JoinColumn(name = "categoria_ID")
    private Categoria categorias;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarios_ID")
    private Usuario usuarios;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "articulosComentarios")  // 1 --> n el foreing key que mandas a otra tabla
    private List<Comentario> articuloComentario; // "articuloComentario" hay que cogerlo del MayToOne

    public void addComentario(Comentario comentario){ // añadir comentario, en repository
        articuloComentario.add(comentario);
        comentario.setArticulosComentarios(this);
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "articulosValorados")  // 1 --> n el foreing key que mandas a otra tabla
    private List<Valoracion> articuloValorado; // "articuloComentario" hay que cogerlo del MayToOne

    public void addValoracion(Valoracion valoracion){ // añadir comentario, en repository
        articuloValorado.add(valoracion);
        valoracion.setArticulosValorados(this);
    }

    @ManyToMany(mappedBy = "articulos_leidos")
    private List<Usuario> articulos_leidos_usuarios;

    public void addVisualizacion(Usuario usuario, Articulo articulo){
        articulos_leidos_usuarios.add(usuario);

        usuario.addVisualizacion(articulo);
    }

    @ManyToMany(mappedBy = "articulos_favoritos")
    private List<Usuario> articulos_favoritos_usuarios;

    public void addFavorito(Usuario usuario){
        articulos_favoritos_usuarios.add(usuario);
    }

    public void removeFavorito(Usuario usuario){
        articulos_favoritos_usuarios.remove(usuario);
    }
    

    public Articulo(Long articuloId, String titulo, LocalDate fecha_publ, String text, 
                    String alt_img, String src_img)
    {
        this.articuloId = articuloId;
        this.titulo = titulo;
        this.fecha_publ = fecha_publ;
        this.contenido = text;
        this.alt_img = alt_img;
        this.src_img = src_img;
    }


    public Articulo(String titulo, String alt_img, String src_video, String src_img, String contenido, String lang, Usuario author,
            LocalDate date, Categoria categoria) {

                this.titulo = titulo;
                this.alt_img = alt_img;
                this.src_video = src_video; 
                this.src_img = src_img;
                this.contenido = contenido;
                this.lang = lang;
                this.usuarios = author;
                this.fecha_publ = date;
                this.categorias = categoria;
    }


    public Articulo(String titulo, String alt_img, String src_video, String contenido, Usuario usuario) {
                this.titulo = titulo;
                this.alt_img = alt_img;
                this.src_video = src_video;
                this.contenido = contenido;
                this.usuarios = usuario;

    }


    public Articulo(String titulo, String alt_img, String src_video, String contenido, LocalDate fecha_publ, Usuario usuario) {
                this.titulo = titulo;
                this.alt_img = alt_img;
                this.src_video = src_video;
                this.contenido = contenido;
                this.fecha_publ = fecha_publ;
                this.usuarios = usuario;
    }
}


