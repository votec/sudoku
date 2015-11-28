package server;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Die Klasse ServerTest testet die Methoden der Klasse Server.
 * 
 * @author Thomas Fraenkler
 * 
 */
public class ServerTest extends TestCase {

	/**
	 * Testet das Erstellen eines Spiels mit einem uebergebenen Spielstand.
	 */
	@Test
	public void testErstelleSpielISpielstand() {
		/*
		 * Server server = new Server(); common.ISpielstand spielstand = new
		 * SpielstandDummy(); int dummyspielID =
		 * server.erstelleSpiel(spielstand); for (int i = 0; i <
		 * server.gibSpielliste().size(); i++) { int spielID =
		 * server.gibSpielliste().get(i).gibSpielID(); //jedesmal??
		 * assertEquals(spielstand,server.gibSpielstand(spielID)); }
		 */
	}

	/**
	 * Testet das Erstellen eines Spiels mit uebergebenen Spielmodus,
	 * Spielvariante, Schwierigkeitsgrad und Strafzeit.
	 */
	@Test
	public void testErstelleSpielSpielmodusSpielvarianteSchwierigkeitsgradInt() {
		/*
		 * IServer server = new Server("121.123.0.5"); try { int spielnummer =
		 * server.erstelleSpiel(EnumContainer.Spielmodus.einzelspieler,
		 * EnumContainer.Spielvariante.standard,EnumContainer.Schwierigkeitsgrad.anfaenger,
		 * 5); } catch (RemoteException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } assertEquals("Testfall zur Erstellung eines
		 * Spieles: ",)
		 */
	}

	/**
	 * Testet das Erstellen eines Spiels mit uebergebenen Spielmodus,
	 * Spielvariante, Schwierigkeitsgrad, Strafzeit und Spielfeld.
	 */
	@Test
	public void testErstelleSpielSpielmodusSpielvarianteSchwierigkeitsgradIntISpielfeld() {

	}

	/**
	 * Testet die Pruefung, ob genuegend Spieler angemeldet sind.
	 */
	@Test
	public void testGenuegendSpielerAngemeldet() {
		/*
		 * Server server = new Server(); ISpiel testSpiel = new SpielDummy();
		 * int spielID = testSpiel.gibSpielID(); assertEquals("Test
		 * GibFeldLoesung:
		 * ",testSpiel.genuegendSpielerAngemeldet(),server.genuegendSpielerAngemeldet(spielID));
		 */
	}

	/**
	 * Testet, ob die richtige Feldloesung zurueckgegeben wird.
	 */
	@Test
	public void testGibFeldLoesung() {
		/*
		 * ISpiel testSpiel = new SpielDummy(); Server server = new Server();
		 * int spielID = testSpiel.gibSpielID(); assertEquals("Test
		 * GibFeldLoesung:
		 * ",testSpiel.gibSpielfeldLoesung(),server.gibSpielfeldLoesung(spielID));
		 */
	}

}
