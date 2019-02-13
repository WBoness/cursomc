package com.boness.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.boness.cursomc.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity // Mapeamento Objeto-relacional
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id //Estratégia para a criação automática dos campos, índice e etc. (automaticamente) --- Mapeamento Objeto-Relacional
	@GeneratedValue(strategy=GenerationType.IDENTITY) // definindo a estratégia de geração automática da chave primária (id).
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo; // ENUM -- Internamente o cliente armazenará dado como  inteiro - tem alterar nos getters e setters
	
	// ASSOCIAÇÕES --- TELEFONE, ENDEREÇO, PEDIDO
	@JsonManagedReference // proteção contra serialização ciclica: o cliente pode serializar endereço, mas o endereço não
	@OneToMany (mappedBy ="cliente")	
	private List<Endereco> enderecos = new ArrayList<>();//Associação Um cliente tem vários endereços (nome de papel)
	
	// como modelar o telefone (entidade fraca e totlmente dependente do cliente
	// como ele e uma string que contem um numero, não é necessário a criação da classe
	//um cliente tem varios telefones: solução: coleção de strings associadas ao cliente
	// usar tipo set (conjunto - não aceita repetição)
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>(); //java.util.HashSet e ..util.Set --- tem que fazer o mapeamento Objeto-relacional com o JPA
	
	// Mapeamento com pedidos: 
	@OneToMany (mappedBy="cliente")
	private List<Pedido> pedidos = new ArrayList<>(); //Lista de pedidos. Não vai para o construtor porque é uma Lista --- tem que criar os Getters e Setters
	
	//Construtores
	public Cliente( ) {
		
	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) { // Não pode ter coleções
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo.getCodigo(); //tem que armazenar um número, pea alteração feita no atributo
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() { //como retornar o tipo cliente se está armazenando inteiro
		return TipoCliente.toEnum(tipo); //retornará através do método no enum
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCodigo(); //tem que armazenar um número pela modificação feita no atributo
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	
	
		
}
