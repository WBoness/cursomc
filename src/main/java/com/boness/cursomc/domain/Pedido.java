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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/*
 * ENDPOINT PARA RESPONDER POR UM PEDIDO
 * 		Criar PedidoServico (com uma função que retorna o pedido por um id)
 * 		Criar PedidoResource (REST Controller)
 * 		Proteger contra Serialização Json ciclica (Sempre fazer quando se tem associações de mão dupla)
 * 			- Entre pedido e cliente: *-1. Como vai fazer um endpoint para pedido e pedido tem que mostrar cliente: serializa em pedido e não em cliente 
 * 					Pedido: @JsonManagedReference  (em cliente) - permite serialização
 * 					Cliente: @JsonBackReference (em pedidos) - não permite serialização
 * 			- Entre pedido e endereço: é uma associação de mão única: não tem problema
 * 			- Entre pedido e pagamento (mão dupla): Um pedido tem que enxergar um pagamento. Pedido do pagamento não pode ser serializado
 * 					Pedido: @JsonManagedReference (em pagamento)
 * 					Pagamento: @JsonBackReference  (em pedido) - Não serializa
 * 			- Entre Pedido e ItemPedido e Produto - o itemPedido tem uma chave composta ItemPedidoPk (teoricamente temos 2 niveis de acesso)
 * 					solução: Em ItemPedido, na chave: ItemPedidoPk insere @JsonIgnore. 
 * 							Não será serializado, ou seja, a partir do Item de pedido, não serializa nem pedido, nem produto.
 * 							Como o pedido tem a coleção de itens, se não colocar nada ela será serializada
 * 							Produto: tem a lista de itens. 
 * 							Questão: a partir de um produto que for recuperado de um sistema, vai querer serializar os itens de pedido associados a ele?
 * 									 é importante é a partir de um item de pedido, saber que produto está associado com o item
 * 									ou a partir do produto, saber os itens que podem estar associados a ele?
 * 							O IMPORTANTE É: a partir de um item de pedido, poder ter acesso ao produto: ignorar a lista de itens associadas ao produto em Produto.
 * 							Em Produto, se deixar o método getPedidos, ele vai serializar. Tem que mandar ignorar (senão terá referência cíclica)
 */

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	// aqui o id é gerado automaticamente
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm") //Formata a data de acordo com o padrão indicado
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
	
	//Endpoint com pagamento
	@JsonManagedReference	//permite serialização
	// Mapeamento com Pagamento
	@OneToOne (cascade = CascadeType.ALL, mappedBy="pedido")// Mapeamento com Pagamento 1-1 --> necessário senão dá erro de entidade transiente quando vai salvar um pedido e seu pagto	
	private Pagamento pagamento;
	
	//Endpoint Pedido: permitir serialização em pedido
	@JsonManagedReference
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
