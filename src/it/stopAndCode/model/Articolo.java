package it.stopAndCode.model;

import java.util.ArrayList;
import java.util.List;

public class Articolo {
	private int id;
	private String descrizione;
	private float prezzoUnitario;
	private int quantita;
	private List<Fattura> fatture = new ArrayList<>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public float getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public void setPrezzoUnitario(float prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public List<Fattura> getFatture() {
		return fatture;
	}
	public void setFatture(List<Fattura> fatture) {
		this.fatture = fatture;
	}
	@Override
	public String toString() {
		return "Articolo: " + descrizione + " Prezzo: " + prezzoUnitario + "€ " + " Quantità: N°" + quantita;
	}
	
	
	

}
