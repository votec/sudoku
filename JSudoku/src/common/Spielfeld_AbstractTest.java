package common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import common.EnumContainer.Serverantwort;

/**
 * Die Klasse Spielfeld_AbstractTest testet die Methoden der Klasse ASpielfeld.
 * 
 * @author Heiko Schroeder
 * 
 */
public class Spielfeld_AbstractTest extends TestCase {

	/**
	 * Die Klasse AbstraktesSpielfeld dient nur zu Testzwecken der Klasse
	 * ASpielfeld.
	 */
	public class AbstraktesSpielfeld extends ASpielfeld {

		/** serial Version UID */
		private static final long serialVersionUID = -1443376816927065441L;

		/**
		 * Erzeugt ein neues Spielfeld mit Standardwerten.
		 */
		public AbstraktesSpielfeld() {
			this.setzeSpielfeldBreite(3);
			this.setzeSpielfeldHoehe(3);
			this.setzeBlockBreite(3);
			this.setzeBlockHoehe(3);
			this.initSpielfeld();
		}

		/**
		 * @see ISpielfeld#generiereSpielfeld(common.EnumContainer.Schwierigkeitsgrad)
		 */
		public void generiereSpielfeld(Schwierigkeitsgrad stufe) {
		}

	}

	/**
	 * Testet, ob ein korrektes Sudokufeld erzeugt wird.
	 */
	@Test
	public void testErzeugeSudoku() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.erzeugeSudoku(45);

		int spielfeldBreite = spielfeld.gibBlockBreite()
				* spielfeld.gibSpielfeldBreite();

		int spielfeldHoehe = spielfeld.gibBlockHoehe()
				* spielfeld.gibSpielfeldHoehe();

		// Gib Spielfeld aus
		// Laufe ueber das Spielfeld und suche die Startbelegung heraus
		for (int i = 0; i < spielfeldHoehe; i++) {
			for (int j = 0; j < spielfeldBreite; j++) {
				IKoordinate koordinate = new Koordinate(j + 1, i + 1);
				IFeld feld = spielfeld.gibFeld(koordinate);

				// Gehoert Feld zur Startbelegung, fuege Koorinate und Inhalt in
				// Listen ein

				if (feld.istInStartbelegung() == true) {
					System.out.print(feld.gibInhalt());
				} else {
					System.out.print(".");
				}

				if (j == 2 || j == 5) {
					System.out.print(" | ");
				}
			}
			if (i == 2 || i == 5) {
				System.out.println("\n--------------");
			} else {
				System.out.print("\n");
			}
		}
		System.out.println();

		// Gib Spielfeld aus
		// Laufe ueber das Spielfeld und suche die Startbelegung heraus
		for (int i = 0; i < spielfeldHoehe; i++) {
			for (int j = 0; j < spielfeldBreite; j++) {
				IKoordinate koordinate = new Koordinate(j + 1, i + 1);
				IFeld feld = spielfeld.gibFeld(koordinate);

				// Gehoert Feld zur Startbelegung, fuege Koorinate und Inhalt in
				// Listen ein

				System.out.print(feld.gibInhalt());

				if (j == 2 || j == 5) {
					System.out.print(" | ");
				}
			}
			if (i == 2 || i == 5) {
				System.out.println("\n--------------");
			} else {
				System.out.print("\n");
			}
		}

		// Pruefe auf Konfliktfelder in allen Bloecken
		for (int i = 1; i < spielfeld.bloecke.length; i++) {
			int konflikte = spielfeld.bloecke[i].gibKonfliktfelder().size();
			assertEquals("Pruefe Anzahl Konflikte im Block", 0, konflikte);
		}

		// Pruefe auf Konfliktfelder in allen Zeilen
		for (int i = 1; i < spielfeld.zeilen.length; i++) {
			int konflikte = spielfeld.zeilen[i].gibKonfliktfelder().size();
			assertEquals("Pruefe Anzahl Konflikte in den Zeilen", 0, konflikte);
		}

