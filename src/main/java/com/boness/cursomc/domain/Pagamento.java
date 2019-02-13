package com.boness.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.boness.cursomc.domain.enums.EstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED) // herança e a estratégia de como vai usar para gerar a tabela no BD:são duas básicas - 
// 1 - Tabelão com todos os campos de pgto com boleto e cartão, colocando null onde não corresponder --> geram muitos campos nulos (bom quando tem poucos atributos)
// 2 - Duas tabelas: uma para cada subclasse + a da superclasse (bom quando tem muitos atributos)

public abstract class Pagamento implements Serializable { // é abstrata para não ser instanciada: força instanciar as subclasses
	private static final long serialVersionUID = 1L;

	@Id //não terá anotação automática, mas a mesma do pedido (tem que colocar a anotação no atributo pedido)
		
	private Integer id;
	private Integer estado; //mudança para Integer
	
	//Associações:
	@OneToOne //relação 1-1
	@JoinColumn(name="pedido_id") //coluna correspondente ao id do pedido --> identifica no BD o campo
	@MapsId // para garantir que o id do pgto seja o mesmo do pedido (em seguida tem que mapear em pedido tb 1-1
	private Pedido pedido; 
	
	//Mapeamento: associacao com pedido 1-1, Pedido tem id e pgto é 1-1 --> pgt terá mesmo id do pedido
	
	//Construtores
	public Pagamento() {
		
	}


	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCodigo(); //pela mudança do tipo pata Integer
		this.pedido = pedido;
	}


	//Getters e Setters
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado); //pela mudança do tipo pata Integer
	}


	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCodigo(); //pela mudança do tipo pata Integer
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
