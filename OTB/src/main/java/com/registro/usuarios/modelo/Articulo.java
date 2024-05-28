package com.registro.usuarios.modelo;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Integer articuloId;
    
    @Column
    private String titulo;
    
    @Column
    private Date fecha_publ;
    
    @Column
    private String src_img;
    
    @Column
    private String alt_img;
    
    @Column
    private String lang;
    
    @Column
    private String text;

    @ManyToOne // n --> 1 el foreing key que recibes
    private Categoria categorias;

    @ManyToOne
    private Usuario usuarios;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "articulosComentarios")  // 1 --> n el foreing key que mandas a otra tabla
    private List<Comentario> articuloComentario; // "articuloComentario" hay que cogerlo del MayToOne

    public void addComentario(Comentario comentario){ // añadir comentario, en repository
        articuloComentario.add(comentario);
        comentario.setArticulosComentarios(this);
    }
}


