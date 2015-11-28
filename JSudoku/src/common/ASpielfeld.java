package common;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Die Klasse ASpielfeld enthaelt Instanzvariablen und Methoden, die jedem
 * Spielfeldtypen zur Verfuegung stehen. Die Methoden zum Generieren gueltiger
 * Spielfelder und dem Pruefen auf Konflikte werden in den Subklassen
 * implementiert.
 * 
 * @author Sandra Gothan
 * 
 */
public abstract class ASpielfeld implements ISpielfeld, Serializable {

	/** 2D-Array fuer das Spielfeld mit Feldern. */
	protected IFeld[][] felder;
	/**
	 * Array von Bloecken, in dem die zugehoerigen Felder zu einem Block
	 * eingetragen werden.
	 */
	protected IEinheit[] bloecke;
	/**
	 * Array von Zeilen, in dem die zugehoerigen Felder zu einer Zeile
	 * eingetragen werden.
	 */
	protected IEinheit[] zeilen;
	/**
	 * Array von Spalten, in dem die zugehoerigen Felder zu einer Spalte
	 * eingetragen werden.
	 */
	protected IEinheit[] spalten;
	/**
	 * Beinhaltet bei eindeutigen Sudokus die richtige Loesung fuer ein
	 * Spielfeld.
	 */
	protected int[][] felderLoesung;
	/**
	 * Enthaelt die Anzahl der bereits ausgefuellten Felder im Spielfeld.
	 */
	private int anzahlGesetzteFelder;
	/** Gibt die Breite des Spielfeldes an. */
	private int spielfeldBreite;
	/** Gibt die Hoehe des Spielfeldes an. */
	private int spielfeldHoehe;
	/** Gibt die Breite eines Blocks an. */
	private int blockBreite;
	/** Gibt die Hoehe eines Blocks an. */
	private int blockHoehe;
	/**
	 * Gibt die groesstmoegliche Ziffer an, welche in das Sudoku eingetragen
	 * werden darf; Standard = 9.
	 */
	private int maxZiffer;
	/**
	 * Gibt an, wie viele Loesungen ein Spielfeld besitzt: 0 = keine Loesung; 1 =
	 * eindeutig; >1 = mehrdeutig
	 */
	private int anzLoesung;

	private boolean sackgasse;

	/**
	 * Entfernt alle Ziffern mit den uebergebenen Koordinaten
	 */
	private void entferneZiffern(List<IKoordinate> ziffern) {
		// Iteriere ueber Koordinatenliste und loesche Ziffern
		for (IKoordinate koordinate : ziffern) {
			loescheZiffer(koordinate);
		}
	}

