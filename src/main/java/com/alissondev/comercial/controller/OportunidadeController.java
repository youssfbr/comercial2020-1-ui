package com.alissondev.comercial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alissondev.comercial.model.Oportunidade;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {
	
	@GetMapping
	public String listar() {
		Oportunidade oportunidade = new Oportunidade();
		oportunidade.
		return "Hello";
	}

}
