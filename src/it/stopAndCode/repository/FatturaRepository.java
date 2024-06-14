package it.stopAndCode.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import it.stopAndCode.model.Articolo;
import it.stopAndCode.model.Cliente;
import it.stopAndCode.model.Fattura;

public interface FatturaRepository extends Repository{
	
	public int registraFattura(Fattura fattura, Connection connection) throws SQLException;
	
	public List<Fattura> getFatture();
	
	public void registraFatturaConDettagli(Fattura fattura, Cliente cliente,
			List<Articolo> articoli);
}
