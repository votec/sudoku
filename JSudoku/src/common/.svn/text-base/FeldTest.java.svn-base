package common;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Die Klasse FeldTest testet die Methoden der Klasse Feld.
 * 
 * @author Sandra Gothan
 *
 */
public class FeldTest extends TestCase {

	/**
	 * Testfall fuer einen leeren Konstruktor, testet die Standardwerte.
	 */
	@Test
	public void testFeld() {
		Feld feld = new Feld();
		Koordinate koordinate = new Koordinate();
		assertEquals(" Test von Konstruktor Feld auf Inhalt ", 0, feld
				.gibInhalt());
		assertEquals(" Test von Konstruktor Feld auf IstInStartbelegung ",
				false, feld.istInStartbelegung());
		assertEquals(" Test von Konstruktor Feld auf Koordinate: ", koordinate
				.gibX(), feld.gibKoordinate().gibX());
		assertEquals(" Test von Konstruktor Feld auf Koordinate: ", koordinate
				.gibY(), feld.gibKoordinate().gibY());
	}

	/**
	 * Testfall fuer einen Konstruktor mit Koordinate eines Feldes.
	 */
	@Test
	public void testFeld_Koordinate() {
		Koordinate koordinate = new Koordinate();
		Feld feld = new Feld(koordinate);
		assertEquals(" Test von Konstruktor Feld auf Inhalt ", 0, feld
				.gibInhalt());
		assertEquals(" Test von Konstruktor Feld auf IstInStartbelegung ",
				false, feld.istInStartbelegung());
		assertEquals(" Test von Konstruktor Feld auf Koordinate: ", koordinate,
				feld.gibKoordinate());
	}

	/**
	 * Testfall fuer einen Konstruktor mit Koordinate und Inhalt eines Feldes.
	 */
	@Test
	public void testFeld_KoordinateUndInhalt() {
		int x = 1;
		Koordinate koordinate = new Koordinate();
		Feld feld = new Feld(koordinate, x);
		assertEquals(" Test von Konstruktor Feld auf Inhalt ", x, feld
				.gibInhalt());
		assertEquals(" Test von Konstruktor Feld auf IstInStartbelegung ",
				false, feld.istInStartbelegung());
		assertEquals(" Test von Konstruktor Feld auf Koordinate: ", koordinate,
				feld.gibKoordinate());
	}

	/**
	 * Testfall fuer einen Konstruktor mit Koordinate und zu grossem Inhalt
	 * eines Feldes.
	 */
	@Test
	public void testFeld_KoordinateUndInhalt_falsch_groesser() {
		int x = 17;
		Koordinate koordinate = new Koordinate();
		Feld feld = new Feld(koordinate, x);
		assertEquals(" Test von Konstruktor Feld auf Inhalt ", 0, feld
				.gibInhalt());
		assertEquals(" Test von Konstruktor Feld auf IstInStartbelegung ",
				false, feld.istInStartbelegung());
		assertEquals(" Test von Konstruktor Feld auf Koordinate: ", koordinate,
				feld.gibKoordinate());
	}

	/**
	 * Testfall fuer einen Konstruktor mit Koordinate und zu kleinem Inhalt
	 * eines Feldes.
	 */
	@Test
	public void testFeld_KoordinateUndInhalt_falsch_kleiner() {
		int x = -1;
		Koordinate koordinate = new Koordinate();
		Feld feld = new Feld(koordinate, x);
		assertEquals(" Test von Konstruktor Feld auf Inhalt ", 0, feld
				.gibInhalt());
		assertEquals(" Test von Konstruktor Feld auf IstInStartbelegung ",
				false, feld.istInStartbelegung());
		assertEquals(" Test von Konstruktor Feld auf Koordinate: ", koordinate,
				feld.gibKoordinate());
	}

