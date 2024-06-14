package it.stopAndCode.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.stopAndCode.model.Articolo;


public class ArticoloRepositoryImpl implements ArticoloRepository {


	//Funzione utilizzata per la registrazione dell'articolo
	@Override
	public int registraArticolo(Articolo articolo) {
		String sql = "INSERT INTO articoli (Descrizione,PrezzoUnitario,Quantita) VALUES (?,?,?)";
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1,articolo.getDescrizione());
			statement.setFloat(2,articolo.getPrezzoUnitario());
			statement.setInt(3, articolo.getQuantita());
			statement.executeUpdate();
			try(ResultSet resultSet = statement.getGeneratedKeys()){
				if(resultSet.next()) {
					resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;

	}
	
	@Override
	public void registraArticoli(List<Articolo> articoli, Connection connection) {
		
		String sql = "INSERT INTO articoli (Descrizione,PrezzoUnitario,Quantita) VALUES (?,?,?)";
		String sqlArticoliFattura = "INSERT INTO Articoli_Fatture (ID_Articolo,ID_Fattura) VALUES ((SELECT MAX(ID) FROM articoli),(SELECT MAX(ID) FROM fatture))";
		
		for(Articolo a : articoli) {
			try (PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1,a.getDescrizione());
				statement.setFloat(2,a.getPrezzoUnitario());
				statement.setInt(3, a.getQuantita());
				statement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			try (PreparedStatement statement = connection.prepareStatement(sqlArticoliFattura)) {
				statement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	//Funzione utilizzate per ricevere la lista di tutti gli articoli
	@Override
	public List<Articolo> getArticoli() {
		String sql = "SELECT * FROM articoli";
		try(Connection connection = getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)){
			List<Articolo> articoli = new ArrayList<>();
			while(resultSet.next()) {
				Articolo articolo = new Articolo();
				articolo.setId(resultSet.getInt("ID"));
				articolo.setDescrizione(resultSet.getString("Descrizione"));
				articolo.setPrezzoUnitario(resultSet.getFloat("PrezzoUnitario"));
				articolo.setQuantita(resultSet.getInt("Quantita"));
				articoli.add(articolo);
			}
			return articoli;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}	

}
