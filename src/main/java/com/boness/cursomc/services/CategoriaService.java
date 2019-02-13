package com.boness.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boness.cursomc.domain.Categoria;
import com.boness.cursomc.repositories.CategoriaRepository;
import com.boness.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar (Integer id) {
		Categoria obj = repo.findOne(id); // vai no banco de dados, busca uma categoria pelo id e retorna o objeto
		//se o obj retornar nulo, lança a exceção
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id "+ id // O método de serviço lança uma exceção caso o id não exista
					+ ", Tipo: " + Categoria.class.getName()); // quem recebe esta exceção é a camada de recurso REST
		}
		return obj;
	}
	
}
