package br.com.estudo.biblioteca.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estudo.biblioteca.controller.form.AtualizacaoLivroForm;
import br.com.estudo.biblioteca.controller.form.LivroForm;
import br.com.estudo.biblioteca.modelo.Livro;
import br.com.estudo.biblioteca.repository.LivroRepository;

@Controller
@ResponseBody
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	LivroRepository livroRepository;


	@GetMapping
	public List<Livro> lista() {

		return livroRepository.findAll();
	}

	@GetMapping("/{nomeLivro}")
	public ResponseEntity<Livro> buscaLivroPorNome(@PathVariable String nomeLivro) {

		Livro livro = livroRepository.findLivroByNome(nomeLivro);
		System.out.println(livro);
		if (livro != null) {
			return ResponseEntity.ok(livro);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{nomeLivro}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable String nomeLivro) {

		Livro livro = livroRepository.findLivroByNome(nomeLivro);
		System.out.println(livro);
		if (livro != null) {
			livroRepository.delete(livro);
			return ResponseEntity.ok(livroRepository.findAll());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{nomeLivro}")
	@Transactional
	public  ResponseEntity<?> atualizar(@PathVariable String nomeLivro, 
			@RequestBody AtualizacaoLivroForm form){
		Livro livroSeExistir = livroRepository.findLivroByNome(nomeLivro);
		if (livroSeExistir != null) {
			form.atualizar(nomeLivro, livroRepository);			
			return ResponseEntity.ok(livroRepository.findLivroByNome(nomeLivro));
		}
		return ResponseEntity.notFound().build();
				
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody LivroForm form, UriComponentsBuilder uriBuilder){
		Livro livro = form.converter();
		livroRepository.save(livro);
		
		URI uri = uriBuilder.path("/livros/{nomeLivro}").buildAndExpand(livro.getNome()).toUri();
		return ResponseEntity.created(uri).body(new Livro(form.getNomeLivro(), form.getAutor(), form.getEditora()));
	
	}

}