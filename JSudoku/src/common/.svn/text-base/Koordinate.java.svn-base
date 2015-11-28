package common;

import java.io.Serializable;

/**
 * Die Klasse Koordinate repraesentiert die kartesische Koordinate eines Feldes
 * in einem zweidimensionalen Sudokuspielfeld.
 * 
 * @author Heiko Schroeder
 * 
 */
public class Koordinate implements IKoordinate, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9116145298799061651L;
	
	/** Die X-Koordinate. */
	private int x;
	/** Die Y-Koordinate. */
	private int y;

	/** Erzeugt eine neue Koordinate mit Standardwerten. */
	public Koordinate() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Erzeugt eine neue Koordinate mit uebergebenen Werten fuer die X- und die
	 * Y-Koordinate.
	 * 
	 * @param x
	 *            die X-Koordinate
	 * @param y
	 *            die Y-Koordinate
	 */
	public Koordinate(int x, int y) {
		setzeY(y);
		setzeX(x);
	}

	/**
	 * @see IKoordinate#gibX()
	 */
	public int gibX() {
		return x;
	}

	/**
	 * @see IKoordinate#gibY()
	 */
	public int gibY() {
		return y;
	}

	/**
	 * @see IKoordinate#setzeX(int)
	 */
	public void setzeX(int x) {
		if (x >= 0) {
			this.x = x;
		} else {
			this.x = 0;
		}
	}

	/**
	 * @see IKoordinate#setzeY(int)
	 */
	public void setzeY(int y) {
		if (y >= 0) {
			this.y = y;
		} else {
			this.y = 0;
		}
	}
}