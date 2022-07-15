package br.senai.logistica.backend.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "meio_transporte")
public class MeioTransporte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "tipo_veiculo")
	@NotNull(message = "O tipo do veiculo é obrigatorio")
	Tipo tipoVeiculo;
	
	@NotNull(message = "A data da revisao é obrigatoria")
	@Column(name = "data_revisao")
	@Future(message = "A data da revisao nao deve ser anterior ou igual a data atual")
	LocalDate dataRevisao;
	
	@Column
	@NotEmpty(message = "A descricao é obrigatoria")
	@Size(min = 10, max = 1500, message = "A descricao deve conter entre {min} e {max} caracteres")
	String descricao;
	
	@OneToOne
	@NotNull(message = "O motorista é obrigatorio")
	@JoinColumn(name = "id_motorista")
	Motorista motorista;
	
}
