package it.stopAndCode.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.stopAndCode.model.Cliente;

public class ClienteRepositoryImpl implements ClienteRepository {

	/**
	 * Funzione utilizzata per registrare il nuovo cliente Passando l'oggetto Cliente creato e la connessione
	 */
	public void registraCliente(Cliente cliente, Connection connection) throws SQLException {
		
		//istanzio la stringa sql per inserire il cliente nella tabella clienti
		String sqlClienti = "INSERT INTO clienti (Nome,Cognome) VALUES (?,?)";
		
		try (PreparedStatement statement = connection.prepareStatement(sqlClienti)) {
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getCognome());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Utente non inserito, si è verificato un errore");
		}
	}
	
	/**
	 * Funzione utilizzata per leggere l'intero elenco clienti
	 * @return ritorna l'intera lista dei clienti presenti all'interno del database
	 */
	@Override
	public List<Cliente> getClienti() {
		String sql = "SELECT * FROM clienti";
		
		try(Connection connection = getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)){
			
			List<Cliente> clienti = new ArrayList<>();
			while(resultSet.next()) {
				
				//costruisco l'oggetto cliente dopo aver ricevuto i dati dal database
				Cliente cliente = new Cliente();
				
				cliente.setId(resultSet.getInt("ID"));
				cliente.setNome(resultSet.getString("Nome"));
				cliente.setCognome(resultSet.getString("Cognome"));
				
				clienti.add(cliente);
			}
			return clienti;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Impossibile ricevere la lista dei clienti, si è verificato un errore");
			return null;
		}
	}
}
