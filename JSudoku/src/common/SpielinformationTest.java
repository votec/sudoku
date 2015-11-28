package common;

import junit.framework.TestCase;

import org.junit.Test;

import common.EnumContainer.*;

/**
 * Die Klasse SpielinformationTest testet die Methoden der Klasse
 * Spielinformation.
 * 
 * @author Heiko Schroeder
 * 
 */
public class SpielinformationTest extends TestCase {

	/**
	 * Testet, ob der Schwierigkeitsgrad korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSchwierigkeitsgrad() {
		Schwierigkeitsgrad g = Schwierigkeitsgrad.mittel;
		Spielinformation s = new Spielinformation();

		s.setzeSchwierigkeitsgrad(g);
		assertEquals("Teste gibSchwierigkeitsgrad", g.toString(), s
				.gibSchwierigkeitsgrad().toString());
	}

	/**
	 * Testet, ob die Spieleranzahl korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSpieleranzahl() {
		Spielinformation s = new Spielinformation();

		s.fuegeSpielernamenHinzu("Spieler 1");
		s.fuegeSpielernamenHinzu("Spieler 2");
		s.fuegeSpielernamenHinzu("Spieler 3");
		assertEquals("Teste gibSpieleranzahl", 3, s.gibSpieleranzahl());
	}

	/**
	 * Testet, ob die SpielID korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSpielID() {
		int id = 5;
		Spielinformation s = new Spielinformation();

		s.setzeSpielID(id);
		assertEquals("Teste gibSpielID", id, s.gibSpielID());
	}

	/**
	 * Testet, ob die Spielvariante korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibSpielvariante() {
		Spielvariante v = Spielvariante.fudschijama4x3;
		Spielinformation s = new Spielinformation();

		s.setzeSpielvariante(v);
		assertEquals("Teste gibSpielvariante", v.toString(), s.gibSpielvariante().toString());
	}

	/**
	 * Testet, ob die Strafzeit korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibStrafzeit() {
		int t = 50;
		Spielinformation s = new Spielinformation();

		s.setzeStrafzeit(t);
		assertEquals("Teste gibStrafzeit", t, s.gibStrafzeit());
	}

	/**
	 * Testet, ob der Schwierigkeitsgrad korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeSchwierigkeitsgrad() {
		Schwierigkeitsgrad g1 = Schwierigkeitsgrad.mittel;
		Schwierigkeitsgrad g2 = Schwierigkeitsgrad.schwer;
		Spielinformation s = new Spielinformation();

		s.setzeSchwierigkeitsgrad(g1);
		s.setzeSchwierigkeitsgrad(g2);
		assertEquals("Teste setzeSchwierigkeitsgrad", g2.toString(), s
				.gibSchwierigkeitsgrad().toString());
	}

	/**
	 * Testet, ob die SpielID korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeSpielID() {
		int id1 = 5;
		int id2 = 10;
		Spielinformation s = new Spielinformation();

		s.setzeSpielID(id1);
		s.setzeSpielID(id2);
		assertEquals("Teste gibSpielID positiv", id2, s.gibSpielID());
	}

	/**
	 * Testet, ob die Spielvariante korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeSpielvariante() {
		Spielvariante v1 = Spielvariante.fudschijama4x3;
		Spielvariante v2 = Spielvariante.standard;
		Spielinformation s = new Spielinformation();

		s.setzeSpielvariante(v1);
		s.setzeSpielvariante(v2);
		assertEquals("Teste setzeSpielvariante", v2.toString(), s.gibSpielvariante().toString());
	}

	/**
	 * Testet, ob die Strafzeit korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeStrafzeit() {
		int t1 = 50;
		int t2 = 100;
		Spielinformation s = new Spielinformation();

		s.setzeStrafzeit(t1);
		s.setzeStrafzeit(t2);
		assertEquals("Teste setzeStrafzeit", t2, s.gibStrafzeit());
	}

}
