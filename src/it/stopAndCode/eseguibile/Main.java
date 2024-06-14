package it.stopAndCode.eseguibile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.stopAndCode.model.Articolo;
import it.stopAndCode.model.Cliente;
import it.stopAndCode.model.Fattura;
import it.stopAndCode.repository.ArticoloRepository;
import it.stopAndCode.repository.ArticoloRepositoryImpl;
import it.stopAndCode.repository.ClienteRepository;
import it.stopAndCode.repository.ClienteRepositoryImpl;
import it.stopAndCode.repository.FatturaRepository;
import it.stopAndCode.repository.FatturaRepositoryAvanzata;

public class Main {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static FatturaRepository repository = new FatturaRepositoryAvanzata();
	private static ArticoloRepository articoloRepository = new ArticoloRepositoryImpl();
	private static ClienteRepository clienteRepository = new ClienteRepositoryImpl();

	public static void main(String[] args) throws IOException {
		GestioneFattura();
	}

	/**
	 * Con questa funzione avvio il programma
	 * @throws IOException 
	 * @Author Nicola
	 * @Version 1.0.0
	 */
	static void GestioneFattura() throws IOException {
		System.out.println("Cosa vuoi fare?");
		System.out.println(
				"N - Nuova Fattura "
				+ "\nL - Registro Fattura "
				+ "\nRA - Registra Articolo "
				+ "\nA - Elenco Articoli "
				+ "\nC - Elenco Clienti "
				+ "\nE - Esci");
		String scelta = reader.readLine(); // è necessario salvare lo scanner in una variabile per essere analizzato da
										// tutti gli if
		if (scelta.equalsIgnoreCase("N")) {
			registraFattura();
			GestioneFattura();
		} else if (scelta.equalsIgnoreCase("L")) {
			leggiFattura();
			GestioneFattura();
		} else if (scelta.equalsIgnoreCase("A")) {
			elencoArticoli();
			GestioneFattura();
		} else if (scelta.equalsIgnoreCase("C")) {
			elencoClienti();
			GestioneFattura();
		} else if (scelta.equalsIgnoreCase("E")) {
			System.out.println("Uscita dal Programma in corso");
			System.exit(0);
		} else if(scelta.equalsIgnoreCase("RA")) {
			articoloRepository.registraArticolo(registraArticolo());
			GestioneFattura();
		} else {
			System.out.println("Scelta non valida, riprova.");
			GestioneFattura();
		}

	}
	/**
	 * Questa funzione permette di creare la fattura, in base ai dati richiesti in input, crea l'oggetto fattura
	 * @throws IOException 
	 * @Author Nicola
	 */
	static void registraFattura() throws IOException {
		
		Fattura fattura = new Fattura();

		System.out.println("CREA UNA NUOVA FATTURA \nInserisci la data in formato aaaa-mm-gg");
		String dataInput = reader.readLine();
		
		
		 // Funzione utilizzata per la verifica dell'input Data inserita dall'utente.
		if (dataInput.matches("^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date parseDate;

			
			try {
				parseDate = dateFormat.parse(dataInput);
				java.sql.Date sqlDate = new java.sql.Date(parseDate.getTime());
				fattura.setDataDiEmissione(sqlDate);
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}

			System.out.println("Inserisci l'aliquota IVA");
			int iva = Integer.parseInt(reader.readLine());
			fattura.setIva(iva);

			Cliente cliente = registraCliente();

			List<Articolo> articoli = registraArticolinew();

			fattura.setArticoli(articoli);
			fattura.setImponibile(fattura.calcoloImponibile(articoli));
			fattura.setTotale(fattura.calcoloTotaleDb(iva, fattura.getImponibile()));
			fattura.setCliente(cliente);
			repository.registraFatturaConDettagli(fattura, cliente, articoli);

		} else {
			System.out.println("Hai inserito la data in un formato non consentito");
			registraFattura();
		}
	}

	/**
	 * Questa funzione permette di leggere dati dal database, passa una query che ritorna tutte le fatture registrate
	 * @throws IOException 
	 */
	static void leggiFattura() throws IOException {
		System.out.println("REGISTRO FATTURE EMESSE");
		System.out.println("----------------------------");
		for (Fattura fattura : repository.getFatture()) {
			System.out.println(fattura);
			System.out.println("--------------------------");
		}
		GestioneFattura();
	}
	
	/**
	 * Questa funzione viene utilizzate per leggere l'intero archivio degli articoli nel database
	 */
	static void elencoArticoli() {
		System.out.println("ELENCO ARTICOLI REGISTRATI");
		System.out.println("------------------------------");
		for (Articolo articolo : articoloRepository.getArticoli()) {
			System.out.println(articolo);
			System.out.println("-----------------------------");
		}
	}

	/**
	 * Funzione utilizzata per leggere l'intero elenco Clienti
	 */
	static void elencoClienti() {
		System.out.println("ELENCO CLIENTI REGISTRATI");
		System.out.println("------------------------------");
		for (Cliente c : clienteRepository.getClienti()) {
			System.out.println(c);
			System.out.println("-----------------------------");
		}
	}

	/**
	 *  Funzione utilizzata per registrare il singolo articolo
	 * @return un oggetto di tipo Articolo dopo aver ricevuto in input i dati necessari alla costruzione dello stesso
	 */
	static Articolo registraArticolo() {

		try {
			Articolo articolo = new Articolo();


			System.out.println("Inserisci la descrizione dell'articolo");
			String descrizioneString = reader.readLine();
			articolo.setDescrizione(descrizioneString);

			System.out.println("Inserisci il prezzo della singola unità");
			String prezzoUnitario = reader.readLine();
			prezzoUnitario = prezzoUnitario.replace(",", ".");
			float prezzoUni = Float.parseFloat(prezzoUnitario);
			articolo.setPrezzoUnitario(prezzoUni);

			System.out.println("Inserisci la quantità del prodotto");
			int quantita = Integer.parseInt(reader.readLine());
			articolo.setQuantita(quantita);

			return articolo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	/**
	 *  Funzione utilizzata per registrare il Cliente
	 * @return un oggetto di tipo Cliente dopo aver ricevuto in input i dati per la costruzione
	 */
	static Cliente registraCliente() {
		try {
			Cliente cliente = new Cliente();

			System.out.println("Inserisci il nome del cliente");
			String nomeCliente = reader.readLine();
			cliente.setNome(nomeCliente);

			System.out.println("Inserisci il cognome del cliente");
			String cognomeCliente = reader.readLine();
			cliente.setCognome(cognomeCliente);

			return cliente;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 *  Funzione utilizzata per registrare più articoli
	 * @return un'ArrayList di articoli registrati
	 */
	static List<Articolo> registraArticolinew() {
		System.out.println("Per Uscire dalla registrazione dell'articolo premere Q, altrimenti premi N");

		try {
			String sceltaString = reader.readLine();
			List<Articolo> articoli = new ArrayList<>();
			while (sceltaString.equalsIgnoreCase("N")) {
				Articolo articolo = registraArticolo(); 
				articoli.add(articolo);
				
				System.out.println("Vuoi inserire un altro prodotto? premi N altrimenti altro per uscire");
				sceltaString = reader.readLine();
			}
			return articoli;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
