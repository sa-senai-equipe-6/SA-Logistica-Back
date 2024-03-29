package br.senai.logistica.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.senai.logistica.backend.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("SELECT u FROM Usuario u WHERE UPPER(u.login) LIKE UPPER(:login)")
	Usuario buscarLogin(@Param("login") String login);
	
	@Query("SELECT u FROM Usuario u WHERE UPPER(u.nomeCompleto) LIKE UPPER(:nome)")
	Usuario buscarNome(@Param("nome") String nome);
}
