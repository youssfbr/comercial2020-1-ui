package com.alissondev.comercial.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alissondev.comercial.model.Oportunidade;
import com.alissondev.comercial.repository.OportunidadeRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/oportunidades")
public class OportunidadeController {
	
	@Autowired
	private OportunidadeRepository oportunidades;
	
	@GetMapping
	public ResponseEntity<List<Oportunidade>> listar() {
		return ResponseEntity.ok(oportunidades.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidades.findById(id);
		
		if (oportunidade.isEmpty()) 
			return ResponseEntity.notFound().build();		
		
		return ResponseEntity.ok(oportunidade.get());
	}
	
	@PostMapping
	public ResponseEntity<Void> adicionar(@Valid @RequestBody Oportunidade oportunidade) {
		Optional<Oportunidade> oportunidadeExistente = oportunidades
				.findByDescricaoAndNomeProspecto(
						oportunidade.getDescricao(), 
						oportunidade.getNomeProspecto());
		
		if (oportunidadeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Já existe uma oportunidade para este prospecto com a mesma descrição");
		}
		
		oportunidade = oportunidades.save(oportunidade);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(oportunidade.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		oportunidades.deleteById(id); 
	}
	
	@PutMapping("/{id}")
	public void atualizar(@RequestBody Oportunidade oportunidade, @PathVariable Long id) {
		oportunidade.setId(id);
		oportunidades.save(oportunidade);
	}
}