	/**
	 * Erzeugt solange ein zufaelliges Spielfeld mit einer vorgegebenen Anzahl
	 * von Startwerten, bis das Spielfeld eindeutig ist.
	 * 
	 * @param anzahlStartwerte
	 *            Anzahl der Startwerte
	 */
	protected void erzeugeSudoku(int anzahlStartwerte) {

		// Anzahl gefundener Loesungen auf Null setzen
		setzeAnzLoesung(0);

		// Erzeuge Zufallszahlen
		Random rand = new Random();

		// Berechne laenge und Breite des Sudokufeldes
		int sudokuHoehe = (gibSpielfeldHoehe() * gibBlockHoehe());
		int sudokuBreite = (gibSpielfeldBreite() * gibBlockBreite());

		// generiert ein ausgefuelltes Spielfeld
		fuelleSpielfeldAus(0);		 		
		
		// kopiert die Loesung zur Speicherung um, um Ziffern anschliessend
		// entfernen zu koennen
		for (int i = 0; i < sudokuBreite; i++) {
			for (int j = 0; j < sudokuHoehe; j++) {
				IKoordinate k = new Koordinate(i + 1, j + 1);
				gibFeld(k).setzeStartbelegung(true);
				felderLoesung[i][j] = gibFeld(k).gibInhalt();
			}
		}

		// EndLoesung gefunden -> alle Felder ausgefuellt
		setzeAnzahlGesetzteFelder(sudokuHoehe * sudokuBreite);

		// anzahl wird auf die Anzahl von Feldern im Spielfeld gesetzt
		int anzahl = sudokuHoehe * sudokuBreite;

		// gibt an, wie oft ein Feld im Spielfeld nicht
		// erfolgreich geloescht werden konnte, da dann keine Eindeutigkeit mehr
		int anzahlZifferLoeschenNichtMoeglich = 0;

		/*
		 * es werden solange Felder aus der Startbelegung entfernt, mit
		 * anschliessender Ueberpruefung auf Eindeutigkeit, bis die Anzahl der
		 * Startwerte erreicht ist
		 */
		while (anzahl > anzahlStartwerte) {

			// zufaellige Koordinate im Spielfeld generieren
			int zeilennummer = (int) (Math.abs(rand.nextInt()) % sudokuHoehe) + 1;
			int spaltennummer = (int) (Math.abs(rand.nextInt()) % sudokuBreite) + 1;
			IKoordinate koordinate = new Koordinate(spaltennummer, zeilennummer);

			// wenn das Feld noch nicht entfernt wurde aus dem Spielfeld
			if (!(gibFeld(koordinate).istLeer())) {

				// loeschen des Feldes und pruefen auf Eindeutigkeit
				int zahl = gibFeld(koordinate).gibInhalt();
				gibFeld(koordinate).setzeStartbelegung(false);
				loescheZiffer(koordinate);

				if (sudokuBreite == 16) {
					// wenn Fudschijma 4x4 -> dann mehrdeutig erlaubt
					anzahl--;
				} else {
					// wenn Sudoku eindeutig sein muss

					setzeAnzLoesung(0);
					pruefeEindeutigeLoesbarkeit(0);
					
					setzeNurStartbelegung();
					
					/*if (anzahl <= 67) {
						System.out.println(anzahl);
					}*/
					
					if (gibAnzLoesung() == 1) {
						// wenn eindeutig, dann eine Ziffer weniger im Spielfeld
						anzahl--;
						anzahlZifferLoeschenNichtMoeglich = 0;
					} else {
						// ansonsten Ziffer wieder setzen und zur Startbelegung
						// hinzufuegen
						setzeZiffer(koordinate, zahl);
						gibFeld(koordinate).setzeStartbelegung(true);
						anzahlZifferLoeschenNichtMoeglich++;

					}
				}
			} else {
				// Ziffer wurde bereits aus dem Feld entfernt
				if (sudokuBreite!=16) {
					// Im 4x4 Sudoku erhoehe Counter, wie oft eine
					// Ziffer nicht geloescht werden konnte.
					anzahlZifferLoeschenNichtMoeglich++;
				}
			}

			// wenn zu oft keine Ziffer mehr geloescht werden konnte in einem Sudoku,
			// dann beginne mit dem Loeschen erneut
			if (anzahlZifferLoeschenNichtMoeglich >= 50) {
				// zuruecksetzen	
				System.out.println("Ziffer zu oft geloscht");
				for (int i = 0; i < sudokuBreite; i++) {
					for (int j = 0; j < sudokuHoehe; j++) {
						IKoordinate k = new Koordinate(i + 1, j + 1);
						if (!(gibFeld(k).istInStartbelegung())) {
							gibFeld(k).setzeInhalt(felderLoesung[i][j]);
							gibFeld(k).setzeStartbelegung(true);
						}
					}
				}
				anzahl = sudokuHoehe * sudokuBreite;
				setzeAnzahlGesetzteFelder(sudokuHoehe * sudokuBreite);
			}

		}
		
		setzeAnzahlGesetzteFelder(anzahlStartwerte);
		
		// alle verbleibenden Zahlen im Spielfeld zur Startbelegung hinzufuegen;
		// Loesung ins aktuelle Spielfeld mitaufnehmen
		for (int i = 0; i < sudokuBreite; i++) {
			for (int j = 0; j < sudokuHoehe; j++) {
				IKoordinate k = new Koordinate(i + 1, j + 1);
				if (!(gibFeld(k).istInStartbelegung())) {
					gibFeld(k).setzeInhalt(felderLoesung[i][j]);
				}
			}
		}

	}

