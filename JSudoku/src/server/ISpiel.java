package server;

import java.util.HashMap;
import java.util.List;
import common.*;

/**
 * Das Interface ISpiel dient als Schnittstelle fuer die Klasse Spiel.
 * 
 * @author Arne Busch
 * 
 */
public interface ISpiel extends EnumContainer {

	/**
	 * Generiert ein neues Spielfeld.
	 */
	public void generiereSpielfeld();

	/**
	 * Generiert ein neues Spielfeld aus einem vorgegebenen Ausgangsspielfeld.
	 */
	public void generiereSpielfeld(ISpielfeld spielfeld);

	/**
	 * Gibt zurueck ob genug Spieler angemeldet sind um ein Spiel zu starten.
	 * 
	 * @return ob genuegend Spieler angemeldet sind
	 */
	public boolean genuegendSpielerAngemeldet();

	/**
	 * Gibt die Punktestaende aller Spieler zurueck, die am Spiel teilnehmen
	 * oder teilgenommen haben.
	 * 
	 * @return eine HashMap mit dem Spielernamen als Schluessel und dem
	 *         Punktestand als Wert
	 */
	public HashMap<String, Integer> gibEndstand();

	/**
	 * Liefert die Loesung eines bestimmten Feldes zurueck.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @return die Loesung des Feldes
	 */
	public int gibFeldLoesung(IKoordinate koordinate);

	/**
	 * Gibt die Moeglichkeiten zurueck, welche in einem bestimmten Feld zu
	 * kenien momentanen Regelverstoessen fuehren.
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @return 1D boolean Array mit Markierungen fuer moegliche Ziffern.
	 */
	public List<Integer> gibFeldMoeglichkeiten(String spielername,
			IKoordinate koordinate);

	/**
	 * Gibt zurueck, welche Felder zu Konflikten fuehren (ohne Startbelegung).
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 * @return Liste der Koordinaten der Konfliktfelder
	 */
	public List<IKoordinate> gibKonfliktfelder(String spielername);

	/**
	 * Gibt den aktuellen Punktestand zurueck.
	 * 
	 * @return eine Liste mit den Punkten der Spieler
	 */
	public List<Integer> gibPunktestand();

	/**
	 * Gibt den Schwierigkeitsgrad zurueck.
	 * 
	 * @return der Schwierigkeitsgrad
	 */
	public Schwierigkeitsgrad gibSchwierigkeitsgrad();

	/**
	 * Gibt eine Liste mit den Farben der im Spiel angemeldeten Spieler zurueck.
	 * 
	 * @return die Liste mit den Spielerfarben
	 */
	public List<Farbe> gibSpielerfarbenliste();

	/**
	 * Gibt eine Liste mit den Namen der im Spiel angemeldeten Spieler zurueck.
	 * 
	 * @return die Liste mit den Spielernamen
	 */
	public List<String> gibSpielerliste();

	/**
	 * Gibt das Spielfeld des Spielers zurueck.
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 * @return Spielfeld mit der Startbelegung
	 */
	public ISpielfeld gibSpielfeld(String spielername);

	/**
	 * Gibt die Loesung des Spielfeldes als vollstaendig ausgefuelltes Spielfeld
	 * zurueck.
	 * 
	 * @return das ausgefuellte Spielfeld
	 */
	public ISpielfeld gibSpielfeldLoesung();

	/**
	 * Gibt die SpielID zurueck.
	 * 
	 * @return die SpielID
	 */
	public int gibSpielID();

	/**
	 * Gibt den Spielmodus zurueck.
	 * 
	 * @return der Spielmodus
	 */
	public Spielmodus gibSpielmodus();

	/**
	 * Gibt den aktuellen Spielstand zurueck. Gespeichert darin sind die
	 * aktuellen Spielfelder der Spieler mit den zugehoerigen Spielerfarben.
	 * Ausserdem werden der Spielmodus, die Spielvariante, der
	 * Schwierigkeitsgrad und die Loesung des Sudokus gespeichert.
	 * 
	 * @return der aktuelle Spielstand
	 */
	public ISpielstand gibSpielstand();

	/**
	 * Liefert die Spielvariante zurueck
	 * 
	 * @return die Spielvariante
	 */
	public Spielvariante gibSpielvariante();

	/**
	 * Liefert die Strafzeit zurueck.
	 * 
	 * @return die Strafzeit
	 */
	public int gibStrafzeit();

	/**
	 * Loescht eine bestimmte Ziffer im Spielfeld des Spielers.
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 * @param koordinate
	 *            die Koordinate des Feldes die Koordinate des Feldes
	 * @return ok, feldLeer, feldInStartbelegung
	 */
	public Serverantwort loescheZiffer(String spielername,
			IKoordinate koordinate);

	/**
	 * Setzt eine Ziffer im Spielfeld des Spielers.
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @param ziffer
	 *            die zu setzende Ziffer
	 * @return ok, feldNichtLeer, spielfeldGeloest
	 */
	public Serverantwort setzeZiffer(String spielername,
			IKoordinate koordinate, int ziffer);

	/**
	 * Meldet den Spieler mit Namen 'spielername' ab.
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 */
	public void spielerAbmelden(String spielername);

	/**
	 * Meldet einen Spieler bei einem Spiel an.
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 * @param spielerfarbe
	 *            die Farbe des Spielers
	 * @return ok, spielVoll, spielernameVergeben, spielerfarbeVergeben
	 */
	public Serverantwort spielerAnmelden(String spielername, Farbe spielerfarbe);

	/**
	 * Zeigt an ob das Spiel an sich lauft. Wichtig fuer den Uebergang vom
	 * Spielbeitritt zum Spiel und fuer die Abfrage ob jemand im
	 * Mehrspielermodus das Spiel fuer sich entschieden hat.
	 * 
	 * @return ob das Spiel schon/noch laeuft
	 */
	public boolean spielLaeuft();

	/**
	 * Startet ein erstelltes Spiel.
	 * 
	 * @return ok, zuWenigSpieler
	 */
	public Serverantwort spielStarten();

}