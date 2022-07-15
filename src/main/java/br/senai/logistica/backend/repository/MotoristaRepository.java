package br.senai.logistica.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.senai.logistica.backend.entity.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Integer>{

}
