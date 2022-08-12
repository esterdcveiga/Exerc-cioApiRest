package com.exercicio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cachorro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String raca;
	@Column(length = 50, nullable = false)
	private String nome;
	@Column(length = 50)
	private String porte;	
	
	public Cachorro() { }

	public Cachorro(String raca, String nome, String porte) {
		this.raca = raca;
		this.nome = nome;
		this.porte = porte;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRaca() {
		return raca;
	}
	
	public void setRaca(String raca) {
		this.raca = raca;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getPorte() {
		return porte;
	}
	
	public void setPorte(String porte) {
		this.porte = porte;
	}
	
	@Override
	public String toString() {
		return "Cachorro: id= "+id+", raça= "+raca+", nome= "+nome+", porte= "+porte;
	}
}