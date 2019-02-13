package com.boness.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable  // clase será um subtipo
public class ItemPedidoPk implements Serializable {
	private static final long serialVersionUID = 1L;
	/* Esta classe:
	 * - terá uma referência para o pedido e outra para o produto
	 * - deverá implementar serializable (colocar número de versão padrão)
	 * - Getters e Setters
	 * - hashCode e equals (com base nos dois atributos)
	 * - Não precisa de construtor
	 * - colocar a anotação embeddable para indicar que a classe é uma subclasse
	 * - Fazer o Mapeamento
	*/
	
	// Mapeamento
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	
	// Getters e Setters
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
	
	//hashCode e equals
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
