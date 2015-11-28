package common;

/**
 * Das Interface IFeld dient als Schnittstelle fuer die Klasse Feld.
 * 
 * @author Heiko Schroeder
 * 
 */
public interface IFeld {

	/**
	 * Gibt den Inhalt des Feldes zurueck oder den Wert 0, wenn Feld nicht
	 * ausgefuellt ist.
	 * 
	 * @return der Inhalt des Feldes
	 */
	public int gibInhalt();

	/**
	 * Gibt die Koordinate des Feldes zurueck.
	 * 
	 * @return die Koordinate des Feldes
	 */
	public IKoordinate gibKoordinate();

	/**
	 * Gibt zurueck, ob das Feld zur Startbelegung gehoert.
	 * 
	 * @return true: das Feld ist in der Startbelegung; true: das Feld ist NICHT
	 *         in der Startbelegung
	 */
	public boolean istInStartbelegung();

	/**
	 * Gibt zurueck, ob das Feld nicht ausgefuellt ist.
	 * 
	 * @return true: das Feld ist NICHT ausgefuellt; false: das Feld ist
	 *         ausgefuellt
	 */
	public boolean istLeer();

	/**
	 * Setzt den Inhalt des Feldes.
	 * 
	 * @param ziffer
	 *            der neue Inhalt des Feldes
	 */
	public void setzeInhalt(int ziffer);

	/**
	 * Setzt das Koordinatenattribut des Feldes.
	 * 
	 * @param koordinate
	 *            das Koordinatenattribut
	 */
	public void setzeKoordinate(IKoordinate koordinate);

	/**
	 * Setzt, ob das Feld zu Startbelegung gehoert.
	 * 
	 * @param belegung
	 *            true: das Feld gehoert zu Startbelegung; false: das Feld
	 *            gehoert NICHT zu Startbelegung
	 */
	public void setzeStartbelegung(boolean belegung);
}
