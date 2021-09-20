package br.com.estudo.biblioteca.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Autor {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
//	private List<Livro> livros;
	
	public Autor(String nome, List<Livro> livros) {
		this.nome = nome;
//		this.livros = livros;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
//	public List<Livro> getLivros() {
//		return livros;
//	}
//	public void setLivros(List<Livro> livros) {
//		this.livros = livros;
//	}
	
	
}