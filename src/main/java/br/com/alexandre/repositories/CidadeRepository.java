package br.com.alexandre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexandre.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
