package server;

import java.util.HashMap;
import java.util.List;
import common.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Das Interface IServer dient als Schnittstelle fuer die Klasse Server.
 * 
 * @author Heiko Schroeder
 * 
 */
public interface IServer extends EnumContainer, Remote {

	/**
	 * Erstellt ein neues Spiel aus einem gespeicherten Spielstand
	 * (Einzelspieler oder Mehrspieler). Gibt die SpielID zurueck.
	 * 
	 * @param spielstand
	 *            der gespeicherte Spielstand
	 * 
	 * @return die SpielID des Spiels
	 */
	public int erstelleSpiel(ISpielstand spielstand) throws RemoteException;;

	/**
	 * Erstellt ein neues Spiel und generiert ein neues Spielfeld. Gibt die
	 * SpielID zurueck.
	 * 
	 * @param spielmodus
	 *            der Spielmodus des Spiels
	 * @param spielvariante
	 *            die Spielvariante des Spiels
	 * @param schwierigkeitsgrad
	 *            der Schwierigkeitsgrad des Spiels
	 * @param strafzeit
	 *            die Strafzeit des Spiels
	 * 
	 * @return die SpielID des Spiels
	 */
	public int erstelleSpiel(Spielmodus spielmodus,
			Spielvariante spielvariante, Schwierigkeitsgrad schwierigkeitsgrad,
			int strafzeit) throws RemoteException;

	/**
	 * Erstellt ein neues Spiel mit einem uebergebenen Startspielfeld. Gibt die
	 * SpielID zurueck.
	 * 
	 * @param spielmodus
	 *            der Spielmodus des Spiels
	 * @param spielvariante
	 *            die Spielvariante des Spiels
	 * @param schwierigkeitsgrad
	 *            der Schwierigkeitsgrad des Spiels
	 * @param strafzeit
	 *            die Strafzeit des Spiels
	 * @param spielfeld
	 *            das Startspielfeld
	 * @return die SpielID des Spiels
	 */
	public int erstelleSpiel(Spielmodus spielmodus,
			Spielvariante spielvariante, Schwierigkeitsgrad schwierigkeitsgrad,
			int strafzeit, ISpielfeld spielfeld) throws RemoteException;

	/**
	 * Gibt zurueck, ob genuegend Spieler angemeldet sind, um ein Spiel zu
	 * starten.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @return true: es sind genuegend Spieler angemeldet; false: es sind NICHT
	 *         genuegend Spieler angemeldet
	 */
	public boolean genuegendSpielerAngemeldet(int spielID)
			throws RemoteException;

	/**
	 * Gibt den Endstand des Spiels zurueck
	 * 
	 * @param spielID
	 * @return Endstand
	 * @throws RemoteException
	 */
	public HashMap<String, Integer> gibEndstand(int spielID)
			throws RemoteException;

	/**
	 * Testmethode, die eine Liste der erfolgten Methodenaufrufe zurueckgibt.
	 * 
	 * @return die Liste der erfolgten Methodenaufrufe
	 */
	public List<String> gibErfolgteMethodenAufrufe() throws RemoteException;

	/**
	 * Gibt die richtige Ziffer fuer ein Feld zurueck.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @param spielername
	 *            der Name des Spielers
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @return die richtige Ziffer fuer das Feld
	 */
	public int gibFeldLoesung(int spielID, String spielername,
			IKoordinate koordinate) throws RemoteException;

	/**
	 * Gibt eine Liste mit allen moeglichen Ziffern fuer ein Feld zurueck, die
	 * zu keinen Konflikten fuehren.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @param spielername
	 *            der Name des Spielers
	 * @return die Liste mit den moeglichen Ziffern
	 */
	public List<Integer> gibFeldMoeglichkeiten(int spielID, String spielername,
			IKoordinate koordinate) throws RemoteException;

	/**
	 * Gibt eine Liste mit den Koordinaten der Felder zurueck, welche zu
	 * Konflikten fuehren (ohne Startbelegungsfelder).
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @param spielername
	 *            der Name des Spielers
	 * @return die Liste mit den Koordinaten der Konfliktfelder
	 */
	public List<IKoordinate> gibKonfliktfelder(int spielID, String spielername)
			throws RemoteException;

	/**
	 * Gibt eine Liste der noch nicht gestarteten Mehrspielerspiele zurueck.
	 * 
	 * @return die Liste mit den Spielinformationen der Spiele
	 */
	public List<ISpielinformation> gibMehrspielerspiele()
			throws RemoteException;

	/**
	 * Gibt den aktuellen Punktestand eines Spiels als Liste mit der Anzahl der
	 * ausgefuellten Felder jedes Spielers zurueck.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @return die Liste mit der Anzahl der ausgefuellten Felder jedes Spielers
	 */
	public List<Integer> gibPunktestand(int spielID) throws RemoteException;

