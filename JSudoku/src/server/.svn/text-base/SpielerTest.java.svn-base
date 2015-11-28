package server;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import common.IKoordinate;
import common.ISpielfeld;
import common.Koordinate;
import common.Spielfeld_Standard;
import common.EnumContainer.Farbe;

/**
 * Die Klasse SpielerTest testet die Methoden der Klasse Spieler.
 * 
 * @author Thomas Fraenkler
 * 
 */
public class SpielerTest extends TestCase {

	/** die Farbe eines Spielers */
	private Farbe farbe;
	/** eine Koordinate im Spielfeld */
	private Koordinate koordinate;
	/** der Name eines Spielers */
	private String name;
	/** das Spielfeld eines Spielers */
	private ISpielfeld spielfeld;

	/**
	 * Testet, ob die Anzahl der gesetzten Felder eines Spielers korrekt
	 * zurueckgegeben wird.
	 */
	@Test
	public void testGibAnzahlGesetzeFelder() {
		name = "Spieler 1";
		farbe = Farbe.rot;
		ISpielfeld spielfeld = new Spielfeld_Standard();
		Spieler spieler = new Spieler(name, farbe, spielfeld);

		assertEquals(" Test AnzahlGesetzterFelder: ", 0, spieler
				.gibAnzahlGesetzteFelder());
	}

	/**
	 * Testet, ob die Farbe eines Spielers korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibFarbe() {
		name = "Spieler 1";
		farbe = Farbe.rot;
		spielfeld = new Spielfeld_Standard();
		Spieler spieler = new Spieler(name, farbe, spielfeld);

		assertEquals(" Test Spielerfarbe: ", farbe, spieler.gibFarbe());
	}

	/**
	 * Testet, ob die Feldmoeglichkeiten eines Spielers korrekt zurueckgegeben
	 * werden.
	 */
	@Test
	public void testGibFeldMoeglichkeiten() {
		name = "Spieler 1";
		farbe = Farbe.rot;
		spielfeld = new Spielfeld_Standard();
		Spieler spieler = new Spieler(name, farbe, spielfeld);
		ISpielfeld testspielfeld = new Spielfeld_Standard();
		koordinate = new Koordinate(1,1);
		List<Integer> feldMoeglichkeiten = testspielfeld
				.gibFeldMoeglichkeiten(koordinate);

		assertEquals(" Test GibFeldMoeglichkeiten: ", feldMoeglichkeiten,
				spieler.gibFeldMoeglichkeiten(koordinate));
	}

	/**
	 * Testet, ob die Konfliktfelder eines Spielers korrekt zurueckgegeben
	 * werden.
	 */
	@Test
	public void testGibKonfliktfelder() {
		name = "Spieler 1";
		farbe = Farbe.rot;
		spielfeld = new Spielfeld_Standard();
		Spieler spieler = new Spieler(name, farbe, spielfeld);
		ISpielfeld testspielfeld = new Spielfeld_Standard();
		List<IKoordinate> konfliktfelder = testspielfeld.gibKonfliktfelder();

		assertEquals(" Test GibFeldMoeglichkeiten: ", konfliktfelder, spieler
				.gibKonfliktfelder());
	}

	/**
	 * Testet, ob der Name eines Spielers korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibName() {
		name = "Spieler 1";
		farbe = Farbe.rot;
		spielfeld = new Spielfeld_Standard();
		Spieler spieler = new Spieler(name, farbe, spielfeld);

		assertEquals(" Test Spielername: ", name, spieler.gibName());
	}

	/**
	 * Testet, ob das Spielfeld eines Spielers korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSpielfeld() {
		name = "Spieler 1";
		farbe = Farbe.rot;
		spielfeld = new Spielfeld_Standard();
		Spieler spieler = new Spieler(name, farbe, spielfeld);

		assertEquals(" Test GibSpielfeld: ", spielfeld, spieler.gibSpielfeld());
	}

	/**
	 * Testet, ob eine Ziffer korrekt geloescht wird.
	 */
	@Test
	public void testLoescheZiffer() {
		name = "Spieler 1";
		farbe = Farbe.rot;
		spielfeld = new Spielfeld_Standard();
		Spieler spieler = new Spieler(name, farbe, spielfeld);
		ISpielfeld testSpielfeld = new Spielfeld_Standard();
		koordinate = new Koordinate(1,1);
		testSpielfeld.loescheZiffer(koordinate);
		spieler.gibSpielfeld().loescheZiffer(koordinate);
		assertEquals(" Test GibSpielfeld: ", testSpielfeld
				.gibFeldinhalt(koordinate), spieler.gibSpielfeld()
				.gibFeldinhalt(koordinate));
	}

	/**
	 * Testet, ob eine Ziffer korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeZiffer() {
		name = "Spieler 1";
		farbe = Farbe.rot;
		spielfeld = new Spielfeld_Standard();
		Spieler spieler = new Spieler(name, farbe, spielfeld);
		ISpielfeld testSpielfeld = new Spielfeld_Standard();
		koordinate = new Koordinate(1,1);
		testSpielfeld.setzeZiffer(koordinate, 0);
		spieler.setzeZiffer(koordinate, 0);
		assertEquals(" Test setzeZiffer: ", testSpielfeld
				.gibFeldinhalt(koordinate), spieler.gibSpielfeld()
				.gibFeldinhalt(koordinate));
	}

}
