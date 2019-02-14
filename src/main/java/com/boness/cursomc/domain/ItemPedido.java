package com.boness.cursomc.domain;
/* 0 Atributos Básicos
 * 1 Tem que criar uma classe auxiliar ItemPedidoPk 
 * 2 Criar como id um objeto da classe auxiliar - ItemPedidoPk id - e instanciar
 * 3 O id é um atributo composto (como um item primitivo) e
 *   quando se faz uma entidade do JPA, tend como atributo uma outra classe
 *   usa-se (NA OUTRA CLASSE - ItemPedidoPk)a anotação Embeddable
 * 4 Associações --> foram feitas na ItemPedidoPk
 * 5 Tem que fazer a associação deão dupla, pois os pedidos tem que conhecer seus itens 
 *   (e o produto os pedidos) - isso é feito nas classes, pois tem o ItemPedido no meio
 * 6 Criar Construtores: Vazio e a partir dos elementos
 * 7 trocar o id por produto e pedido
 * 8 Criar Getters e Setters
 * 9 Criar getPedidos e getProdutos
 * 10 hashCode e equals
 * 11 Serializable
 * 12 Mapeamento
 * 		@Entity
 *      @EmbeddedId
 *  	Para pedido e produt já foi feito na classe auxiliar: em pedido e produto, fazer o mapeamento inverso
 */

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// criação do id
		@EmbeddedId
	private ItemPedidoPk id = new ItemPedidoPk(); 

	private Double desconto;
	private Integer quantidade;
	private Double preco;
		
	
	//Construtores
	public ItemPedido() {
		
	}

	// modificar o contrutor
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;		
	}

	public Pedido getPedido () {
		return id.getPedido();
	}
	
	
	
	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ItemPedidoPk getId() {
		return id;
	}

	public void setId(ItemPedidoPk id) {
		this.id = id;
	}

	//hashCode e equals
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
		
	
	
		
}
