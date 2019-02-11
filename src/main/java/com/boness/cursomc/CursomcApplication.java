package com.boness.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.boness.cursomc.domain.Categoria;
import com.boness.cursomc.repositories.CetegoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner { // permite executar um método auxiliar que será executado na inicialização

	@Autowired
	private CetegoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

@Override
public void run(String... args) throws Exception { // onde serão instanciados os objetos
	
	Categoria cat1 = new Categoria(null, "Informatica");
	Categoria cat2 = new Categoria(null, "Escritório");
	
	categoriaRepository.save(Arrays.asList(cat1,cat2));
}

	
	
}

