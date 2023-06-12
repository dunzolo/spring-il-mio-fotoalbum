package org.java.project.pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il titolo non può essere null")
	private String nome;
	
	@ManyToMany(mappedBy = "categoria")
	private List<Foto> foto;
	
	public Categoria() {}
	public Categoria(String nome) {
		setNome(nome);
	}
	
	//get e set
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Foto> getFotos() {
		return foto;
	}
	public void setFotos(List<Foto> foto) {
		this.foto = foto;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] " + getNome();
	}
}
