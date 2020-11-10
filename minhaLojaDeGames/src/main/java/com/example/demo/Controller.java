package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class Controller implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry index) {
		index.addViewController("/").setViewName("forward:/index.html");
	}


	// Injetar o repository
	@Autowired
	private GameRepository repository;

	// 4 Métodos, Get, Post, Put e Delete
	// Get By Id
	// localhost:8080/manutencoes
	@GetMapping("/games")
	public List<GameTable> buscarTodos() {
		return repository.findAll();

	}

	// @Path Variable serve para pegar o parâmetro
	@GetMapping("/games/{id}")
	public Optional<GameTable> buscarUm(@PathVariable Long id) {
		return repository.findById(id);

	}

	// Post é inserir, nest caso na mesma URL(localhost:8080/games)
	@PostMapping("/games")
	public GameTable criar(@RequestBody GameTable jogo) {
		repository.save(jogo);
		return jogo;

	}

	// Put serve para atualizar, primeiro pega com o @Pathvariable, depois insere
	// com
	// @RequestBody
	// setId vai forçar a alteração do body, setar = mudar
	@PutMapping("/game/{id}")
	public GameTable atualizar(@PathVariable Long id, @RequestBody GameTable model) {
		model.setId(id);
		repository.save(model);
		return model;

	}

	@GetMapping("/games/{nome}")
	public List<GameTable> buscarPorNome(@PathVariable String nome) {
		return repository.findByNome(nome);

	}

	@GetMapping("/games/{nome}/{categoria}")
	public Optional<GameTable> findByNomeAndCategoria(@PathVariable String nome, @PathVariable String categoria) {
		return repository.findByNomeAndCategoria(nome, categoria);

	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
