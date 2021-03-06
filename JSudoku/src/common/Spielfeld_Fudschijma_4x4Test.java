package common;

import junit.framework.TestCase;

/**
 * Die Klasse Spielfeld_Fudschijma_4x4Test testet die Methoden der Klasse
 * Spielfeld_Fudschijma_4x4.
 * 
 * @author Heiko Schroeder
 * 
 */
public class Spielfeld_Fudschijma_4x4Test extends TestCase {

	/**
	 * Testet, ob ein Spielfeld im Schwierigkeitsgrad "Anfaenger" korrekt
	 * generiert wird.
	 */
	public void testGeneriereSpielfeldAnfaenger() {
		ISpielfeld spielfeld = new Spielfeld_Fudschijma_4x4();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.anfaenger;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 123) && (felder <= 134);
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals(
				"Test ob Spielfeld generiert wurde mit anzahlFelder = anfaenger",
				true, anzahl);

		/*
		 * int spielfeldBreite = spielfeld.gibBlockBreite()
		 * spielfeld.gibSpielfeldBreite(); int spielfeldHoehe =
		 * spielfeld.gibBlockHoehe() spielfeld.gibSpielfeldHoehe(); // Gib
		 * Spielfeld aus for (int i = 0; i < spielfeldHoehe; i++) { for (int j =
		 * 0; j < spielfeldBreite; j++) { IKoordinate koordinate = new
		 * Koordinate(j + 1, i + 1); IFeld feld = spielfeld.gibFeld(koordinate);
		 * if (feld.gibInhalt() < 10) { System.out.print(feld.gibInhalt() + "
		 * "); } else { System.out.print(feld.gibInhalt() + " "); } if (j == 3 ||
		 * j == 7 || j == 11) { System.out.print(" | "); } } if (i == 3 || i ==
		 * 7 || i == 11) { System.out
		 * .println("\n------------------------------------------------"); }
		 * else { System.out.print("\n"); } }
		 */
	}

	/**
	 * Testet, ob ein Spielfeld im Schwierigkeitsgrad "Knifflig" korrekt
	 * generiert wird.
	 */
	public void testGeneriereSpielfeldKnifflig() {
		ISpielfeld spielfeld = new Spielfeld_Fudschijma_4x4();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.knifflig;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 95) && (felder <= 107);
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
		ISpielfeld spielfeld = new Spielfeld_Fudschijma_4x4();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.mittel;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 111) && (felder <= 120);
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
		ISpielfeld spielfeld = new Spielfeld_Fudschijma_4x4();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.profi;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 70) && (felder <= 79);
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals(
				"Test ob Spielfeld generiert wurde mit anzahlFelder = profi",
				true, anzahl);
	}

	/**
	 * Testet, ob ein Spielfeld im Schwierigkeitsgrad "Schwer" korrekt generiert
	 * wird.
	 */
	public void testGeneriereSpielfeldSchwer() {
		ISpielfeld spielfeld = new Spielfeld_Fudschijma_4x4();

		EnumContainer.Schwierigkeitsgrad s = EnumContainer.Schwierigkeitsgrad.schwer;
		spielfeld.generiereSpielfeld(s);

		int felder = spielfeld.gibAnzahlGesetzteFelder();
		boolean anzahl = (felder >= 82) && (felder <= 92);
		assertEquals("Test auf Konfliktfelder", 0, spielfeld
				.gibKonfliktfelder().size());
		assertEquals(
				"Test ob Spielfeld generiert wurde mit anzahlFelder = schwer",
				true, anzahl);
	}

	/**
	 * Testet, ob das Spielfeld korrekt initialisiert wird.
	 */
	public void testSpielfeld_Fudschijma_4x4() {
		ISpielfeld spielfeld = new Spielfeld_Fudschijma_4x4();
		int breite = spielfeld.gibSpielfeldBreite()
				* spielfeld.gibBlockBreite();
		int hoehe = spielfeld.gibSpielfeldHoehe() * spielfeld.gibBlockHoehe();
		assertEquals("Spielfeldbreite", 16, breite);
		assertEquals("Spielfeldhoehe", 16, hoehe);
	}
}
