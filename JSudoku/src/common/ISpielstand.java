package common;

import java.util.HashMap;
import java.util.List;

/**
 * Das Interface ISpielstand dient als Schnittstelle fuer die Klasse Spielstand.
 * 
 * @author Thomas Fraenkler
 * 
 */
public interface ISpielstand extends EnumContainer {

	/**
	 * Fuegt der Liste mit den Spielfeldern das Spielfeld des Spielers mit der
	 * uebergebenen Farbe hinzu.
	 * 
	 * @param spielerfarbe
	 *            die Farbe des Spielers
	 * @param spielfeld
	 *            das Spielfeld des Spielers
	 */
	public void fuegeSpielfeldHinzu(Farbe spielerfarbe, ISpielfeld spielfeld);

	/**
	 * Gibt die Anzahl der Spieler zurueck, die an dem gespeicherten Spiel
	 * teilgenommen haben.
	 * 
	 * @return die Spieleranzahl
	 */
	public int gibAnzahlSpieler();

	/**
	 * Gibt die Masterspielerfarbe zurueck.
	 * 
	 * @return der Name des Masterspielers
	 */
	public Farbe gibMasterspielerfarbe();

	/**
	 * Gibt den Namen des Masterspielers zurueck.
	 * 
	 * @return der Name des Masterspielers
	 */
	public String gibMasterspielername();

	/**
	 * Gibt den Schwierigkeitsgrad des gespeicherten Spiels zurueck.
	 * 
	 * @return anfaenger, mittel, knifflig, schwer, profi
	 */
	public Schwierigkeitsgrad gibSchwierigkeitsgrad();

	/**
	 * Gibt eine Liste mit den Farben der Spieler zurueck, die an dem
	 * gespeicherten Spiel teilgenommen haben.
	 * 
	 * @return die Liste mit den Spielerfarben
	 */
	public List<Farbe> gibSpielerfarben();

	/**
	 * Gibt das Spielfeld der entsprechenden Spielerfarbe zurueck, sonst null.
	 * 
	 * @param spielerfarbe
	 *            die Farbe des Spielers
	 * @return das Spielfeld des Spielers oder null, wenn die Farbe im Spiel
	 *         nicht existierte
	 */
	public ISpielfeld gibSpielfeld(Farbe spielerfarbe);

	/**
	 * Gibt eine HashMap mit den Spielfeldern der Spieler eines gespeicherten
	 * Spiels zurueck.
	 * 
	 * @return die Spielfelder aller Spieler, die am Spiel teilgenommen haben
	 */
	public HashMap<Farbe, ISpielfeld> gibSpielfelder();

	/**
	 * Gibt die Spielfeldloesung zurueck.
	 * 
	 * @return die Spielfeldloesung
	 */
	public ISpielfeld gibSpielfeldloesung();

	/**
	 * Gibt den Spielmodus des gespeicherten Spiels zurueck.
	 * 
	 * @return einzelspieler, mehrspieler
	 */
	public Spielmodus gibSpielmodus();

	/**
	 * Gibt die Spielvariante des gespeicherten Spiels zurueck.
	 * 
	 * @return standard, fudschijama4x4, fudschijama4x3, fudschijama2x3,
	 *         comparison
	 */
	public Spielvariante gibSpielvariante();

	/**
	 * Gibt das Startspielfeld des gespeicherten Spiels zurueck.
	 * 
	 * @return das Startspielfeld
	 */
	public ISpielfeld gibStartbelegung();

	/**
	 * Gibt die Strafzeit des gespeicherten Spiels zurueck.
	 * 
	 * @return die Strafzeit des Spiels
	 */
	public int gibStrafzeit();

	/**
	 * Setzt die Farbe des Masterspielers.
	 * 
	 * @param masterspielerfarbe
	 *            die Farbe des Masterspielers
	 */
	public void setzeMasterspielerfarbe(Farbe masterspielerfarbe);

	/**
	 * Setzt den Namen des Masterspielers.
	 * 
	 * @param masterspielername
	 *            der Name des Masterspielers
	 */
	public void setzeMasterspielername(String masterspielername);

	/**
	 * Setzt den Schwierigkeitsgrad des gespeicherten Spiels.
	 * 
	 * @param schwierigkeitsgrad
	 *            der Schwierigkeitsgrad des Spiels
	 */
	public void setzeSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad);

	/**
	 * Setzt die Spielfeldloesung des gespeicherten Spiels.
	 * 
	 * @param spielfeldloesung
	 *            die Loesung des Spielfeldes
	 */
	public void setzeSpielfeldloesung(ISpielfeld spielfeldloesung);

	/**
	 * Setzt den Spielmodus des gespeicherten Spiels.
	 * 
	 * @param spielmodus
	 *            der Spielmodus des Spiels
	 */
	public void setzeSpielmodus(Spielmodus spielmodus);

	/**
	 * Setzt die Spielvariante des gespeicherten Spiels.
	 * 
	 * @param spielvariante
	 *            die Spielvariante des Spiels
	 */
	public void setzeSpielvariante(Spielvariante spielvariante);

	/**
	 * Setzt die Strafzeit des gespeicherten Spiels.
	 * 
	 * @param strafzeit
	 *            die Strafzeit des Spiels
	 */
	public void setzeStrafzeit(int strafzeit);
}
