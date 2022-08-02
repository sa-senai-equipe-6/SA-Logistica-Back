package br.senai.logistica.backend;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
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

import br.senai.logistica.backend.entity.Motorista;
import br.senai.logistica.backend.entity.Perfil;
import br.senai.logistica.backend.entity.Usuario;
import br.senai.logistica.backend.service.MotoristaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class MotoristaServiceTest {
	
	@Autowired
	private MotoristaService service;
	
	@BeforeAll
	public void insereMotoristaComUsuario() {
		var user1 = new Usuario("Adolfinho Cardoso", "adolfin", "adolfo123", Perfil.MOTORISTA);
		var user2 = new Usuario("Robertinho Cardoso", "robertun", "rob123", Perfil.MOTORISTA);
		var user3 = new Usuario("Raphael Cardoso", "raphael", "raphabeny1", Perfil.MOTORISTA);
		var user4 = new Usuario("Alberto Cardoso", "alberto", "alberto3", Perfil.MOTORISTA);
		var user5 = new Usuario("Rodrigo Cardoso", "rodrigun", "rodrigo7", Perfil.MOTORISTA);
		var mot1 = new Motorista("123123123", LocalDate.now().plus(1, ChronoUnit.YEARS), 'A', user1);
		var mot2 = new Motorista("123123124", LocalDate.now().plus(1, ChronoUnit.YEARS), 'B', user2);
		var mot3 = new Motorista("123123125", LocalDate.now().plus(1, ChronoUnit.YEARS), 'C', user3);
		var mot4 = new Motorista("123123126", LocalDate.now().plus(1, ChronoUnit.YEARS), 'D', user4);
		var mot5 = new Motorista("123123127", LocalDate.now().plus(1, ChronoUnit.YEARS), 'E', user5);
		
		service.inserir(mot1);
		service.inserir(mot2);
		service.inserir(mot3);
		service.inserir(mot4);
		service.inserir(mot5);
	}
	
	@Test
	@DisplayName("Mostra todos os motoristas cadastrados")
	@Order(1)
	public void listaTodosMotoristas() {
		List<Motorista> motoristas = service.listar();
		assertFalse(motoristas.isEmpty());
		assertDoesNotThrow(() -> motoristas.forEach(m -> log.info(m.getUsuario().getNomeCompleto())));
	}
	
//	@Test
//	@DisplayName("Edita um motorista")
//	@Order(2)
//	public void editarUmMotorista() {
//		var motoristaSalvo = service.buscarPor("adolfinho");
//		motoristaSalvo.setCategoria('D');
//		System.out.println(motoristaSalvo.getId());
//		assertDoesNotThrow(() -> service.editar(motoristaSalvo));
//	}
	
	@Test
	@DisplayName("Busca um motorista")
	@Order(3)
	public void buscarMotorista() {
		assertDoesNotThrow(() -> service.buscarPorUsuario(1));
	}
	
//	@Test
//	@DisplayName("Deleta um motorista")
//	@Order(4)
//	public void deletarMotorista() {
//		var motoristaSalvo = service.buscarPor("adolfinho");
//		int id = motoristaSalvo.getId();
//		service.deletarPor(id);
//		assertNull(service.buscarPor(id));
//	}
	
//	@Test
//	@DisplayName("Valida pre-requisitos para motorista")
//	@Order(5)
//	public void validarPreRequisitosMotorista() {
//		var motorista = service.buscarPor("alb");
//		
//		//validacao de id na insercao
//		motorista.setId(123342);
//		motorista.setCnh("453254325");
//		motorista.getUsuario().setLogin("dusahu45");
//		assertThrows(IllegalArgumentException.class, () -> service.inserir(motorista));
//		
//		//validacao de cnh ja existente
//		motorista.setCnh("123123125");
//		assertThrows(IllegalArgumentException.class, () -> service.inserir(motorista));
//		motorista.setCnh("485389545");
//		
//		//validacao de usuario ja existente
//		motorista.getUsuario().setLogin("raphael");
//		assertThrows(IllegalArgumentException.class, () -> service.inserir(motorista));
//		
//		//validacao de id na edicao
//		motorista.setId(null);
//		assertThrows(IllegalArgumentException.class, () -> service.editar(motorista));
//	}
	
}
