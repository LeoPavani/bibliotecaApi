package br.com.estudo.biblioteca.controller.form;

import br.com.estudo.biblioteca.modelo.Autor;
import br.com.estudo.biblioteca.repository.AutorRepository;

public class AtualizacaoAutorForm {
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Autor atualiza(Long id, AutorRepository autorRepository) {
		Autor autor = autorRepository.getById(id);
		autor.setNome(nome);
		return autor;
	}
}
