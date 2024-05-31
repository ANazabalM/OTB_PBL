package com.registro.usuarios.repositorio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.registro.usuarios.modelo.Articulo;

import java.util.List;

public class UsuarioRepositorioEspecial{

    @PersistenceContext
    private EntityManager entityManager;

    public List<Articulo> findUsuariosByCustomQuery() {
        String jpql = "SELECT articulo_id FROM articulos_leidos GROUP BY articulo_id order by COUNT(articulo_id) desc";
        TypedQuery<Articulo> query = entityManager.createQuery(jpql, Articulo.class);
        return query.getResultList();
    }
}