package com.boness.cursomc.domain;
/* 1 Tem que ter referencia para cada tabela associada
 * 2 Implementar a interface seerializable e versão
 * NÃO PRECISA DE CONSTRUTOR
 * 3 Getters e Setters
 * 4 hashCode e equals (produto e pedido)
 * 5 Colocar a anotação Embeddable por ser subclasse da ItemPedido
 * 6 Fazer o mapeamento do pedido e do produto: ItemProduto tem que 
 *   conhecer um pedido e um produto: Produto 1<-* ItemPedido *->1 Pedido
 */


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable //indica que é subtipo de outra classe
public class ItemPedidoPk implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// 6 Mapeamento - ItemProduto --> produto e pedido
	@ManyToOne
	@JoinColumn (name="pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn (name="produto_id")
	private Produto produto;
	
	
	// Gettes e Setters
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	// hashCode e equals
	@Override
	public int hashCode() {
		return Objects.hash(pedido, produto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPk other = (ItemPedidoPk) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(produto, other.produto);
	}

	
}
