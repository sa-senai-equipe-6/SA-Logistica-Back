package br.senai.logistica.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senai.logistica.backend.entity.MeioTransporte;

@Repository
public interface MeioTransporteRepository extends JpaRepository<MeioTransporte, Integer>{

	@Query("SELECT mt FROM MeioTransporte mt WHERE mt.motorista.id = :id")
	public MeioTransporte buscarPorMotorista(@Param("id") Integer id);

}
