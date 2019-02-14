package com.boness.cursomc.domain;

/*
 * 
 * Para a associação com pedido e ItemPedido
 * 1 Conhecer os itens de pedidos associados a pedido (conjunto de itens de pedido)
 *   Set ....HashSet ---pedido conhece os itens associados a ele
 * 2 Criar Getter e Setter dos itens
 */


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	// aqui o id é gerado automaticamente
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date instante;
	
	/* Associações: Pedido com pagamento - pedido com Cliente - pedido com Endereco
	 * - Tem que fazer a associação de mão dupla: pedido tem que conhecer os itens dele o produto tem que conhecer os pedidos
	 * - mas não estão diretamente associados: tem o itemPedido no meio
	 * - implementa nas classes:
	 * -- pedido --> conhecer -> Itens associados a ele -> conjunto de itens (igual no produto)
	 * criar os Getters e Setters de itens
	 * - Mapear com ItemPedidoPK inversamente @OnetoMany --> mapeado por id.pedido
	*/
	
	// mapeamento com ItemPedido
	@OneToMany (mappedBy="id.pedido") //quem mapeou na classe ItemPedido 
	private Set <ItemPedido> itens = new HashSet<>(); //Associação de Mão dupla(pedido tem vários itens); conhecer os itens e garantir que não terá repetição
	
	
	// Mapeamento com Pagamento
	@OneToOne (cascade = CascadeType.ALL, mappedBy="pedido")// Mapeamento com Pagamento 1-1 --> necessário senão dá erro de entidade transiente quando vai salvar um pedido e seu pagto	
	private Pagamento pagamento;
	
	//Mapeamento com Cliente
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	//Mapeamento com Endereco --- Não tem correspondente no outro lado
		@ManyToOne
		@JoinColumn(name="enderecoDeEntrega_id")
	private Endereco endedecoDeEntrega; //Ver papel no diagrama - é Unidirecional: logo o endereço não conhece o pedido
	
		
	//Construtores
	public Pedido() {
		
	}
	public Pedido(Integer id, Date instante, Cliente cliente, Endereco endedecoDeEntrega) { // retirou-se o Pagamanto pagamento para instanciar depois
		super();
		this.id = id;
		this.instante = instante;
		//this.pagamento = pagamento;
		this.cliente = cliente;
		this.endedecoDeEntrega = endedecoDeEntrega;
	}
	
	//Getters e Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getInstante() {
		return instante;
	}
	public void setInstante(Date instante) {
		this.instante = instante;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Endereco getEndedecoDeEntrega() {
		return endedecoDeEntrega;
	}
	public void setEndedecoDeEntrega(Endereco endedecoDeEntrega) {
		this.endedecoDeEntrega = endedecoDeEntrega;
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
