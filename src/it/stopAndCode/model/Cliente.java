package it.stopAndCode.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private int id;
	private String nome;
	private String cognome;
	private List<Fattura> fatture = new ArrayList<>();
	
	public List<Fattura> getFatture() {
		return fatture;
	}
	public void setFatture(List<Fattura> fatture) {
		this.fatture = fatture;
	}
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
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	@Override
	public String toString() {
		return "Cliente: " + nome + " "+  cognome;
	}
	
	
	
	
	
	

}
