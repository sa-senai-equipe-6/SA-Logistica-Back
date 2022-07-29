package br.senai.logistica.backend;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.senai.logistica.backend.entity.Perfil;
import br.senai.logistica.backend.entity.Usuario;
import br.senai.logistica.backend.exceptions.RegistroNaoEncontradoException;
import br.senai.logistica.backend.service.UsuarioService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	@DisplayName("Testa a inserção de um usuario")
	@Order(1)
	public void insercaoDeUmUsuario() {
		assertThrows(ConstraintViolationException.class, () -> service.inserirUsuario(null));
		assertThrows(ConstraintViolationException.class, () -> service.inserirUsuario(new Usuario()));
		assertThrows(ConstraintViolationException.class, () -> service.inserirUsuario(new Usuario("", "", "", Perfil.MOTORISTA)));
		assertDoesNotThrow(() -> service.inserirUsuario(new Usuario("Roberto da Silva", "roberto", "roberto123", Perfil.MOTORISTA)));
	}
	
	@Test
	@DisplayName("Testa a validaçao de um usuario")
	@Order(2)
	public void validacaoDeUmUsuario() {
		assertThrows(IllegalArgumentException.class, () -> service.validarUsuario("roberto", "roberto12"));
		assertThrows(RegistroNaoEncontradoException.class, () -> service.validarUsuario("robertoo", "roberto12"));
		assertDoesNotThrow(() -> service.validarUsuario("roberto", "roberto123"));
	}
	
	@Test
	@DisplayName("Testa a edição de um usuario")
	@Order(3)
	public void editarUsuario() throws JsonMappingException, JsonProcessingException {
		var usuarioOriginal = service.buscarLogin("roberto");	
		var usuarioCopy = mapper.readValue(mapper.writeValueAsString(usuarioOriginal), Usuario.class);
		usuarioCopy.setLogin("rodrigo");
		
		assertFalse(usuarioOriginal.equals(usuarioCopy));
		assertDoesNotThrow(() -> service.inserirUsuario(usuarioCopy));
		assertDoesNotThrow(() -> service.validarUsuario("rodrigo", "roberto123"));
	}
	
}
