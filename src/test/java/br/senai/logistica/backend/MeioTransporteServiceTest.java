package br.senai.logistica.backend;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.Period;

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

import br.senai.logistica.backend.entity.MeioTransporte;
import br.senai.logistica.backend.entity.Motorista;
import br.senai.logistica.backend.entity.Perfil;
import br.senai.logistica.backend.entity.Tipo;
import br.senai.logistica.backend.entity.Usuario;
import br.senai.logistica.backend.service.MeioTransporteService;
import br.senai.logistica.backend.service.MotoristaService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class MeioTransporteServiceTest {

	@Autowired
	private MeioTransporteService service;

	@Autowired
	private MotoristaService serviceMotorista;

	@BeforeAll
	public void inserirMotorista() {
		serviceMotorista.inserir(
				new Motorista("987654352", LocalDate.now().plus(Period.ofYears(10)), 'A',
						new Usuario("Ripa na Xulipa", "xulipa", "ripa123", Perfil.MOTORISTA)));
	}

	@Test
	@DisplayName("Insere um meio de transporte")
	@Order(1)
	public void inserirTransporte() {
		var transp = new MeioTransporte();
		transp.setDataRevisao(LocalDate.now().plus(Period.ofYears(10)));
		transp.setDescricao("Um caminhao mto legal");
		transp.setTipoVeiculo(Tipo.CARRO);
		transp.setMotorista(serviceMotorista.listar().get(0));
		assertDoesNotThrow(() -> service.inserir(transp));
	}

	@Test
	@DisplayName("Edição de um transporte previamente inserido")
	@Order(2)
	public void editarTransporte() {
		var transpExistente = service.buscarPor("xulipa").stream().findFirst().orElse(null);
		
		if (transpExistente == null)
			fail("Transporte null");
		
		transpExistente.setTipoVeiculo(Tipo.MOTO);
		assertDoesNotThrow(() -> service.alterar(transpExistente));
	}

	@Test
	@DisplayName("Deletando um meio de transporte")
	@Order(3)
	public void deletarTransporte() {
		var transpExistente = service.buscarPor("xulipa").stream().findFirst().orElse(null);
		
		if (transpExistente == null)
			fail("Transporte null");
		
		assertDoesNotThrow(() -> service.deletarPor(transpExistente.getId()));
	}

	@Test
	@DisplayName("Listar todos os meios de transporte")
	@Order(4)
	public void listarTodosOsTransportes() {
		inserirTransporte();
		assertFalse(service.listarTodos().isEmpty());
	}

	@Test
	@DisplayName("Testar validacao de tranportes")
	@Order(5)
	public void validacaoTransportes() {
		var transporte = service.listarTodos().get(0);
		assertThrows(IllegalArgumentException.class, () -> service.inserir(transporte));
		transporte.setId(null);
		assertThrows(IllegalArgumentException.class, () -> service.alterar(transporte));
	}

}
