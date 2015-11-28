package server;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import common.IKoordinate;
import common.ISpielfeld;
import common.ISpielstand;
import common.Koordinate;
import common.Spielstand;
import common.EnumContainer.*;

/**
 * Die Klasse SpielTest testet die Methoden der Klasse Spiel.
 * 
 * @author Sandra Gothan
 * 
 */
public class SpielTest extends TestCase {

	/**
	 * Testfall fuer einen angemeldeten Spieler im Einzelspielermodus.
	 */
	@Test
	public void testGenuegendSpielerAngemeldet_Einzelspiel() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Harry", Farbe.blau);
		assertEquals(
				" Test ob ein Spieler im Einzelspielermodus angemeldet ist: ",
				true, spiel.genuegendSpielerAngemeldet());
	}

	/**
	 * Testfall fuer einen angemeldeten Spieler im Mehrspielermodus.
	 */
	@Test
	public void testGenuegendSpielerAngemeldet_Mehrspiel_AnzSpieler1() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Harry", Farbe.blau);
		assertEquals(
				" Test ob genuegend Spieler im Mehrspielermodus angemeldet sind: ",
				false, spiel.genuegendSpielerAngemeldet());
	}

	/**
	 * Testfall fuer zwei angemeldete Spieler im Mehrspielermodus.
	 */
	@Test
	public void testGenuegendSpielerAngemeldet_Mehrspiel_AnzSpieler2() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Harry", Farbe.blau);
		spiel.spielerAnmelden("Heinz", Farbe.gelb);
		assertEquals(
				" Test ob genuegend Spieler im Mehrspielermodus angemeldet sind: ",
				true, spiel.genuegendSpielerAngemeldet());
	}

	/**
	 * Testfall fuer das Auslesen des aktuellen Punktestand eines Spiels.
	 */
	@Test
	public void testGibPunktestand() {
		Spiel spiel = new Spiel(Spielmodus.einzelspieler,
				Spielvariante.fudschijama2x3, Schwierigkeitsgrad.knifflig, 1);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		List<Integer> liste = spiel.gibPunktestand();
		assertEquals("Test auf Punktestand: ", 1, liste.size());
	}

	/**
	 * Testfall fuer Auslesen des Schwierigkeitsgrades eines Spiels.
	 */
	@Test
	public void testGibSchwierigkeitsgrad() {
		Spiel spiel = new Spiel();
		spiel.setzeSchwierigkeitsgrad(Schwierigkeitsgrad.schwer);
		assertEquals(" Test gib Spielvariante: ", Schwierigkeitsgrad.schwer,
				spiel.gibSchwierigkeitsgrad());
	}

	/**
	 * Testfall fuer das Auslesen der Spielerfarben eines Spiels.
	 */
	@Test
	public void testGibSpielerfarbenliste() {
		Spiel spiel = new Spiel(Spielmodus.einzelspieler,
				Spielvariante.fudschijama2x3, Schwierigkeitsgrad.knifflig, 1);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		List<Farbe> liste = spiel.gibSpielerfarbenliste();
		assertEquals("Test auf Punktestand: ", Farbe.blau, liste.get(0));
	}

	/**
	 * Testfall fuer das Auslesen eines Spielers aus einer Spielerliste.
	 */
	@Test
	public void testGibSpielerliste() {
		List<String> namen = new ArrayList<String>();
		namen.add("Hugo");
		Spiel spiel = new Spiel();
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		assertEquals(" Test gib Namen in der Spielerliste: ", namen, spiel
				.gibSpielerliste());
	}

	/**
	 * Testfall fuer das Auslesen der spielID eines Spiels.
	 */
	@Test
	public void testGibSpielID() {
		int x = 1;
		Spiel spiel = new Spiel(Spielmodus.mehrspieler, Spielvariante.standard,
				Schwierigkeitsgrad.mittel, 0);
		spiel.setzeSpielID(x);
		assertEquals(" Test gib SpielID: ", x, spiel.gibSpielID());
	}

	/**
	 * Testfall fuer das Auslesen des Spielmodus eines Spiels.
	 */
	@Test
	public void testGibSpielmodus() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		assertEquals(" Test gib Spielmodus: ", Spielmodus.einzelspieler, spiel
				.gibSpielmodus());
	}

	/**
	 * Testfall fuer das Auslesen der Spielvariante eines Spiels.
	 */
	@Test
	public void testGibSpielvariante() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielvariante(Spielvariante.standard);
		assertEquals(" Test gib Spielvariante: ", Spielvariante.standard, spiel
				.gibSpielvariante());
	}

	/**
	 * Testfall fuer das Auslesen der Strafzeit eines Spiels.
	 */
	@Test
	public void testGibStrafzeit() {
		int x = 10;
		Spiel spiel = new Spiel();
		spiel.setzeStrafzeit(x);
		assertEquals(" Test gib Strafzeit: ", x, spiel.gibStrafzeit());
	}

	/**
	 * Testfall fuer das Setzen des Schwierigkeitsgrades eines Spiels.
	 */
	@Test
	public void testSetzeSchwierigkeitsgrad() {
		Spiel spiel = new Spiel();
		spiel.setzeSchwierigkeitsgrad(Schwierigkeitsgrad.schwer);
		spiel.setzeSchwierigkeitsgrad(Schwierigkeitsgrad.profi);
		assertEquals(" Test gib Spielvariante: ", Schwierigkeitsgrad.profi,
				spiel.gibSchwierigkeitsgrad());
	}

	/**
	 * Testfall fuer Setzen eines Spielers in einer Spielerliste.
	 */
	@Test
	public void testSetzeSpielerliste() {
		List<String> namen = new ArrayList<String>();
		namen.add("Hugo");
		namen.add("Heinz");
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielerAnmelden("Heinz", Farbe.gelb);
		assertEquals(" Test setze Namen in der Spielerliste: ", namen, spiel
				.gibSpielerliste());
	}

	/**
	 * Testfall fuer das Setzen, dass das Spiel laeuft.
	 */
	@Test
	public void testSetzeSpielLaeuft() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielLaeuft(false);
		spiel.setzeSpielLaeuft(true);
		assertEquals(" Test auf setze Spiel laeuft: ", true, spiel
				.spielLaeuft());
	}

	/**
	 * Testfall fuer das Setzen des Spielmodus eines Spiels.
	 */
	@Test
	public void testSetzeSpielmodus() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		assertEquals(" Test gib Spielmodus: ", Spielmodus.mehrspieler, spiel
				.gibSpielmodus());
	}

	/**
	 * Testfall fuer das Setzen der Spielvariante eines Spiels.
	 */
	@Test
	public void testSetzeSpielvariante() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielvariante(Spielvariante.standard);
		spiel.setzeSpielvariante(Spielvariante.comparison);
		assertEquals(" Test gib Spielvariante: ", Spielvariante.comparison,
				spiel.gibSpielvariante());
	}

	/**
	 * Testfall fuer das Setzen einer negativen Strafzeit eines Spiels im
	 * Konstruktor.
	 */
	@Test
	public void testSpiel_SetzeStrafzeitNegativ() {
		int x = 0;
		Spiel spiel = new Spiel(Spielmodus.mehrspieler,
				Spielvariante.fudschijama4x4, Schwierigkeitsgrad.mittel, x - 1);
		assertEquals(" Test setze negative Strafzeit: ", x, spiel
				.gibStrafzeit());
	}

	/**
	 * Testfall fuer einen Spieler abmelden.
	 */
	@Test
	public void testSpielerAbmelden() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielerAnmelden("Heinz", Farbe.gelb);
		spiel.spielerAbmelden("Hugo");
		List<String> namen = new ArrayList<String>();
		namen.add("Heinz");
		assertEquals(" Testfall Spieler abmelden: ", namen, spiel
				.gibSpielerliste());
	}

	/**
	 * Testfall fuer einen Spieler abmelden im Einzelspielermodus.
	 */
	@Test
	public void testSpielerAbmelden_Einzelspieler() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielerAbmelden("Hugo");
		List<String> namen = new ArrayList<String>();
		assertEquals(" Testfall Spieler abmelden: ", namen, spiel
				.gibSpielerliste());
		//assertEquals("Testfall ob Spiel noch laeuft")
	}
	
	/**
	 * Testfall fuer einen Spieler abmelden, wobei der Spieler im Spiel nicht
	 * vorhanden ist.
	 */
	@Test
	public void testSpielerAbmelden_NameNichtVorhanden() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielerAbmelden("Heinz");
		List<String> namen = new ArrayList<String>();
		namen.add("Hugo");
		assertEquals(" Testfall Spieler abmelden: ", namen, spiel
				.gibSpielerliste());
	}

	/**
	 * Testfall fuer einen Spieler in einem Spiel anmelden: Erfolgreiches
	 * Anmelden von 8 Spielern.
	 */
	@Test
	public void testSpielerAnmelden_AnzSpieler8() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Otto1", Farbe.blau);
		spiel.spielerAnmelden("Otto2", Farbe.gelb);
		spiel.spielerAnmelden("Otto3", Farbe.gruen);
		spiel.spielerAnmelden("Otto4", Farbe.pink);
		spiel.spielerAnmelden("Otto5", Farbe.orange);
		spiel.spielerAnmelden("Otto6", Farbe.rot);
		spiel.spielerAnmelden("Otto7", Farbe.schwarz);
		assertEquals(" Test Spieler anmelden: ", Serverantwort.ok, spiel
				.spielerAnmelden("Otto8", Farbe.weiss));
	}

	/**
	 * Testfall fuer einen Spieler in einem Einzelspielerspiel anmelden.
	 */
	@Test
	public void testSpielerAnmelden_Einzelspieler() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		assertEquals(" Test Spieler anmelden: ", Serverantwort.ok, spiel
				.spielerAnmelden("Heinz", Farbe.blau));
	}

	/**
	 * Testfall fuer einen Spieler in einem Einzelspielerspiel anmelden, wobei
	 * ein Spieler bereits angemeldet ist.
	 */
	@Test
	public void testSpielerAnmelden_Einzelspieler_zuVielSpieler() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Heinz", Farbe.gelb);
		assertEquals(" Test Spieler anmelden: ", Serverantwort.spielVoll, spiel
				.spielerAnmelden("Hugo", Farbe.blau));
	}

	/**
	 * Testfall fuer einen Spieler in einem Spiel anmelden, wobei in dem Spiel
	 * aber bereits ein Spieler mit der gleichen Farbe angemeldet ist.
	 */
	@Test
	public void testSpielerAnmelden_FarbeVergeben() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		assertEquals(" Test Spieler anmelden: ",
				Serverantwort.spielerfarbeVergeben, spiel.spielerAnmelden(
						"Heinz", Farbe.blau));
	}

	/**
	 * Testfall fuer einen Spieler in einem neuen Spiel anmelden, wobei keine
	 * anderen Spieler bisher angemeldet sind.
	 */
	@Test
	public void testSpielerAnmelden_Mehrspieler() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		assertEquals(" Test Spieler anmelden: ", Serverantwort.ok, spiel
				.spielerAnmelden("Heinz", Farbe.blau));
	}

	/**
	 * Testfall fuer einen Spieler in einem Spiel anmelden, wobei in dem Spiel
	 * aber bereits ein Spieler mit der gleichen Farbe und gleichem Namen
	 * angemeldet ist.
	 */
	@Test
	public void testSpielerAnmelden_NameUndFarbeVergeben() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Heinz", Farbe.blau);
		assertEquals(" Test Spieler anmelden: ",
				Serverantwort.spielernameVergeben, spiel.spielerAnmelden(
						"Heinz", Farbe.blau));
	}

	/**
	 * Testfall fuer einen Spieler in einem Spiel anmelden, wobei in dem Spiel
	 * aber bereits ein Spieler mit dem gleichen Namen angemeldet ist.
	 */
	@Test
	public void testSpielerAnmelden_NameVergeben() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Heinz", Farbe.gelb);
		assertEquals(" Test Spieler anmelden: ",
				Serverantwort.spielernameVergeben, spiel.spielerAnmelden(
						"Heinz", Farbe.blau));
	}

	/**
	 * Testfall fuer einen Spieler in einem Spiel anmelden, wobei das Spiel
	 * bereits voll ist.
	 */
	@Test
	public void testSpielerAnmelden_SpielVoll() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Otto1", Farbe.blau);
		spiel.spielerAnmelden("Otto2", Farbe.gelb);
		spiel.spielerAnmelden("Otto3", Farbe.gruen);
		spiel.spielerAnmelden("Otto4", Farbe.pink);
		spiel.spielerAnmelden("Otto5", Farbe.orange);
		spiel.spielerAnmelden("Otto6", Farbe.rot);
		spiel.spielerAnmelden("Otto7", Farbe.schwarz);
		spiel.spielerAnmelden("Otto8", Farbe.weiss);
		assertEquals(" Test Spieler anmelden: ", Serverantwort.spielVoll, spiel
				.spielerAnmelden("Heinz", Farbe.blau));
	}

	/**
	 * Testfall fuer das Pruefen, ob Spiel laeuft.
	 */
	@Test
	public void testSpielLaeuft() {
		boolean x = true;
		Spiel spiel = new Spiel();
		spiel.setzeSpielLaeuft(x);
		assertEquals(" Test auf Spiel laeuft: ", x, spiel.spielLaeuft());
	}

	/**
	 * Testfall das Starten eines Einzelspielerspiels.
	 */
	@Test
	public void testSpielStarten_Einzelspieler() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		assertEquals(" Test Einzelspielerspiel starten: ", Serverantwort.ok,
				spiel.spielStarten());
	}

	/**
	 * Testfall das Starten eines Einzelspielerspiels, wobei kein Spieler
	 * angemeldet ist.
	 */
	@Test
	public void testSpielStarten_Einzelspieler_keinSpielerAngemeldet() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		assertEquals(" Test Einzelspielerspiel starten: ",
				Serverantwort.zuWenigSpieler, spiel.spielStarten());
	}

	/**
	 * Testfall fuer das Starten eines Mehrspielerspiels, wobei mehr als 2
	 * Spieler angemeldet sind.
	 */
	@Test
	public void testSpielStarten_Mehrspieler() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielerAnmelden("Heinz", Farbe.weiss);
		assertEquals(" Test Mehrspielerspiel starten: ", Serverantwort.ok,
				spiel.spielStarten());
	}

	/**
	 * Testfall fuer das Starten eines Mehrspielerspiels, wobei kein Spieler
	 * angemeldet ist.
	 */
	@Test
	public void testSpielStarten_Mehrspieler_KeinSpielerAngemeldet() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		assertEquals(" Test Mehrspielerspiel starten: ",
				Serverantwort.zuWenigSpieler, spiel.spielStarten());
	}

	/**
	 * Testfall fuer das Starten eines Mehrspielerspiels, wobei weniger als 2
	 * Spieler angemeldet sind.
	 */
	@Test
	public void testSpielStarten_Mehrspieler_ZuWenigSpieler() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.mehrspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		assertEquals(" Test Mehrspielerspiel starten: ",
				Serverantwort.zuWenigSpieler, spiel.spielStarten());
	}
	
	/**
	 * Testfall fuer das Finden von Konflikten im Spielfeld des betreffenden Spielers.
	 */
	@Test
	public void testGibKonfliktfelder() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielStarten();
		List<IKoordinate> liste = spiel.gibKonfliktfelder("Hugo");
		assertEquals("Testfall auf gibKonfliktfelder: ",0,liste.size());
	}

	/**
	 * Testfall fuer das Finden von Konflikten im Spielfeld eines nicht existierenden Spielers.
	 */
	@Test
	public void testGibKonfliktfelder_SpielerNichtVorhanden() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielStarten();
		List<IKoordinate> liste = spiel.gibKonfliktfelder("Egon");
		assertEquals("Testfall auf gibKonfliktfelder mit nicht existierendem Spieler: ",null,liste);
	}
	
	
	/**
	 * Testfall fuer das Austeilen eines Spielfeldes an den betreffenden Spieler.
	 */
	@Test
	public void testGibSpielfeld() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielStarten();
		ISpielfeld spielfeld = spiel.gibSpielfeld("Hugo");
		assertEquals("Testfall auf gibSpielfeld: ",true,spielfeld.gibAnzahlGesetzteFelder()>=0);
	}

	/**
	 * Testfall fuer das Austeilen eines Spielfeldes an einen nicht existierenden Spielers.
	 */
	@Test
	public void testGibSpielfeld_SpielerNichtVorhanden() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielStarten();
		ISpielfeld spielfeld = spiel.gibSpielfeld("Egon");
		assertEquals("Testfall auf gibSpielfeld mit nicht existierendem Spieler: ",null,spielfeld);
	}
	
	/**
	 * Testfall fuer das Geben von Feldmoeglichkeiten eines Feldes 
	 * an den betreffenden Spieler.
	 */
	@Test
	public void testGibFeldMoeglichkeiten() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielStarten();
		IKoordinate k = new Koordinate(1,1);		
		spiel.setzeZiffer("Hugo",k,1);
		List<Integer> liste = spiel.gibFeldMoeglichkeiten("Hugo", k);
		assertEquals("Testfall auf gibFeldMoeglichkeiten: ",0,liste.size());
	}

	/**
	 * Testfall fuer das Geben von Feldmoeglichkeiten eines Feldes
	 * an einen nicht existierenden Spielers.
	 */
	@Test
	public void testGibFeldMoeglichkeiten_SpielerNichtVorhanden() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielStarten();
		IKoordinate k = new Koordinate(1,1);
		List<Integer> liste = spiel.gibFeldMoeglichkeiten("Egon", k);
		assertEquals("Testfall auf gibFeldMoeglichkeiten mit nicht existierendem Spieler: ",null,liste);
	}
	
	/**
	 * Testfall fuer das Loeschen eines Feldes 
	 * eines betreffenden Spielers.
	 */
	@Test
	public void testLoescheZiffer() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielStarten();
		IKoordinate k = new Koordinate(1,1);
		ISpielfeld spielfeld = spiel.gibSpielfeld("Hugo");
		spielfeld.gibFeld(k).setzeStartbelegung(false);
		spiel.setzeZiffer("Hugo",k,1);
		spiel.loescheZiffer("Hugo",k);
		assertEquals("Testfall auf Loeschen einer Ziffer: ",0,spielfeld.gibFeld(k).gibInhalt());
	}

	/**
	 * Testfall fuer das Loeschen eines Feldes
	 * eines nicht existierenden Spielers.
	 */
	@Test
	public void testLoescheZiffer_SpielerNichtVorhanden() {
		Spiel spiel = new Spiel();
		spiel.setzeSpielmodus(Spielmodus.einzelspieler);
		spiel.spielerAnmelden("Hugo", Farbe.blau);
		spiel.spielStarten();
		IKoordinate k = new Koordinate(1,1);
		assertEquals("Testfall auf Loeschen einer Ziffer eines nicht existierendem Spieler: ",null,spiel.loescheZiffer("Egon",k));
	}
	
	/**
	 * Testfall fuer das Erstellen eines Spieles mittels Spielstandes
	 */
	@Test
	public void testSpiel_Spielstand() {
		Spiel spiel = new Spiel();
		spiel.spielerAnmelden("Hugo",Farbe.blau);
		ISpielstand spielstand = new Spielstand();
		spielstand.setzeSchwierigkeitsgrad(Schwierigkeitsgrad.anfaenger);
		spielstand.setzeStrafzeit(14);
		spielstand.fuegeSpielfeldHinzu(Farbe.blau, spiel.gibSpielfeld("Hugo"));
		Spiel spielMitSpielstand = new Spiel(spielstand);
		int strafe = spielMitSpielstand.gibStrafzeit();
		assertEquals("Testfall ob richtige Strafzeit im Spielstand gesetzt wird im Konstruktor Spiel : ",14,strafe);
	}
	
	/**
	 * Testfall fuer das Erzeugen eines Spielstandes in einem Spiel
	 */
	@Test
	public void testGibSpielstand() {
		Spiel spiel = new Spiel(Spielmodus.mehrspieler,Spielvariante.fudschijama4x3,Schwierigkeitsgrad.anfaenger,10);
		spiel.spielerAnmelden("Hugo",Farbe.blau);
		spiel.spielStarten();
		ISpielstand spielstand = spiel.gibSpielstand();
		List<Farbe> liste = new ArrayList<Farbe>();
		liste.add(Farbe.blau);
		assertEquals("Testfall ob richtige Spielvariante zurueckgegeben wird : ",Spielvariante.fudschijama4x3,spielstand.gibSpielvariante());
		assertEquals("Testfall ob richtiger Schwierigkeitsgrad zurueckgegeben wird : ",Schwierigkeitsgrad.anfaenger,spielstand.gibSchwierigkeitsgrad());
		assertEquals("Testfall ob richtige Spielerfarbenliste zurueckgegeben wird : ",liste,spielstand.gibSpielerfarben());
	}
	
	/**
	 * Testfall fuer das Anmelden eines Spielers in einem Spiel,
	 * welches zuvor aus einem Spielstand geladen wurde.
	 * Die Spielerfarbe des anzumeldenden Spielers ist nicht im Spielstand vorhanden.
	 */
	@Test
	public void testSpielerAnmeldenInSpielAusSpielstandMitFalscherFarbe() {
		Spiel spiel = new Spiel();
		spiel.spielerAnmelden("Hugo",Farbe.blau);
		spiel.spielStarten();
		ISpielstand spielstand = spiel.gibSpielstand();
		ISpiel spielMitSpielstand = new Spiel(spielstand);
		Serverantwort antwort = spielMitSpielstand.spielerAnmelden("Egon",Farbe.gelb);
		assertEquals("Testfall fuer das Anmelden eines Spielers in Spiel mit Spielstand mit nicht vorhandener Farbe: ",Serverantwort.spielerfarbeNichtVorhanden,antwort);
	}
	
	/**
	 * Testfall fuer das Anmelden eines Spielers in einem Spiel,
	 * welches zuvor aus einem Spielstand geladen wurde.
	 * Die Spielerfarbe des anzumeldenden Spielers ist im Spielstand vorhanden.
	 */
	@Test
	public void testSpielerAnmeldenInSpielAusSpielstand() {
		Spiel spiel = new Spiel();
		spiel.spielerAnmelden("Hugo",Farbe.blau);
		spiel.spielStarten();
		ISpielstand spielstand = spiel.gibSpielstand();
		ISpiel spielMitSpielstand = new Spiel(spielstand);
		Serverantwort antwort = spielMitSpielstand.spielerAnmelden("Egon",Farbe.blau);
		assertEquals("Testfall fuer das Anmelden eines Spielers in Spiel mit Spielstand: ",Serverantwort.ok,antwort);
	}
	
	/**
	 * Testfall fuer das Setzen einer falschen Ziffer im Mehrspielermodus
	 */
	@Test
	public void testSetzeZiffer_MehrspielerZifferFalsch() {
		Spiel spiel = new Spiel(Spielmodus.mehrspieler,Spielvariante.comparison,Schwierigkeitsgrad.schwer,10);
		spiel.spielerAnmelden("Hugo",Farbe.blau);
		spiel.spielerAnmelden("Heinz",Farbe.gruen);
		spiel.spielStarten();
		ISpielfeld spielfeldHeinz = spiel.gibSpielfeld("Heinz");
		IKoordinate k = new Koordinate(1,1);
		spielfeldHeinz.gibFeld(k).setzeStartbelegung(false);
		spiel.loescheZiffer("Heinz",k);
		int loesung = spiel.gibSpielfeldLoesung().gibFeldinhalt(k);
		Serverantwort antwort = spiel.setzeZiffer("Heinz",k,loesung+1);
		assertEquals("Testfall fuer das Setzen einer falschen Ziffer im Mehrspielermodus: ",Serverantwort.zifferFalsch,antwort);
		
	}
}
