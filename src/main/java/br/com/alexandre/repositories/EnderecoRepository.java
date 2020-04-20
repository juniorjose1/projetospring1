package br.com.alexandre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexandre.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
