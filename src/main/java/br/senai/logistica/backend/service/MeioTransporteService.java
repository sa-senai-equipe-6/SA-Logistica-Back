package br.senai.logistica.backend.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.senai.logistica.backend.entity.MeioTransporte;
import br.senai.logistica.backend.repository.MeioTransporteRepository;

@Service
@Validated
public class MeioTransporteService {
	
	@Autowired
	private MeioTransporteRepository repository;
	
	public MeioTransporte inserir(
			@Valid
			@NotNull(message = "O meio de transporte é obrigatorio")
			MeioTransporte transporteRecebido) {
		
		Preconditions.checkArgument(transporteRecebido.getId() == null, "O id do meio de transporte deve ser nulo");
		return repository.save(transporteRecebido);
	}
	
	public MeioTransporte alterar(
			@Valid
			@NotNull(message = "O meio de transporte é obrigatorio")
			MeioTransporte transporteRecebido) {
		Preconditions.checkArgument(transporteRecebido.getId() != null, "O id do meio de transporte é obrigatorio");
		return repository.save(transporteRecebido);
	}
	
	public void deletarPor(
			@Valid
			@NotNull(message = "O id do transporte é obrigatorio")
			Integer id) {
		repository.deleteById(id);
	}
	
	public List<MeioTransporte> listarTodos() {
		return repository.findAll();
	}

	public MeioTransporte buscarPor(Integer idTransporte) {
		return repository.buscarPor(idTransporte);
	}

	public List<MeioTransporte> buscarPor(String nomeMotorista) {
		return repository.buscarPorMotorista(String.format("%%%s%%", nomeMotorista));
	}
	
}
