package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import common.*;

/**
 * Die Klasse Spiel repraesentiert ein Sudokuspiel. Ein Spiel hat ein
 * vorgegebenes oder neu generiertes Spielfeld und es koennen im
 * Mehrspielermodus bis zu acht Spieler teilnehmen.
 * 
 * @author Arne Busch
 * 
 */
public class Spiel implements ISpiel, EnumContainer {

	/** Gibt die Hoechstzahl an Spielern in einem Mehrspielerspiel an. */
	private static final int MAX_SPIELERANZAHL = 8;
	/** Speichert die ID des naechsten Spiels, das erstellt wird. */
	private static int naechsteSpielID = 1;

	/** Eine HashMap mit den Punktestaenden aktiver und abgemeldeter Spieler. */
	private HashMap<String, Integer> endstand;
	/** Eine HashMap mit den Spielfeldern der Spieler eines gespeicherten Spiels. */
	private HashMap<Farbe, ISpielfeld> gespeicherteSpielfelder;
	/** Der Schwierigkeitsgrad des Spiels. */
	private Schwierigkeitsgrad schwierigkeitsgrad;
	/** Die Liste der teilnehmenden Spieler. */
	private List<ISpieler> spielerliste;
	/** Das komplett und richtig ausgefuellte Spielfeld. */
	private ISpielfeld spielfeldloesung;
	/** Die ID des Spiels. */
	private int spielID;
	/** Gibt an, ob das Spiel laeuft. */
	private boolean spielLaeuft;
	/** Der Spielmodus des Spiels. */
	private Spielmodus spielmodus;
	/** Die Spielvariante des Spiels. */
	private Spielvariante spielvariante;
	/** Die Strafzeit des Spiels. */
	private int strafzeit;

	/**
	 * Standardkonstruktor zum Testen. Erzeugt ein neues Spiel und initialisiert
	 * es mit Standardwerten.
	 */
	public Spiel() {
		this.spielID = naechsteSpielID++;
		this.spielmodus = Spielmodus.einzelspieler;
		this.spielvariante = Spielvariante.standard;
		this.schwierigkeitsgrad = Schwierigkeitsgrad.anfaenger;
		this.strafzeit = 0;
		this.spielfeldloesung = new Spielfeld_Standard();
		this.spielerliste = new ArrayList<ISpieler>();
		this.endstand = new HashMap<String, Integer>();
		this.gespeicherteSpielfelder = null;
		this.spielLaeuft = false;
	}

	/**
	 * Erzeugt ein neues Spiel aus einem gespeicherten Spielstand.
	 * 
	 * @param spielstand
	 *            der gespeicherte Spielstand
	 */
	public Spiel(ISpielstand spielstand) {
		this.spielID = naechsteSpielID++;
		this.spielmodus = spielstand.gibSpielmodus();
		this.spielvariante = spielstand.gibSpielvariante();
		this.schwierigkeitsgrad = spielstand.gibSchwierigkeitsgrad();
		this.strafzeit = spielstand.gibStrafzeit();
		this.spielfeldloesung = spielstand.gibSpielfeldloesung();
		this.spielerliste = new ArrayList<ISpieler>();
		this.gespeicherteSpielfelder = spielstand.gibSpielfelder();
		this.endstand = new HashMap<String, Integer>();
		this.spielLaeuft = false;
	}

	/**
	 * Erzeugt ein neues Spiel aus den uebergebenen Parametern spielmodus,
	 * spielvariante, schwierigkeitsgrad und strafzeit.
	 * 
	 * @param spielmodus
	 *            der Spielmodus
	 * @param spielvariante
	 *            die Spielvariante
	 * @param schwierigkeitsgrad
	 *            der Schwierigkeitsgrad
	 * @param strafzeit
	 *            die Strafzeit
	 */
	public Spiel(Spielmodus spielmodus, Spielvariante spielvariante,
			Schwierigkeitsgrad schwierigkeitsgrad, int strafzeit) {
		this.spielID = naechsteSpielID++;
		this.spielmodus = spielmodus;
		this.spielvariante = spielvariante;
		this.schwierigkeitsgrad = schwierigkeitsgrad;

		// pruefen, ob die Strafzeit nicht negativ ist
		if (strafzeit < 0) {
			this.strafzeit = 0;
		} else {
			this.strafzeit = strafzeit;
		}

		this.spielerliste = new ArrayList<ISpieler>();
		this.endstand = new HashMap<String, Integer>();
		this.gespeicherteSpielfelder = null;
		this.spielLaeuft = false;

		// Erzeugen einer neuen Spielfeldinstanz anhand der Spielvariante
		switch (spielvariante) {
		case standard:
			this.spielfeldloesung = new Spielfeld_Standard();
			break;
		case fudschijama4x4:
			this.spielfeldloesung = new Spielfeld_Fudschijma_4x4();
			break;
		case fudschijama4x3:
			this.spielfeldloesung = new Spielfeld_Fudschijma_4x3();
			break;
		case fudschijama2x3:
			this.spielfeldloesung = new Spielfeld_Fudschijma_2x3();
			break;
		case comparison:
			this.spielfeldloesung = new Spielfeld_Comparison();
		}
	}

