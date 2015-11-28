package common;

import junit.framework.TestCase;

/**
 * Die Klasse Spielfeld_StandardTest testet die Methoden der Klasse
 * Spielfeld_Standard.
 * 
 * @author Heiko Schroeder
 * 
 */
public class Spielfeld_StandardTest extends TestCase {

	/**
	 * Testet, ob das Spielfeld im Schwierigkeitsgrad "Anfaenger" korrekt
	 * generiert wird.
	 */
	public void testGeneriereSpielfeldAnfaenger() {
		ISpielfeld spielfeld = new Spielfeld_Standard();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.anfaenger;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 39) && (felder <= 42);
		assertEquals("Test auf Konfliktfelder", 0, spielfeld.gibKonfliktfelder().size());
		assertEquals("Test ob Spielfeld generiert wurde mit anzahlFelder = anfaenger"+felder,true, anzahl);
	}

	/**
	 * Testet, ob ein Spielfeld im Schwierigkeitsgrad "Knifflig" korrekt
	 * generiert wird.
	 */
	public void testGeneriereSpielfeldKnifflig() {
		ISpielfeld spielfeld = new Spielfeld_Standard();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.knifflig;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 30) && (felder <= 34);
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals(
				"Test ob Spielfeld generiert wurde mit anzahlFelder = knifflig",
				true, anzahl);
	}

	/**
	 * Testet, ob ein Spielfeld im Schwierigkeitsgrad "Mittel" korrekt generiert
	 * wird.
	 */
	public void testGeneriereSpielfeldMittel() {
		ISpielfeld spielfeld = new Spielfeld_Standard();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.mittel;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 35) && (felder <= 38);
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals(
				"Test ob Spielfeld generiert wurde mit anzahlFelder = mittel",
				true, anzahl);
	}

	/**
	 * Testet, ob ein Spielfeld im Schwierigkeitsgrad "Profi" korrekt generiert
	 * wird.
	 */
	public void testGeneriereSpielfeldProfi() {
		ISpielfeld spielfeld = new Spielfeld_Standard();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.profi;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 22) && (felder <= 25);
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals(
				"Test ob Spielfeld generiert wurde mit anzahlFelder = schwer",
				true, anzahl);
	}

	/**
	 * Testet, ob ein Spielfeld im Schwierigkeitsgrad "Schwer" korrekt generiert
	 * wird.
	 */
	public void testGeneriereSpielfeldSchwer() {
		ISpielfeld spielfeld = new Spielfeld_Standard();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.schwer;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 26) && (felder <= 29);
		assertEquals("Test auf Konfliktfelder", 0, spielfeld.gibKonfliktfelder().size());
		assertEquals(
				"Test ob Spielfeld generiert wurde mit anzahlFelder = schwer",
				true, anzahl);
	}

	/**
	 * Testet, ob das Spielfeld korrekt initialisiert wird.
	 */
	public void testSpielfeld_Standard() {
		ISpielfeld spielfeld = new Spielfeld_Standard();
		int breite = spielfeld.gibSpielfeldBreite()
				* spielfeld.gibBlockBreite();
		int hoehe = spielfeld.gibSpielfeldHoehe() * spielfeld.gibBlockHoehe();
		assertEquals("Spielfeldbreite", 9, breite);
		assertEquals("Spielfeldhoehe", 9, hoehe);
	}

}
