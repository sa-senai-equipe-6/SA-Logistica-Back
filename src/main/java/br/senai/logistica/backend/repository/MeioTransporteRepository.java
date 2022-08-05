package br.senai.logistica.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senai.logistica.backend.entity.MeioTransporte;

@Repository
public interface MeioTransporteRepository extends JpaRepository<MeioTransporte, Integer>{

	@Query("SELECT mt FROM MeioTransporte mt WHERE UPPER(mt.descricao) LIKE UPPER(:descricao)")
	public List<MeioTransporte> buscarPorDescricao(@Param("descricao") String descricao);

	default MeioTransporte buscarPor(Integer id) {
		return this.findById(id).get();
	}

}
