package br.senai.logistica.backend.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.senai.logistica.backend.entity.Motorista;
import br.senai.logistica.backend.entity.Usuario;
import br.senai.logistica.backend.repository.MotoristaRepository;
import br.senai.logistica.backend.repository.UsuarioRepository;

@Service
@Validated
public class MotoristaService {

	@Autowired
	private MotoristaRepository repository;
	
	public Motorista inserir(
			@Valid
			Motorista motoristaRecebido) {
		
		return repository.save(motoristaRecebido);
	}
	
}
