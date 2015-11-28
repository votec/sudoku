package server;

import java.util.List;

import common.*;

/**
 * Das Interface ISpieler dient als Schnittstelle fuer die Klasse Spieler.
 * 
 * @author Arne Busch
 * 
 */
public interface ISpieler extends EnumContainer {

	/**
	 * Gibt die Anzahl der gesetzen Felder des Spielers zurueck.
	 * 
	 * @return die Anzahl der gesetzen Felder
	 */
	public int gibAnzahlGesetzteFelder();

	/**
	 * Gibt die Farbe des Spielers zurueck.
	 * 
	 * @return die Farbe des Spielers
	 */
	public Farbe gibFarbe();

	/**
	 * Gibt die moeglichen Ziffern fuer ein Feld zurueck, welche zu keinen
	 * Konflikten fuehren.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @return die Liste mit den moeglichen Ziffern
	 */
	public List<Integer> gibFeldMoeglichkeiten(IKoordinate koordinate);

	/**
	 * Gibt eine Liste mit den Koordinaten der Felder zurueck, welche zu
	 * Konflikten fuehren (ohne Startbelegungsfelder).
	 * 
	 * @return die Liste mit den Koordinaten der Konfliktfelder
	 */
	public List<IKoordinate> gibKonfliktfelder();

	/**
	 * Gibt den Namen des Spielers zurueck.
	 * 
	 * @return der Name des Spielers
	 */
	public String gibName();

	/**
	 * Gibt das Spielfeld des Spielers zurueck.
	 * 
	 * @return das Spielfeld des Spielers
	 */
	public ISpielfeld gibSpielfeld();

	/**
	 * Loescht eine Ziffer in einem Feld.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @return ok, feldLeer, feldInStartbelegung
	 */
	public Serverantwort loescheZiffer(IKoordinate koordinate);

	/**
	 * Setzt eine Ziffer in einem Feld.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @param ziffer
	 *            die zu setzende Ziffer
	 * @return ok, feldNichtLeer, spielfeldGeloest
	 */
	public Serverantwort setzeZiffer(IKoordinate koordinate, int ziffer);

}