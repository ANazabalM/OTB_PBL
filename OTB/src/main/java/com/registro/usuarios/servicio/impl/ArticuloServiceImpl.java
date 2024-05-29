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

public Articulo getArticulo(Long id)
	{
		Optional<Articulo> articulo = articuloRepository.findById(id);
		Articulo articulo1 = null;

		if(!articulo.isPresent()) {
			
			throw new UsernameNotFoundException("Usuario o password inválidos");

		}
		else{
			articulo1 = articulo.get();
		}

		return articulo1;
	}
    
}
