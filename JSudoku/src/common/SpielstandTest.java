package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import common.EnumContainer.*;

/**
 * Die Klasse ClientTest testet die Methoden der Klasse Client.
 * 
 * @author Arne Busch
 * 
 */
public class SpielstandTest extends TestCase {

	/**
	 * Testet, ob ein Spielfeld korrekt hinzugefuegt wird.
	 */
	@Test
	public void testFuegeSpielfeldHinzu() {
		Spielstand spielstand = new Spielstand();
		Farbe farbe = Farbe.rot;
		ISpielfeld spielfeld = new Spielfeld_Standard();
		HashMap<Farbe, ISpielfeld> spielfelder = new HashMap<Farbe, ISpielfeld>();

		spielfelder.put(farbe, spielfeld);
		spielstand.fuegeSpielfeldHinzu(farbe, spielfeld);
		assertEquals(spielfelder, spielstand.gibSpielfelder());
	}

	/**
	 * Testet, ob die Anzahl der Spieler korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibAnzahlSpieler() {
		Spielstand spielstand = new Spielstand();
		Farbe farbe1 = Farbe.rot;
		ISpielfeld spielfeld1 = new Spielfeld_Standard();
		Farbe farbe2 = Farbe.blau;
		ISpielfeld spielfeld2 = new Spielfeld_Standard();

		spielstand.fuegeSpielfeldHinzu(farbe1, spielfeld1);
		assertEquals(1, spielstand.gibAnzahlSpieler());
		spielstand.fuegeSpielfeldHinzu(farbe2, spielfeld2);
		assertEquals(2, spielstand.gibAnzahlSpieler());
	}

	/**
	 * Testet, ob der Schwierigkeitsgrad korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSchwierigkeitsgrad() {
		Spielstand spielstand = new Spielstand();
		Schwierigkeitsgrad schwierigkeitsgrad = Schwierigkeitsgrad.anfaenger;

		spielstand.setzeSchwierigkeitsgrad(schwierigkeitsgrad);
		assertEquals(schwierigkeitsgrad, spielstand.gibSchwierigkeitsgrad());
	}

	/**
	 * Testet, ob die Spielerfarben korrekt zurueckgegeben werden.
	 */
	@Test
	public void testGibSpielerfarben() {
		Spielstand spielstand = new Spielstand();
		Farbe farbe = Farbe.rot;
		ISpielfeld spielfeld = new Spielfeld_Standard();
		List<Farbe> spielerfarben = new ArrayList<Farbe>();

		spielstand.fuegeSpielfeldHinzu(farbe, spielfeld);
		spielerfarben.add(farbe);
		assertEquals(spielerfarben, spielstand.gibSpielerfarben());
	}

	/**
	 * Testet, ob ein Spielfeld korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSpielfeld() {
		Spielstand spielstand = new Spielstand();
		Farbe farbe = Farbe.rot;
		ISpielfeld spielfeld = new Spielfeld_Standard();

		spielstand.fuegeSpielfeldHinzu(farbe, spielfeld);
		assertEquals(spielfeld, spielstand.gibSpielfeld(farbe));
	}

	/**
	 * Testet, ob die Spielfeldloesung korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSpielfeldloesung() {
		Spielstand spielstand = new Spielstand();
		ISpielfeld spielfeld = new Spielfeld_Standard();

		spielstand.setzeSpielfeldloesung(spielfeld);
		assertEquals(spielfeld, spielstand.gibSpielfeldloesung());
	}

	/**
	 * Testet, ob der Spielmodus korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSpielmodus() {
		Spielstand spielstand = new Spielstand();
		Spielmodus spielmodus = Spielmodus.einzelspieler;

		spielstand.setzeSpielmodus(spielmodus);
		assertEquals(spielmodus, spielstand.gibSpielmodus());
	}

	/**
	 * Testet, ob die Spielvariante korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSpielvariante() {
		Spielstand spielstand = new Spielstand();
		Spielvariante spielvariante = Spielvariante.comparison;

		spielstand.setzeSpielvariante(spielvariante);
		assertEquals(spielvariante, spielstand.gibSpielvariante());
	}

	/**
	 * Testet, ob der Schwierigkeitsgrad korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeSchwierigkeitsgrad() {
		Spielstand spielstand = new Spielstand();
		Schwierigkeitsgrad schwierigkeitsgrad = Schwierigkeitsgrad.anfaenger;

		spielstand.setzeSchwierigkeitsgrad(schwierigkeitsgrad);
		assertEquals(schwierigkeitsgrad, spielstand.gibSchwierigkeitsgrad());
	}

	/**
	 * Testet, ob die Spielfeldloesung korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeSpielfeldloesung() {
		Spielstand spielstand = new Spielstand();
		ISpielfeld spielfeld = new Spielfeld_Standard();

		spielstand.setzeSpielfeldloesung(spielfeld);
		assertEquals(spielfeld, spielstand.gibSpielfeldloesung());
	}

	/**
	 * Testet, ob der Spielmodus korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeSpielmodus() {
		Spielstand spielstand = new Spielstand();
		Spielmodus spielmodus = Spielmodus.einzelspieler;

		spielstand.setzeSpielmodus(spielmodus);
		assertEquals(spielmodus, spielstand.gibSpielmodus());
	}

	/**
	 * Testet, ob die Spielvariante korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeSpielvariante() {
		Spielstand spielstand = new Spielstand();
		Spielvariante spielvariante = Spielvariante.comparison;

		spielstand.setzeSpielvariante(spielvariante);
		assertEquals(spielvariante, spielstand.gibSpielvariante());
	}

}
