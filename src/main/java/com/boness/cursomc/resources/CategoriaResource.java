package com.boness.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boness.cursomc.domain.Categoria;
import com.boness.cursomc.services.CategoriaService;

@RestController //criou uma classe controlada pelo controlador REST
@RequestMapping(value="categorias") // vai responder por este endpoint
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //operação de requisição, obtendo dados: GET.... POST... DELETE...
	public ResponseEntity<?> find (@PathVariable Integer id) {
		
		Categoria obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
