package client;

import java.util.List;
import common.*;
import common.EnumContainer.Spielmodus;

/**
 * Das Interface IGrafik dient als Schnittstelle fuer die Klasse Grafik.
 * 
 * @author Thomas Fraenkler
 * 
 */
public interface IGrafik {

	/**
	 * Aktualisiert permanent den Spielstand.
	 * 
	 * @param spielerliste
	 *            die Liste mit den Namen der teilnehmenden Spieler
	 * @param punkte
	 *            die Liste mit den Punkten der teilnehmenden Spieler
	 * @param farbe
	 *            die Liste mit den Farben teilnehmenden Spieler
	 */
	public void aktualisiereSpielstand(List<String> spielerliste,
			List<Integer> punkte, List<EnumContainer.Farbe> farbe);

	/**
	 * Erstellt die Auswahl der waehlbaren Farben in einer Kombobox.
	 */
	public void erstelleFarbenAuswahl();

	/**
	 * Erstellt die Auswahl der waehlbaren Schwierigkeitsgrade in einer
	 * Kombobox.
	 */
	public void erstelleSchwierigkeitenAuswahl();

	/**
	 * Erstellt eine Auswahl zwischen Spiel laden und Spiel generieren
	 * (Komboboxen + Radiopanel).
	 */
	public void erstelleUntermenue();

	/**
	 * Erstellt die Auswahl der waehlbaren Spielvarianten in einer Kombobox.
	 */
	public void erstelleVariantenAuswahl();

	/**
	 * Gibt den Schwieirgkeitsgrad des Spiels zurueck.
	 * 
	 * @return der Schwierigkeitsgrad
	 */
	public String gibSchwierigkeitsgrad();

	/**
	 * Gibt die Groesse des Spielfelds zurueck.
	 * 
	 * @return die Groesse des Spielfelds
	 */
	public int gibSpielfeldgroesse();

	/**
	 * Gibt den Spielmodus zurueck.
	 * 
	 * @return der Spielmodus
	 */
	public Spielmodus gibSpielmodus();

	/**
	 * Gibt die Variante des Spiels zurueck.
	 * 
	 * @return die Variante
	 */
	public String gibSpielvariante();

	/**
	 * Gibt die Strafzeit des Spiels zurueck.
	 * 
	 * @return die Strafzeit
	 */
	public int gibStrafzeit();

	/**
	 * Gibt zurueck ob der Spieler Master ist
	 * 
	 * @return ist Master
	 */
	public boolean istMaster();

	/**
	 * Gibt zurueck, ob das gewaehlte/erstellte Spiel ein Mehrspielerspiel ist.
	 * 
	 * @return true: das Spiel ist ein Mehrspielerspiel; false: das Spiel ist
	 *         ein Einzelspielerspiel
	 */
	public boolean istMehrspielerspiel();

	/**
	 * Entfernt eine Ziffer aus einem Feld.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 */
	public void loescheZiffer(IKoordinate koordinate);

	/**
	 * Markiert Felder, die zu Konflikten fuehren.
	 * 
	 * @param konfliktfelder
	 *            die Liste mit den Koordinaten der Konfliktfelder
	 * 
	 */
	public void markiereKonfliktfelder(List<IKoordinate> konfliktfelder);

	/**
	 * Meldet den Client bei der Grafik an.
	 * 
	 * @param client
	 *            der zur Grafik gehoerige Client
	 */
	public void setzeClient(IClient client);

	/**
	 * Speichert die gewaehlte Farbe.
	 */
	public void setzeFarbenwahl();

	/**
	 * Setzt die Ziffern in der Grafik, die bei Beginn eines Spiels schon im
	 * Spielfeld eingetragen sind. Dies koennen Startbelegungsfelder sein oder
	 * auch Ziffern, die der Spieler in einem gespeicherten Spiel eingetragen
	 * hat.
	 * 
	 * @param gesetzteZiffern
	 *            die bereits gesetzten Ziffern
	 */
	public void setzeGesetzteZiffern(Integer[][] gesetzteZiffern);

	/**
	 * Setzt die Groesse des Spielfeldes.
	 * 
	 * @param groesse
	 *            die Groesse des Spielfeldes
	 */
	public void setzeGroesse(int groesse);

	/**
	 * Setzt, ob das gewaehlte/erstellte Spiel ein Mehrspielerspiel ist.
	 * 
	 * @param spielmodus
	 *            true: das Spiel ist ein Mehrspielerspiel; false: das Spiel ist
	 *            ein Einzelspielerspiel
	 */
	public void setzeIstMehrspielerspiel(boolean spielmodus);