	/**
	 * Setzt den Inhalt aller Felder auf 0, ausser der Startbelegung.
	 */
	private void setzeNurStartbelegung() {
		// Berechne laenge und Breite des Sudokufeldes
		int sudokuHoehe = (gibSpielfeldHoehe() * gibBlockHoehe());
		int sudokuBreite = (gibSpielfeldBreite() * gibBlockBreite());
		for (int i = 0; i < sudokuBreite; i++) {
			for (int j = 0; j < sudokuHoehe; j++) {
				IKoordinate k = new Koordinate(i + 1, j + 1);
				if (!gibFeld(k).istInStartbelegung()) {
					// wenn Feld nicht in Startbelegung,
					// Inhalt auf 0 setzen
					gibFeld(k).setzeInhalt(0);
				}
			}
		}
	}
	
	/**
	 * Fuegt zwei Listen zu einer Liste zusammen, so dass keine doppelten
	 * Elemente enthalten sind.
	 * 
	 * @param liste1
	 *            erste Liste
	 * @param liste2
	 *            zweite Liste
	 * @return vereinigte Liste
	 */
	protected ArrayList<IKoordinate> fuegeListenZusammen(
			ArrayList<IKoordinate> liste1, ArrayList<IKoordinate> liste2) {
		int anz_liste2 = liste2.size();
		for (int i = 0; i < anz_liste2; i++) {
			IKoordinate koordinate = liste2.get(i);
			if (!liste1.contains(koordinate)) {
				liste1.add(koordinate);
			}
		}
		return liste1;
	}

	/**
	 * Fuellt das Spielfeld mit einer beliebigen Loesung aus, so dass keine
	 * Konflikte entstehen.
	 * 
	 * @param position
	 *            gibt die Anzahl der bereits gesetzten Ziffern im Spielfeld an
	 */
	private boolean fuelleSpielfeldAus(int position) {

		// maximal ausfuellbare Felder fuer ein Sudoku
		int gesamtZahlen = gibMaxZiffer() * gibSpielfeldBreite()
				* gibSpielfeldHoehe() - 1;
		
		// Berechnet anhand des Zaehlers in welcher Zeile und Spalte man sich
		// befindet.
		int zeilennummer = position / (gibSpielfeldHoehe() * gibBlockHoehe());
		int spaltennummer = position
				% (gibSpielfeldBreite() * gibBlockBreite());
		
		// Erstelle Koordinate des aktuellen Elements
		IKoordinate koordinate = new Koordinate(spaltennummer + 1,
				zeilennummer + 1);
		
		// Hat Sudoku noch freie Felder
		if (gesamtZahlen >= position) {
		
			if (gibFeld(koordinate).istLeer() == false) {
				// Aktuelles Feld belegt
				// Fahre fort mit naechstem Feld
				if (fuelleSpielfeldAus(position + 1) == true) {
					return true;
				}

			} else if (gibFeld(koordinate).istLeer() == true) {
				// Aktuelles Feld leer
				// Trage der Reihe nach die moeglichen Ziffern ein und loese
				// Sudoku rekursiv

				List<Integer> ziffernliste = gibFeldMoeglichkeiten(koordinate);
				if(ziffernliste.size() == 0) {
					// Wenn keine Ziffer gesetzt werden kann!
					return false;
				}
				
				// Mixe die Liste durch
				Collections.shuffle(ziffernliste);

				for (Integer ziffer : ziffernliste) {
					
					// Setze Ziffer
					setzeZiffer(koordinate, ziffer);
					boolean loesungGefunden = fuelleSpielfeldAus(position + 1);
					
					if (loesungGefunden) {
						// Spielfeld gefunden
						return true;
					} else {
						// entferne Ziffer wieder
						loescheZiffer(koordinate);
					}
					
				}
			}
		} else { // position>gesamtZahlen

			// Sudokufeld ist vollstaendig ausgefuellt. Blatt im Rekursionsbaum
			return true;
			
		}

		// Standardrueckgabe
		return false;
	}

