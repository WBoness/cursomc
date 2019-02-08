package com.boness.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //criou uma classe controlada pelo controlador REST
@RequestMapping(value="categorias") // vai responder por este endpoint
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET) //operação de requisição, obtendo dados: GET.... POST... DELETE...
	public String listar() {
		return "REST esta funcionando!"; // tem associar a função Rest com os verbos do HTTP. Deve incluir uma anotação
	}
	
}
