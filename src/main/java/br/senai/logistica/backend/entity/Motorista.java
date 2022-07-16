package br.senai.logistica.backend.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
public class Motorista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(unique = true)
	@NotEmpty(message = "A cnh não pode ser vazia")
	@Pattern(regexp = "^[0-9]{9}$", message = "A cnh deve conter o formato NNNNNNNNN")
	String cnh;
	
	@Column
	@NotNull(message = "A data de renovacao é obrigatoria")
	@Future(message = "A data de renovação não pode ser anterior a data atual")
	LocalDate dataRenovacao;
	
	@Column
	@NotNull(message = "A categoria é obrigatória")
	Character categoria;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	Usuario usuario;
	
}
