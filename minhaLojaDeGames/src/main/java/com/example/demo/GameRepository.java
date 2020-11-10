package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

//Faz a condução da tabela ao basnco, ser para persistir od dados no banco
//Para fazer os gets e posts é preciso persistir
public interface GameRepository extends JpaRepository<GameTable, Long> {

	List<GameTable> findByNome(String nome);

	Optional<GameTable> findByNomeAndCategoria(String nome, String categoria);



}

