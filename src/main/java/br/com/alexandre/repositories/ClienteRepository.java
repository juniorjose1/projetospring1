package br.com.alexandre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexandre.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
