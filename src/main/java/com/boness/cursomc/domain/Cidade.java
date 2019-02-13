package com.boness.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@JsonManagedReference // Proteção contra serialização cíclica: permite que cidade serialize Estado
	@ManyToOne //Muitas cidades para um estado
	@JoinColumn(name="estado_id") // nome do campo de chave estrangeira no BD para fazer a associação
	private Estado estado; // Associação: Cidade tem apenas um Estado
	
	//Construtores vazio e com parâmetros
	public Cidade() {
		
	}

	public Cidade(Integer id, String nome, Estado estado) { 
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	//hashCode e equals (só do ID)
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
		Cidade other = (Cidade) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
