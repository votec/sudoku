package client;

import java.util.ArrayList;
import java.util.List;

import common.IKoordinate;
import common.ISpielinformation;
import common.EnumContainer.Farbe;
import common.EnumContainer.Spielmodus;

/**
 * Die Klasse GrafikDummy bietet zu Testzwecken Dummy-Objekte fuer die Klasse
 * Grafik.
 * 
 * @author Arne Busch
 * 
 */
public class GrafikDummy implements IGrafik {

	/** Diese Liste speichert die erfolgten Methodenaufrufe. */
	private List<String> erfolgteMethodenAufrufe;

	/**
	 * Erzeugt einen GrafikDummy mit Standardwerten.
	 */
	public GrafikDummy() {
		this.erfolgteMethodenAufrufe = new ArrayList<String>();
	}

	/**
	 * @see IGrafik#aktualisiereSpielstand(List, List, List)
	 */
	public void aktualisiereSpielstand(List<String> spielerliste,
			List<Integer> punkte, List<Farbe> farbe) {
		erfolgteMethodenAufrufe.add("aktualisiereSpielstand");
	}

	/**
	 * @see IGrafik#erstelleFarbenAuswahl()
	 */
	public void erstelleFarbenAuswahl() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#erstelleSchwierigkeitenAuswahl()
	 */
	public void erstelleSchwierigkeitenAuswahl() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#erstelleUntermenue()
	 */
	public void erstelleUntermenue() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#erstelleVariantenAuswahl()
	 */
	public void erstelleVariantenAuswahl() {
		// TODO Auto-generated method stub

	}

	/**
	 * Gibt eine Liste mit den erfolgten Methodenaufrufen auf dem Grafikdummy
	 * waehrend eines Testdurchlaufs zurueck.
	 * 
	 * @return eine Liste mit den erfolgten Methodenaufrufen
	 */
	public List<String> gibErfolgteMethodenAufrufe() {
		return erfolgteMethodenAufrufe;
	}

	/**
	 * @see IGrafik#gibSchwierigkeitsgrad()
	 */
	public String gibSchwierigkeitsgrad() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IGrafik#gibSpielfeldgroesse()
	 */
	public int gibSpielfeldgroesse() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see IGrafik#gibSpielmodus()
	 */
	public Spielmodus gibSpielmodus() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IGrafik#gibSpielvariante()
	 */
	public String gibSpielvariante() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IGrafik#gibStrafzeit()
	 */
	public int gibStrafzeit() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see IGrafik#istMaster()
	 */
	public boolean istMaster() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see IGrafik#istMehrspielerspiel()
	 */
	public boolean istMehrspielerspiel() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see IGrafik#loescheZiffer(IKoordinate)
	 */
	public void loescheZiffer(IKoordinate koordinate) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#markiereKonfliktfelder(List)
	 */
	public void markiereKonfliktfelder(List<IKoordinate> konfliktfelder) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeClient(IClient)
	 */
	public void setzeClient(IClient client) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeFarbenwahl()
	 */
	public void setzeFarbenwahl() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeGesetzteZiffern(Integer[][])
	 */
	public void setzeGesetzteZiffern(Integer[][] gesetzteZiffern) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeGroesse(int)
	 */
	public void setzeGroesse(int groesse) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeIstMehrspielerspiel(boolean)
	 */
	public void setzeIstMehrspielerspiel(boolean spielmodus) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeLoesung(Integer[][])
	 */
	public void setzeLoesung(Integer[][] loesung) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeSchwierigkeitswahl()
	 */
	public void setzeSchwierigkeitswahl() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeSpielvariantenwahl()
	 */
	public void setzeSpielvariantenwahl() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeStartbelegung(Integer[][])
	 */
	public void setzeStartbelegung(Integer[][] startbelegung) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#setzeZiffer(IKoordinate, int)
	 */
	public void setzeZiffer(IKoordinate koordinate, int ziffer) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#spielfensterSchliessen()
	 */
	public void spielfensterSchliessen() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#verdeckeSpielfeld()
	 */
	public void verdeckeSpielfeld() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeAlleMoeglichkeiten(List)
	 */
	public void zeigeAlleMoeglichkeiten(List<IKoordinate> koordinaten) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeEinzelspielermenue()
	 */
	public void zeigeEinzelspielermenue() {
		erfolgteMethodenAufrufe.add("zeigeEinzelspielermenue");
	}

	/**
	 * @see IGrafik#zeigeEndstand(List, List)
	 */
	public void zeigeEndstand(List<String> spielerliste, List<Integer> punkte) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeFehlermeldung(String)
	 */
	public void zeigeFehlermeldung(String fehlermeldung) {
		erfolgteMethodenAufrufe.add("zeigeFehlermeldung");
	}

	/**
	 * @see IGrafik#zeigeHauptmenue()
	 */
	public void zeigeHauptmenue() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeMehrspielermenue(IClient)
	 */
	public void zeigeMehrspielermenue(IClient client) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeMehrspielerMenueAuswahl(IClient)
	 */
	public void zeigeMehrspielerMenueAuswahl(IClient client) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeServerconnectDialog(IClient, boolean)
	 */
	public boolean zeigeServerconnectDialog(IClient client, boolean laden) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see IGrafik#zeigeSpeichernDialog(boolean)
	 */
	public void zeigeSpeichernDialog(boolean spielfeld) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeSpielBeitretenFenster(List)
	 */
	public void zeigeSpielBeitretenFenster(List<ISpielinformation> information) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeSpielfenster()
	 */
	public void zeigeSpielfenster() {
		// TODO Auto-generated method stub

	}

	/**
	 * Zeigt einen Dialog zum Laden eines gespeicherten Spiels an.
	 * 
	 * @param client
	 *            der aufrufende Client
	 * @param spielmodus
	 *            der Spielmodus des Spiels
	 */
	public void zeigeSpielLadenDialog(IClient client, Spielmodus spielmodus) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeSpielmodusMenueAuswahl(IClient)
	 */
	public void zeigeSpielmodusMenueAuswahl(IClient client) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IGrafik#zeigeWartenAufSpielstartFenster(boolean)
	 */
	public void zeigeWartenAufSpielstartFenster(boolean istMasterspieler) {
		// TODO Auto-generated method stub

	}

}
