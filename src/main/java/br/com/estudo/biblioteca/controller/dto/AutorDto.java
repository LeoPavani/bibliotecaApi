package br.com.estudo.biblioteca.controller.dto;

import br.com.estudo.biblioteca.modelo.Autor;

public class AutorDto {
	
	private Long id;
	private String name;

	public String getName() {
		return name;
	}
	
	public Long getId() {
		return id;
	}
	
	public AutorDto(Autor autor) {
		this.id = autor.getId();
		this.name = autor.getNome();
	}
	
}