	/**
	 * @see ISpielfeld#generiereSpielfeld
	 */
	public void generiereSpielfeld() {
		for (int i = 0; i < (gibSpielfeldBreite() * gibBlockBreite()); i++) {
			for (int j = 0; j < (gibSpielfeldHoehe() * gibBlockHoehe()); j++) {
				IKoordinate koordinate = new Koordinate(i + 1, j + 1);
				if (gibFeld(koordinate).gibInhalt() != 0) {
					gibFeld(koordinate).setzeStartbelegung(true);
				}
			}
		}
		pruefeEindeutigeLoesbarkeit(0);
		if (gibAnzLoesung() == 1) {
			fuelleSpielfeldAus(0);
		} else {
			System.out.println("Spielfeld nicht eindeutig");
		}
	}

	/**
	 * @see ISpielfeld#generiereSpielfeld(common.EnumContainer.Schwierigkeitsgrad)
	 */
	public abstract void generiereSpielfeld(Schwierigkeitsgrad stufe);

	/**
	 * @see ISpielfeld#gibAnzahlGesetzteFelder()
	 */
	public int gibAnzahlGesetzteFelder() {
		return anzahlGesetzteFelder;
	}

	/**
	 * Gibt die Anzahl der Loesungen eines Spielfeldes anhand der Startbelegung
	 * zurueck.
	 * 
	 * @return Anzahl der Loesungen
	 */
	public int gibAnzLoesung() {
		return anzLoesung;
	}

	/**
	 * @see ISpielfeld#gibBlockBreite()
	 */
	public int gibBlockBreite() {
		return blockBreite;
	}

	/**
	 * @see ISpielfeld#gibBlockHoehe()
	 */
	public int gibBlockHoehe() {
		return blockHoehe;
	}

	/**
	 * Gibt die Nummer des Blockes zurueck, in welcher sich das Feld mit der
	 * uebergebenen Koordinate befindet.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @return die Nummer des Blocks
	 */
	protected int gibBlocknummerZuKoordinate(IKoordinate koordinate) {
		return gibSpielfeldBreite()
				* ((koordinate.gibY() - 1) / gibBlockHoehe())
				+ ((koordinate.gibX() - 1) / gibBlockBreite());
	}

	/**
	 * @see ISpielfeld#gibFeld(IKoordinate)
	 */
	public IFeld gibFeld(IKoordinate koordinate) {
		return felder[koordinate.gibX() - 1][koordinate.gibY() - 1];
	}

	/**
	 * @see ISpielfeld#gibFeldinhalt(IKoordinate)
	 */
	public int gibFeldinhalt(IKoordinate koordinate) {
		return gibFeld(koordinate).gibInhalt();
	}

	/**
	 * @see ISpielfeld#gibFeldMoeglichkeiten(IKoordinate)
	 */
	public List<Integer> gibFeldMoeglichkeiten(IKoordinate koordinate) {
		List<Integer> moeglich = new ArrayList<Integer>();
		moeglich.clear();
		if (gibFeld(koordinate).istLeer()) {
			for (int i = 1; i <= gibMaxZiffer(); i++) {
				int nummer = gibBlocknummerZuKoordinate(koordinate);
				boolean inBlockVorhanden = bloecke[nummer].pruefeZahl(i);
				boolean inZeileVorhanden = zeilen[koordinate.gibY() - 1]
						.pruefeZahl(i);
				boolean inSpalteVorhanden = spalten[koordinate.gibX() - 1]
						.pruefeZahl(i);
				if (!inBlockVorhanden && !inZeileVorhanden
						&& !inSpalteVorhanden) {
					moeglich.add(i);
				}
			}
		}
		return moeglich;
	}

