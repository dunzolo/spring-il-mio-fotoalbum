package org.java.project.pojo;

import java.util.Arrays;
import java.util.List;

import org.java.project.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il titolo non può essere null")
	private String titolo;
	@NotBlank(message = "La descrizione non può essere null")
	private String descrizione;
	private String url;
	@Column(columnDefinition = "boolean default true")
	private Boolean visibile;
	
	@ManyToMany
	private List<Categoria> categoria;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonIgnore
	private User user;
	
	public Foto() {}
	public Foto(String titolo, String descrizione, String url, Boolean visibile, User user, Categoria...categorie) {
		setTitolo(titolo);
		setDescrizione(descrizione);
		setUrl(url);
		setVisibile(visibile);
		setUser(user);
		setCategories(categorie);
	}
	
	//get e set
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getVisibile() {
		return visibile;
	}
	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}
	public List<Categoria> getCategorie() {
		return categoria;
	}
	public void setCategorie(List<Categoria> categorie) {
		this.categoria = categorie;
	}
	public void setCategories(Categoria[] categorie) {
		setCategorie(Arrays.asList(categorie));
	}
	public void removeCategoria(Categoria categoria) {
		getCategorie().remove(categoria);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] " 
			+ "\nTitolo: " + getTitolo() 
			+ "\nDescrizione: " + getDescrizione()
			+ "\nUrl: " + getUrl()
			+ "\nVisibile: " + getVisibile();
	}
	
}
