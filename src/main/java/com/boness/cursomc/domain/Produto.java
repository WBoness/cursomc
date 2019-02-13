package com.boness.cursomc.domain;

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
			
	/* - Associação com Pedidos
	 * - criar os Getters e Setters de itens
	 * - Mapear com ItemPedidoPK inversamente @OnetoMany --> mapeado por id.produto
	 */
	@OneToMany (mappedBy="id.produro") //Mapeado na classe ItemPedido
	private Set<ItemPedido> itens = new HashSet<>(); //conhecer os itens e garantir que não terá repetição
	
	// Também tem uma associação com pedidos --> Um produto conhece os pedidos dele (criar um getPedidos, varrendo os itens de pedidos e montando uma lista de pedidos associados aos itens)
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>(); // Inicia uma lista de pedidos
		for (ItemPedido x:itens) {// percorrer a lista de itens que já existe, ara cada item de pedido x que estiver na lista de itens,
			lista.add(x.getPedido()); // adicionar o pedido associado a ele na lista
		}
		return lista;
	}
	
	public Produto() {
		
	}

	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		// categorias não entra pois já foi iniciada
	}

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
	public Set<ItemPedido> getItens() { //criado para a associação com pedido
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

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
