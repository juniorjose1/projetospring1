package br.com.alexandre.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.alexandre.domain.Categoria;
import br.com.alexandre.repositories.CategoriaRepository;
import br.com.alexandre.services.exceptions.DataIntegrityException;
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
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria"
					+ " que possui produtos.");
		}
	}
	
	public List<Categoria> findAll(){
		List<Categoria> listaCategoria = repo.findAll();
		
		return listaCategoria;
	}
	
	public Page<Categoria> findPage(Integer page, Integer size, String direction,
							String orderBy){
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), 
					orderBy);
		
		return repo.findAll(pageRequest);
	}
	
}
