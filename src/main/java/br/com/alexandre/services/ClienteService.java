package br.com.alexandre.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alexandre.domain.Cliente;
import br.com.alexandre.repositories.ClienteRepository;
import br.com.alexandre.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscarCliente(Integer id) {
		Optional<Cliente> clienteEncontrado = repo.findById(id);
		
		return clienteEncontrado.orElseThrow(() -> new ObjectNotFoundException("Cliente"
				+ " não encontrado ! ID: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
