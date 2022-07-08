package br.senai.logistica.backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
//		SpringApplication.run(InitApp.class, args);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println(LocalDate.now().format(formatter));
	}

}
