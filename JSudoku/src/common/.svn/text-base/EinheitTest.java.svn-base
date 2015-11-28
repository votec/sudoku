package common;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Die Klasse EinheitTest testet die Methoden der Klasse Einheit.
 * 
 * @author Thomas Fraenkler
 * 
 */
public class EinheitTest extends TestCase {

	/**
	 * Testfall, ob Felder in Einheiten eingefuegt werden.
	 */
	@Test
	public void testFuegeFeldhinzu() {
		Einheit einheit = new Einheit();
		Feld feld = new Feld();
		einheit.fuegeFeldHinzu(feld);
		assertEquals("Test Feld hinzufuegen:", true, einheit
				.gibKonfliktfelder().isEmpty());
	}

	/**
	 * Testfall, ob Konfliktfelder erkannt werden, die nicht in Startbelegung
	 * sind.
	 */
	@Test
	public void testGibKonfliktfelder() {
		Einheit einheit = new Einheit();
		IKoordinate koordinate1 = new Koordinate(1, 1);
		IKoordinate koordinate2 = new Koordinate(2, 1);
		IFeld feld1 = new Feld(koordinate1, 1, false);
		IFeld feld2 = new Feld(koordinate2, 1, true);
		einheit.fuegeFeldHinzu(feld1);
		einheit.fuegeFeldHinzu(feld2);
		assertEquals("Test GibKonfliktfelder", 1, einheit.gibKonfliktfelder()
				.size());
	}

	/**
	 * Testfall, ob Konfliktfelder erkannt werden, die in Startbelegung sind.
	 */
	@Test
	public void testGibKonfliktfelder_negativ() {
		IEinheit einheit = new Einheit();

		for (int i = 1; i <= 8; i++) {
			IFeld feld = new Feld();
			feld.setzeInhalt(i);
			feld.setzeStartbelegung(true);
			einheit.fuegeFeldHinzu(feld);
		}

		IFeld feld = new Feld();
		feld.setzeInhalt(5);
		einheit.fuegeFeldHinzu(feld);

		assertEquals("Test GibKonfliktfelder", 1, einheit.gibKonfliktfelder()
				.size());
	}

	/**
	 * Testfall fuer die Rueckgabe der Koordinaten aller eingetragenen Felder.
	 */
	@Test
	public void testGibKoordinatenAllerFelder() {
		IEinheit einheit = new Einheit();
		IKoordinate koordinate = new Koordinate(1, 1);
		IFeld feld = new Feld(koordinate, 1);
		einheit.fuegeFeldHinzu(feld);
		ArrayList<IKoordinate> liste = einheit.gibKoordinatenAllerFelder();
		assertEquals("Test auf richtige Koordinaten: ", koordinate, liste
				.get(0));
	}

	/**
	 * Testfall, ob ein eingefuegtes Feld mit Inhalt schon in der Einheit
	 * enthalten ist.
	 */
	@Test
	public void testpruefeZahl() {
		Einheit einheit = new Einheit();
		Feld feld1 = new Feld();
		feld1.setzeInhalt(5);
		Feld feld2 = new Feld();
		feld2.setzeInhalt(2);
		Feld feld3 = new Feld();
		feld3.setzeInhalt(8);
		Feld feld4 = new Feld();
		feld4.setzeInhalt(4);

		einheit.fuegeFeldHinzu(feld1);
		einheit.fuegeFeldHinzu(feld2);
		einheit.fuegeFeldHinzu(feld3);
		einheit.fuegeFeldHinzu(feld4);

		assertEquals("Test pruefe Zahl:", true, einheit.pruefeZahl(5));
		assertEquals("Test pruefe Zahl:", true, einheit.pruefeZahl(2));
		assertEquals("Test pruefe Zahl:", true, einheit.pruefeZahl(8));
		assertEquals("Test pruefe Zahl:", true, einheit.pruefeZahl(4));
		assertEquals("Test pruefe Zahl:", false, einheit.pruefeZahl(3));
		assertEquals("Test pruefe Zahl:", false, einheit.pruefeZahl(6));
		assertEquals("Test pruefe Zahl:", false, einheit.pruefeZahl(7));
		assertEquals("Test pruefe Zahl:", false, einheit.pruefeZahl(1));
		assertEquals("Test pruefe Zahl:", false, einheit.pruefeZahl(9));
	}

	/**
	 * Testfall, ob ein eingefuegtes Feld mit Inhalt schon in der Einheit
	 * enthalten ist.
	 */
	@Test
	public void testpruefeZahl_negativ() {
		IEinheit einheit = new Einheit();

		for (int i = 1; i <= 9; i++) {
			IFeld feld = new Feld();
			feld.setzeInhalt(i);
			feld.setzeStartbelegung(true);
			einheit.fuegeFeldHinzu(feld);
		}

		for (int i = 1; i <= 9; i++) {
			assertEquals("Test pruefe Zahl:", true, einheit.pruefeZahl(i));
		}
	}
}
