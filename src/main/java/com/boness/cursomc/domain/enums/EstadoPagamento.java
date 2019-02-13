package com.boness.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE (1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	//criação do Construtor - private
	private EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	//Getters e Setters
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	//operação para receber um código e retorna um objeto já instanciado TipoCliente conforme o código
	// Será static pois vai ter que ser possível ser executada mesmo ser ser instanciado objetos
	public static EstadoPagamento toEnum(Integer id) { // toEnum --> converte para enum
		if (id == null) { // tem que fazer uma busca: se for nulo, não retorna ninguém
		return null;
		}
		for (EstadoPagamento x : EstadoPagamento.values()) { // todo objeto x nos valores possíveiis tipo enumerado TipoCliente (PF e PJ) - varre todas as possibilidades
		if (id.equals(x.getCodigo())) { // se for igual ao código que está procurando, retorna o objeto
		return x; // se for informado 1, retorna "pessoa Física -- 2, PJ
		}
		}
		throw new IllegalArgumentException("Id inválido " + id); // se esgotar a busca e não encontar ninguém, 
	}
}
