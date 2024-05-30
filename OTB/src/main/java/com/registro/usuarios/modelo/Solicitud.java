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
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long solicitudId;

    @Column
    private String titulo;
    
    @Column
    private String descripcion;
    
    @Column
    private LocalDate fecha_sol;

    @ManyToOne
    @JoinColumn(name = "usuario_ID")
    private Usuario solicitudUsuarios;

    public Solicitud(String titulo,String descripcion2, Usuario solicitudUsuarios2, LocalDate fecha_sol) {
        this.titulo = titulo;
        this.descripcion = descripcion2;
        this.solicitudUsuarios = solicitudUsuarios2;
        this.fecha_sol = fecha_sol;
    }
}