	/**
	 * @see ISpielfeld#gibKonfliktfelder()
	 */
	public ArrayList<IKoordinate> gibKonfliktfelder() {
		ArrayList<IKoordinate> konflikte = new ArrayList<IKoordinate>();
		konflikte.clear();
		ArrayList<IKoordinate> temp_liste = new ArrayList<IKoordinate>();
		temp_liste.clear();
		for (int i = 0; i < bloecke.length; i++) {
			temp_liste = bloecke[i].gibKonfliktfelder();
			konflikte = fuegeListenZusammen(konflikte, temp_liste);
			temp_liste.clear();
		}
		for (int i = 0; i < zeilen.length; i++) {
			temp_liste = zeilen[i].gibKonfliktfelder();
			konflikte = fuegeListenZusammen(konflikte, temp_liste);
			temp_liste.clear();
		}
		for (int i = 0; i < spalten.length; i++) {
			temp_liste = spalten[i].gibKonfliktfelder();
			konflikte = fuegeListenZusammen(konflikte, temp_liste);
			temp_liste.clear();
		}
		return konflikte;
	}

	/**
	 * Gibt die groesstmoegliche Ziffer zurueck, die in das Sudoku eingetragen
	 * werden darf.
	 * 
	 * @return die groesstmoegliche Ziffer
	 */
	protected int gibMaxZiffer() {
		return maxZiffer;
	}

	/**
	 * @see ISpielfeld#gibSpielfeldBreite()
	 */
	public int gibSpielfeldBreite() {
		return spielfeldBreite;
	}

	/**
	 * @see ISpielfeld#gibSpielfeldHoehe()
	 */
	public int gibSpielfeldHoehe() {
		return spielfeldHoehe;
	}

	/**
	 * Initialisiert die Felder und Loesungsfelder eines Spielfeldes mit
	 * Feldern. Felder werden in zugehoerige Bloecke, Zeilen und Spalten
	 * eingefuegt.
	 */
	protected void initSpielfeld() {
		setzeMaxZiffer();
		felder = new IFeld[gibSpielfeldBreite() * gibBlockBreite()][gibSpielfeldHoehe()
				* gibBlockHoehe()];
		bloecke = new IEinheit[gibSpielfeldBreite() * gibSpielfeldHoehe()];
		zeilen = new IEinheit[gibSpielfeldHoehe() * gibBlockHoehe()];
		spalten = new IEinheit[gibSpielfeldBreite() * gibBlockBreite()];

		/* Initialisieren der Einheiten */
		for (int i = 0; i < (gibSpielfeldBreite() * gibSpielfeldHoehe()); i++) {
			bloecke[i] = new Einheit();
		}
		for (int i = 0; i < (gibSpielfeldHoehe() * gibBlockHoehe()); i++) {
			zeilen[i] = new Einheit();
		}
		for (int i = 0; i < (gibSpielfeldBreite() * gibBlockBreite()); i++) {
			spalten[i] = new Einheit();
		}

		setzeAnzahlGesetzteFelder(0);
		for (int i = 0; i < gibSpielfeldBreite() * gibBlockBreite(); i++) {
			for (int j = 0; j < gibSpielfeldHoehe() * gibBlockHoehe(); j++) {
				IKoordinate koordinate = new Koordinate(i + 1, j + 1);
				IFeld feld = new Feld(koordinate);
				felder[i][j] = feld;
				int nummer = gibBlocknummerZuKoordinate(koordinate);
				bloecke[nummer].fuegeFeldHinzu(feld);
				zeilen[j].fuegeFeldHinzu(feld);
				spalten[i].fuegeFeldHinzu(feld);
			}
		}

		felderLoesung = new int[gibSpielfeldBreite() * gibBlockBreite()][gibSpielfeldHoehe()
				* gibBlockHoehe()];
	}

	/**
	 * @see ISpielfeld#loescheZiffer(IKoordinate)
	 */
	public Serverantwort loescheZiffer(IKoordinate koordinate) {
		if (gibFeld(koordinate).istInStartbelegung()) {
			return Serverantwort.feldInStartbelegung;
		}
		if (gibFeld(koordinate).istLeer()) {
			return Serverantwort.feldLeer;
		}
		gibFeld(koordinate).setzeInhalt(0);
		setzeAnzahlGesetzteFelder(gibAnzahlGesetzteFelder() - 1);
		return Serverantwort.ok;
	}

