package com.boness.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Estado implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id //mapeando o id para a criação automática
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@OneToMany(mappedBy="estado") //um Estado tem várias Cidades. mapped corresponde ao atributo do outro lado que mapeou: estado, no caso
	private List<Cidade> cidades = new ArrayList<>(); //aSSOCIAÇÃO: UM ESTADO TEM CÁRIAS CIDADES. Tem que instanciar
	
	public Estado(){
		
	}

	public Estado(Integer id, String nome) { // não incluir coleções (Listas) no construtor com parâmetros
		super();
		this.id = id;
		this.nome = nome;
	}
	
	//Getters e Setters (Todos)
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

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	//hashCode e equals só do ID
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
		Estado other = (Estado) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}