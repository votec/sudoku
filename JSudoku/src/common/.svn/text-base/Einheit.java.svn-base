package common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Die Klasse Einheit speichert Felder, die im Sudokufeld zu einer Einheit
 * (Spalte, Zeile, Block) gehoeren.
 * 
 * @author Heiko Schroeder
 * 
 */
public class Einheit implements IEinheit, Serializable {

	/** serial Versin UID (gebraucht wegen implementierung von Serializable) */
	private static final long serialVersionUID = -4036193463439802306L;
	/** eine Liste mit den in der Einheit enthaltenen Feldern */
	private ArrayList<IFeld> felder;

	/**
	 * Erzeugt eine neue Einheit ohne dazugehoerige Felder.
	 */
	public Einheit() {
		felder = new ArrayList<IFeld>();
	}

	/**
	 * @see IEinheit#gibKonfliktfelder()
	 */
public ArrayList<IKoordinate> gibKonfliktfelder() {
		 //
		 // DIES IST DIE OPTIMIERTE VERSION
		 //
		
		// Erstelle eine Liste um die gefundenen Konfliktfelder zu sammeln
		ArrayList<IKoordinate> konflikte = new ArrayList<IKoordinate>();
		
		// HashMap um Koordinate zum ers gefundenen Wert zu sichern
		HashMap<Integer,IKoordinate> koordinatenMap = new HashMap<Integer,IKoordinate>();
		
		// Erstelle Array um Feldeintraege zu merken
		int[] felderInhalt = new int[20];
		
		// Array um zu merken, ob Koordinate in HashMap zur Startbelegung gehoerte
		boolean[] startbelegung = new boolean[20];
		
		// Iteriere ueber alle Felder dieser Einheit
		for (IFeld feld : felder) {
			
			// Hole aktuelle Feldattribte
			int wert = feld.gibInhalt();
			felderInhalt[wert] = felderInhalt[wert]+1;
			IKoordinate koordinate = feld.gibKoordinate();
			
			if(feld.gibInhalt() != 0) {
				if(felderInhalt[wert]==2) {
					// Es ist bereits ein Feld mit diesem Wert gefunden.
					// Trage beide Felder als Konfliktfelder ein.
					if(!feld.istInStartbelegung()) {
						// Aktuelles Feld gehoert nicht zur Startbelegung
						konflikte.add(koordinate);
					}
					if(!startbelegung[wert]) {
						// Vorheriges Feld gehoerte nicht zur Startbelegung
						IKoordinate v = koordinatenMap.get(wert);
						konflikte.add(v);
					}
				} else if(felderInhalt[wert]>2) {
					// Dieses Feld ist ein Konfliktfeld. Trage es in die
					// Liste der Konfliktfelder ein
					if(!feld.istInStartbelegung()) {
						konflikte.add(koordinate);
					}
					
				} else if (felderInhalt[wert]<2) {
					// Dieses Feld ist kein Konfliktfeld und das erste Feld
					// mit dem entsprechenden Wert. Merke es in HashMap.
					if(feld.istInStartbelegung()) {
						startbelegung[wert]=true;
					}
					koordinatenMap.put(wert, koordinate);
				}
			}
		}
		
		return konflikte;
	}
	
	/*public ArrayList<IKoordinate> gibKonfliktfelder() {
 		//DIES IST DIE LANGSAMERE VERSION
 		// FUER TESTZWECKE BEHALTEN!
		// Erstelle eine HashMap um vorhandene Ziffern zu mappen
		HashMap<Integer, IFeld> feldHashMap = new HashMap<Integer, IFeld>();
		feldHashMap.clear();

		// Erstelle eine Liste um die gefundenen Konfliktfelder zu sammeln
		ArrayList<IKoordinate> konfliktListe = new ArrayList<IKoordinate>();
		konfliktListe.clear();

		// Erstelle einen Iterator der Feld-Liste
		Iterator<IFeld> it = felder.iterator();

		// Iteriere ueber die Liste der Felder um ein
		// Feld zu suchen welche "zahl" als Inhalt hat.
		while (it.hasNext()) {
			IFeld feld = it.next();
			int inhalt = feld.gibInhalt();
			IKoordinate koordinate = feld.gibKoordinate();

			if (feldHashMap.containsKey(inhalt)) {
				// Ein Feld mit identischem Inhalt wurde bereits gelesen
				// Fuege Feld als Konflikt hinzu
				if (!feld.istInStartbelegung()) {
					if (!konfliktListe.contains(koordinate)) {
						konfliktListe.add(koordinate);
					}
				}

				// Fuege Feld mit identischem Inhalt aus HashMap
				// als Konflikt hinzu
				IFeld hmFeld = feldHashMap.get(inhalt);
				if (!hmFeld.istInStartbelegung()) {
					IKoordinate hmKoordinate = hmFeld.gibKoordinate();
					if (!konfliktListe.contains(hmKoordinate)) {
						konfliktListe.add(hmKoordinate);
					}
				}
			} else {
				// Bisher kein Feld mit identischem Inhalt gefunden
				// Fuege Feld der HashMap hinzu
				if (inhalt != 0) {
					feldHashMap.put(inhalt, feld);
				}
			}

		}

		return konfliktListe;
	}*/

	/**
	 * @see IEinheit#fuegeFeldHinzu(IFeld)
	 */
	public void fuegeFeldHinzu(IFeld feld) {
		felder.add(feld);
	}

	/**
	 * @see IEinheit#pruefeZahl(int)
	 */
	public boolean pruefeZahl(int zahl) {
		// Erstelle einen Iterator der Feld-Liste
		Iterator<IFeld> it = felder.iterator();

		// Iteriere ueber die Liste der Felder um ein
		// Feld zu suchen welche "zahl" als Inhalt hat.
		while (it.hasNext()) {
			IFeld feld = it.next();
			if (feld.gibInhalt() == zahl) {
				return true;
			}
		}

		// Schleife durchlaufen: Kein Feld mit Inhalt "zahl"
		// in der Liste vorhanden.
		return false;
	}

	/**
	 * @see IEinheit#gibKoordinatenAllerFelder()
	 */
	public ArrayList<IKoordinate> gibKoordinatenAllerFelder() {

		// erzeugen einer neuen Liste von Koordinaten
		ArrayList<IKoordinate> liste = new ArrayList<IKoordinate>();
		liste.clear();

		// erstellt einen Iterator der Feld-Liste
		Iterator<IFeld> it = felder.iterator();

		// iteriert ueber die Liste der Felder
		while (it.hasNext()) {
			IFeld feld = it.next();
			liste.add(feld.gibKoordinate());
		}

		return liste;
	}

}
