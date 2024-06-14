package it.stopAndCode.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.stopAndCode.model.Articolo;
import it.stopAndCode.model.Cliente;
import it.stopAndCode.model.Fattura;

public class FatturaRepositoryAvanzata implements FatturaRepository {
	private ClienteRepository clienteRepository = new ClienteRepositoryImpl();
	private ArticoloRepository articoloRepository = new ArticoloRepositoryImpl();

	/**
	 * Funzione utilizzata per la registrazione della fattura dopo passando i dati al database
	 */
	@Override
	public void registraFattura(Fattura fattura, Connection connection) throws SQLException {

		String sqlFattura = "INSERT INTO Fatture (Data_di_Emissione,iva,imponibile,Totale_Fattura,p_Cliente) VALUES (?,?,?,?,(SELECT MAX(ID) FROM clienti))";
		
		try (PreparedStatement statement = connection.prepareStatement(sqlFattura)) {
			statement.setDate(1, (Date) fattura.getDataDiEmissione());
			statement.setInt(2, fattura.getIva());
			statement.setDouble(3, fattura.getImponibile());
			statement.setDouble(4, fattura.getTotale());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Impossibile registrare la fattura, si è verificato un problema");
		}
	}

	/**
	 * Funzione utilizzata per chiedere al database l'intera lista delle fatture
	 */
	@Override
	public List<Fattura> getFatture() {
		String sql = "SELECT * FROM fatture";
		
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			List<Fattura> fatture = new ArrayList<>();
			while (resultSet.next()) {
				//compongo l'oggetto fattura con i dati ricevuti dal database
				Fattura fattura = new Fattura();
				fattura.setNumeroFattura(resultSet.getInt("ID"));
				fattura.setDataDiEmissione(resultSet.getDate("Data_Di_Emissione"));
				fattura.setImponibile(resultSet.getDouble("imponibile"));
				fattura.setIva(resultSet.getInt("iva"));
				fattura.setTotale(resultSet.getDouble("Totale_Fattura"));
				fattura.setP_Cliente(resultSet.getInt("p_Cliente"));
				fattura.setArticoli(setArticoliFattura(connection, fattura));
				SetClienteFattura(connection, fattura);
				fatture.add(fattura);
			}
			return fatture;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	/**
	 * Funzione utilizzata per settare gli articoli nella fattura, facendo una query al database
	 * 
	 * @param fattura passiamo l'oggetto fattura per poter prendere il numero di fattura e utilizzarlo per la query
	 * @return la lista degli articoli per il numero di fattura determinato.
	 */
	private List<Articolo> setArticoliFattura(Connection connection, Fattura fattura) {
		String sql = "SELECT * FROM Articoli JOIN Articoli_Fatture on Articoli.ID=Articoli_Fatture.ID_Articolo JOIN Fatture ON Articoli_Fatture.ID_Fattura = Fatture.ID WHERE Fatture.ID=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, fattura.getNumeroFattura());
			ResultSet resultSet = statement.executeQuery();
			List<Articolo> articoli = new ArrayList<>();
			while (resultSet.next()) {
				Articolo articolo = new Articolo();
				articolo.setId(resultSet.getInt("Articoli.ID"));
				articolo.setDescrizione(resultSet.getString("Articoli.Descrizione"));
				articolo.setPrezzoUnitario(resultSet.getFloat("Articoli.PrezzoUnitario"));
				articolo.setQuantita(resultSet.getInt("Articoli.Quantita"));
				articoli.add(articolo);
			}
			return articoli;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * Funzione utilizzata per recuperare il cliente della fattura con una query al database
	 * @param fattura Passiamo l'oggetto fattura per poter prelevare il numero di fattura collegato all'utente e passarlo alla query
	 */
	private void SetClienteFattura(Connection connection, Fattura fattura) {
		String sql = "SELECT * FROM Clienti JOIN Fatture ON Clienti.ID=Fatture.p_Cliente WHERE Fatture.ID=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, fattura.getNumeroFattura());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(resultSet.getInt("Clienti.ID"));
				cliente.setNome(resultSet.getString("Clienti.Nome"));
				cliente.setCognome(resultSet.getString("Clienti.Cognome"));
				fattura.setP_Cliente(cliente.getId());
				fattura.setCliente(cliente);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Questa funzione viene utilizzata per registrare l'intera fattura con cliente e articoli collegati
	 */
	@Override
	public void registraFatturaConDettagli(Fattura fattura, Cliente cliente, List<Articolo> articoli) {

		Connection connection = null;
		
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			clienteRepository.registraCliente(cliente, connection);

			registraFattura(fattura, connection);

			articoloRepository.registraArticoli(articoli, connection);

			connection.commit();
			connection.setAutoCommit(true);

		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
			if (connection != null)
				try {
					connection.rollback();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
	}

}
