package common;

import junit.framework.TestCase;

/**
 * Die Klasse Spielfeld_ComparisonTest testet die Methoden der Klasse
 * Spielfeld_Comparison.
 * 
 * @author Heiko Schroeder
 * 
 */
public class Spielfeld_ComparisonTest extends TestCase {

	/**
	 * Testet, ob das Spielfeld korrekt generiert wird mit Schwierigkeitsgrad
	 * anfaenger.
	 */
	public void testGeneriereSpielfeldAnfaenger() {
		ISpielfeld spielfeld = new Spielfeld_Comparison();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.anfaenger;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals(
				"Test ob Spielfeld generiert wurde mit Anfaenger-Feldern: ",
				30, felder);
	}

	/**
	 * Testet, ob das Spielfeld korrekt generiert wird mit Schwierigkeitsgrad
	 * knifflig.
	 */
	public void testGeneriereSpielfeldKnifflig() {
		ISpielfeld spielfeld = new Spielfeld_Comparison();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.knifflig;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals(
				"Test ob Spielfeld generiert wurde mit Knifflig-Feldern: ", 15,
				felder);
	}

	/**
	 * Testet, ob das Spielfeld korrekt generiert wird mit Schwierigkeitsgrad
	 * mittel.
	 */
	public void testGeneriereSpielfeldMittel() {
		ISpielfeld spielfeld = new Spielfeld_Comparison();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.mittel;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals("Test ob Spielfeld generiert wurde mit Mittel-Feldern: ",
				20, felder);
	}

	/**
	 * Testet, ob das Spielfeld korrekt generiert wird mit Schwierigkeitsgrad
	 * profi.
	 */
	public void testGeneriereSpielfeldProfi() {
		ISpielfeld spielfeld = new Spielfeld_Comparison();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.profi;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals("Test ob Spielfeld generiert wurde mit Profi-Feldern: ",
				0, felder);
	}

	/**
	 * Testet, ob das Spielfeld korrekt generiert wird mit Schwierigkeitsgrad
	 * schwer.
	 */
	public void testGeneriereSpielfeldSchwer() {
		ISpielfeld spielfeld = new Spielfeld_Comparison();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.schwer;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals("Test ob Spielfeld generiert wurde mit Schwer-Feldern: ",
				10, felder);
	}

	/**
	 * Testet, ob die Zeichen "<" und ">" im Spielfeld richtig gesetzt werden.
	 */
	public void testSetzeZeichen() {
		ISpielfeld spielfeld = new Spielfeld_Comparison();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.anfaenger;
		spielfeld.generiereSpielfeld(s);

		int anzahl = ((Spielfeld_Comparison) spielfeld).gibZeichen().size();
		boolean inhaltVorhanden = !(anzahl == 0);
		assertEquals("Testet auf nicht leeren Inhalt", true, inhaltVorhanden);
	}

	/**
	 * Testet, ob das Spielfeld korrekt initialisiert wird.
	 */
	public void testSpielfeld_Comparison() {
		ISpielfeld spielfeld = new Spielfeld_Comparison();
		int breite = spielfeld.gibSpielfeldBreite()
				* spielfeld.gibBlockBreite();
		int hoehe = spielfeld.gibSpielfeldHoehe() * spielfeld.gibBlockHoehe();
		assertEquals("Spielfeldbreite", 9, breite);
		assertEquals("Spielfeldhoehe", 9, hoehe);
	}

}
