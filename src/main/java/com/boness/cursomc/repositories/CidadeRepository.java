package com.boness.cursomc.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boness.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> { //interface que extende JpaRepository com Categoria e o tipo do objeto id
/* Um objeto capaz de realizar operacoes (inserir, deletar, alterar) de acesso a dados referentes ao objeto categoria, que está mapeado com a tabela categoria */

	
}
