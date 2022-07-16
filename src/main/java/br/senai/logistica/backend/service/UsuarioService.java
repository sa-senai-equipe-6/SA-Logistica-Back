package br.senai.logistica.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.senai.logistica.backend.repository.UsuarioRepository;

@Service
@Validated
public class UsuarioService {

	private UsuarioRepository repository;
	
}