	/**
	 * Setzt die Spielfeldloesung im Spielfeld.
	 * 
	 * @param loesung
	 *            die Spielfeldloesung
	 */
	public void setzeLoesung(Integer[][] loesung);

	/**
	 * Speichert den gewaehlten Schwierigkeitsgrad.
	 */
	public void setzeSchwierigkeitswahl();

	/**
	 * Speichert die gewaehlte Spielvariante.
	 */
	public void setzeSpielvariantenwahl();

	/**
	 * Setzt die Startbelegung im Spielfeld.
	 * 
	 * @param startbelegung
	 *            die Startbelegung
	 */
	public void setzeStartbelegung(Integer[][] startbelegung);

	/**
	 * Setzt eine Ziffer in einem Feld.
	 * 
	 * @param koordinate
	 *            die Koordinate des Feldes
	 * @param ziffer
	 *            die anzuzeigende Ziffer
	 */
	public void setzeZiffer(IKoordinate koordinate, int ziffer);

	/**
	 * Schliesst das Spielfenster.
	 */
	public void spielfensterSchliessen();

	/**
	 * Verdeckt das Spielfeld fuer die Dauer der Strafzeit.
	 */
	public void verdeckeSpielfeld();



	/**
	 * Zeigt das Einzelspielermenue an.
	 */
	public void zeigeEinzelspielermenue();

	/**
	 * Zeigt nach Spielende den Endstand an.
	 * 
	 * @param spielerliste
	 *            die Liste mit den Namen der teilnehmenden Spieler
	 * @param punkte
	 *            die Liste mit den Punkten der teilnehmenden Spieler
	 */
	public void zeigeEndstand(List<String> spielerliste, List<Integer> punkte);

	/**
	 * Zeigt eine Fehlermeldung als Popupfenster an, z.B. "Der von Ihnen
	 * gewaehlte Name existiert bereits. Waehlen Sie einen neuen Namen.".
	 * 
	 * @param fehlermeldung
	 *            die anzuzeigende Fehlerbeschreibung
	 */
	public void zeigeFehlermeldung(String fehlermeldung);

	/**
	 * Zeigt das Hauptmenue an.
	 */
	public void zeigeHauptmenue();

	/**
	 * Zeigt das Menue an, in dem man auf den Start eines Mehrspielerspiels
	 * wartet.
	 */
	public void zeigeMehrspielermenue(IClient client);

	/**
	 * Zeigt die Mehrspielerspiele an, denen man beitreten kann.
	 * 
	 * @param client
	 *            der Client des Benutzers
	 */
	public void zeigeMehrspielerMenueAuswahl(IClient client);

	/**
	 * Zeigt einen Dialog, um die IP-Adresse des Servers einzugeben.
	 * 
	 * @param client
	 *            der aufrufende Client
	 * @param laden
	 *            ob ein Spiel geladen werden soll
	 * @return ob zum Server verbunden wurde
	 */
	public boolean zeigeServerconnectDialog(IClient client, boolean laden);

	/**
	 * Zeigt einen Dialog zum Speichern eines Spiels an.
	 * 
	 * @param spielfeld
	 *            ob ein spielfeld gespeichert werden soll
	 */
	public void zeigeSpeichernDialog(boolean spielfeld);

	/**
	 * Zeigt das Spielfenster an, in welchem Spieler im Mehrspielermodus auf den
	 * Start des beigetretenen Spieles warten / der Masterspieler das Spiel
	 * starten kann.
	 * 
	 * @param information
	 *            die Liste mit den Informationen ueber die Spiele
	 */
	public void zeigeSpielBeitretenFenster(List<ISpielinformation> information);

	/**
	 * Zeigt das Hauptfenster bei laufendem Spiel an. In ihm befinden sich das
	 * Spielfeld, der Punktestand sowie diverse Buttons.
	 */
	public void zeigeSpielfenster();

	/**
	 * Zeigt einen Dialog zum Laden eines gespeicherten Spiels an.
	 * 
	 * @param client
	 *            der aufrufende Client
	 * @param spielmodus
	 *            der Spielmodus des Spiels
	 */
	public void zeigeSpielLadenDialog(IClient client, Spielmodus spielmodus);

	/**
	 * Zeigt die Auswahl der Spielmodi an.
	 * 
	 * @param client
	 *            der aufrufende Client
	 */
	public void zeigeSpielmodusMenueAuswahl(IClient client);

	/**
	 * Zeigt das Fenster an, in dem man auf den Start eines Mehrspielerspiels
	 * wartet.
	 * 
	 * @param istMasterspieler
	 *            gibt an, ob der aufrufende Client in diesem Spiel der
	 *            Mastzerspieler ist
	 */
	public void zeigeWartenAufSpielstartFenster(boolean istMasterspieler);

}
