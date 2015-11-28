package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Die Klasse Spielstand enthaelt die Daten eines gespeicherten Spiels.
 * 
 * @author Thomas Fraenkler
 * 
 */
public class Spielstand implements ISpielstand, Serializable {

	/** serial Version UID */
	private static final long serialVersionUID = 5664585069725996347L;
	/** Die Farbe des Masterspielers */
	private Farbe masterspielerfarbe;
	/** der Name des Masterspielers */
	private String masterspielername;
	/** der Schwierigkeitsgrad des Spiels */
	private Schwierigkeitsgrad schwierigkeitsgrad;
	/** die Spielfelder der Spieler, die am Spiel teilgenommen haben */
	private HashMap<Farbe, ISpielfeld> spielfelder;
	/** die Spielfeldloesung des Spiels */
	private ISpielfeld spielfeldloesung;
	/** der Spielmodus des Spiels */
	private Spielmodus spielmodus;
	/** die Spielvariante des Spiels */
	private Spielvariante spielvariante;
	/** die Strafzeit des Spiels */
	private int strafzeit;

	/**
	 * Erzeugt einen neuen Spielstand mit Standardwerten.
	 */
	public Spielstand() {
		this.masterspielername = "Spieler";
		this.schwierigkeitsgrad = Schwierigkeitsgrad.mittel;
		this.spielfelder = new HashMap<Farbe, ISpielfeld>();
		this.spielfeldloesung = new Spielfeld_Standard();
		this.spielmodus = Spielmodus.einzelspieler;
		this.spielvariante = Spielvariante.standard;
		this.strafzeit = 0;
	}

	/**
	 * @see ISpielstand#fuegeSpielfeldHinzu(common.EnumContainer.Farbe,
	 *      ISpielfeld)
	 */
	public void fuegeSpielfeldHinzu(Farbe spielerfarbe, ISpielfeld spielfeld) {
		spielfelder.put(spielerfarbe, spielfeld);
	}

	/**
	 * @see ISpielstand#gibAnzahlSpieler()
	 */
	public int gibAnzahlSpieler() {
		return spielfelder.size();
	}

	/**
	 * @see ISpielstand#gibMasterspielerfarbe()
	 */
	public Farbe gibMasterspielerfarbe() {
		return this.masterspielerfarbe;
	}

	/**
	 * @see ISpielstand#gibMasterspielername()
	 */
	public String gibMasterspielername() {
		return masterspielername;
	}

	/**
	 * @see ISpielstand#gibSchwierigkeitsgrad()
	 */
	public Schwierigkeitsgrad gibSchwierigkeitsgrad() {
		return schwierigkeitsgrad;
	}

	/**
	 * @see ISpielstand#gibSpielerfarben()
	 */
	public List<Farbe> gibSpielerfarben() {
		List<Farbe> spielerfarben = new ArrayList<Farbe>();
		// temporaere Variable zur Speicherung der Menge der Farben
		Set<Farbe> farbenmenge = spielfelder.keySet();
		Iterator<Farbe> it = farbenmenge.iterator();

		// alle Farben in die Liste der Spielerfarben eintragen
		while (it.hasNext()) {
			Farbe farbe = (Farbe) it.next();
			spielerfarben.add(farbe);
		}

		return spielerfarben;
	}

	/**
	 * @see ISpielstand#gibSpielfeld(common.EnumContainer.Farbe)
	 */
	public ISpielfeld gibSpielfeld(Farbe spielerfarbe) {
		return spielfelder.get(spielerfarbe);
	}

	/**
	 * @see ISpielstand#gibSpielfelder()
	 */
	public HashMap<Farbe, ISpielfeld> gibSpielfelder() {
		return spielfelder;
	}

	/**
	 * @see ISpielstand#gibSpielfeldloesung()
	 */
	public ISpielfeld gibSpielfeldloesung() {
		return spielfeldloesung;
	}

	/**
	 * @see ISpielstand#gibSpielmodus()
	 */
	public Spielmodus gibSpielmodus() {
		return spielmodus;
	}

	/**
	 * @see ISpielstand#gibSpielvariante()
	 */
	public Spielvariante gibSpielvariante() {
		return spielvariante;
	}

	/**
	 * @see ISpielstand#gibStartbelegung()
	 */
	public ISpielfeld gibStartbelegung() {
		ISpielfeld startspielfeld = gibStartspielfeld();
		return startspielfeld;
	}

	/**
	 * @see ISpielstand#gibStrafzeit()
	 */
	public int gibStrafzeit() {
		return strafzeit;
	}

	/**
	 * @see ISpielstand#setzeMasterspielerfarbe(common.EnumContainer.Farbe)
	 */
	public void setzeMasterspielerfarbe(Farbe masterspielerfarbe) {
		this.masterspielerfarbe = masterspielerfarbe;

	}

	/**
	 * @see ISpielstand#setzeMasterspielername(String)
	 */
	public void setzeMasterspielername(String masterspielername) {
		this.masterspielername = masterspielername;
	}

	/**
	 * @see ISpielstand#setzeSchwierigkeitsgrad(common.EnumContainer.Schwierigkeitsgrad)
	 */
	public void setzeSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad) {
		this.schwierigkeitsgrad = schwierigkeitsgrad;
	}

	/**
	 * @see ISpielstand#setzeSpielfeldloesung(ISpielfeld)
	 */
	public void setzeSpielfeldloesung(ISpielfeld spielfeldloesung) {
		this.spielfeldloesung = spielfeldloesung;
	}

	/**
	 * @see ISpielstand#setzeSpielmodus(common.EnumContainer.Spielmodus)
	 */
	public void setzeSpielmodus(Spielmodus spielmodus) {
		this.spielmodus = spielmodus;
	}

	/**
	 * @see ISpielstand#setzeSpielvariante(common.EnumContainer.Spielvariante)
	 */
	public void setzeSpielvariante(Spielvariante spielvariante) {
		this.spielvariante = spielvariante;
	}

	/**
	 * @see ISpielstand#setzeStrafzeit(int)
	 */
	public void setzeStrafzeit(int strafzeit) {
		this.strafzeit = strafzeit;
	}

	/**
	 * Gibt das Startspielfeld zurueck.
	 * 
	 * @return das Startspielfeld
	 */
	private ISpielfeld gibStartspielfeld() {
		ISpielfeld spielfeld = null;

		// Erzeugen einer neuen Spielfeldinstanz anhand der Spielvariante
		switch (spielvariante) {
		case standard:
			spielfeld = new Spielfeld_Standard();
			break;
		case fudschijama4x4:
			spielfeld = new Spielfeld_Fudschijma_4x4();
			break;
		case fudschijama4x3:
			spielfeld = new Spielfeld_Fudschijma_4x3();
			break;
		case fudschijama2x3:
			spielfeld = new Spielfeld_Fudschijma_2x3();
			break;
		case comparison:
			spielfeld = new Spielfeld_Comparison();
		}

		spielfeld.setzeStartspielfeld(spielfeldloesung);

		return spielfeld;
	}
}
