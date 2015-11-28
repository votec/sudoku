package client;

import java.util.List;

import server.IServer;
import common.*;

/**
 * Das Interface IClient dient als Schnittstelle fuer die Klasse Client.
 * 
 * @author Heiko Schroeder
 * 
 */
public interface IClient extends EnumContainer {

	/**
	 * Beendet die Verbindung zum Server
	 */
	public void beendeSerververbindung();

	/**
	 * Erstellt ein neues Einzel- oder Mehrspielerspiel mit einem aus einer
	 * Datei ausgelesenen Spielfeld.
	 * 
	 * @param spielmodus
	 *            der Spielmodus des Spiels
	 * @param strafzeit
	 *            die Strafzeit des Spiels
	 * @param dateiname
	 *            der Name der Datei, in der das Spiel gespeichert ist
	 * @return true: das Spiel konnte erstellt werden; false: das Spiel konnte
	 *         NICHT erstellt werden
	 */
	public boolean erstelleSpiel(Spielmodus spielmodus, int strafzeit,
			String dateiname);

	/**
	 * Erstellt ein neues Einzel- oder Mehrspielerspiel und generiert ein neues
	 * Spielfeld.
	 * 
	 * @param spielmodus
	 *            der Spielmodus des Spiels
	 * @param spielvariante
	 *            die Spielvariante des Spiels
	 * @param schwierigkeitsgrad
	 *            der Schwierigkeitsgrad des Spiels
	 * @param strafzeit
	 *            die Strafzeit des Spiels
	 * @return true: das Spiel konnte erstellt werden; false: das Spiel konnte
	 *         NICHT erstellt werden
	 */
	public boolean erstelleSpiel(Spielmodus spielmodus,
			Spielvariante spielvariante, Schwierigkeitsgrad schwierigkeitsgrad,
			int strafzeit);

	/**
	 * Methode gibt als Rückgabewert eine Liste mit Koordinaten zurueck,
	 * welche der Grafik anzeigen, auf welchen Koordinaten die Moeglichkeiten
	 * aufgerufen werden muessen.
	 * 
	 * @return Liste
	 * 				Koordinaten der leeren Felder
	 */
	public List<IKoordinate> gibKoordinatenLeererFelder();

	/**
	 * Gibt die richtige Ziffer fuer ein Feld zurueck.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @return ziffer
	 * 				die Loesung des Feldes
	 */
	public int gibFeldLoesung(IKoordinate koordinate);

	/**
	 * Gibt alle moeglichen Ziffern fuer ein Feld zurueck.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @return Liste mit allen Moeglichen Ziffern des Feldes
	 */
	public List<Integer> gibFeldMoeglichkeiten(IKoordinate koordinate);

	/**
	 * Markiert alle Felder in der Grafik, welche in Konflikt miteinander
	 * stehen.
	 */
	public void gibKonfliktfelder();

	/**
	 * Gibt den lokalen Server zurueck.
	 * 
	 * @return das Serverobjekt
	 */
	public IServer gibLokalServer();

	/**
	 * Gibt eine List mit den aktuellen Mehrspielerspielen vom Server zurueck
	 * 
	 * @return List von ISpielinformation Objekten
	 */
	public List<ISpielinformation> gibMehrspielerspiele();

	/**
	 * Gibt den Server zurueck.
	 * 
	 * @return das Serverobjekt
	 */
	public IServer gibServer();

	/**
	 * Gibt die Serveradresse (IP oder URL) zurueck.
	 * 
	 * @return die Serveradresse
	 */
	public String gibServerAdresse();

	/**
	 * Gibt eine Liste von Spielern zurueck, die am aktiven Spiel teilnehmen.
	 * 
	 * @return Liste mit den Spielern
	 */
	public List<String> gibSpielerliste();

	/**
	 * Gibt Informationen zum aktuell angemeldeten Spiel zurueck
	 * 
	 * @return Objekt com Typ ISpielinformaton
	 */
	public ISpielinformation gibSpielinformation();

	/**
	 * Loescht die Ziffer in einem Feld.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 */
	public void loescheZiffer(IKoordinate koordinate);

	/**
	 * Startet einen lokalen Server und verbindet mit diesem.
	 */
	public void setzeEinzelspielermodus();

	/**
	 * Laedt ein Spiel aus einem gespeicherten Spielstand.
	 * 
	 * @param dateiname
	 *            Name der Datei, in der das Spiel gespeichert ist
	 * @param spielmodus
	 *            der Spielmodus des Spiels
	 */
	public void setzeGespeichertesSpielFort(String dateiname,
			Spielmodus spielmodus);

	/**
	 * Setzt das Grafikobjekt im Client
	 * 
	 * @param grafik
	 *            die Grafikinstanz des Clients
	 */
	public void setzeGrafik(IGrafik grafik);

	/**
	 * Setzt Client in des Zustand des Mehrspielermodus
	 */
	public void setzeMehrspielermodus();

	/**
	 * Setzt die Farbe des Spielers.
	 * 
	 * @param spielerfarbe
	 *            die Farbe des Spielers
	 */
	public void setzeSpielerfarbe(Farbe spielerfarbe);

	/**
	 * Setzt den Namen des Spielers.
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 */
	public void setzeSpielername(String spielername);

	/**
	 * Setzt eine Ziffer in ein Feld.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @param ziffer
	 *            die zu setzende Ziffer
	 */
	public void setzeZiffer(IKoordinate koordinate, int ziffer);

	/**
	 * Speichert ein laufendes Spiel im Einzelspieler- oder Mehrspielermodus
	 * unter dem uebergebenen Dateinamen ab.
	 * 
	 * @param dateiname
	 *            der Name der Datei, in der das Spiel gespeichert werden soll
	 */
	public void speichereSpiel(String dateiname);

	/**
	 * Speichert die aktuelle Startbelegung im WebFriendly Sudoku Format in der
	 * uebergebenen Datei.
	 * 
	 * @param dateiname
	 *            der Name der Datei, in der das Spielfeld gespeichert werden
	 *            soll
	 */
	public void speichereSpielfeld(String dateiname);

	/**
	 * Wechselt in den Zustand, in dem man einem Mehrspielerspiel beitreten
	 * kann.
	 */
	public void spielAuswaehlen();

	/**
	 * Beendet ein laufendes oder in Vorbereitung stehendes Spiel.
	 */
	public void spielBeenden();

	/**
	 * Ueberprueft, ob im Spiel noch ein freier Platz ist, und versucht in
	 * diesem Fall, den Spieler bei dem Spiel anzumelden.
	 * 
	 * @param spielID
	 * @return true, false (ob beitreten erfolgreich)
	 */
	public boolean spielBeitreten(int spielID);

	/**
	 * Meldet einen Spieler von seinem Spiel ab.
	 */
	public void spielerAbmelden();

	/**
	 * Startet das vom Spieler erstellte Spiel.
	 */
	public void spielStarten();

	/**
	 * Methode startet die Hauptmethode / Hauptschleife des Client
	 */
	public void starteClient();

	/**
	 * Verbindet mit dem Server
	 * 
	 * @param adresse
	 *            Adresse des Servers (String)
	 * @return true: die Verbindung wurde hergestellt; false: die Verbindung
	 *         wurde NICHT hergestellt
	 */
	public boolean verbindeMitServer(String adresse);
}
