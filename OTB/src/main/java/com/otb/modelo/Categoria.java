package com.otb.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria", uniqueConstraints = @UniqueConstraint(columnNames = "titulo"))
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    
    @Column
    private String titulo;
    
    @Column
    private String color;

    @Column
    private String icono;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "categorias")
    private List<Articulo> articulosCategoria;

    public void addArticulo(Articulo articulo){
        articulosCategoria.add(articulo);
        articulo.setCategorias(this);
    }

    public Categoria(String titulo, String color)
    {
        this.titulo = titulo;
        this.color = color;
    }
}
