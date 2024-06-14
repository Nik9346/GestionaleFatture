package it.stopAndCode.repository;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import it.stopAndCode.model.Cliente;

public interface ClienteRepository extends Repository {
	
	//funzione utilizzata per la registrazione del cliente
	public int registraCliente(Cliente cliente, Connection connection) throws SQLException;
	
	//funzione utilizzata per ottenere l'elenco di tutti i clienti registrati
	public List<Cliente> getClienti();

}
