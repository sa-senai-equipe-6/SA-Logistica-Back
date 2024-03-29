package br.senai.logistica.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.logistica.backend.service.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping("{user}/senha/{pass}")
	public ResponseEntity<?> loginCom(
			@PathVariable("user")
			String usuario,
			@PathVariable("pass")
			String senha) {
		
		System.out.println(usuario + senha);
		
		var usuarioEncontrado = service.validarUsuario(usuario, senha);
		return ResponseEntity.ok(usuarioEncontrado);
	}
	
}
