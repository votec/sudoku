package common;

import java.io.Serializable;
import common.EnumContainer.Schwierigkeitsgrad;
import common.EnumContainer.Spielvariante;

/**
 * Das Interface ISpielinformation dient als Schnittstelle fuer die Klasse
 * Spielinformation.
 * 
 * @author Arne Busch
 * 
 */
public interface ISpielinformation extends Serializable {

	/**
	 * Fuegt der Liste mit den Namen der teilnehmenden Spieler einen neuen Namen
	 * hinzu.
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 */
	public void fuegeSpielernamenHinzu(String spielername);

	/**
	 * Gibt den Namen des Spiels zurueck.
	 * 
	 * @return der Name des Spiels
	 */
	public String gibName();

	/**
	 * Gibt den Schwierigkeitsgrad des Spiels zurueck.
	 * 
	 * @return der Schwierigkeitsgrad des Spiels
	 */
	public String gibSchwierigkeitsgrad();

	/**
	 * Gibt die Anzahl der teilnehmenden Spieler zurueck.
	 * 
	 * @return die Anzahl der teilnehmenden Spieler
	 */
	public int gibSpieleranzahl();

	/**
	 * Gibt die SpielID des Spiels zurueck.
	 * 
	 * @return die SpielID des Spiels
	 */
	public int gibSpielID();

	/**
	 * Gibt die Spielvariante des Spiels zurueck.
	 * 
	 * @return die Spielvariante des Spiels
	 */
	public String gibSpielvariante();

	/**
	 * Gibt die Strafzeit des Spiels zurueck.
	 * 
	 * @return die Strafzeit des Spiels
	 */
	public int gibStrafzeit();

	/**
	 * Setzt den Namen des Spiels.
	 * 
	 * @param name
	 *            der neue Name des Spiels
	 */
	public void setzeName(String name);

	/**
	 * Setzt den Schwierigkeitsgrad des Spiels.
	 * 
	 * @param schwierigkeitsgrad
	 *            der neue Schwierigkeitsgrad des Spiels
	 */
	public void setzeSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad);

	/**
	 * Setzt die SpielID des Spiels.
	 * 
	 * @param id
	 *            die neue SpielID des Spiels
	 */
	public void setzeSpielID(int id);

	/**
	 * Setzt die Spielvariante des Spiels.
	 * 
	 * @param spielvariante
	 *            die neue Spielvariante des Spiels
	 */
	public void setzeSpielvariante(Spielvariante spielvariante);

	/**
	 * Setzt die Strafzeit des Spiels.
	 * 
	 * @param strafzeit
	 *            die neue Strafzeit des Spiels
	 */
	public void setzeStrafzeit(int strafzeit);
}
