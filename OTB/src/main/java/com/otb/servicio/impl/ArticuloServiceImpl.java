package com.otb.servicio.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.otb.modelo.Articulo;
import com.otb.repositorio.ArticuloRepository;
import com.otb.servicio.ArticuloService;

@Service
public class ArticuloServiceImpl implements ArticuloService{
    @Autowired
    private ArticuloRepository articuloRepository;

    public Articulo save(Articulo articulo){
        return  articuloRepository.save(articulo);
    }

    @Override
    public Articulo getArticulo(long articuloId) {
        Optional<Articulo> articuloOptional = articuloRepository.findById(articuloId);
		Articulo articulo = null;

		if(!articuloOptional.isPresent()) {
			
			throw new UsernameNotFoundException("Articulo no válido");
		}
		else{
			articulo = articuloOptional.get();
		}

		return articulo;
    }

    @Override
    public void deleteArticulo(long articuloId) {
        articuloRepository.deleteById(articuloId);
    }

    @Override
    public void editarArticulo(long articuloId, Articulo articuloEditado) {
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
    
}
