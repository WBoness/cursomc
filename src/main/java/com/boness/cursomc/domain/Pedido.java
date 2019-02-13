package com.boness.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	// aqui o id é gerado automaticamente
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date instante;
	
	// Associações: Pedido com pagamento - pedido com Cliente - pedido com Endereco
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
