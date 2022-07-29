package br.senai.logistica.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@NonNull
	@Column(name = "nome_completo")
	@NotEmpty(message = "O nome completo é obrigatorio")
	@Size(min = 5, max = 50, message = "O nome completo deve conter de {min} a {max} caracteres")
	String nomeCompleto;
	
	@NonNull
	@Column(unique = true)
	@NotEmpty(message = "O login é obrigatorio")
	@Size(min = 2, max = 20, message = "O login deve conter de {min} a {max} caracteres")
	String login;
	
	@NonNull
	@Column
	@NotEmpty(message = "A senha é obrigatoria")
	@Size(min = 2, max = 10, message = "A senha deve conter de {min} a {max} caracteres")
	@Pattern(regexp = "([0-9]+[a-zA-Z]+)|([a-zA-Z]+[0-9]+)", message = "A senha deve conter letras e numeros")
	String senha;
	
	@NonNull
	@Column
	@NotNull(message = "O perfil não pode ser nulo")
	Perfil perfil;
	
}
