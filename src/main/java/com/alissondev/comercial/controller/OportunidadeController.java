package com.alissondev.comercial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {
	
	@GetMapping
	public String listar() {
		return "Hello";
	}

}
