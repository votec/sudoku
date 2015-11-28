package common;

import java.util.ArrayList;

/**
 * Das Interface IEinheit dient als Schnittstelle fuer die Klasse Einheit.
 * 
 * @author Heiko Schroeder
 * 
 */
public interface IEinheit {

	/**
	 * Fuegt ein Feld zur Einheit hinzu.
	 * 
	 * @param feld
	 *            das hinzuzufuegende Feld
	 */
	public void fuegeFeldHinzu(IFeld feld);

	/**
	 * Prueft, ob eine Zahl bereits in der Einheit vorhanden ist.
	 * 
	 * @param zahl
	 *            die zu pruefende Zahl
	 * @return Zahl true: die Zahl ist enthalten; false: die Zahl ist NICHT
	 *         enthalten.
	 */
	public boolean pruefeZahl(int zahl);

	/**
	 * Gibt eine Liste mit den Koordinaten der Felder zurueck, welche zu
	 * Konflikten fuehren. Felder aus der Startbelegung sind nicht enthalten.
	 * 
	 * @return eine Liste mit den Koordinaten der Konfliktfelder
	 */
	public ArrayList<IKoordinate> gibKonfliktfelder();

	/**
	 * Gibt eine Liste mit den Koordinaten der in der Einheit enthaltenen Felder
	 * zurueck.
	 * 
	 * @return eine Liste mit den Koordinaten der in der Einheit enthaltenen
	 *         Felder
	 */
	public ArrayList<IKoordinate> gibKoordinatenAllerFelder();

}
