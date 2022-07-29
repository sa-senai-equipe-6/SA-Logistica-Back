package br.senai.logistica.backend.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senai.logistica.backend.entity.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer>{

	@Query("SELECT m FROM Motorista m WHERE m.cnh LIKE :cnh")
	Motorista buscarPor(
			@Param("cnh") String cnh);

	@Query("SELECT m FROM Motorista m WHERE m.usuario.id = :id")
	Motorista buscarPorUsuario(
			@NotNull 
			@Param("id")
			Integer idUsuario);

	@Query("SELECT m FROM Motorista m WHERE m.id = :id")
	Motorista buscarPor(@NotNull Integer id);
	
}
