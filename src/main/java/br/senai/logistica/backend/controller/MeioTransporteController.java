package br.senai.logistica.backend.controller;

import java.net.URI;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.logistica.backend.entity.MeioTransporte;
import br.senai.logistica.backend.service.MeioTransporteService;

@RestController
@RequestMapping("/api/transporte")
public class MeioTransporteController {

	@Autowired
	private MeioTransporteService service;
	
	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		var listaMeioTransportes = service.listarTodos();
		return ResponseEntity.ok(listaMeioTransportes);
	}
	
	@PutMapping()
	public ResponseEntity<?> editarTransporte(
			@NotNull
			@RequestBody
			MeioTransporte transporteRecebido) {
		service.alterar(transporteRecebido);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping()
	public ResponseEntity<?> inserirTransporte(
			@NotNull
			@RequestBody
			MeioTransporte novoTransporte) {
		return ResponseEntity.created(URI.create("")).build();
	}
	
}
