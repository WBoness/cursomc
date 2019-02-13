package com.boness.cursomc.domain;

import javax.persistence.Entity;

import com.boness.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L; //não coloca o serializable, mas coloca o número de versão

	private Integer numeroDeParcelas;
	
	
	//Construtores
	public PagamentoComCartao () {
		
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		// TODO Auto-generated constructor stub
		this.numeroDeParcelas = numeroDeParcelas;
	}

	//Getters e Setters
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	//hashCode e equals --- Não precisa, pois herdam da superclasse
	
	
}
