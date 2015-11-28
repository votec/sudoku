package common;

import java.io.Serializable;

/**
 * Die Klasse Feld repraesentiert ein Feld in einem Sudokuspielfeld.
 * 
 * @author Heiko Schroeder
 * 
 */
public class Feld implements IFeld, Serializable {

	/** serial Version UID */
	private static final long serialVersionUID = 1838373698236699565L;
	/** Der Inhalt des Feldes. */
	private int inhalt;
	/** Speichert, ob das Feld zur Startbelegung gehoert. */
	private boolean istInStartbelegung;
	/** Das Koordinatenattribut des Feldes. */
	private IKoordinate koordinate;

	/** Erzeugt ein neues Feld mit Standardwerten. */
	public Feld() {
		this.inhalt = 0;
		this.istInStartbelegung = false;
		this.koordinate = new Koordinate(0, 0);
	}

	/**
	 * Erzeugt ein neues Feld mit einem uebergebenen Koordinatenattribut.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 */
	public Feld(IKoordinate koordinate) {
		this.inhalt = 0;
		this.istInStartbelegung = false;
		this.koordinate = koordinate;
	}

	/**
	 * Erzeugt ein neues Feld mit einem uebergebenen Koordinatenattribut und
	 * einem Inhalt.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @param inhalt
	 *            der Inhalt des Feldes
	 */
	public Feld(IKoordinate koordinate, int inhalt) {
		setzeInhalt(inhalt);
		this.istInStartbelegung = false;
		this.koordinate = koordinate;
	}

	/**
	 * Erzeugt ein neues Feld mit einem uebergebenen Koordinatenattribut und
	 * einem Inhalt und setzt, ob das Feld zur Startbelegung gehoert.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @param inhalt
	 *            der Inhalt des Feldes
	 * @param belegung
	 *            die Zugehoerigkeit des Feldes zur Startbelegung
	 */
	public Feld(IKoordinate koordinate, int inhalt, boolean belegung) {
		setzeInhalt(inhalt);
		this.istInStartbelegung = belegung;
		this.koordinate = koordinate;
	}

	/**
	 * @see IFeld#gibInhalt()
	 */
	public int gibInhalt() {
		return inhalt;
	}

	/**
	 * @see IFeld#gibKoordinate()
	 */
	public IKoordinate gibKoordinate() {
		return koordinate;

	}

	/**
	 * @see IFeld#istInStartbelegung()
	 */
	public boolean istInStartbelegung() {
		return istInStartbelegung;
	}

	/**
	 * @see IFeld#istLeer()
	 */
	public boolean istLeer() {
		if (inhalt == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @see IFeld#setzeInhalt(int)
	 */
	public void setzeInhalt(int inhalt) {
		if (inhalt <= 16 && inhalt >= 0) {
			this.inhalt = inhalt;
		}
	}

	/**
	 * @see IFeld#setzeKoordinate(IKoordinate)
	 */
	public void setzeKoordinate(IKoordinate koordinate) {
		this.koordinate = koordinate;
	}

	/**
	 * @see IFeld#setzeStartbelegung(boolean)
	 */
	public void setzeStartbelegung(boolean belegung) {
		this.istInStartbelegung = belegung;
	}

}
