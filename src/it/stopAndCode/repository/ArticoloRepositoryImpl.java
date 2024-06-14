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

	/**
	 * Funzione utilizzata per la registrazione dell'articolo nel database
	 */
	@Override
	public void registraArticolo(Articolo articolo) {
		String sql = "INSERT INTO articoli (Descrizione,PrezzoUnitario,Quantita) VALUES (?,?,?)";
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, articolo.getDescrizione());
			statement.setFloat(2, articolo.getPrezzoUnitario());
			statement.setInt(3, articolo.getQuantita());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Funzione utilizzata per registrare i vari articoli nel database e associare
	 * gli articoli alle fatture
	 */
	@Override
	public void registraArticoli(List<Articolo> articoli, Connection connection) {
		String sql = "INSERT INTO articoli (Descrizione,PrezzoUnitario,Quantita) VALUES (?,?,?)";
		String sqlArticoliFattura = "INSERT INTO Articoli_Fatture (ID_Articolo,ID_Fattura) VALUES ((SELECT MAX(ID) FROM articoli),(SELECT MAX(ID) FROM fatture))";

		for (Articolo a : articoli) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, a.getDescrizione());
				statement.setFloat(2, a.getPrezzoUnitario());
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

	/**
	 * Funzione utilizzata per ricevere la lista di tutti gli articoli
	 */
	@Override
	public List<Articolo> getArticoli() {
		String sql = "SELECT * FROM articoli";
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			List<Articolo> articoli = new ArrayList<>();
			while (resultSet.next()) {
				
				//Compongo l'oggetto Articolo con i dati ricevuti dalla query
				Articolo articolo = new Articolo();
				articolo.setId(resultSet.getInt("ID"));
				articolo.setDescrizione(resultSet.getString("Descrizione"));
				articolo.setPrezzoUnitario(resultSet.getFloat("PrezzoUnitario"));
				articolo.setQuantita(resultSet.getInt("Quantita"));
				
				//aggiungo gli articoli alla lista degli articoli
				articoli.add(articolo);
			}
			return articoli;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
