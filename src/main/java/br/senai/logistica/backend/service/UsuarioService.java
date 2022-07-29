package br.senai.logistica.backend.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
	
	public Usuario validarUsuario(String usuario, String senha) {
		var usuarioEncontrado = this.repository.buscarLogin(usuario);
		if (usuarioEncontrado == null)
			throw new RegistroNaoEncontradoException("Usuario não encontrado");
		if (!usuarioEncontrado.getSenha().equals(senha))
			throw new IllegalArgumentException("Senha incorreta");
		return usuarioEncontrado;
	}

	public Usuario inserirUsuario(
			@Valid
			@NotNull
			Usuario novoUsuario
			) {
		return repository.save(novoUsuario);
	}
	
	public Usuario buscarLogin(String login) {
		return repository.buscarLogin(login);
	}

	public Usuario buscarNome(String nome) {
		return repository.buscarNome(nome);
	}
	
}
