package br.senai.logistica.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.logistica.backend.entity.Motorista;
import br.senai.logistica.backend.service.MotoristaService;

@RestController
@RequestMapping("api/motorista")
public class MotoristaController {
	
	@Autowired
	private MotoristaService service;
	
	@PostMapping
	public ResponseEntity<?> inserirMotorista(
			@RequestBody
			Motorista motorista) {
		service.inserir(motorista);
		return ResponseEntity.ok("blz");
	}
	
	@GetMapping
	public ResponseEntity<?> teste() {
		return ResponseEntity.ok("funcionando");
	}
	
}
