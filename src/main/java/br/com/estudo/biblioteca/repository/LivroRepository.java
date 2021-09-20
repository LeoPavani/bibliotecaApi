package br.com.estudo.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudo.biblioteca.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	
	Livro findLivroByNome(String nomeLivro);
	
}
