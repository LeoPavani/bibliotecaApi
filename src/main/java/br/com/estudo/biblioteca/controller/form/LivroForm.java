package br.com.estudo.biblioteca.controller.form;

import com.sun.istack.NotNull;

import br.com.estudo.biblioteca.modelo.Livro;

public class LivroForm {
	
	@NotNull 
	private String nomeLivro;
	
	@NotNull
	private String autor;
	
	@NotNull
	private String editora;

	public String getNomeLivro() {
		return nomeLivro;
	}



	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}



	public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public String getEditora() {
		return editora;
	}



	public void setEditora(String editora) {
		this.editora = editora;
	}



	public Livro converter() {
		return new Livro(nomeLivro, autor, editora);
	}
	
	
	
}