	/**
	 * Ueberprueft eine Spielfeldloesung auf Eindeutigkeit.
	 * 
	 * @param zaehler
	 *            gibt an, wie viele Zahlen bereits ins Spielfeld eingetragen
	 *            wurden
	 * @return gibt zurueck, ob die Spielfeldloesung eindeutig ist
	 */
	private void pruefeEindeutigeLoesbarkeit(int position) {
		sackgasse = false;

		// maximal ausfuellbare Felder fuer ein Sudoku
		int gesamtZahlen = gibMaxZiffer() * gibSpielfeldBreite()
				* gibSpielfeldHoehe() - 1;

		// Berechnet anhand des Zaehlers in welcher Zeile und Spalte man sich
		// befindet.
		int zeilennummer = position / (gibSpielfeldHoehe() * gibBlockHoehe());
		int spaltennummer = position
				% (gibSpielfeldBreite() * gibBlockBreite());

		// Erstelle Koordinate des aktuellen Elements
		IKoordinate koordinate = new Koordinate(spaltennummer + 1,
				zeilennummer + 1);

		// Hat Sudoku noch freie Felder
		if (gesamtZahlen >= position) {

			List<IKoordinate> eindeutigeZiffern = setzeEindeutigeZiffern();

			if (!sackgasse) {

				if (gibFeld(koordinate).istLeer() == false) {
					// Aktuelles Feld belegt
					// Fahre fort mit naechstem Feld
					pruefeEindeutigeLoesbarkeit(position + 1);

				} else if (gibFeld(koordinate).istLeer() == true) {
					// Aktuelles Feld leer
					// Trage der Reihe nach die moeglichen Ziffern ein und loese
					// Sudoku rekursiv

					List<Integer> ziffernliste = gibFeldMoeglichkeiten(koordinate);
					Collections.shuffle(ziffernliste);
					for (int i = 0; i < ziffernliste.size(); i++) {

						// Pruefe ob es bereits mehrere Loesungen gab
						if (gibAnzLoesung() < 2) {
							// Setze Ziffer
							setzeZiffer(koordinate, ziffernliste.get(i));

							// Setze Pruefung ab de naechsten Feld fort
							pruefeEindeutigeLoesbarkeit(position + 1);

							// Loesche ziffer wieder un fahre fort
							loescheZiffer(koordinate);
						}
					}
				}
			}

			entferneZiffern(eindeutigeZiffern);

		} else { // position>gesamtZahlen
			// Sudokufeld ist vollstaendig ausgefuellt. Blatt im Rekursionsbaum
			setzeAnzLoesung(gibAnzLoesung() + 1);
		}

	}

	/**
	 * Setzt die Anzahl der ausgefuellten Felder in einem Spielfeld.
	 * 
	 * @param anz
	 *            Anzahl der ausgefuellten Felder
	 */
	protected void setzeAnzahlGesetzteFelder(int anz) {
		anzahlGesetzteFelder = anz;
	}

	/**
	 * Setzt die Anzahl der Loesungen eines Spielfeldes.
	 * 
	 * @param anzahl
	 *            Anzahl der Loesungen
	 */
	public void setzeAnzLoesung(int anzahl) {
		anzLoesung = anzahl;
	}

	/**
	 * Setzt die Blockbreite eines Spielfeldes.
	 * 
	 * @param b
	 *            Blockbreite
	 */
	protected void setzeBlockBreite(int b) {
		blockBreite = b;
	}

	/**
	 * Setzt die Blockhoehe eines Spielfeldes.
	 * 
	 * @param h
	 *            Blockhoehe
	 */
	protected void setzeBlockHoehe(int h) {
		blockHoehe = h;
	}

