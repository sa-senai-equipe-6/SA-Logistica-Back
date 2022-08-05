package br.senai.logistica.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		Motorista motoristaInserido = service.inserir(motorista);
		return ResponseEntity.created(URI.create("/id/" + motoristaInserido.getId())).body(motoristaInserido);
	}
	
	@PutMapping
	public ResponseEntity<?> editarMotorista(
			@RequestBody
			Motorista motorista) {
		service.editar(motorista);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("id/{id}")
	public ResponseEntity<?> deletarMotorista(
			@PathVariable("id")
			Integer idMotorista) {
		service.deletarPor(idMotorista);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<?>  buscarMotorista(
			@PathVariable("id")
			Integer idMotorista) {
		Motorista motoristaEncontrado = service.buscarPor(idMotorista);
		return ResponseEntity.ok(motoristaEncontrado);
	}
	
	@GetMapping("/usuario/id/{id}")
	public ResponseEntity<?>  buscarMotoristaPorUsuario(
			@PathVariable("id")
			Integer idMotorista) {
		Motorista motoristaEncontrado = service.buscarPorUsuario(idMotorista);
		return ResponseEntity.ok(motoristaEncontrado);
	}
	
	
	@GetMapping
	public ResponseEntity<?> listarTodos() {
		List<Motorista> motoristas = service.listar();
		return ResponseEntity.ok(motoristas);
	}
	
	@GetMapping("filtro/{filtro}")
	public ResponseEntity<?> listarComFiltro(
			@PathVariable("filtro")
			String filtro) {
		var motoristas = service.buscarPor(filtro);
		return ResponseEntity.ok(motoristas);
	}
	
}