	/**
	 * @see ISpiel#generiereSpielfeld()
	 */
	public void generiereSpielfeld() {
		spielfeldloesung.generiereSpielfeld(schwierigkeitsgrad);
	}

	/**
	 * @see ISpiel#generiereSpielfeld(ISpielfeld)
	 */
	public void generiereSpielfeld(ISpielfeld spielfeld) {
		spielfeld.generiereSpielfeld();
		spielfeldloesung = spielfeld;
	}

	/**
	 * @see ISpiel#genuegendSpielerAngemeldet()
	 */
	public boolean genuegendSpielerAngemeldet() {
		if (spielmodus == Spielmodus.einzelspieler && spielerliste.size() == 1) {
			return true;
		}

		if (spielmodus == Spielmodus.mehrspieler && spielerliste.size() >= 2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @see ISpiel#gibEndstand()
	 */
	public HashMap<String, Integer> gibEndstand() {
		return endstand;
	}

	/**
	 * @see ISpiel#gibFeldLoesung(IKoordinate)
	 */
	public int gibFeldLoesung(IKoordinate koordinate) {
		return spielfeldloesung.gibFeldinhalt(koordinate);
	}

	/**
	 * @see ISpiel#gibFeldMoeglichkeiten(String, IKoordinate)
	 */
	public List<Integer> gibFeldMoeglichkeiten(String spielername,
			IKoordinate koordinate) {
		// den passenden Spieler aus der Spielerliste heraussuchen
		for (ISpieler spieler : spielerliste) {
			if (spieler.gibName().equals(spielername)) {
				return spieler.gibFeldMoeglichkeiten(koordinate);
			}
		}

		return null;
	}

	/**
	 * @see ISpiel#gibKonfliktfelder(String)
	 */
	public List<IKoordinate> gibKonfliktfelder(String spielername) {
		// den passenden Spieler aus der Spielerliste heraussuchen
		for (ISpieler spieler : spielerliste) {
			if (spieler.gibName().equals(spielername)) {
				return spieler.gibKonfliktfelder();
			}
		}

		return null;
	}

	/**
	 * @see ISpiel#gibPunktestand()
	 */
	public List<Integer> gibPunktestand() {
		List<Integer> punktestand = new ArrayList<Integer>();

		// die Anzahl der gesetzten Felder aller Spieler heraussuchen
		for (ISpieler spieler : spielerliste) {
			Integer anzGesFelder = new Integer(spieler
					.gibAnzahlGesetzteFelder());
			punktestand.add(anzGesFelder);
		}

		return punktestand;
	}

	/**
	 * @see ISpiel#gibSchwierigkeitsgrad()
	 */
	public Schwierigkeitsgrad gibSchwierigkeitsgrad() {
		return schwierigkeitsgrad;
	}

	/**
	 * @see ISpiel#gibSpielerfarbenliste()
	 */
	public List<Farbe> gibSpielerfarbenliste() {
		List<Farbe> farbenliste = new ArrayList<Farbe>();

		// den Namen jedes Spielers in die Liste aufnehmen
		for (ISpieler spieler : spielerliste) {
			farbenliste.add(spieler.gibFarbe());
		}

		return farbenliste;
	}

	/**
	 * @see ISpiel#gibSpielerliste()
	 */
	public List<String> gibSpielerliste() {
		List<String> namenliste = new ArrayList<String>();

		// den Namen jedes Spielers in die Liste aufnehmen
		for (ISpieler spieler : spielerliste) {
			namenliste.add(spieler.gibName());
		}

		return namenliste;
	}

	/**
	 * @see ISpiel#gibSpielfeld(String)
	 */
	public ISpielfeld gibSpielfeld(String spielername) {
		// den passenden Spieler aus der Spielerliste heraussuchen
		for (ISpieler spieler : spielerliste) {
			if (spieler.gibName().equals(spielername)) {
				return spieler.gibSpielfeld();
			}
		}

		return null;
	}

	/**
	 * @see ISpiel#gibSpielfeldLoesung()
	 */
	public ISpielfeld gibSpielfeldLoesung() {
		return spielfeldloesung;
	}

	/**
	 * @see ISpiel#gibSpielID()
	 */
	public int gibSpielID() {
		return spielID;
	}

	/**
	 * @see ISpiel#gibSpielmodus()
	 */
	public Spielmodus gibSpielmodus() {
		return spielmodus;
	}

	/**
	 * @see ISpiel#gibSpielstand()
	 */
	public ISpielstand gibSpielstand() {
		ISpielstand spielstand = new Spielstand();
		spielstand.setzeMasterspielername(spielerliste.get(0).gibName());
		spielstand.setzeMasterspielerfarbe(spielerliste.get(0).gibFarbe());
		spielstand.setzeSpielmodus(spielmodus);
		spielstand.setzeSpielvariante(spielvariante);
		spielstand.setzeSchwierigkeitsgrad(schwierigkeitsgrad);
		spielstand.setzeSpielfeldloesung(spielfeldloesung);

		// alle Spielfelder speichern
		for (ISpieler spieler : spielerliste) {
			spielstand.fuegeSpielfeldHinzu(spieler.gibFarbe(), spieler
					.gibSpielfeld());
		}

		return spielstand;
	}

	/**
	 * @see ISpiel#gibSpielvariante()
	 */
	public Spielvariante gibSpielvariante() {
		return spielvariante;
	}

	/**
	 * @see ISpiel#gibStrafzeit()
	 */
	public int gibStrafzeit() {
		return strafzeit;
	}

	/**
	 * @see ISpiel#loescheZiffer(String, IKoordinate)
	 */
	public Serverantwort loescheZiffer(String spielername,
			IKoordinate koordinate) {
		// den passenden Spieler aus der Spielerliste heraussuchen
		for (ISpieler spieler : spielerliste) {
			if (spieler.gibName().equals(spielername)) {
				Serverantwort antwort = spieler.loescheZiffer(koordinate);
				Integer anzGesFelder = new Integer(spieler
						.gibAnzahlGesetzteFelder());
				endstand.put(spielername, anzGesFelder);

				return antwort;
			}
		}

		return null;
	}

	/**
	 * Setzt den Schwierigkeitsgrad des Spiels.
	 * 
	 * @param schwierigkeitsgrad
	 *            der Schwierigkeitsgrad des Spiels
	 */
	public void setzeSchwierigkeitsgrad(Schwierigkeitsgrad schwierigkeitsgrad) {
		this.schwierigkeitsgrad = schwierigkeitsgrad;
	}

	/**
	 * Setzt die SpielID des Spiels.
	 * 
	 * @param spielID
	 *            die SpielID des Spiels
	 */
	public void setzeSpielID(int spielID) {
		this.spielID = spielID;
	}

	/**
	 * Setzt, ob das Spiel laeuft.
	 * 
	 * @param spielLaeuft
	 *            true: das Spiel laeuft; false: das Spiel laeuft NICHT
	 */
	public void setzeSpielLaeuft(boolean spielLaeuft) {
		this.spielLaeuft = spielLaeuft;
	}

	/**
	 * Setzt den Spielmodus des Spiels.
	 * 
	 * @param spielmodus
	 *            der Spielmodus des Spiels
	 */
	public void setzeSpielmodus(Spielmodus spielmodus) {
		this.spielmodus = spielmodus;
	}

	/**
	 * Setzt die Spielvariante des Spiels.
	 * 
	 * @param spielvariante
	 *            die Spielvariante des Spiels
	 */
	public void setzeSpielvariante(Spielvariante spielvariante) {
		this.spielvariante = spielvariante;
	}

	/**
	 * Setzt die Strafzeit des Spiels.
	 * 
	 * @param strafzeit
	 *            die Strafzeit des Spiels
	 */
	public void setzeStrafzeit(int strafzeit) {
		this.strafzeit = strafzeit;
	}

	/**
	 * @see ISpiel#setzeZiffer(String, IKoordinate, int)
	 */
	public Serverantwort setzeZiffer(String spielername,
			IKoordinate koordinate, int ziffer) {
		// falls in einem Mehrspielerspiel eine falsche Ziffer eingegeben wird
		if (spielmodus == Spielmodus.mehrspieler
				&& !istZifferRichtig(koordinate, ziffer)) {
			return Serverantwort.zifferFalsch;
		}

		// ansonsten den passenden Spieler aus der Spielerliste heraussuchen
		for (ISpieler spieler : spielerliste) {
			if (spieler.gibName().equals(spielername)) {
				Serverantwort antwort = spieler.setzeZiffer(koordinate, ziffer);
				Integer anzGesFelder = new Integer(spieler
						.gibAnzahlGesetzteFelder());
				endstand.put(spielername, anzGesFelder);

				if (antwort == Serverantwort.spielfeldGeloest) {
					spielLaeuft = false;
				}

				return antwort;
			}
		}

		return null;
	}

	/**
	 * @see ISpiel#spielerAbmelden(String)
	 */
	public void spielerAbmelden(String spielername) {
		ISpieler spieler = null;

		// den passenden Spieler aus der Spielerliste heraussuchen
		for (ISpieler tempSpieler : spielerliste) {
			if (tempSpieler.gibName().equals(spielername)) {
				spieler = tempSpieler;
			}
		}

		spielerliste.remove(spieler);

		if (!spielLaeuft) {
			endstand.remove(spielername);
		}

		if (spielmodus == Spielmodus.einzelspieler && spielerliste.size() == 0) {
			spielLaeuft = false;
		}

		if (spielmodus == Spielmodus.mehrspieler && spielerliste.size() <= 1) {
			spielLaeuft = false;
		}
	}

	/**
	 * @see ISpiel#spielerAnmelden(String, common.EnumContainer.Farbe)
	 */
	public Serverantwort spielerAnmelden(String spielername, Farbe spielerfarbe) {
		Serverantwort antwort = pruefeAnmeldung(spielername, spielerfarbe);
		ISpieler spieler;
		ISpielfeld spielfeld;
		Integer anzGesFelder;

		// falls die Anmeldung ok ist, Spieler erstellen und in die Spielerliste
		// einfuegen
		if (antwort == Serverantwort.ok) {
			// falls das Spiel kein gespeichertes Spiel ist, dem Spieler ein
			// neues Startspielfeld zuteilen, ansonsten das der Farbe
			// zugehoerige
			if (gespeicherteSpielfelder == null) {
				spielfeld = gibStartspielfeld();
			} else {
				if (gespeicherteSpielfelder.containsKey(spielerfarbe)) {
					spielfeld = gespeicherteSpielfelder.get(spielerfarbe);
				} else {
					return Serverantwort.spielerfarbeNichtVorhanden;
				}
			}

			spieler = new Spieler(spielername, spielerfarbe, spielfeld);
			spielerliste.add(spieler);
			anzGesFelder = new Integer(spieler.gibAnzahlGesetzteFelder());
			endstand.put(spielername, anzGesFelder);
		}

		return antwort;
	}

	/**
	 * @see ISpiel#spielLaeuft()
	 */
	public boolean spielLaeuft() {
		return spielLaeuft;
	}

	/**
	 * @see ISpiel#spielStarten()
	 */
	public Serverantwort spielStarten() {
		if (genuegendSpielerAngemeldet()) {
			spielLaeuft = true;
			return Serverantwort.ok;
		} else {
			return Serverantwort.zuWenigSpieler;
		}
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

	/**
	 * Prueft, ob die eingegebene Ziffer richtig ist.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @param ziffer
	 *            die eingegebene Ziffer
	 * @return ob die eingegebene Ziffer richtig ist
	 */
	private boolean istZifferRichtig(IKoordinate koordinate, int ziffer) {
		return ziffer == spielfeldloesung.gibFeldinhalt(koordinate);
	}

	/**
	 * Ueberprueft, ob die maximale Spieleranzahl bereits erreicht ist und ob
	 * Name und Farbe des sich anmeldenden Spielers gueltig sind.
	 * 
	 * @param spielername
	 *            der Name des Spielers
	 * @param spielerfarbe
	 *            die Farbe des Spielers
	 * @return ob der Spieler angemeldet werden kann
	 */
	private Serverantwort pruefeAnmeldung(String spielername, Farbe spielerfarbe) {
		if ((spielmodus == Spielmodus.einzelspieler && spielerliste.size() == 1)
				|| (spielmodus == Spielmodus.mehrspieler && spielerliste.size() == MAX_SPIELERANZAHL)) {
			return Serverantwort.spielVoll;
		}

		// pruefen, ob Name oder Farbe schon vergeben sind
		for (ISpieler spieler : spielerliste) {
			if (spieler.gibName().equals(spielername)) {
				return Serverantwort.spielernameVergeben;
			}

			if (spieler.gibFarbe().equals(spielerfarbe)) {
				return Serverantwort.spielerfarbeVergeben;
			}
		}

		return Serverantwort.ok;
	}
}
