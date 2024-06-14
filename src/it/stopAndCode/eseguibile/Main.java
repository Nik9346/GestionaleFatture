package it.stopAndCode.eseguibile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

	private static Scanner scanner = new Scanner(System.in);
	private static FatturaRepository repository = new FatturaRepositoryAvanzata();
	private static ArticoloRepository articoloRepository = new ArticoloRepositoryImpl();
	private static ClienteRepository clienteRepository = new ClienteRepositoryImpl();

	public static void main(String[] args) {
		GestioneFattura();

	}

	static void GestioneFattura() {
		System.out.println("Cosa vuoi fare?");
		System.out.println(
				"N - Nuova Fattura \nL - Registro Fattura \nE - Esci \nA - Elenco Articoli \nC - Elenco Clienti");
		String scelta = scanner.next(); // è necessario salvare lo scanner in una variabile per essere analizzato da
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
		} else {
			System.out.println("Scelta non valida, riprova.");
			GestioneFattura();
		}

	}

	static void registraFattura() {
		Fattura fattura = new Fattura();

		System.out.println("CREA UNA NUOVA FATTURA \nInserisci la data in formato aaaa-mm-gg");
		String dataInput = scanner.next();
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
			int iva = scanner.nextInt();
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

	static void leggiFattura() {
		System.out.println("REGISTRO FATTURE EMESSE");
		System.out.println("----------------------------");
		for (Fattura fattura : repository.getFatture()) {
			System.out.println(fattura);
			System.out.println("--------------------------");
		}
		GestioneFattura();
	}

	// Funzione utilizzata per leggere l'intero archivio degli articoli
	static void elencoArticoli() {
		System.out.println("ELENCO ARTICOLI REGISTRATI");
		System.out.println("------------------------------");
		for (Articolo articolo : articoloRepository.getArticoli()) {
			System.out.println(articolo);
			System.out.println("-----------------------------");
		}
	}

	// Funzione utilizzata per leggere l'intero elenco Clienti
	static void elencoClienti() {
		System.out.println("ELENCO CLIENTI REGISTRATI");
		System.out.println("------------------------------");
		for (Cliente c : clienteRepository.getClienti()) {
			System.out.println(c);
			System.out.println("-----------------------------");
		}
	}

	// Funzione utilizzata per registrare il singolo articolo
	static Articolo registraArticolo() {

		try {
			Articolo articolo = new Articolo();

			System.out.println("Inserisci la descrizione dell'articolo");
			String descrizioneString = scanner.next();
			articolo.setDescrizione(descrizioneString);

			System.out.println("Inserisci il prezzo della singola unità");
			float prezzoUnitario = scanner.nextFloat();
			articolo.setPrezzoUnitario(prezzoUnitario);

			System.out.println("Inserisci la quantità del prodotto");
			int quantita = scanner.nextInt();
			articolo.setQuantita(quantita);

			return articolo;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	// Funzione utilizzata per registrare il Cliente
	static Cliente registraCliente() {
		try {
			Cliente cliente = new Cliente();

			System.out.println("Inserisci il nome del cliente");
			String nomeCliente = scanner.next();
			cliente.setNome(nomeCliente);

			System.out.println("Inserisci il cognome del cliente");
			String cognomeCliente = scanner.next();
			cliente.setCognome(cognomeCliente);

			return cliente;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	// Funzione utilizzata per registrare più articoli
	static List<Articolo> registraArticolinew() {
		System.out.println("Per Uscire dalla registrazione dell'articolo premere Q, altrimenti premi N");

		try {
			String sceltaString = scanner.next();
			List<Articolo> articoli = new ArrayList<>();
			while (sceltaString.equalsIgnoreCase("N")) {
				Articolo articolo = registraArticolo(); // devo capire per quale motivo rimane appeso e non passa sotto
														// al
														// messaggio di inserimento
				articoli.add(articolo);
				
				System.out.println("Vuoi inserire un altro prodotto? premi N altrimenti altro per uscire");
				sceltaString = scanner.next();
			}
			return articoli;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
