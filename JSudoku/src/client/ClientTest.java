package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Die Klasse ClientTest testet die Methoden der Klasse Client.
 * 
 * @author Arne Busch
 *
 */
public class ClientTest extends TestCase {

	/**
	 * Testet das Erstellen eines Einzelspielerspiels mit einem geladenen
	 * Spielfeld.
	 * 
	 * @throws RemoteException
	 */
/*
	@Test
	public void testErstelleSpielSpielmodusIntString() throws RemoteException {
		Client client = new Client();
		GrafikDummy grafik = new GrafikDummy();
		List<String> erfolgteServerMethodenAufrufe = new ArrayList<String>();
		List<String> erfolgteGrafikMethodenAufrufe = new ArrayList<String>();

		client.setzeGrafik(grafik);
		client.starteServerDummy();
		erfolgteServerMethodenAufrufe.add("erstelleSpiel3");
		erfolgteServerMethodenAufrufe.add("gibSchwierigkeitsgrad");
		erfolgteServerMethodenAufrufe.add("gibStrafzeit");
		erfolgteServerMethodenAufrufe.add("gibSpielvariante");
		erfolgteServerMethodenAufrufe.add("spielerAnmelden");
		erfolgteGrafikMethodenAufrufe.add("zeigeFehlermeldung");

		client.erstelleSpiel(Spielmodus.einzelspieler, 0, "dateiname");

		assertEquals(erfolgteGrafikMethodenAufrufe, grafik
				.gibErfolgteMethodenAufrufe());
		assertEquals(erfolgteServerMethodenAufrufe, client.gibServer()
				.gibErfolgteMethodenAufrufe());
	}
*/
	/**
	 * Testet das Erstellen eines Einzelspielerspiels in der Spielvariante
	 * Standard und Schwierigkeitsgrad mittel.
	 * 
	 * @throws RemoteException
	 */
/*
	@Test
	public void testErstelleSpielSpielmodusSpielvarianteSchwierigkeitsgradInt()
			throws RemoteException {
		Client client = new Client();
		GrafikDummy grafik = new GrafikDummy();
		List<String> erfolgteServerMethodenAufrufe = new ArrayList<String>();
		List<String> erfolgteGrafikMethodenAufrufe = new ArrayList<String>();

		client.setzeGrafik(grafik);
		client.starteServerDummy();
		erfolgteServerMethodenAufrufe.add("erstelleSpiel2");
		erfolgteServerMethodenAufrufe.add("gibSchwierigkeitsgrad");
		erfolgteServerMethodenAufrufe.add("gibStrafzeit");
		erfolgteServerMethodenAufrufe.add("gibSpielvariante");
		erfolgteServerMethodenAufrufe.add("spielerAnmelden");
		erfolgteServerMethodenAufrufe.add("genuegendSpielerAngemeldet");
		erfolgteGrafikMethodenAufrufe.add("zeigeFehlermeldung");

		client.erstelleSpiel(Spielmodus.einzelspieler, Spielvariante.standard,
				Schwierigkeitsgrad.mittel, 1);

		assertEquals(erfolgteGrafikMethodenAufrufe, grafik
				.gibErfolgteMethodenAufrufe());
		assertEquals(erfolgteServerMethodenAufrufe, client.gibServer()
				.gibErfolgteMethodenAufrufe());
	}
*/
	/**
	 * Testet das Setzen des Einzelspielermodus.
	 * 
	 * @throws RemoteException
	 */
	@Test
	public void testSetzeEinzelspielerModus() throws RemoteException {
		Client client = new Client();
		GrafikDummy grafik = new GrafikDummy();
		List<String> erfolgteGrafikMethodenAufrufe = new ArrayList<String>();

		client.setzeGrafik(grafik);
		client.starteLokalenServerDummy();
		erfolgteGrafikMethodenAufrufe.add("zeigeEinzelspielermenue");

		String servername = "SudokuServer";
		String serveradresse = "127.0.0.1";

		client.setzeEinzelspielermodus();

		assertEquals(servername, client.gibLokalServer().gibServername());
		assertEquals(serveradresse, client.gibServerAdresse());
		assertEquals(erfolgteGrafikMethodenAufrufe, grafik
				.gibErfolgteMethodenAufrufe());
	}

	/*
	 * @Test public void testSetzeServerAdresse() { Client client = new
	 * Client(); String serveradresse = "148.120.0.80";
	 * 
	 * client.setzeServerAdresse(serveradresse);
	 * 
	 * assertEquals(serveradresse, client.gibServerAdresse()); }
	 */

	/**
	 * Testet das Verbinden zu einem lokalen Server.
	 * 
	 * @throws RemoteException
	 */
	@Test
	public void testVerbindeMitServer() throws RemoteException {
		Client client = new Client();
		GrafikDummy grafik = new GrafikDummy();
		List<String> erfolgteGrafikMethodenAufrufe = new ArrayList<String>();

		client.setzeGrafik(grafik);
		client.starteLokalenServerDummy();

		String serveradresse = "127.0.0.1";

		assertEquals(true, client.verbindeMitServer(serveradresse));
		assertEquals(erfolgteGrafikMethodenAufrufe, grafik
				.gibErfolgteMethodenAufrufe());
	}

}
