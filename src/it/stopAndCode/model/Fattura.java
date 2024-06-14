package it.stopAndCode.model;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class Fattura {

	private int numeroFattura;
	private Date dataDiEmissione;
	private double imponibile;
	private int iva;
	private double totale;
	private int p_Cliente;
	
	
	private Cliente cliente;
	private List<Articolo> articoli = new ArrayList<>();
	
	
	//Getter e Setter per tutti gli attributi della fattura
	public int getP_Cliente() {
		return p_Cliente;
	}


	public void setP_Cliente(int p_Cliente) {
		this.p_Cliente = p_Cliente;
	}

	
	public List<Articolo> getArticoli() {
		return articoli;
	}


	public void setArticoli(List<Articolo> articoli) {
		this.articoli = articoli;
	}


	public int getNumeroFattura() {
		return numeroFattura;
	}


	public void setNumeroFattura(int numeroFattura) {
		this.numeroFattura = numeroFattura;
	}


	public Date getDataDiEmissione() {
		return dataDiEmissione;
	}


	public void setDataDiEmissione(Date dataDiEmissione) {
		this.dataDiEmissione = dataDiEmissione;
	}


	public double getImponibile() {
		return imponibile;
	}


	public void setImponibile(double imponibile) {
		this.imponibile = imponibile;
	}


	public int getIva() {
		return iva;
	}


	public void setIva(int iva) {
		this.iva = iva;
	}


	public double getTotale() {
		return totale;
	}


	public void setTotale(double totale) {
		this.totale = totale;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	
	}
	

// funzione utilizzata per il calcolo del totale
	public String calcoloTotale(int iva, double imponibile)
	{
		DecimalFormat df = new DecimalFormat("#0.00", new DecimalFormatSymbols());
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		double importoFatturaDouble =  (imponibile * ((double)iva/100)) + imponibile;
		String importoFattura = df.format(importoFatturaDouble);
		return importoFattura;
	}
	public double calcoloTotaleDb(int iva, double imponibile)
	{
		double importoFatturaDouble =  (imponibile * ((double)iva/100)) + imponibile;
		return importoFatturaDouble;
	}
	//Funzione utilizzata per calcolare l'imponibile
	public double calcoloImponibile(List<Articolo> articoli) {
		
		double imponibile = 0;
		for(Articolo a : articoli) {
			imponibile += a.getPrezzoUnitario() * a.getQuantita();
		}
		return imponibile;
	}
	
	
	//Funzione utilizzate per formattare la lista degli articoli presenti in fattura
	public String articoloToString(List<Articolo> articoli) {
		StringBuilder descrizione = new StringBuilder();
		for(Articolo a: articoli) {
			descrizione.append("-------------------")
					.append("\nDescrizione: ").append(a.getDescrizione())
					.append("\nPrezzo Unitario: ").append(a.getPrezzoUnitario())
					.append("\nQuantità: ").append(a.getQuantita())
					.append("\n-----------------------------------\n");
		}
		return descrizione.toString();
	}

	
	//To string per la rappresentazione testuale dell'oggetto fattura
	@Override
	public String toString() {
		return  "************************************" + "\nFattura n. " + numeroFattura + "  del " + dataDiEmissione  + "\n----------------------------------" +  "\n"+ cliente + "\n" + articoloToString(articoli)+ "\nImponibile: " + calcoloImponibile(getArticoli()) + " €" + "\nIva: " + iva +"%" + "\nTotale da pagare: " + calcoloTotale(getIva(), calcoloImponibile(getArticoli()))+" €" + "\n************************************";
	}
	
	


	
	
	
}
