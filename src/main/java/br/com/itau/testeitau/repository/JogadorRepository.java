package br.com.itau.testeitau.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.itau.testeitau.model.Jogador;
import br.com.itau.testeitau.model.ListaOrigemEnum;

public interface JogadorRepository extends CrudRepository<Jogador, Long>{

	@Query("SELECT distinct a.codinome FROM Jogador a WHERE a.origem=:origem")
	List<String> findDistinctCodinomeByOrigem(@Param("origem") ListaOrigemEnum origem);
}
