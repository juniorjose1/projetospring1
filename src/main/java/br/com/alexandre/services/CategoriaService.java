package br.com.alexandre.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alexandre.domain.Categoria;
import br.com.alexandre.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscarPorId(Integer id) {
		Optional<Categoria> categoriaEncontrada = repo.findById(id);
		
		return categoriaEncontrada.orElse(null);
	}
	
}
