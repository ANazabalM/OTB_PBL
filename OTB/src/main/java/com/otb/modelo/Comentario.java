package com.otb.modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long comentarioId;
    
    @Column
    private String contenido;
    
    @Column
    private LocalDate fecha_com;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "articulo_id")
    private Articulo articulosComentarios;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id")
    private Usuario usuariosComentarios;

    public Comentario(String contenido, Usuario id, String apellido) {
        this.contenido = contenido;
        this.usuariosComentarios = id;
    }

    public Comentario(String contenido2, LocalDate now, Usuario usuario, Articulo articulo) {
        this.fecha_com = now;
        this.contenido = contenido2;
        this.usuariosComentarios = usuario;
        this.articulosComentarios = articulo;
    }
}
