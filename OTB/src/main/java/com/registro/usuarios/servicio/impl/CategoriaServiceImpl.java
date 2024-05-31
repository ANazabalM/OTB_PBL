package com.registro.usuarios.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Categoria;
import com.registro.usuarios.repositorio.CategoriaRepository;
import com.registro.usuarios.servicio.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria save(Categoria categoria){
        return  categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> cogerTodas() {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        
        return listaCategorias;
    }

    @Override
    public Categoria getCategoria(Long categoriaId) {
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
		Categoria categoria1 = null;

		if(!categoria.isPresent()) {
			
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		else{
			categoria1 = categoria.get();
		}

		return categoria1;
    }

    @Override
    public void borrarCategoria(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }

    
}
