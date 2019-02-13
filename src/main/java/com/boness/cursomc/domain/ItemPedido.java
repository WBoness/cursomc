package com.boness.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	/* - A classe de associação não tem id próprio
	 * - ela é identificada pelos dois objetos associados  a ela (produto e pedido)
	 * - como implementar no JPA: criar uma chave composta contendo o produto e o pedido (criar uma classe auxiliar --> ItemPedidoPk)
	 * - inserir um objeto ItemPedidoPk
	 * - trocar no construtor itemPedidoPk pelo objeto id e inserir os valores de produto e pedido
	 * - criar os Gets de pedido e produto
	 * - Mapeamento: 
	 * 		@EmbeddedId --> pela chave composta --
	*/

	@EmbeddedId // Id embutido em um tipo auxiliar
	private ItemPedidoPk id = new ItemPedidoPk(); //objeto auxiliar deve ser instanciado. oID é um atributo composto - colocar na outra classe Embeddable)
	
	
	//Construtores
	public ItemPedido() {
		
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) { // o itemPedidoPk deve ser trocado por pedido e produto
		super();
		id.setPedido(pedido); // atribuir o pedido e o produto dentro do objeto id (que é do tipo ItemPedidoPk)
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
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
	
	public Pedido getPedido() { //criar parater acesso direto a pedido e produto fora da classe pedido
		return id.getPedido();
	}

	public Produto getProduto() {
		return id.getProduto();
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

