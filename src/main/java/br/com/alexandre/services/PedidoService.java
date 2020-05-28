package br.com.alexandre.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alexandre.domain.Pedido;
import br.com.alexandre.repositories.PedidoRepository;
import br.com.alexandre.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repo;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> pedidoEncontrado = repo.findById(id);
		
		return pedidoEncontrado.orElseThrow(() -> new ObjectNotFoundException("Objeto"
				+ "não encontrado ! ID: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
