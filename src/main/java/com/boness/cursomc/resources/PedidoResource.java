package com.boness.cursomc.resources;
/*
 * 
 * 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boness.cursomc.domain.Pedido;
import com.boness.cursomc.services.PedidoService;

@RestController //criou uma classe controlada pelo controlador REST
@RequestMapping(value="pedidos") // vai responder por este endpoint
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) //operação de requisição, obtendo dados: GET.... POST... DELETE...
	public ResponseEntity<?> find (@PathVariable Integer id) {
		
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj); 
	}
}
