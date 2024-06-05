package com.otb.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otb.excepciones.ResourceNotFoundException;
import com.otb.modelo.Categoria;
import com.otb.repositorio.CategoriaRepository;
import com.otb.servicio.CategoriaService;

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
			
			throw new ResourceNotFoundException("Categoria no encontrada");
		}
		else{
			categoria1 = categoria.get();
		}

		return categoria1;
    }

    @Override
    public void borrarCategoria(Categoria categoria) {
        if (!categoriaRepository.existsById(categoria.getCategoriaId())) {
            throw new ResourceNotFoundException("Artículo no encontrado");
        }
        categoriaRepository.deleteById(categoria.getCategoriaId());
    }

    
}
