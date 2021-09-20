package br.com.estudo.biblioteca.controller.dto;

import br.com.estudo.biblioteca.modelo.Livro;

public class LivroDto {
	private Long id;
	private String nome;
	private String autor;
	private String editora;
	
	public LivroDto(Livro livro) {
		this.id = livro.getId();
		this.nome = livro.getNome();
		this.autor = livro.getAutor();
		this.editora = livro.getEditora();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditora() {
		return editora;
	}
	
	
}
