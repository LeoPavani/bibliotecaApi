package br.com.estudo.biblioteca.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.estudo.biblioteca.controller.dto.LivroDto;
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

	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> buscaLivroPorId(@PathVariable Long id) {

		Optional<Livro> optional = livroRepository.findById(id);
		if (optional.isPresent()) {
			LivroDto dto = new LivroDto(optional.get());
			return ResponseEntity.ok(dto);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {

		Optional<Livro> optional = livroRepository.findById(id);
		if (optional.isPresent()) {
			livroRepository.deleteById(id);
			return ResponseEntity.ok(lista());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public  ResponseEntity<LivroDto> atualizar(@PathVariable Long id, 
			@RequestBody AtualizacaoLivroForm form){
		Optional<Livro> livroSeExistir = livroRepository.findById(id);
		if (livroSeExistir.isPresent()) {
			Livro livro = form.atualizar(id, livroRepository);			
			return ResponseEntity.ok(new LivroDto(livro));
		}
		return ResponseEntity.notFound().build();
				
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Livro> cadastrar(@RequestBody LivroForm form, UriComponentsBuilder uriBuilder){
		Livro livro = form.converter();
		livroRepository.save(livro);
		
		URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
		return ResponseEntity.created(uri).body(new Livro(form.getNomeLivro(), form.getAutor(), form.getEditora()));
	
	}

}