package it.stopAndCode.repository;

import java.sql.Connection;

import java.util.List;

import it.stopAndCode.model.Articolo;

public interface ArticoloRepository extends Repository {
	
	public void registraArticolo(Articolo articolo);
	
	public void registraArticoli(List<Articolo> articoli, Connection connection);
	
	public List<Articolo> getArticoli();
	

}
