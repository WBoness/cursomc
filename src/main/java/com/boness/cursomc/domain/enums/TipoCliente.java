package com.boness.cursomc.domain.enums;

public enum TipoCliente {
	//PESSOAFISICA,
	//PESSOAJURIDICA ;//DESTE TIPO ELE GRAVA UM CAMPO DO TIPO STRING.
	//Nao é interessante gravar na forma de String, mas como número que é feito automaticamente (0,1,....);
	//é melhor fazer um controle manual

	PESSOAFISICA (1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private Integer codigo;
	private String descricao;
	
	//criação do Construtor - private
	private TipoCliente(Integer codigo, String descricao) {
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
	public static TipoCliente toEnum(Integer id) { // toEnum --> converte para enum
		if (id == null) { // tem que fazer uma busca: se for nulo, não retorna ninguém
		return null;
		}
		for (TipoCliente x : TipoCliente.values()) { // todo objeto x nos valores possíveiis tipo enumerado TipoCliente (PF e PJ) - varre todas as possibilidades
		if (id.equals(x.getCodigo())) { // se for igual ao código que está procurando, retorna o objeto
		return x; // se for informado 1, retorna "pessoa Física -- 2, PJ
		}
		}
		throw new IllegalArgumentException("Id inválido " + id); // se esgotar a busca e não encontar ninguém, 
	}
}
	

