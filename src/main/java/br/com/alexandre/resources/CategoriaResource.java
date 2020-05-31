package br.com.alexandre.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alexandre.domain.Categoria;
import br.com.alexandre.dto.CategoriaDTO;
import br.com.alexandre.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria categoriaSelecionada = service.findById(id);
		
		return ResponseEntity.ok().body(categoriaSelecionada);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> insert(@RequestBody Categoria categoria){
		Categoria categoriaInserida = service.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(categoriaInserida.getId()).toUri();
		
		return ResponseEntity.created(uri).body(categoriaInserida);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Integer id, 
							@RequestBody Categoria categoria){
		categoria.setId(id);
		Categoria categoriaAlterada = service.update(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.ok().location(uri).body(categoriaAlterada);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> listaCategorias = service.findAll();
		List<CategoriaDTO> listaDTO = listaCategorias.stream()
				.map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "size", defaultValue = "24") Integer size,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction){
		Page<Categoria> listaCategorias = service.findPage(page, size, direction, orderBy);
		Page<CategoriaDTO> listaDTO = listaCategorias
				.map(categoria -> new CategoriaDTO(categoria));
		
		return ResponseEntity.ok().body(listaDTO);
	}

}
