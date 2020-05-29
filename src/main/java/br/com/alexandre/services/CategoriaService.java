package br.com.alexandre.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alexandre.domain.Categoria;
import br.com.alexandre.repositories.CategoriaRepository;
import br.com.alexandre.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> categoriaEncontrada = repo.findById(id);
		
		return categoriaEncontrada.orElseThrow(() -> new ObjectNotFoundException(
				"Categoria não encotrada ! ID: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		Categoria categoriaSalva = repo.save(categoria);
		
		return categoriaSalva;
	}
	
	public Categoria update(Categoria categoria) {
		findById(categoria.getId());
		Categoria categoriaAlterada = repo.save(categoria);
		
		return categoriaAlterada;
	}
	
}