		// Pruefe auf Konfliktfelder in allen Spalten
		for (int i = 1; i < spielfeld.spalten.length; i++) {
			int konflikte = spielfeld.spalten[i].gibKonfliktfelder().size();
			assertEquals("Pruefe Anzahl Konflikte in Spalten", 0, konflikte);
		}

	}

	/**
	 * Testet, ob ein korrektes leichtes komplexes Sudokufeld erzeugt wird. Beim
	 * Erstellen soll der Algorithmus besonders in Sackgasen laufen
	 */
	@Test
	public void testErzeugeSudokuAnfaenger() {
		ASpielfeld spielfeld = new Spielfeld_Fudschijma_4x3();
		spielfeld
				.generiereSpielfeld(EnumContainer.Schwierigkeitsgrad.anfaenger);

		// Pruefe auf Konfliktfelder in allen Bloecken
		for (int i = 1; i < spielfeld.bloecke.length; i++) {
			int konflikte = spielfeld.bloecke[i].gibKonfliktfelder().size();
			assertEquals("Pruefe Anzahl Konflikte im Block", 0, konflikte);
		}

		// Pruefe auf Konfliktfelder in allen Zeilen
		for (int i = 1; i < spielfeld.zeilen.length; i++) {
			int konflikte = spielfeld.zeilen[i].gibKonfliktfelder().size();
			assertEquals("Pruefe Anzahl Konflikte in den Zeilen", 0, konflikte);
		}

		// Pruefe auf Konfliktfelder in allen Spalten
		for (int i = 1; i < spielfeld.spalten.length; i++) {
			int konflikte = spielfeld.spalten[i].gibKonfliktfelder().size();
			assertEquals("Pruefe Anzahl Konflikte in Spalten", 0, konflikte);
		}

	}

	/**
	 * Testet, ob ein korrektes schwieriges Sudokufeld erzeugt wird.
	 */
	@Test
	public void testErzeugeSudokuSchwierig() {
		ASpielfeld spielfeld = new Spielfeld_Fudschijma_4x4();
		spielfeld.generiereSpielfeld(EnumContainer.Schwierigkeitsgrad.schwer);

		// Pruefe auf Konfliktfelder in allen Bloecken
		for (int i = 1; i < spielfeld.bloecke.length; i++) {
			int konflikte = spielfeld.bloecke[i].gibKonfliktfelder().size();
			assertEquals("Pruefe Anzahl Konflikte im Block", 0, konflikte);
		}

		// Pruefe auf Konfliktfelder in allen Zeilen
		for (int i = 1; i < spielfeld.zeilen.length; i++) {
			int konflikte = spielfeld.zeilen[i].gibKonfliktfelder().size();
			assertEquals("Pruefe Anzahl Konflikte in den Zeilen", 0, konflikte);
		}

		// Pruefe auf Konfliktfelder in allen Spalten
		for (int i = 1; i < spielfeld.spalten.length; i++) {
			int konflikte = spielfeld.spalten[i].gibKonfliktfelder().size();
			assertEquals("Pruefe Anzahl Konflikte in Spalten", 0, konflikte);
		}

	}

	/**
	 * Testet, ob die Listen der Konfliktfelder korrekt zusammengefuegt werden.
	 */
	@Test
	public void testFuegeListenZusammen() {
		ArrayList<IKoordinate> liste1 = new ArrayList<IKoordinate>();
		ArrayList<IKoordinate> liste2 = new ArrayList<IKoordinate>();
		liste1.clear();
		liste2.clear();

		// Gleiche Elemente (3 Elemente in jeder Liste)
		for (int j = 7; j <= 9; j++) {
			IKoordinate koordinate = new Koordinate(j, j);
			liste1.add(koordinate);
			liste2.add(koordinate);
		}

		// Unterschiedliche Elemente (jeweils 5 St. pro Liste)
		for (int j = 1; j <= 5; j++) {
			IKoordinate koordinate1 = new Koordinate(j, j);
			IKoordinate koordinate2 = new Koordinate(j + 1, j + 1);
			liste1.add(koordinate1);
			liste2.add(koordinate2);
		}

		// Zusammengefuegte Liste muss also 13 Elemente haben
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		ArrayList<IKoordinate> gesamtliste = spielfeld.fuegeListenZusammen(
				liste1, liste2);
		assertEquals("Pruefe Anzahl Konfliktfelder nach Zusammenfuegen", 13,
				gesamtliste.size());

		// Pruefe nun ob alle Elemente der einzelnen Listen in der Gesamten
		// vorkommen.
		for (int i = 1; i < liste1.size(); i++) {
			boolean resultat = gesamtliste.contains(liste1.get(i));
			assertEquals(
					"Pruefe ob alle Felder aus Liste 1 in gesamtliste vorkommen",
					true, resultat);
		}
		for (int i = 1; i < liste2.size(); i++) {
			boolean resultat = gesamtliste.contains(liste2.get(i));
			assertEquals(
					"Pruefe ob alle Felder aus Listr 2 in gesamtliste vorkommen",
					true, resultat);
		}
	}

	/**
	 * Testet, ob die Anzahl der gesetzten Felder korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibAnzahlGesetzteFelder() {
		int x = 5;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeAnzahlGesetzteFelder(x);
		assertEquals("Test GibAnzahlGesetzteFelder", x, spielfeld
				.gibAnzahlGesetzteFelder());
	}

	/**
	 * Testet, ob die Anzahl der gesetzten Felder korrekt initialisiert wird.
	 */
	@Test
	public void testGibAnzahlGesetzteFelder_Initialisierung() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		assertEquals("Test Initialisierung AnzahlGesetzteFelder", 0, spielfeld
				.gibAnzahlGesetzteFelder());
	}

	/**
	 * Testet, ob die korrekte Blockbreite zurueckgegeben wird.
	 */
	@Test
	public void testGibBlockBreite() {
		int x = 5;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeBlockBreite(x);
		assertEquals("Test GibBlockBreite()", x, spielfeld.gibBlockBreite());
	}

	/**
	 * Testet, ob die korrekte Blockhoehe zurueckgegeben wird.
	 */
	@Test
	public void testGibBlockHoehe() {
		int x = 5;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeBlockHoehe(x);
		assertEquals("Test GibBlockHoehe()", x, spielfeld.gibBlockHoehe());
	}

	/**
	 * Testet, ob zu einer Koordinate die korrekte Blocknummer zurueckgegeben
	 * wird.
	 */
	@Test
	public void testGibBlocknummerZuKorrdinate() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();

		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				IKoordinate koordinate = new Koordinate(i, j);
				int nummer = spielfeld.gibBlocknummerZuKoordinate(koordinate);

				if (i >= 1 && i <= 3) {
					if (j >= 1 && j <= 3) {
						assertEquals("gibBlocknummer", 0, nummer);
					} else if (j >= 4 && j <= 6) {
						assertEquals("gibBlocknummer", 3, nummer);
					} else if (j >= 7 && j <= 9) {
						assertEquals("gibBlocknummer", 6, nummer);
					}
				} else if (i >= 4 && i <= 6) {
					if (j >= 1 && j <= 3) {
						assertEquals("gibBlocknummer", 1, nummer);
					} else if (j >= 4 && j <= 6) {
						assertEquals("gibBlocknummer", 4, nummer);
					} else if (j >= 7 && j <= 9) {
						assertEquals("gibBlocknummer", 7, nummer);
					}
				} else if (i >= 7 && i <= 9) {
					if (j >= 1 && j <= 3) {
						assertEquals("gibBlocknummer", 2, nummer);
					} else if (j >= 4 && j <= 6) {
						assertEquals("gibBlocknummer", 5, nummer);
					} else if (j >= 7 && j <= 9) {
						assertEquals("gibBlocknummer", 8, nummer);
					}
				}
			}
		}
	}

	/**
	 * Testet, ob Inhalt eines Feldes korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibFeldinhalt() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();

		for (int i = 0; i < 9; i++) {
			int inhalt = 1;
			for (int j = 0; j < 9; j++) {
				IKoordinate koordinate = new Koordinate(i + 1, j + 1);
				spielfeld.felder[i][j] = new Feld(koordinate, inhalt);
				inhalt++;
			}
		}

		IKoordinate koordinate = new Koordinate(2, 2);
		assertEquals("Test GibFeldInhalt", 2, spielfeld
				.gibFeldinhalt(koordinate));
	}

	/**
	 * Test der GibFeldMoeglichkeiten Methode
	 * 
	 * Schreibt in die Mitte eines 9er Blocks eine Zahl (1...9) Pruefe dann ob
	 * das erste Feld eines 9er Blocks (oben links) immer 8 Moeglichkeiten fuer
	 * eine Ziffernbelegung hat.
	 */
	public void testGibFeldMoeglichkeiten() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();

		// Setze in das Zentrum jedes 9er Blocks eine Zahl
		int inhalt = 1;
		for (int i = 1; i < 9; i = i + 3) {
			for (int j = 1; j < 9; j = j + 3) {
				IKoordinate koordinate = new Koordinate(i + 1, j + 1);
				// spielfeld.felder[i][j] = new Feld(koordinate,inhalt,true);
				spielfeld.setzeZiffer(koordinate, inhalt);
				inhalt++;
			}
		}

		inhalt = 1;
		for (int i = 0; i < 9; i = i + 3) {
			for (int j = 0; j < 9; j = j + 3) {
				IKoordinate koordinate = new Koordinate(i + 1, j + 1);
				List<Integer> moeglichkeiten = spielfeld
						.gibFeldMoeglichkeiten(koordinate);
				inhalt++;

				int cnt = moeglichkeiten.size();

				Iterator<Integer> iterator = moeglichkeiten.iterator();
				while (iterator.hasNext()) {
					int zahl = iterator.next();
					if (zahl == inhalt - 1) {
						fail("Falsche Moeglichkeit wird zurueckgegeben!");
					}
				}

				assertEquals("[" + i + "," + j
						+ "] Test GibFeldMoeglichkeiten(): Anzahl", 8, cnt);
			}
		}

	}

	/**
	 * Initialisiert das Spielfeld und prueft auf Konfliktfelder (Soll = 0).
	 */
	public void testGibKonfliktfelder_Anzahl_Init() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		assertEquals("Pruefe Anzahl Konfliktfelder nach Initialisierung", 0,
				spielfeld.gibKonfliktfelder().size());
	}

	/**
	 * Setzt einen Konflikt und prueft, ob dieser erkannt wird.
	 */
	public void testGibKonfliktfelder_Anzahl1() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();

		// Setze ein Feld in die Startbelegung
		spielfeld.felder[0][0].setzeInhalt(1);
		spielfeld.felder[0][0].setzeStartbelegung(true);

		// Setze ein identisches Feld in selben Block, welches nicht zur
		// Startbelegung
		// gehoert.
		spielfeld.felder[1][1].setzeInhalt(1);

		assertEquals("Pruefe Anzahl Konfliktfelder", 1, spielfeld
				.gibKonfliktfelder().size());
	}

	/**
	 * Testet, ob die groesstmoegliche Ziffer korrekt zurueckgegeben wird.
	 */
	@Test
	public void testGibMaxZiffer() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeBlockBreite(6);
		spielfeld.setzeBlockHoehe(5);
		spielfeld.setzeMaxZiffer();
		assertEquals("Test gibMaxZiffer", 30, spielfeld.gibMaxZiffer());
	}

	/**
	 * Testet, ob die korrekte Spielfeldbreite zurueckgegeben wird.
	 */
	@Test
	public void testGibSpielfeldBreite() {
		int x = 5;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeSpielfeldBreite(x);
		assertEquals("Test GibSpielfeldBreite()", x, spielfeld
				.gibSpielfeldBreite());
	}

	/**
	 * Testet, ob die korrekte Spielfeldhoehe zurueckgegeben wird.
	 */
	@Test
	public void testGibSpielfeldHoehe() {
		int x = 5;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeSpielfeldHoehe(x);
		assertEquals("Test GibSpielfeldHoehe()", x, spielfeld
				.gibSpielfeldHoehe());
	}

	/**
	 * Testet das Initiieren eines Spielfeldes.
	 */
	@Test
	public void testInitSpielfeld() {
		try {
			new AbstraktesSpielfeld();
		} catch (NullPointerException e) {
			fail("NULL POINTER EXCEPTION: initSpielfeld()");
		}

	}

	/**
	 * Testet das Loeschen eines leeren Feldes.
	 */
	public void testLoescheLeeresFeld() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		for (int i = 0; i < 9; i++) {
			int inhalt = 1;
			for (int j = 0; j < 9; j++) {
				IKoordinate koordinate = new Koordinate(i + 1, j + 1);
				spielfeld.felder[i][j] = new Feld(koordinate, inhalt, true);
				inhalt++;
			}
		}

		Koordinate koordinate = new Koordinate(2, 2);
		spielfeld.felder[1][1] = new Feld(koordinate);

		Serverantwort sa = spielfeld.loescheZiffer(koordinate);

		assertEquals("Test loesche leeres Feld : Serverantwort",
				Serverantwort.feldLeer, sa);
		assertEquals("Test loesche leeres Feld: Ziffer", 0, spielfeld
				.gibFeldinhalt(koordinate));
	}

	/**
	 * Testet, ob eine Ziffer korrekt aus einem Feld geloescht wird.
	 */
	public void testLoescheZiffer() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();

		for (int i = 0; i < 9; i++) {
			int inhalt = 1;
			for (int j = 0; j < 9; j++) {
				IKoordinate koordinate = new Koordinate(i + 1, j + 1);
				spielfeld.felder[i][j] = new Feld(koordinate, inhalt);
				inhalt++;
			}
		}

		IKoordinate koordinate = new Koordinate(2, 2);
		Serverantwort sa = spielfeld.loescheZiffer(koordinate);
		assertEquals("Test loescheZiffer Serverantwort", Serverantwort.ok, sa);
		assertEquals("Test loescheZiffer Ziffer", 0, spielfeld
				.gibFeldinhalt(koordinate));

	}

	/**
	 * Testet das Loeschen eines Feldes, welches sich in der Startbelegung
	 * befindet.
	 */
	public void testLoescheZiffer_NEGATIV() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		for (int i = 0; i < 9; i++) {
			int inhalt = 1;
			for (int j = 0; j < 9; j++) {
				IKoordinate koordinate = new Koordinate(i + 1, j + 1);
				spielfeld.felder[i][j] = new Feld(koordinate, inhalt, true);
				inhalt++;
			}
		}

		Koordinate koordinate = new Koordinate(2, 2);
		Serverantwort sa = spielfeld.loescheZiffer(koordinate);

		assertEquals(
				"Test loescheZiffer (Ziffer Startbelegung): Serverantwort",
				Serverantwort.feldInStartbelegung, sa);
		assertEquals("Test loescheZiffer (Ziffer Startbelegung): Ziffer", 2,
				spielfeld.gibFeldinhalt(koordinate));
	}

	/**
	 * Testet, ob einer Koordinate der korrekte Block zugeordnet wird.
	 */
	@Test
	public void testPruefeBlockKoordinatenZuordnung() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();

		int ziffer = 1;
		for (int j = 7; j <= 9; j++) {
			for (int i = 7; i <= 9; i++) {
				IKoordinate koordinate = new Koordinate(j, i);
				spielfeld.setzeZiffer(koordinate, ziffer);
				ziffer++;
			}
		}

		IEinheit e = spielfeld.bloecke[8];
		for (int zahl = 1; zahl <= 9; zahl++) {
			assertEquals("Pruefe Block-Koordinaten Zuordnung, Zahl: " + zahl,
					true, e.pruefeZahl(zahl));

		}

	}

	/**
	 * Testet, ob die Anzahl der gesetzten Felder korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeAnzahlGesetzteFelder() {
		int x = 5;
		int y = 7;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeAnzahlGesetzteFelder(x);
		spielfeld.setzeAnzahlGesetzteFelder(y);
		assertEquals("Test SetzeAnzahlGesetzteFelder", y, spielfeld
				.gibAnzahlGesetzteFelder());
	}

	/**
	 * Testet, ob die korrekte Blockbreite gesetzt wird.
	 */
	@Test
	public void testSetzeBlockBreite() {
		int x = 5;
		int y = 7;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeBlockBreite(x);
		spielfeld.setzeBlockBreite(y);
		assertEquals("Test SetzeBlockBreite()", y, spielfeld.gibBlockBreite());
	}

	/**
	 * Testet, ob die korrekte Blockhoehe gesetzt wird.
	 */
	@Test
	public void testSetzeBlockHoehe() {
		int x = 5;
		int y = 7;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeBlockHoehe(x);
		spielfeld.setzeBlockHoehe(y);
		assertEquals("Test SetzeBlockHoehe()", y, spielfeld.gibBlockHoehe());
	}

	/**
	 * Testet, ob die groesstmoegliche Ziffer korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeMaxZiffer() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeBlockBreite(6);
		spielfeld.setzeBlockHoehe(5);
		spielfeld.setzeMaxZiffer();
		spielfeld.setzeBlockBreite(2);
		spielfeld.setzeBlockHoehe(4);
		spielfeld.setzeMaxZiffer();
		assertEquals("Test SetzeAnzahlGesetzteFelder", 8, spielfeld
				.gibMaxZiffer());
	}

	/**
	 * Testet, ob die korrekte Spielfeldbreite gesetzt wird.
	 */
	@Test
	public void testSetzeSpielfeldBreite() {
		int x = 5;
		int y = 7;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeSpielfeldBreite(x);
		spielfeld.setzeSpielfeldBreite(y);
		assertEquals("Test SetzeSpielfeldBreite()", y, spielfeld
				.gibSpielfeldBreite());
	}

	/**
	 * Testet, ob die korrekte Spielfeldbreite gesetzt wird.
	 */
	@Test
	public void testSetzeSpielfeldHoehe() {
		int x = 5;
		int y = 7;
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		spielfeld.setzeSpielfeldHoehe(x);
		spielfeld.setzeSpielfeldHoehe(y);
		assertEquals("Test SetzeSpielfeldHoehe()", y, spielfeld
				.gibSpielfeldHoehe());
	}

	/**
	 * Testet, ob eine Ziffer korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeZiffer() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		IKoordinate koordinate = new Koordinate(1, 2);

		// Setze zwei verschiedene Ziffern nacheinander
		spielfeld.setzeZiffer(koordinate, 5);
		spielfeld.loescheZiffer(koordinate);
		spielfeld.setzeZiffer(koordinate, 6);

		// Pruefe ob die zweite Ziffer gesetzt wurde
		assertEquals("pruefe setze Ziffer", 6, spielfeld
				.gibFeldinhalt(koordinate));
	}

	/**
	 * Testet, ob eine Ziffer nicht gesetzt wird, auf einem bereits gesetzten
	 * Feld. Prueft ob die Serverantwort korrekt ist
	 */
	@Test
	public void testSetzeZifferBelegtServerantwort() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		IKoordinate koordinate = new Koordinate(1, 2);

		// Setze zwei verschiedene Ziffern nacheinander
		spielfeld.setzeZiffer(koordinate, 5);
		EnumContainer.Serverantwort s = spielfeld.setzeZiffer(koordinate, 6);

		boolean richtigeAntwort = (s == EnumContainer.Serverantwort.feldNichtLeer);
		assertEquals("pruefe setze Ziffer, Serverantwort", true,
				richtigeAntwort);
	}

	/**
	 * Testet, ob eine Ziffer nicht gesetzt wird, auf einem bereits gesetzten
	 * Feld
	 */
	@Test
	public void testSetzeZifferBereitsBelegt() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		IKoordinate koordinate = new Koordinate(1, 2);

		// Setze zwei verschiedene Ziffern nacheinander
		spielfeld.setzeZiffer(koordinate, 5);
		spielfeld.setzeZiffer(koordinate, 6);

		// Pruefe ob die zweite Ziffer gesetzt wurde
		assertEquals("pruefe setze Ziffer", 5, spielfeld
				.gibFeldinhalt(koordinate));
	}

	/**
	 * Testet, ob eine Ziffer korrekt gesetzt wird.
	 */
	@Test
	public void testSetzeZifferServerantwort() {
		ASpielfeld spielfeld = new AbstraktesSpielfeld();
		IKoordinate koordinate = new Koordinate(1, 2);

		// Setze zwei verschiedene Ziffern nacheinander
		spielfeld.setzeZiffer(koordinate, 5);
		spielfeld.loescheZiffer(koordinate);

		EnumContainer.Serverantwort s = spielfeld.setzeZiffer(koordinate, 6);

		assertEquals("pruefe setze Ziffer, Serverantwort" + s.toString(), s,
				EnumContainer.Serverantwort.ok);
	}

}
