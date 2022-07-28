package br.senai.logistica.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import br.senai.logistica.backend.entity.Usuario;
import br.senai.logistica.backend.exceptions.RegistroNaoEncontradoException;
import br.senai.logistica.backend.repository.UsuarioRepository;

@Service
@Validated
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private MotoristaService motoristaService;
	
	public Usuario validarUsuario(String usuario, String senha) {
		var usuarioEncontrado = this.repository.buscarPor(usuario);
		if (usuarioEncontrado == null)
			throw new RegistroNaoEncontradoException("Usuario não encontrado");
		if (!usuarioEncontrado.getSenha().equals(senha))
			throw new IllegalArgumentException("Senha incorreta");
		return usuarioEncontrado;
	}
	
}
