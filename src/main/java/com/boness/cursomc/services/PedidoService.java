package com.boness.cursomc.services;

/* Usado para criar o endpoint de Pedido
 * Busca por código, utilizando o FindOne do repositório
 * 
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boness.cursomc.domain.Pedido;
import com.boness.cursomc.repositories.PedidoRepository;
import com.boness.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar (Integer id) {
		Pedido obj = repo.findOne(id); // vai no banco de dados, busca uma categoria pelo id e retorna o objeto
		//se o obj retornar nulo, lança a exceção
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id "+ id // O método de serviço lança uma exceção caso o id não exista
					+ ", Tipo: " + Pedido.class.getName()); // quem recebe esta exceção é a camada de recurso REST
		}
		return obj;
	}
	
}