	/**
	 * Setzt die Ziffern, welche nur eine Moeglichkeit besitzen
	 */
	private List<IKoordinate> setzeEindeutigeZiffern() {

		List<IKoordinate> koordinaten = new ArrayList<IKoordinate>();

		// Berechne laenge und Breite des Sudokufeldes
		int sudokuHoehe = (gibSpielfeldHoehe() * gibBlockHoehe());
		int sudokuBreite = (gibSpielfeldBreite() * gibBlockBreite());

		// Ziffern im Spielfeld setzen, bei denen die Moeglichkeiten fuer Zahlen
		// fuer das Feld 1 betraegt
		boolean feldHatSichGeaendert = true;
		// laeuft solange wie sich noch Felder aendern
		while (feldHatSichGeaendert) {
			feldHatSichGeaendert = false;
 
			// Iteration ueber alle Felder
			for (int i = 0; i < sudokuBreite; i++) {
				for (int j = 0; j < sudokuHoehe; j++) {
					IKoordinate k = new Koordinate(i + 1, j + 1);
					if (gibFeld(k).istLeer()) {
						// wenn Feld leer
						List<Integer> liste = gibFeldMoeglichkeiten(k);
						if (liste.size() == 1) {
							// es gibt nur eine Moeglichkeit fuer das Feld
							gibFeld(k).setzeInhalt(liste.get(0));
							feldHatSichGeaendert = true;
							koordinaten.add(k);
						} else if (liste.size() == 0) {
							sackgasse = true;
							return koordinaten;
						}
					}
				}
			}

		}

		return koordinaten;

	}

	/**
	 * Setzt die groesstmoegliche Ziffer, die in das Sudoku eingetragen werden
	 * darf.
	 */
	protected void setzeMaxZiffer() {
		maxZiffer = gibBlockBreite() * gibBlockHoehe();
	}

	/**
	 * Setzt die Breite eines Spielfeldes.
	 * 
	 * @param b
	 *            Spielfeldbreite
	 */
	protected void setzeSpielfeldBreite(int b) {
		spielfeldBreite = b;
	}

	/**
	 * Setzt die Hoehe eines Spielfeldes.
	 * 
	 * @param h
	 *            Spielfeldhoehe
	 */
	protected void setzeSpielfeldHoehe(int h) {
		spielfeldHoehe = h;
	}

	/**
	 * @see ISpielfeld#setzeStartspielfeld(ISpielfeld)
	 */
	public void setzeStartspielfeld(ISpielfeld spielfeldloesung) {
		for (int i = 0; i < gibSpielfeldBreite() * gibBlockBreite(); i++) {
			for (int j = 0; j < gibSpielfeldHoehe() * gibBlockHoehe(); j++) {
				IKoordinate koordinate = new Koordinate(i + 1, j + 1);
				if (spielfeldloesung.gibFeld(koordinate).istInStartbelegung()) {
					gibFeld(koordinate).setzeInhalt(
							spielfeldloesung.gibFeld(koordinate).gibInhalt());
					gibFeld(koordinate).setzeStartbelegung(true);
					setzeAnzahlGesetzteFelder(gibAnzahlGesetzteFelder() + 1);
				}
			}
		}
	}

	/**
	 * @see ISpielfeld#setzeZiffer(IKoordinate, int)
	 */
	public Serverantwort setzeZiffer(IKoordinate koordinate, int ziffer) {
		if (gibFeld(koordinate).istLeer()) {
			gibFeld(koordinate).setzeInhalt(ziffer);
			setzeAnzahlGesetzteFelder(gibAnzahlGesetzteFelder() + 1);
			int maxFelder = gibMaxZiffer() * gibSpielfeldBreite()
					* gibSpielfeldHoehe();
			if (gibAnzahlGesetzteFelder() == maxFelder) {
				if (gibKonfliktfelder().size() == 0) {
					return Serverantwort.spielfeldGeloest;
				} else {
					return Serverantwort.spielfeldFalschGeloest;
				}
			}
			return Serverantwort.ok;
		}
		return Serverantwort.feldNichtLeer;
	}
}
