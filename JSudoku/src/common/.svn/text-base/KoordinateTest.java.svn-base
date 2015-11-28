package common;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Die Klasse KoordinateTest testet die Methoden der Klasse Koordinate.
 * 
 * @author Sandra Gothan
 */
public class KoordinateTest extends TestCase {

	/**
	 * Testfall fuer Auslesen der x-Koordinate.
	 */
	@Test
	public void testGibX() {
		int x = 0;
		Koordinate koordinate = new Koordinate();
		koordinate.setzeX(x);
		assertEquals(" Test gib x-Koordinate: ", x, koordinate.gibX());
	}

	/**
	 * Testfall fuer Auslesen der y-Koordinate.
	 */
	@Test
	public void testGibY() {
		int y = 0;
		Koordinate koordinate = new Koordinate();
		koordinate.setzeY(y);
		assertEquals(" Test gib y-Koordinate: ", y, koordinate.gibY());
	}

	/**
	 * Testfall fuer den Konstruktor mit Koordinaten.
	 */
	@Test
	public void testKoordinateXY() {
		int x = 1;
		int y = 1;
		Koordinate koordinate = new Koordinate(x, y);
		assertEquals(" Test gib x-Koordinate: ", x, koordinate.gibX());
		assertEquals(" Test gib y-Koordinate: ", y, koordinate.gibY());
	}

	/**
	 * Testfall fuer Setzen der x-Koordinate.
	 */
	@Test
	public void testSetzeX() {
		int x = 0;
		Koordinate koordinate = new Koordinate();
		koordinate.setzeX(x);
		koordinate.setzeX(x + 1);
		assertEquals(" Test setze x-Koordinate: ", x + 1, koordinate.gibX());
	}

	/**
	 * Testfall fuer negatives Setzen der x-Koordinate.
	 */
	@Test
	public void testSetzeXNegativ() {
		int x = 0;
		Koordinate koordinate = new Koordinate();
		koordinate.setzeX(x);
		koordinate.setzeX(x - 1);
		assertEquals(" Test setze x-Koordinate: ", x, koordinate.gibX());
	}

	/**
	 * Testfall fuer Setzen der y-Koordinate.
	 */
	@Test
	public void testSetzeY() {
		int y = 0;
		Koordinate koordinate = new Koordinate();
		koordinate.setzeY(y);
		koordinate.setzeY(y + 1);
		assertEquals(" Test setze y-Koordinate: ", y + 1, koordinate.gibY());
	}

	/**
	 * Testfall fuer negatives Setzen der y-Koordinate.
	 */
	@Test
	public void testSetzeYNegativ() {
		int y = 0;
		Koordinate koordinate = new Koordinate();
		koordinate.setzeY(y);
		koordinate.setzeY(y - 1);
		assertEquals(" Test setze y-Koordinate: ", y, koordinate.gibY());
	}

}
