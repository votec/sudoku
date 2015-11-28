package common;

/**
 * Das Interface IKoordinate dient als Schnittstelle fuer die Klasse Koordinate.
 * 
 * @author Heiko Schroeder
 * 
 */
public interface IKoordinate {

	/**
	 * Gibt die X-Koordinate zurueck.
	 * 
	 * @return die X-Koordinate
	 */
	public int gibX();

	/**
	 * Gibt die Y-Koordinate zurueck.
	 * 
	 * @return die Y-Koordinate
	 */
	public int gibY();

	/**
	 * Setzt die X-Koordinate.
	 * 
	 * @param x
	 *            die neue X-Koordinate
	 */
	public void setzeX(int x);

	/**
	 * Setzt die Y-Koordinate.
	 * 
	 * @param y
	 *            die neue Y-Koordinate
	 */
	public void setzeY(int y);

}
