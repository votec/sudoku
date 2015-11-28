package common;

import java.util.ArrayList;
import java.util.List;

/**
 * Das Interface ISpielfeld dient als Schnittstelle fuer die verschiedenen Arten
 * von Sudokuspielfelder (z.B. Standardfeld, Comparisonfeld).
 * 
 * @author Sandra Gothan
 * 
 */
public interface ISpielfeld extends EnumContainer {

	/**
	 * Generiert ein vollstaendig ausgefuelltes Spielfeld mit einer unbekannten
	 * Anzahl an Startwerten.
	 */
	public void generiereSpielfeld();

	/**
	 * Generiert ein vollstaendig ausgefuelltes Spielfeld mit einem uebergebenen
	 * Schwierigkeitsgrad und der daraus resultierenden Anzahl an ausgefuellten
	 * Startfeldern..
	 * 
	 * @param stufe
	 *            der Schwierigkeitsgrad des Sudoku
	 */
	public void generiereSpielfeld(Schwierigkeitsgrad stufe);

	/**
	 * Gibt die Anzahl der gesetzten Felder zurueck.
	 * 
	 * @return die Anzahl der gesetzten Felder
	 */
	public int gibAnzahlGesetzteFelder();

	/**
	 * Gibt die Blockbreite eines Spielfeldes zurueck.
	 * 
	 * @return die Blockbreite des Spielfeldes
	 */
	public int gibBlockBreite();

	/**
	 * Gibt die Blockhoehe eines Spielfeldes zurueck.
	 * 
	 * @return die Blockhoehe des Spielfeldes
	 */
	public int gibBlockHoehe();

	/**
	 * Gibt das Feld mit der uebergebenen Koordinate im Spielfeld zurueck.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes im Spielfeld
	 * @return das Feld mit der uebergebenen Koordinate
	 */
	public IFeld gibFeld(IKoordinate koordinate);

	/**
	 * Gibt den Inhalt eines Feldes zurueck.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes im Spielfeld
	 * @return die im Feld eingetragene Ziffer
	 */
	public int gibFeldinhalt(IKoordinate koordinate);

	/**
	 * Gibt die moeglichen Ziffern fuer ein Feld zurueck, die zu keinen
	 * Konflikten fuehren.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes im Spielfeld
	 * @return die Liste mit allen moeglichen Ziffern fuer das Feld
	 */
	public List<Integer> gibFeldMoeglichkeiten(IKoordinate koordinate);

	/**
	 * Gibt zurueck, welche Felder zu Konflikten fuehren (ohne
	 * Startbelegungsfelder).
	 * 
	 * @return die Liste mit den Koordinaten der Konfliktfelder
	 */
	public ArrayList<IKoordinate> gibKonfliktfelder();

	/**
	 * Gibt die Breite eines Spielfeldes zurueck.
	 * 
	 * @return die Breite des Spielfeldes
	 */
	public int gibSpielfeldBreite();

	/**
	 * Gibt die Hoehe eines Spielfeldes zurueck.
	 * 
	 * @return die Hoehe des Spielfeldes
	 */
	public int gibSpielfeldHoehe();

	/**
	 * Loescht die in einem Feld eingetragene Ziffer.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes im Spielfeld
	 * @return ok, feldLeer, feldIstInStartbelegung
	 */
	public Serverantwort loescheZiffer(IKoordinate koordinate);

	/**
	 * Setzt das Startspielfeld anhand der vollstaendig ausgefuellten
	 * Spielfeldloesung.
	 * 
	 * @param spielfeldloesung
	 *            die Spielfeldloesung
	 */
	public void setzeStartspielfeld(ISpielfeld spielfeldloesung);

	/**
	 * Traegt eine Ziffer in ein Feld ein.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes im Spielfeld
	 * @param ziffer
	 *            die zu setzende Ziffer
	 * @return ok, feldNichtLeer, spielfeldGeloest, spielfeldFalschGeloest
	 */
	public Serverantwort setzeZiffer(IKoordinate koordinate, int ziffer);
}
