package com.boness.cursomc.domain;
/*
 * 
 * Para a Associação com Pedido e ItemPedido
 * 1 Produto também conhece os itens associados a ele
 *   Set ....HashSet
 * 2 Criar Getter e Setter itens
 * 3 Criar getPedidos (um produto conhece os seus pedidos)
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Double preco;
	
	// tem que mapear o relacionamento ( no caso é um relacionamento muitos par muitos
	@JsonBackReference
	@ManyToMany
	@JoinTable (name="PRODUTO_CATEGORIA", 
		joinColumns=@JoinColumn(name="produto_id"),
		inverseJoinColumns= @JoinColumn(name="categoria_id")
			)
	private List<Categoria> categorias = new ArrayList<>(); //Associacao com Categoria: Um produto pode ter várias categorias
			
	/* - Associação com ItemPedidos
	 * - criar os Getters e Setters de itens
	 * - Mapear com ItemPedidoPK inversamente @OnetoMany --> mapeado por id.produto
	 */
	
	//Endpoint com pedido e ItemPedido
	@JsonIgnore // ver anotação em pedido
	//Mapeamento com ItemPedido
	@OneToMany (mappedBy="id.produto") //Mapeado na classe ItemPedido -->ItemPedidoPk
	private Set <ItemPedido> itens = new HashSet<>(); //Associação de Mão dupla(produto tem vários itens);conhecer os itens e garantir que não terá repetição
	
	//Construtores
	public Produto() {
		
	}

	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		// categorias não entra pois já foi iniciada
	}

	// Endpoint com Pedidos: evitar serialização
	@JsonIgnore
	// Também tem uma associação com pedidos --> Um produto conhece os pedidos dele (criar um getPedidos, varrendo os itens de pedido e montando uma lista de pedidos associados aos itens)
		public List<Pedido> getPedidos(){
			List<Pedido> lista = new ArrayList<>(); // Inicia uma lista de pedidos
			for (ItemPedido x:itens) {// percorrer a lista de itens que já existe, ara cada item de pedido x que estiver na lista de itens,
				lista.add(x.getPedido()); // adicionar o pedido associado a ele na lista
			}
			return lista;
		}
		
	//Getters e Setters
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public java.util.Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(java.util.Set<ItemPedido> itens) {
		this.itens = itens;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

	
	



}
