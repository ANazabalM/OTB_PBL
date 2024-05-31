package com.registro.usuarios.modelo;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "articulo_ID")
    private Articulo articulosComentarios;

    @ManyToOne
    @JoinColumn(name = "usuario_ID")
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
