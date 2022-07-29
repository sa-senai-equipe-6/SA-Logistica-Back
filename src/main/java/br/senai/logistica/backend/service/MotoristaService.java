package br.senai.logistica.backend.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.senai.logistica.backend.entity.Motorista;
import br.senai.logistica.backend.repository.MotoristaRepository;

@Service
@Validated
public class MotoristaService {

	@Autowired
	private MotoristaRepository motoristaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Motorista buscarPor(
			@NotNull
			Integer id) {
		return this.motoristaRepository.buscarPor(id);
	}
	
	public Motorista buscarPorUsuario(
			@NotNull
			Integer idUsuario) {
		return this.motoristaRepository.buscarPorUsuario(idUsuario);
	}
	
	public Motorista buscarPor(
			@NotNull
			String nome) {
		Integer idUsuario = usuarioService.buscarNome(String.format("%%%s%%", nome)).getId();
		return motoristaRepository.buscarPorUsuario(idUsuario);
	}
	
	public Motorista inserir(
			@Valid
			@NotNull(message = "O novo motorista é obrigatorio")
			Motorista motoristaRecebido) {
		validarCampos(motoristaRecebido);
		Preconditions.checkArgument(motoristaRecebido.getId() == null, "O id do motorista deve ser nulo");
		return motoristaRepository.save(motoristaRecebido);
	}
	
	public void deletarPor(
			@Valid
			@NotNull(message = "O id do motorista é obrigatório")
			Integer id) {
		motoristaRepository.deleteById(id);
	}

	public Motorista editar(
			@Valid
			@NotNull(message = "O motorista é obrigatorio")
			Motorista motoristaRecebido) {
		Preconditions.checkArgument(motoristaRecebido.getId() != null, "O id do motorista é obrigatorio");
		return motoristaRepository.save(motoristaRecebido);
	}
	
	public List<Motorista> listar() {
		return motoristaRepository.findAll();
	}
	
	private void validarCampos(Motorista motoristaRecebido) {
		Preconditions.checkArgument(motoristaRepository.buscarPor(motoristaRecebido.getCnh()) == null, 
				"Ja existe um motorista com este CNH cadastrado");
		Preconditions.checkArgument(usuarioService.buscarLogin(motoristaRecebido.getUsuario().getLogin()) == null,
				"Ja existe um motorista com este login cadastrado");
	}
	
}
