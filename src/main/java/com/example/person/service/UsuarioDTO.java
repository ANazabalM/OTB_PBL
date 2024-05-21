package com.example.person.service;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UsuarioDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Basic
    String nombre;
    String apellido;
    String email;
    String tipo;
    String descripcion;

    public UsuarioDTO(Long id, String nombre, String apellido, String email, String tipo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.tipo = tipo;
        this.descripcion = descripcion;
    } 

    public UsuarioDTO(){}
    

}