	public EnumContainer.Schwierigkeitsgrad gibSchwierigkeitsgrad(int spielID)
			throws RemoteException;

	/**
	 * Gibt den Namen des Servers zurueck.
	 * 
	 * @return der Servername
	 * @throws RemoteException
	 */
	public String gibServername() throws RemoteException;

	/**
	 * Gibt die Farben der am Spiel teilnehmenden Spieler zurueck.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @return die Liste mit den Farben der Spieler
	 * @throws RemoteException
	 */
	public List<EnumContainer.Farbe> gibSpielerfarben(int spielID)
			throws RemoteException;

	/**
	 * Gibt eine Liste mit den Namen aller beim Spiel angemeldeten Spieler
	 * zurueck.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @return die Liste mit den Spielernamen
	 * @throws RemoteException
	 */
	public List<String> gibSpielerliste(int spielID) throws RemoteException;

	/**
	 * Gibt das aktuelle Spielfeld eines Spielers zurueck.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @param spielername
	 *            der Name des Spielers
	 * @return das Spielfeld des Spielers
	 */
	public ISpielfeld gibSpielfeld(int spielID, String spielername)
			throws RemoteException;

	/**
	 * Gibt das richtig ausgefuellte Spielfeld des gewaehlten Spiels zurueck.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @return das richtig ausgefuellte Spielfeld
	 */
	public ISpielfeld gibSpielfeldLoesung(int spielID) throws RemoteException;

	/**
	 * Gibt eine Liste mit den aktuell auf dem Server laufenden
	 * Mehrspielerspielen zurueck.
	 * 
	 * @return die Liste mit den Spielen
	 */
	public List<ISpiel> gibSpielliste() throws RemoteException;

	/**
	 * Gibt den aktuellen Spielstand eines Spieles zurueck.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @return der aktuelle Spielstand
	 */
	public ISpielstand gibSpielstand(int spielID) throws RemoteException;

	/**
	 * Gibt die Spielvariante fuer das gewaehlte Spiel zurueck.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @return die Spielvariante des Spiels
	 */
	public Spielvariante gibSpielvariante(int spielID) throws RemoteException;

	/**
	 * Gibt die Strafzeit fuer das gewaehlte Spiel zurueck.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @return die Strafzeit des Spiels
	 */
	public int gibStrafzeit(int spielID) throws RemoteException;

	/**
	 * Loescht die Ziffer in einem Feld.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @param spielername
	 *            der Name des Spielers
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @return ok, feldLeer, feldInStartbelegung
	 */
	public Serverantwort loescheZiffer(int spielID, String spielername,
			IKoordinate koordinate) throws RemoteException;

	/**
	 * Setzt den Namen des Servers.
	 * 
	 * @param name
	 *            der neue Name des Servers
	 * @throws RemoteException
	 */
	public void setzeServername(String name) throws RemoteException;

	/**
	 * Setzt eine Ziffer in einem Feld.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @param spielername
	 *            der Name des Spielers
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @param ziffer
	 *            die einzutragende Ziffer
	 * @return ok, feldNichtLeer, spielfeldGeloest
	 */
	public Serverantwort setzeZiffer(int spielID, String spielername,
			IKoordinate koordinate, int ziffer) throws RemoteException;

	/**
	 * Meldet einen Spieler von einem Spiel ab.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @param spielername
	 *            der Name des Spielers
	 */
	public void spielerAbmelden(int spielID, String spielername)
			throws RemoteException;

	/**
	 * Meldet einen Spieler bei einem Spiel an.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @param spielername
	 *            der Name des Spielers
	 * @param spielerfarbe
	 *            die Farbe des Spielers
	 * @return ok, spielVoll, spielernameVergeben, spielerfarbeVergeben
	 */
	public Serverantwort spielerAnmelden(int spielID, String spielername,
			Farbe spielerfarbe) throws RemoteException;

	/**
	 * Zeigt an, ob das Spiel gestartet wurde. Wichtig fuer den Uebergang vom
	 * Spielbeitritt zum Spiel und fuer die Abfrage, ob jemand im
	 * Mehrspielermodus das Spiel fuer sich entschieden hat.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @param spielername
	 *            der Name des Spielers
	 * @return true: das Spiel laeuft; false: das Spiel laeuft NICHT
	 */
	public boolean spielLaeuft(int spielID, String spielername)
			throws RemoteException;

	/**
	 * Startet ein erstelltes Spiel.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 * @return ok, zuWenigSpieler
	 */
	public Serverantwort spielStarten(int spielID) throws RemoteException;
}