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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id")
    private Usuario solicitudUsuarios;

    public Solicitud(String titulo,String descripcion, Usuario solicitudUsuarios, LocalDate fecha_sol) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.solicitudUsuarios = solicitudUsuarios;
        this.fecha_sol = fecha_sol;
    }

    public Solicitud(String titulo,String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }


    public Solicitud(Long solicitudId2, String titulo2, String descripcion2, LocalDate fecha_sol2) {
        this.solicitudId = solicitudId2;
        this.titulo = titulo2;
        this.descripcion = descripcion2;
        this.fecha_sol = fecha_sol2;
    }
}

