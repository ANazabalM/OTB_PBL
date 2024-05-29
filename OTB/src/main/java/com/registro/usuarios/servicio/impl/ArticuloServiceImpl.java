package com.registro.usuarios.servicio.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.registro.usuarios.modelo.Articulo;
import com.registro.usuarios.repositorio.ArticuloRepository;
import com.registro.usuarios.servicio.ArticuloService;

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
}