package com.boness.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boness.cursomc.domain.Categoria;

@RestController //criou uma classe controlada pelo controlador REST
@RequestMapping(value="categorias") // vai responder por este endpoint
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET) //operação de requisição, obtendo dados: GET.... POST... DELETE...
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1,"Informática");
		Categoria cat2 = new Categoria(2,"Escritório");
		
		List<Categoria> lista = new ArrayList<>(); //Como o List não pode ser instanciado (é uma Interface)
												   // tem que usar uma classe que implementa essa interface
		lista.add (cat1); //adiciona cat1 à lista
		lista.add(cat2);
		
		return lista; // Retorna a lista de categorias
	}
}