	/**
	 * Testfall fuer einen Konstruktor mit Koordinate,Inhalt und Startbelegung
	 * eines Feldes.
	 */
	@Test
	public void testFeld_KoordinateUndInhaltUndStartbelegung() {
		int x = 1;
		boolean s = true;
		Koordinate koordinate = new Koordinate();
		Feld feld = new Feld(koordinate, x, s);
		assertEquals(" Test von Konstruktor Feld auf Inhalt ", x, feld
				.gibInhalt());
		assertEquals(" Test von Konstruktor Feld auf IstInStartbelegung ", s,
				feld.istInStartbelegung());
		assertEquals(" Test von Konstruktor Feld auf Koordinate: ", koordinate,
				feld.gibKoordinate());
	}

	/**
	 * Testfall fuer Auslesen des Inhalts eines Feldes.
	 */
	@Test
	public void testGibInhalt() {
		int x = 0;
		Feld feld = new Feld();
		feld.setzeInhalt(x);
		assertEquals(" Test gib Inhalt von Feld: ", x, feld.gibInhalt());
	}

	/**
	 * Testfall fuer Auslesen, ob Feld in Startbelegung ist.
	 */
	@Test
	public void testIstInStartbelegung() {
		boolean x = true;
		Feld feld = new Feld();
		feld.setzeStartbelegung(x);
		assertEquals(" Test gib IstInStartbelegung von Feld: ", x, feld
				.istInStartbelegung());
	}

	/**
	 * Testfall, ob Feld leer ist.
	 */
	@Test
	public void testIstLeerTrue() {
		Feld feld = new Feld();
		assertEquals(" Test ist Feld leer ", true, feld.istLeer());
	}

	/**
	 * Testfall, ob Feld leer ist -> Feld aber nicht leer.
	 */
	@Test
	public void testIstLeerFalse() {
		Koordinate koordinate = new Koordinate(1, 1);
		Feld feld = new Feld(koordinate, 1);
		assertEquals(" Test ist Feld leer ", false, feld.istLeer());
	}

	/**
	 * Testfall fuer Auslesen des Inhalts eines Feldes.
	 */
	@Test
	public void testSetzeInhalt() {
		int x = 1;
		Feld feld = new Feld();
		feld.setzeInhalt(x);
		feld.setzeInhalt(x + 1);
		assertEquals(" Test setze Inhalt von Feld: ", x + 1, feld.gibInhalt());
	}

	/**
	 * Testfall fuer Setzen eines zu grossen falschen Inhalts eines Feldes.
	 */
	@Test
	public void testSetzeInhalt_falsch_groesser() {
		int x = 16;
		Feld feld = new Feld();
		feld.setzeInhalt(x);
		feld.setzeInhalt(x + 1);
		assertEquals(" Test setze falschen (groesseren) Inhalt von Feld: ", x,
				feld.gibInhalt());
	}

	/**
	 * Testfall fuer Setzen eines zu grossen kleinen Inhalts eines Feldes.
	 */
	@Test
	public void testSetzeInhalt_falsch_kleiner() {
		int x = 0;
		Feld feld = new Feld();
		feld.setzeInhalt(x);
		feld.setzeInhalt(x - 1);
		assertEquals(" Test setze kleiner (kleineren) Inhalt von Feld: ", x,
				feld.gibInhalt());
	}

	/**
	 * Testfall fuer Setzen einer Koordinate eines Feldes.
	 */
	@Test
	public void testSetzeKoordinate() {
		int n = 1;
		Koordinate koordinate = new Koordinate();
		koordinate.setzeX(n);
		koordinate.setzeY(n);
		Feld feld = new Feld();
		feld.setzeKoordinate(koordinate);
		koordinate.setzeX(n + 1);
		koordinate.setzeY(n + 1);
		feld.setzeKoordinate(koordinate);
		assertEquals(" Test setze Koordinate: ", koordinate, feld
				.gibKoordinate());
	}

	/**
	 * Testfall fuer Auslesen der Koordinate eines Feldes.
	 */
	@Test
	public void testGibKoordinate() {
		int n = 1;
		Koordinate koordinate = new Koordinate();
		koordinate.setzeX(n);
		koordinate.setzeY(n);
		Feld feld = new Feld();
		feld.setzeKoordinate(koordinate);
		assertEquals(" Test setze Koordinate: ", koordinate, feld
				.gibKoordinate());
	}
}
