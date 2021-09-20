package br.com.estudo.biblioteca.controller.form;

import br.com.estudo.biblioteca.modelo.Livro;
import br.com.estudo.biblioteca.repository.LivroRepository;

public class AtualizacaoLivroForm {
	
	private String editora;

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public Livro atualizar(String nomeLivro, LivroRepository livroRepository) {
		Livro livro = livroRepository.findLivroByNome(nomeLivro);
		livro.setEditora(this.editora);
		return livro;
		
	}
	
}
