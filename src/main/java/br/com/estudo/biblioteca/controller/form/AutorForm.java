package br.com.estudo.biblioteca.controller.form;

import br.com.estudo.biblioteca.modelo.Autor;

public class AutorForm {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Autor converter() {
		return new Autor(nome);
	}
}
