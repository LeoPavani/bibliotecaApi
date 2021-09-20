package br.com.estudo.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudo.biblioteca.modelo.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

	Autor findAutorByNome(String nomeAutor);
	
}
