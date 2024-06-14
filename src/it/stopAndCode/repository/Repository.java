package it.stopAndCode.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface Repository {
	String MAMPPORT = "8889";
	String XAAMPPORTS = "3310";
	String URL = "jdbc:mysql://localhost:"+MAMPPORT+"/dbgestionefatture"; // jdbc:mysql driver per il database di mysql --
	// indirizzo del server / nome del database con
	// il quale si vuole interagire "sql_01"
	String USERNAME = "root";
	String PASSWORD = null;
	String MAMPPASSWORD = "root";

// metodo ausiliario che ritorna la connessione con il database
	default Connection getConnection() { // lo facciamo diventare un metodo defaul
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, MAMPPASSWORD);
			return connection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
