package com.otb.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "valoracion")
public class Valoracion {
    

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long valoracion_id;

	@Column
    private Integer puntuacion;
    
    @ManyToOne (cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)// n --> 1 el foreing key que recibes
    @JoinColumn(name = "articulo_id")
    private Articulo articulosValorados;

    @ManyToOne (cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarios_id")
    private Usuario usuariosValorados;

    public Valoracion(Integer puntuacion2, Usuario usuario, Articulo articulo) {
        this.puntuacion = puntuacion2;
        this.articulosValorados = articulo;
        this.usuariosValorados = usuario;
    }
}
