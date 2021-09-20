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

import br.com.estudo.biblioteca.controller.dto.AutorDto;
import br.com.estudo.biblioteca.controller.form.AtualizacaoAutorForm;
import br.com.estudo.biblioteca.controller.form.AutorForm;
import br.com.estudo.biblioteca.modelo.Autor;
import br.com.estudo.biblioteca.repository.AutorRepository;

@Controller
@ResponseBody
@RequestMapping("/autores")
public class AutorController {
	
	@Autowired
	AutorRepository autorRepository;
	
	@GetMapping
	public List<Autor> lista(){
		return autorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AutorDto> buscarAutorPorId(@PathVariable Long id){
		Optional<Autor> optional = autorRepository.findById(id);
		if (optional.isPresent()) {
			AutorDto dto = new AutorDto(optional.get());
			return ResponseEntity.ok(dto);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Autor> cadastrar(@RequestBody AutorForm form, UriComponentsBuilder uriBuilder){
		Autor autor = form.converter();
		autorRepository.save(autor);
		
		URI uri = uriBuilder.path("/autores/{nome}").buildAndExpand(autor.getNome()).toUri();
		return ResponseEntity.created(uri).body(new Autor(form.getNome()));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeAutor(@PathVariable Long id){
		Optional<Autor> optional = autorRepository.findById(id);
		if (optional.isPresent()) {
			autorRepository.deleteById(id);
			return ResponseEntity.ok(lista());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AutorDto> altera(@PathVariable Long id, 
			@RequestBody AtualizacaoAutorForm form){
		Optional<Autor> autorSeExistir = autorRepository.findById(id);
		if (autorSeExistir.isPresent()) {
			Autor autor = form.atualiza(id, autorRepository);
			return ResponseEntity.ok(new AutorDto(autor));	
		}else {
			return ResponseEntity.notFound().build();
		}
		
		
	}
	
	
}
