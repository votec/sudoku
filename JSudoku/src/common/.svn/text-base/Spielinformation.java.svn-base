package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Spielfeldinformation dient als Containerklasse fuer die Daten
 * eines Mehrspielerspiels. Die enthaltenen Spielinformationen werden fuer die
 * Anzeige der Mehrspielerspiele in einer Liste in der Grafik benoetigt.
 * 
 * @author Arne Busch
 * 
 */
public class Spielinformation implements ISpielinformation, EnumContainer,
		Serializable {

	/** serial Version UID */
	private static final long serialVersionUID = -4608922468959540991L;
	/** der Name des Spiels */
	private String name;
	/** der Schwierigkeitsgrad des Spiels */
	private Schwierigkeitsgrad schwierigkeitsgrad;
	/** eine Liste mit den Namen der am Spiel teilnehmenden Spieler */
	private List<String> spielernamenliste;
	/** die SpielID des Spiels */
	private int spielID;
	/** die Spielvariante des Spiels */
	private Spielvariante spielvariante;
	/** die Strafzeit des Spiels */
	private int strafzeit;

	/**
	 * Erzeugt eine neue Spielinformation mit Standardwerten.
	 */
	public Spielinformation() {
		this.name = "Mehrspielerspiel";
		this.schwierigkeitsgrad = Schwierigkeitsgrad.mittel;
		this.spielernamenliste = new ArrayList<String>();
		this.spielID = 0;
		this.spielvariante = Spielvariante.standard;
		this.strafzeit = 0;
	}

	/**
	 * @see ISpielinformation#fuegeSpielernamenHinzu(String)
	 */
	public void fuegeSpielernamenHinzu(String spielername) {
		spielernamenliste.add(spielername);
	}

	/**
	 * @see ISpielinformation#gibName()
	 */
	public String gibName() {
		return name;
	}

	/**
	 * @see ISpielinformation#gibSchwierigkeitsgrad()
	 */
	public String gibSchwierigkeitsgrad() {
		return schwierigkeitsgrad.toString();
	}

	/**
	 * @see ISpielinformation#gibSpieleranzahl()
	 */
	public int gibSpieleranzahl() {
		return spielernamenliste.size();
	}

	/**
	 * @see ISpielinformation#gibSpielID()
	 */
	public int gibSpielID() {
		return spielID;
	}

	/**
	 * @see ISpielinformation#gibSpielvariante()
	 */
	public String gibSpielvariante() {
		return spielvariante.toString();
	}

	/**
	 * @see ISpielinformation#gibStrafzeit()
	 */
	public int gibStrafzeit() {
		return strafzeit;
	}

	/**
	 * @see ISpielinformation#setzeName(String)
	 */
	public void setzeName(String name) {
		this.name = name;
	}

	/**
	 * @see ISpielinformation#setzeSchwierigkeitsgrad(common.EnumContainer.Schwierigkeitsgrad)
	 */
	public void setzeSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad) {
		this.schwierigkeitsgrad = schwierigkeitsgrad;
	}

	/**
	 * @see ISpielinformation#setzeSpielID(int)
	 */
	public void setzeSpielID(int spielID) {
		this.spielID = spielID;
	}

	/**
	 * @see ISpielinformation#setzeSpielvariante(common.EnumContainer.Spielvariante)
	 */
	public void setzeSpielvariante(Spielvariante spielvariante) {
		this.spielvariante = spielvariante;
	}

	/**
	 * @see ISpielinformation#setzeStrafzeit(int)
	 */
	public void setzeStrafzeit(int strafzeit) {
		this.strafzeit = strafzeit;
	}
}
