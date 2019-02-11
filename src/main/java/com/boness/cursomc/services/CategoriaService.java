package com.boness.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boness.cursomc.domain.Categoria;
import com.boness.cursomc.repositories.CetegoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CetegoriaRepository repo;
	
	public Categoria buscar (Integer id) {
		Categoria obj = repo.findOne(id); // vai no banco de dados, busca uma categoria pelo id e retorna o objeto
		return obj;
	}
	
}
