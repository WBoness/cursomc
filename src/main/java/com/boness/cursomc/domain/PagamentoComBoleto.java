package com.boness.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.boness.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L; //não coloca o serializable, mas coloca o número de versão
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataPagamento;
	
	
	//Construtores
	public PagamentoComBoleto() {
		
	}

	//como é uma subclasse, tem que gerar o construtor a partir sa superclasse e acrescentar os próprios
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {// tem que acrescentar os elementos da subclasse
		super(id, estado, pedido);
		// TODO Auto-generated constructor stub
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	
	//Getters e Setters
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	//hashCode e equals --- Não precisa, pois herdam da superclasse
	
}
