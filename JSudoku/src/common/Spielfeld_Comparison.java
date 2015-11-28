package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * Die Klasse fuer ein Comparison-Spielfeld enthaelt eine Methode zum Erzeugen von
 * Spielfeldern inklusive der Zeichen "<" und ">" innerhalb  des Sudokus.
 *
 * @author guest25
 *
 */
public class Spielfeld_Comparison extends ASpielfeld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5113086185892008265L;
	
	/**
	 * HashMap mit einem String (Codierung von 2 Koordinaten) von Feldern
	 * als Schluessel, speichert dann ab, welches Zeichen (<,>) zwischen 
	 * den Feldern steht
	 */
	public HashMap<String, Character> zeichen; 
	/**
	 * gibt an, ob gerade ein neues Comparison-Spielfeld aufgebaut wird
	 */
	private boolean spielfeldWirdAufgebaut = false;
	
	/**
	 * Konstruktor zum Erzeugen eines Comparison-Sudoku
	 */
	public Spielfeld_Comparison() {
		setzeSpielfeldBreite(3);
		setzeSpielfeldHoehe(3);
		setzeBlockBreite(3);
		setzeBlockHoehe(3);
		initSpielfeld();
		
		/* Anlegen der HashMap zeichen */
		zeichen = new HashMap<String, Character>();
	}
	
	/**
	 * Gibt die HashMap mit den eingetragenen Zeichen zurueck.
	 */
	public HashMap<String, Character> gibZeichen() {
		return this.zeichen;
	}
	
	/**
	 * @see ISpielfeld#setzeStartspielfeld(ISpielfeld)
	 */
	@Override
	public void setzeStartspielfeld(ISpielfeld spielfeldloesung) {
		this.zeichen.putAll(((Spielfeld_Comparison) spielfeldloesung).gibZeichen());
		super.setzeStartspielfeld(spielfeldloesung);
	}
	
	/**
	 * Gibt zurueck, ob die Koordinate in der Liste gefunden wurde.
	 * 
	 * @param liste	eine Liste von Koordinaten
	 * @param koordinate	zu suchende Koordinate
	 * @return ob Koordinate enthalten ist
	 */
	private boolean enthaeltListeKoordinate(ArrayList<IKoordinate> liste, IKoordinate koordinate) {
		
		// Erstelle einen Iterator der Koordinaten-Liste
		Iterator<IKoordinate> it = liste.iterator();

		// Iteriere ueber die Liste der Koordinaten
		while (it.hasNext()) {
			IKoordinate k = it.next();
			
			if ((k.gibX() == koordinate.gibX()) && (k.gibY() == koordinate.gibY())) {
				// Koordinate gefunden in der Liste
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Fuegt in die HashMap zeichen ein Element ein, welches beim Vergleich
	 * zweier Felder herausgekommen ist.
	 * 
	 * @param k1	Koordinate des ersten Feldes
	 * @param k2	Koordinate des zweiten Feldes
	 */
	private void fuegeInZeichenEin(IKoordinate k1, IKoordinate k2, ArrayList<IKoordinate> liste) {
		
		// Koordinate existiert, wenn beide (x,y) ungleich 0	
		if ( (k2.gibX() != 0) && (k2.gibY() != 0) && (k2.gibX()!=10) && (k2.gibY()!=10) ) {		
			
			if (enthaeltListeKoordinate(liste,k2)) {
				// wenn Feld in Liste enthalten, vergleiche die beiden Inhalte miteinander

				// Inhalt der beiden Felder
				int inhaltk1 = gibFeld(k1).gibInhalt();
				int inhaltk2 = gibFeld(k2).gibInhalt();
				
				// erzeuge Schluessel zum Hinzufuegen zur HashMap zeichen
				String schluessel = k1.gibX() + "_" + k1.gibY() + "_" + k2.gibX() + "_" + k2.gibY();
				
				// zeichen hinzufuegen entsprechend dem Vergleich
				if (inhaltk1 < inhaltk2) {
					zeichen.put(schluessel, '<');
				} else {
					zeichen.put(schluessel, '>');
				}
			}
			
		}
		
	}
	
	/**
	 * @see ISpielfeld#generiereSpielfeld(common.EnumContainer.Schwierigkeitsgrad)
	 */
	@Override
	public void generiereSpielfeld(Schwierigkeitsgrad stufe) {
	
		int sudokuHoehe = (gibSpielfeldHoehe() * gibBlockHoehe());
		int sudokuBreite = (gibSpielfeldBreite() * gibBlockBreite());
		
		// Start des Spielfeldgenerierens
		spielfeldWirdAufgebaut = true;
		
		// erzeuge ein Sudoku mit 81 Startwerten
		erzeugeSudoku(sudokuHoehe*sudokuBreite);
		
		// fertig mit Spielfeld generieren
		spielfeldWirdAufgebaut = false;
				
		// ermittelt die Anzahl an Startwerten entsprechend des Schwierigkeitsgrades
		int anzahlStartwerte = 0;
		 switch (stufe) {
		 	case anfaenger:
		 		anzahlStartwerte = 30;
		 		break;
		 	case mittel:
		 		anzahlStartwerte = 20;
		 		break;
		 	case knifflig:
		 		anzahlStartwerte = 15;
		 		break;
		 	case schwer:
		 		anzahlStartwerte = 10;
		 		break;
		 	case profi:	
		 		anzahlStartwerte = 0;
		 		break;
		 }
		
		// setzt die maximale Anzahl an Feldern
		int anzahl = gibAnzahlGesetzteFelder();
		
		// erzeuge Zufallszahlengenerator
		Random rand = new Random();
		
		/*
		 * setzt solange bei einem zufaelliges Feld, das es nicht 
		 * in der Startbelegung ist, bis die gewuenschte Anzahl von 
		 * Startwerten erreicht ist
		 */
		while (anzahl > anzahlStartwerte) {

			// zufaellige Koordinate im Spielfeld generieren
			int zeilennummer = (int) (Math.abs(rand.nextInt()) % sudokuHoehe) + 1;
			int spaltennummer = (int) (Math.abs(rand.nextInt()) % sudokuBreite) + 1;
			IKoordinate koordinate = new Koordinate(spaltennummer, zeilennummer);

			// wenn das Feld noch nicht entfernt wurde aus dem Spielfeld
			if (gibFeld(koordinate).istInStartbelegung()) {
				gibFeld(koordinate).setzeStartbelegung(false);
				anzahl--;
			}
		}
		
		setzeAnzahlGesetzteFelder(anzahlStartwerte);
		
		/*
		 * durchlaeuft alle Bloecke im Sudoku und traegt bei jeweils 
		 * benachbarten Feldern mit einem entsprechenden Schluessel
		 * das zugehoerige Zeichen in die HashMap zeichen ein
		 */
		// Iteration ueber alle 9 Bloecke
		for (int block = 0; block < 9; block++) {

			ArrayList<IKoordinate> koordinatenListe = bloecke[block]
					.gibKoordinatenAllerFelder();

			// erzeuge Iterator fuer die koordinatenliste
			Iterator<IKoordinate> it = koordinatenListe.iterator();

			while (it.hasNext()) {

				IKoordinate k = it.next();

				// pruefe, ob noerdliches Feld existiert
				IKoordinate kNord = new Koordinate(k.gibX(), k.gibY() - 1);
				fuegeInZeichenEin(k,kNord,koordinatenListe);
				
				// pruefe, ob suedliches Feld existiert
				IKoordinate kSued = new Koordinate(k.gibX(), k.gibY() + 1);
				fuegeInZeichenEin(k,kSued,koordinatenListe);

				// pruefe, ob westliches Feld existiert
				IKoordinate kWest = new Koordinate(k.gibX() - 1, k.gibY());
				fuegeInZeichenEin(k,kWest,koordinatenListe);

				// pruefe, ob oestliches Feld existiert
				IKoordinate kOst = new Koordinate(k.gibX() + 1, k.gibY());
				fuegeInZeichenEin(k,kOst,koordinatenListe);
			
			}

		}
		System.out.println();
	}

	/**
	 * Gibt zu zwei Feldern mit Koordinaten zurueck, ob Ziffern falsch gesetzt wurden
	 * in Bezug auf die "<" und ">" Zeichen.
	 * 
	 * @param k1	Koordinate des ersten Feldes
	 * @param k2	Koordinate des zweiten Feldes
	 * @return ob Konflikt vorhanden
	 */
	private boolean pruefeAufKonflikt(IKoordinate k1, IKoordinate k2) {
		
		// gibt an, ob Konflikt vorhanden
		boolean konfliktVorhanden = false;
		
		// initialiseren des Schluessel zum Auslesen aus der HashMap
		String schluessel = "";
			
		// pruefe, ob Feld existiert
		// wenn Koordinate = (0,.) oder (.,0) dann nicht existent
		if ( (k2.gibX() != 0) && (k2.gibY() != 0) && (k2.gibX()!=10) && (k2.gibY()!=10) ) {
				
			// wenn Inhalt nicht leer, dann Konflikt moeglich
			if (gibFeld(k2).gibInhalt() != 0) {
				
				// zusammenbauen des Schluessels
				schluessel = k1.gibX() + "_" + k1.gibY() + "_" + k2.gibX() + "_" + k2.gibY();
				
				if (gibZeichen().containsKey(schluessel)) {
					// wenn Schluessel existiert, vergleiche die beiden
					// Inhalte miteinander
					
					// Inhalt der beiden Felder
					int inhaltk1 = gibFeld(k1).gibInhalt();
					int inhaltk2 = gibFeld(k2).gibInhalt();
					
					if (gibZeichen().get(schluessel).equals('<')) {
						if (inhaltk1 > inhaltk2) {
							// Zeichen zwischen beiden Feldern stimmt nicht
							konfliktVorhanden = true;
						}
					} else {
						if (inhaltk1 < inhaltk2) {
							// Zeichen zwischen beiden Feldern stimmt nicht
							konfliktVorhanden = true;
						}
					}
				}
			}
			
		}
		return konfliktVorhanden;
	}
	
	/**
	 * @see ISpielfeld#gibKonfliktfelder()
	 */
	@Override
	public ArrayList<IKoordinate> gibKonfliktfelder() {

		ArrayList<IKoordinate> konflikteComparison = new ArrayList<IKoordinate>();
		konflikteComparison.clear();

		if (!spielfeldWirdAufgebaut) {			
			
			// Berechne laenge und Breite des Sudokufeldes
			int sudokuHoehe = (gibSpielfeldHoehe() * gibBlockHoehe());
			int sudokuBreite = (gibSpielfeldBreite() * gibBlockBreite());
	
			/*
			 * durchlaeuft alle Felder im Sudoku, und ueberprueft benachbarte 
			 * Felder, ob Zeichen zwischen den beiden Inhalten der Felder noch stimmt
			 * wenn nicht, Konfliktfeld gefunden
			 */
			for (int i = 0; i < sudokuBreite; i++) {
				for (int j = 0; j < sudokuHoehe; j++) {
	
					IKoordinate k = new Koordinate(i + 1, j + 1);
					
					if ((gibFeld(k).gibInhalt() != 0) && (!gibFeld(k).istInStartbelegung()) ) {
						// Feld ist nicht leer
						
						// Feld wurde noch nicht in Konfliktliste aufgenommen
						// verhindert doppeltes Einfuegen
						boolean eingefuegt = false;
	
						// pruefe, ob noerdliches Feld stimmt
						IKoordinate kNord = new Koordinate(k.gibX(), k.gibY() - 1);
						if (pruefeAufKonflikt(k,kNord) && (!eingefuegt)) {
							konflikteComparison.add(k);
							eingefuegt = true;
						}
						
						// pruefe, ob suedliches Feld stimmt
						IKoordinate kSued = new Koordinate(k.gibX(), k.gibY() + 1);
						if (pruefeAufKonflikt(k,kSued) && (!eingefuegt)) {
							konflikteComparison.add(k);
							eingefuegt = true;
						}											
		
						// pruefe, ob westliches Feld stimmt
						IKoordinate kWest = new Koordinate(k.gibX() - 1, k.gibY());
						if (pruefeAufKonflikt(k,kWest) && (!eingefuegt)) {
							konflikteComparison.add(k);
							eingefuegt = true;
						}
												
						// pruefe, ob oestliches Feld stimmt
						IKoordinate kOst = new Koordinate(k.gibX() + 1, k.gibY());
						if (pruefeAufKonflikt(k,kOst) && (!eingefuegt)) {
							konflikteComparison.add(k);
							eingefuegt = true;
						}
										
					}
				}
			}
		}	

		ArrayList<IKoordinate> temp_liste = super.gibKonfliktfelder();
		fuegeListenZusammen(konflikteComparison, temp_liste);

		return konflikteComparison;
	}

}
