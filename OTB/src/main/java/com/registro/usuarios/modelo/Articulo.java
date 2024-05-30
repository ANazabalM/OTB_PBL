package com.registro.usuarios.modelo;

import java.sql.Date;
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
    private Date fecha_publ;
    
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
    @JoinColumn(name = "usuario_ID")
    private Usuario usuarios;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "articulosComentarios")  // 1 --> n el foreing key que mandas a otra tabla
    private List<Comentario> articuloComentario; // "articuloComentario" hay que cogerlo del MayToOne

    public void addComentario(Comentario comentario){ // añadir comentario, en repository
        articuloComentario.add(comentario);
        comentario.setArticulosComentarios(this);
    }

    @ManyToMany(mappedBy = "articulos_leidos")
    private List<Usuario> articulos_leidos_usuarios;

    @ManyToMany(mappedBy = "articulos_favoritos")
    private List<Usuario> articulos_favoritos_usuarios;

    public Articulo(Long articuloId, String titulo, Date fecha_publ, String text, 
                    String alt_img, String src_img)
    {
        this.articuloId = articuloId;
        this.titulo = titulo;
        this.fecha_publ = fecha_publ;
        this.contenido = text;
        this.alt_img = alt_img;
        this.src_img = src_img;
    }


    public Articulo(String titulo, String alt_img, String src_video, String contenido, String lang, Usuario author,
            Categoria categoria) {

                this.titulo = titulo;
                this.alt_img = alt_img;
                this.src_video = src_video;
                this.contenido = contenido;
                this.lang = lang;
                this.usuarios = author;
                this.categorias = categoria;
    }

    public Usuario getUsuarios()
    {
        return this.usuarios;
    }
}


