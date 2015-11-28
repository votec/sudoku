package server;

import java.util.List;

import common.*;

/**
 * Die Klasse Spieler repraesentiert einen Spieler, der an einem Sudokuspiel
 * teilnimmt. Ein Spieler hat ein Spielfeld, einen Namen und eine Farbe.
 * 
 * @author Arne Busch
 * 
 */
public class Spieler implements ISpieler {

	/** Die Farbe des Spielers. */
	private Farbe farbe;
	/** Der Name des Spielers. */
	private String name;
	/** Das Spielfeld des Spielers. */
	private ISpielfeld spielfeld;

	/**
	 * Erzeugt einen neuen Spielers mit uebergebenem Namen, Farbe und Spielfeld.
	 * 
	 * @param name
	 *            der Name des Spielers
	 * @param farbe
	 *            die Farbe des Spielers
	 * @param spielfeld
	 *            das Spielfeld des Spielers
	 */
	public Spieler(String name, Farbe farbe, ISpielfeld spielfeld) {
		this.farbe = farbe;
		this.name = name;
		this.spielfeld = spielfeld;
	}

	/**
	 * @see ISpieler#gibAnzahlGesetzteFelder()
	 */
	public int gibAnzahlGesetzteFelder() {
		return spielfeld.gibAnzahlGesetzteFelder();
	}

	/**
	 * @see ISpieler#gibFarbe()
	 */
	public Farbe gibFarbe() {
		return farbe;
	}

	/**
	 * @see ISpieler#gibFeldMoeglichkeiten(IKoordinate)
	 */
	public List<Integer> gibFeldMoeglichkeiten(IKoordinate koordinate) {
		return spielfeld.gibFeldMoeglichkeiten(koordinate);
	}

	/**
	 * @see ISpieler#gibKonfliktfelder()
	 */
	public List<IKoordinate> gibKonfliktfelder() {
		return spielfeld.gibKonfliktfelder();
	}

	/**
	 * @see ISpieler#gibName()
	 */
	public String gibName() {
		return name;
	}

	/**
	 * @see ISpieler#gibSpielfeld()
	 */
	public ISpielfeld gibSpielfeld() {
		return spielfeld;
	}

	/**
	 * @see ISpieler#loescheZiffer(IKoordinate)
	 */
	public Serverantwort loescheZiffer(IKoordinate koordinate) {
		Serverantwort antwort = spielfeld.loescheZiffer(koordinate);

		return antwort;
	}

	/**
	 * @see ISpieler#setzeZiffer(IKoordinate, int)
	 */
	public Serverantwort setzeZiffer(IKoordinate koordinate, int ziffer) {
		Serverantwort antwort = spielfeld.setzeZiffer(koordinate, ziffer);

		return antwort;
	}

}