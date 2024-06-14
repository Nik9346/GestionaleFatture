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

	//Funzione utilizzata per registrare il nuovo cliente
	public int registraCliente(Cliente cliente, Connection connection) throws SQLException {
		//Istanzio la variabile clienteId per poi cambiarla con l'id generato dalla registrazione
		int clienteId=0;
		//istanzio la stringa sql per inserire il cliente nella tabella clienti
		String sqlClienti = "INSERT INTO clienti (Nome,Cognome) VALUES (?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sqlClienti, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getCognome());
			statement.executeUpdate();
			try (ResultSet generateId = statement.getGeneratedKeys()) {
				if (generateId.next()) {
					clienteId = generateId.getInt(1);
					return clienteId;
				}
				else {
					connection.rollback();
					throw new SQLException("Utente non inserito, si è verificato un errore");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	//Funzione utilizzata per leggere l'intero elenco clienti
	@Override
	public List<Cliente> getClienti() {
		
		String sql = "SELECT * FROM clienti";
		
		try(Connection connection = getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)){
			
			List<Cliente> clienti = new ArrayList<>();
			while(resultSet.next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setId(resultSet.getInt("ID"));
				cliente.setNome(resultSet.getString("Nome"));
				cliente.setCognome(resultSet.getString("Cognome"));
				
				clienti.add(cliente);
			}
			return clienti;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	


}
