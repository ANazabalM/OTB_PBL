package com.otb.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otb.excepciones.ResourceNotFoundException;
import com.otb.modelo.Articulo;
import com.otb.modelo.Valoracion;
import com.otb.repositorio.ArticuloRepository;
import com.otb.servicio.ArticuloService;

@Service
public class ArticuloServiceImpl implements ArticuloService{
    @Autowired
    private ArticuloRepository articuloRepository;

     public Articulo save(Articulo articulo){
        return articuloRepository.save(articulo);
    }

    @Override
    public Articulo getArticulo(long articuloId) {
        Optional<Articulo> articuloOptional = articuloRepository.findById(articuloId);
		Articulo articulo = null;

		if(!articuloOptional.isPresent()) {
			
			throw new ResourceNotFoundException("Artículo no encontrado");
		}
		else{
			articulo = articuloOptional.get();
		}

		return articulo;
    }

    @Override
    public void deleteArticulo(long articuloId) {
        if (!articuloRepository.existsById(articuloId)) {
            throw new ResourceNotFoundException("Artículo no encontrado");
        }
        articuloRepository.deleteById(articuloId);
    }

    @Override
    public void editarArticulo(long articuloId, Articulo articuloEditado) {
        if (!articuloRepository.existsById(articuloId)) {
            throw new ResourceNotFoundException("Artículo no encontrado");
        }
        articuloRepository.save(articuloEditado);
    }

    @Override
    public List<Articulo> cogerTodos() {
        List<Articulo> listaTodos = articuloRepository.findAll();
        return listaTodos;
    }

    @Override
    public List<Articulo> cogerMasVistos() {
    
        List<Articulo> listaMejores = articuloRepository.getTopArticulos();
        return listaMejores;
    }

@Override
public void guardarArticulo(Articulo articuloguardado) {
    articuloRepository.save(articuloguardado);
}

@Override
public List<Articulo> cogerArticulosCategoria(String categoriaId) {

    return articuloRepository.getArticulosCategoria(Long.parseLong(categoriaId));
}

@Override
public Long cogerArticuloFavorito(Long usuarioId, Long articuloId) {
    return articuloRepository.getArticulosFavoritos(usuarioId,articuloId);
}
    
}
