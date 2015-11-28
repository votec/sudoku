package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import server.IServer;
import server.Server;
import server.ServerDummy;
import common.*;

/**
 * Die Klasse Client repraesentiert einen Benutzer des Sudokuprogramms. Hier
 * wird der Ablauf, die Menuefuehrung sowie die Kommunikation zwischen Grafik
 * und Server gesteuert. Desweiteren wird mit der Main-Methode das Hauptprogramm
 * gestartet.
 * 
 * @author Heiko Schroeder
 */
public class Client implements IClient {

	/**
	 * Hier werden die moeglichen Zustaende des Clients waehrend der
	 * Programmlaufzeit definiert.
	 */
	private enum ClientZustand {
		Menu, SpielLaeuft, WarteAufSpielstart
	}

	/**
	 * Startet den Client und damit das Hauptprogramm.
	 * 
	 * @param args
	 *            die Argumente der Kommandozeile
	 */
	public static void main(String[] args) {
		// Instanziieren des Clients
		IClient client = new Client();
		// Instanziieren der Grafik
		IGrafik grafik = new Grafik();

		// die Grafik beim Client bekannt machen
		client.setzeGrafik(grafik);
		// den Client bei der Grafik bekannt machen
		grafik.setzeClient(client);
		// das Hauptprogramm starten
		client.starteClient();
	}

	/***************************************************************************
	 * Variablen, zustaendig fuer die Client-Server verbindung
	 */

	/** RMI Registry fuer den Client. */
	private Registry clientRegistry;
	/**
	 * Speichert den Zustand, in welchem sich der Spielaufbau, das Spiel
	 * befindet.
	 */
	private ClientZustand clientZustand;
	/** Instanz der Grafik. */
	private IGrafik grafik;
	/** Gibt an, ob der Client im aktuellen Spiel der Masterspieler ist. */
	private boolean istMasterspieler;
	/** Der lokale Server. */
	private IServer lokalServer;
	/** Port des lokalen Servers. */
	private int lokalServerPort;
	/** Schwierigkeitsgrad des aktuellen Spiels. */
	private Schwierigkeitsgrad schwierigkeitsgrad;
	/** Der per RMI verbundene Server. */
	private IServer server;
	/** Spielerlisten History fuer Vergleich beim Spielstand */
	private List<String> spielerHistory;

	/***************************************************************************
	 * Variablen fuer das laufende oder zu startende Spiel
	 */

	/** Adresse des Servers (IP oder URL). */
	private String serverAdresse;
	/** Port des Servers. */
	private int serverPort;
	/** RMI Registry fuer lokalen Server. */
	private Registry serverRegistry;
	/** Stub des Servers (benoetigt fuer RMI). */
	private IServer serverStub;
	/** Gibt an ob eine Verbindung zu einem Server besteht. */
	private boolean serververbindungBesteht;
	/** Farbe des Spielers. */
	private Farbe spielerfarbe;
	/** Name des Spielers. */
	private String spielername;
	/** ID des Spiels, zu welchem der Spieler gehoert (oder gehoeren will). */
	private int spielID;
	/** Gibt an, ob das aktuelle Spiel laeuft. */
	private boolean spielLaeuft;
	/** Der aktuelle Spielmodus. */
	private Spielmodus spielmodus;
	/** Die aktuelle Spielvariante. */
	private Spielvariante spielvariante;
	/** Die aktuelle Strafzeit. */
	private int strafzeit;

	/**
	 * Initialisiert die wichtigsten Variablen mit Standardwerten.
	 */
	public Client() {
		this.clientZustand = ClientZustand.Menu;
		this.spielLaeuft = false;
		this.istMasterspieler = false;
		this.spielmodus = null;
		this.serverPort = 8080;
		this.lokalServerPort = 8081;
		this.spielerHistory = new ArrayList<String>();
	}

	/**
	 * @see IClient#beendeSerververbindung()
	 */
	public void beendeSerververbindung() {
		this.serververbindungBesteht = false;
	}

	/**
	 * @see IClient#erstelleSpiel(common.EnumContainer.Spielmodus, int, String)
	 */
	public boolean erstelleSpiel(Spielmodus spielmodus, int strafzeit,
			String dateiname) {

		this.spielmodus = spielmodus;
		this.strafzeit = strafzeit;
		this.schwierigkeitsgrad = Schwierigkeitsgrad.anfaenger;
		this.spielvariante = Spielvariante.standard;

		ISpielfeld spielfeld = spielfeldLaden(dateiname);

		// Bei bestehender Verbindung ein Spiel einleiten
		if (serververbindungBesteht) {
			try {
				// Erstelle Spiel auf Server und tritt bei
				spielID = server.erstelleSpiel(spielmodus, spielvariante,
						schwierigkeitsgrad, strafzeit, spielfeld);
				this.istMasterspieler = true;
				spielBeitreten(spielID);
			} catch (RemoteException e) {
				e.printStackTrace(System.err);
			}
		}

		// Keine Serververbindung besteht, oder Exception wurde geworfen.
		return false;
	}

	/**
	 * @see IClient#erstelleSpiel(common.EnumContainer.Spielmodus,
	 *      common.EnumContainer.Spielvariante,
	 *      common.EnumContainer.Schwierigkeitsgrad, int)
	 */
	public boolean erstelleSpiel(Spielmodus spielmodus,
			Spielvariante spielvariante, Schwierigkeitsgrad schwierigkeitsgrad,
			int strafzeit) {

		// Setze lokale Spielattribute
		this.spielmodus = spielmodus;
		this.strafzeit = strafzeit;
		this.schwierigkeitsgrad = schwierigkeitsgrad;
		this.spielvariante = spielvariante;

		// Bei bestehender Verbindung ein Spiel einleiten
		if (serververbindungBesteht) {
			try {
				// Erstelle Spiel auf Server und tritt bei
				spielID = server.erstelleSpiel(spielmodus, spielvariante,
						schwierigkeitsgrad, strafzeit);

				// Lokaler Spieler ist Masterspieler des erstellten Spiels
				this.istMasterspieler = true;

				// Tritt dem Spiel bei
				spielBeitreten(spielID);
				return true;

			} catch (RemoteException e) {
				e.printStackTrace(System.err);
			}
		}

		// Keine Serververbindung besteht, oder Exception wurde geworfen.
		return false;
	}

	/**
	 * @see IClient#gibKoordinatenLeererFelder()
	 */
	public List<IKoordinate> gibKoordinatenLeererFelder() {
		List<IKoordinate> koordinatenListe = new ArrayList<IKoordinate>();

		ISpielfeld spielfeld = null;

		try {
			// Hole aktuelles Spielfeld vom Server
			spielfeld = server.gibSpielfeld(spielID, spielername);

			// Berechne hoehe und breite
			int spielfeldBreite = spielfeld.gibBlockBreite()
					* spielfeld.gibSpielfeldBreite();

			int spielfeldHoehe = spielfeld.gibBlockHoehe()
					* spielfeld.gibSpielfeldHoehe();

			// Laufe ueber das Spielfeld und suche die Moeglickeiten heraus
			for (int i = 0; i < spielfeldHoehe; i++) {
				for (int j = 0; j < spielfeldBreite; j++) {

					// Hole Feld aus uebergebenen Spielfeld-Objekt
					IKoordinate koordinate = new Koordinate(j + 1, i + 1);
					IFeld feld = spielfeld.gibFeld(koordinate);

					if (feld.istLeer()) {
						koordinatenListe.add(koordinate);
					}
				}
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return koordinatenListe;
	}

	/**
	 * @see IClient#gibFeldLoesung(IKoordinate)
	 */
	public int gibFeldLoesung(IKoordinate koordinate) {
		int feldLoesung = 0;
		try {
			// Hole die Loesung des Feldes vom Server
			feldLoesung = server.gibFeldLoesung(spielID, spielername,
					koordinate);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return feldLoesung;
	}

	/**
	 * @see IClient#gibFeldMoeglichkeiten(IKoordinate)
	 */
	public List<Integer> gibFeldMoeglichkeiten(IKoordinate koordinate) {
		List<Integer> moeglichkeiten = new ArrayList<Integer>();
		try {
			moeglichkeiten = server.gibFeldMoeglichkeiten(spielID, spielername,
					koordinate);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return moeglichkeiten;
	}

	/**
	 * @see IClient#gibKonfliktfelder()
	 */
	public void gibKonfliktfelder() {
		// Erstelle eine neue ArrayList, in welcher die Koordinaten der Felder
		// mit konflikten gespeichert werden.
		List<IKoordinate> konfliktfelder = new ArrayList<IKoordinate>();

		try {
			// Hole Konfliktfelder vom Server
			konfliktfelder = server.gibKonfliktfelder(spielID, spielername);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		grafik.markiereKonfliktfelder(konfliktfelder);

	}

	/**
	 * @see IClient#gibLokalServer()
	 */
	public IServer gibLokalServer() {
		return lokalServer;
	}

	/**
	 * @see IClient#gibMehrspielerspiele()
	 */
	public List<ISpielinformation> gibMehrspielerspiele() {
		List<ISpielinformation> information = new ArrayList<ISpielinformation>();
		try {
			information = server.gibMehrspielerspiele();
		} catch (RemoteException e) {
			e.printStackTrace(System.err);
		}
		return information;
	}

	/**
	 * @see IClient#gibServer()
	 */
	public IServer gibServer() {
		return server;
	}

	/**
	 * @see IClient#gibServerAdresse()
	 */
	public String gibServerAdresse() {
		return serverAdresse;
	}

	/**
	 * @see IClient#gibSpielerliste()
	 */
	public List<String> gibSpielerliste() {
		List<String> spielerliste = new ArrayList<String>();

		// Lies Spielerliste vom Server
		try {
			spielerliste = server.gibSpielerliste(spielID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return spielerliste;
	}

	/**
	 * @see IClient#gibSpielinformation()
	 */
	public ISpielinformation gibSpielinformation() {
		ISpielinformation information = new Spielinformation();
		information.setzeSchwierigkeitsgrad(this.schwierigkeitsgrad);
		information.setzeSpielID(this.spielID);
		information.setzeSpielvariante(this.spielvariante);
		information.setzeStrafzeit(this.strafzeit);
		return information;
	}

	/**
	 * @see IClient#loescheZiffer(IKoordinate)
	 */
	public void loescheZiffer(IKoordinate koordinate) {
		Serverantwort antwort = null;

		// Gib die Aufforderung eine Ziffer zu loeschen an den Server weiter
		try {
			antwort = server.loescheZiffer(spielID, spielername, koordinate);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// Werte die empfangene Serverantwort aus
		if (antwort != null) {
			if (antwort == Serverantwort.ok) {
				// Wurde Ziffer im Sudoku geloescht, loesche sie auch aus Grafik
				grafik.loescheZiffer(koordinate);
			} else {
				// Kann Ziffer nicht geloescht werden, so zeige Fehlermeldung an
				grafik
						.zeigeFehlermeldung("Diese Ziffer kann nicht geloescht werden");
			}
		}
	}

	/**
	 * @see IClient#setzeEinzelspielermodus()
	 */
	public void setzeEinzelspielermodus() {
		// Setze Spielmodus
		this.spielmodus = Spielmodus.einzelspieler;

		// Starte eine Instanz des Servers lokal
		starteLokalenServer();

		// Wage eine Verbindung mit Server
		if (verbindeMitServer("127.0.0.1") == true) {
			// Wenn Serververbindung herestellt,
			// zeige Einzelspielermenue in Grafik an
			grafik.zeigeEinzelspielermenue();
		}
	}

	/**
	 * @see IClient#setzeGespeichertesSpielFort(String,
	 *      common.EnumContainer.Spielmodus)
	 */
	public void setzeGespeichertesSpielFort(String dateiname,
			Spielmodus spielmodus) {
		// Setze den Spielmodus
		this.spielmodus = spielmodus;

		// Lade Spielstand aus Datei
		InputStream fis = null;
		ISpielstand spielstand = null;

		try {
			fis = new FileInputStream(dateiname);
			ObjectInputStream o = new ObjectInputStream(fis);
			spielstand = (ISpielstand) o.readObject();
		} catch (IOException e) {
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}

		// Setze lokale Spielattribute
		this.spielmodus = spielmodus;
		this.strafzeit = spielstand.gibStrafzeit();
		this.schwierigkeitsgrad = spielstand.gibSchwierigkeitsgrad();
		this.spielvariante = spielstand.gibSpielvariante();

		// Wenn Spielstand im Einzelspielermodus fortgesetzt werden soll
		if (spielmodus == Spielmodus.einzelspieler) {
			// Starte lokalen Spielserver
			starteLokalenServer();

			if (verbindeMitServer("127.0.0.1") == true) {
				try {
					// Ermittele Spielfeldgroesse
					int blockbreite = spielstand.gibStartbelegung()
							.gibBlockBreite();
					int spielfeldbreite = spielstand.gibStartbelegung()
							.gibSpielfeldBreite();
					grafik.setzeGroesse(blockbreite * spielfeldbreite);

					// Erstelle Spiel auf lokalem Server
					this.spielID = server.erstelleSpiel(spielstand);
					this.istMasterspieler = true;
					this.spielerfarbe = spielstand.gibMasterspielerfarbe();
					this.spielername = spielstand.gibMasterspielername();

					// Melde Spieler am Spiel an und starte Spiel
					server.spielerAnmelden(spielID, spielername, spielerfarbe);
					spielStarten();

				} catch (RemoteException e) {
					// NOP
				}
			}

			// Wenn Spielstand im Mehrspielermodus fortgesetzt werden soll.
		} else if (spielmodus == Spielmodus.mehrspieler) {

			// Uebergebe Spielstand beim erstellen
			try {
				this.spielID = server.erstelleSpiel(spielstand);
				this.istMasterspieler = true;
				this.spielerfarbe = spielstand.gibSpielerfarben().get(0);
				this.spielername = spielstand.gibMasterspielername();

				server.spielerAnmelden(spielID, spielername, spielerfarbe);

				grafik.zeigeWartenAufSpielstartFenster(istMasterspieler);
				clientZustand = ClientZustand.WarteAufSpielstart;
			} catch (RemoteException e) {
				// NOP
			}
		}
	}

	/**
	 * @see IClient#setzeGrafik(IGrafik)
	 */
	public void setzeGrafik(IGrafik grafik) {
		this.grafik = grafik;
	}

	/**
	 * @see IClient#setzeMehrspielermodus()
	 */
	public void setzeMehrspielermodus() {
		this.spielmodus = Spielmodus.mehrspieler;
	}

	/**
	 * @see IClient#setzeSpielerfarbe(common.EnumContainer.Farbe)
	 */
	public void setzeSpielerfarbe(Farbe spielerfarbe) {
		this.spielerfarbe = spielerfarbe;
	}

	/**
	 * @see IClient#setzeSpielername(String)
	 */
	public void setzeSpielername(String spielername) {
		this.spielername = spielername;
	}

	/**
	 * @see IClient#setzeZiffer(IKoordinate, int)
	 */
	public void setzeZiffer(IKoordinate koordinate, int ziffer) {
		try {
			// Gib Aufforderung eine Ziffer zu setzen an Server weiter
			Serverantwort antwort = server.setzeZiffer(spielID, spielername,
					koordinate, ziffer);

			// Wurde die Ziffer falsch gesetzt, verdecke Spielfeld
			if (antwort == Serverantwort.zifferFalsch) {
				grafik.loescheZiffer(koordinate);
				grafik.verdeckeSpielfeld();
				// Ziffer richtig, trage Ziffer fest in Spielfeld ein
			} else if (antwort == Serverantwort.ok) {
				grafik.setzeZiffer(koordinate, ziffer);
				// Spielfeld falsch geloest, markiere alle Konfliktfelder
			} else if (antwort == Serverantwort.spielfeldFalschGeloest) {
				grafik.setzeZiffer(koordinate, ziffer);
				gibKonfliktfelder();
			} else if (antwort == Serverantwort.spielfeldGeloest) {
				grafik.setzeZiffer(koordinate, ziffer);
				grafik.zeigeFehlermeldung("Glueckwunsch! Sie haben das Spielfeld geloest");
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see IClient#speichereSpiel(String)
	 */
	public void speichereSpiel(String dateiname) {
		ISpielstand spielstand = null;
		try {
			spielstand = server.gibSpielstand(spielID);
		} catch (RemoteException e) {
			System.out.println("Remote Exception : gibKonfliktfelder(...)");
		}

		if (!dateiname.endsWith(".sav")) {
			dateiname = dateiname.concat(".sav");
		}

		OutputStream fos = null;
		try {
			fos = new FileOutputStream(dateiname);
			ObjectOutputStream o = new ObjectOutputStream(fos);
			o.writeObject(spielstand);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see IClient#speichereSpielfeld(String)
	 */
	public void speichereSpielfeld(String dateiname) {
		try {
			if (!dateiname.endsWith(".txt")) {
				dateiname = dateiname.concat(".txt");
			}

			Writer fw = new FileWriter(dateiname);
			Writer bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			ISpielfeld spielfeld = server.gibSpielfeld(spielID, spielername);

			// Breite und Hoehe des Feldes berechnen
			int sudokuBreite = spielfeld.gibSpielfeldBreite()
					* spielfeld.gibBlockBreite();
			int sudokuHoehe = spielfeld.gibSpielfeldHoehe()
					* spielfeld.gibBlockHoehe();

			// Iteriere durch Startbelegung und schreibe Startbelegung
			// in die Datei.
			for (int i = 0; i < sudokuHoehe; i++) {
				for (int j = 0; j < sudokuBreite; j++) {
					IKoordinate koordinate = new Koordinate(j + 1, i + 1);
					IFeld feld = spielfeld.gibFeld(koordinate);

					if (feld.istInStartbelegung()) {
						int ziffer = feld.gibInhalt();
						pw.print(ziffer);
					} else {
						pw.print('.');
					}
				}
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Konnte Datei nicht erstellen");
		}
	}

	/**
	 * @see IClient#spielAuswaehlen()
	 */
	public void spielAuswaehlen() {
		List<ISpielinformation> mehrspielerspiele = new ArrayList<ISpielinformation>();

		try {
			mehrspielerspiele = server.gibMehrspielerspiele();
		} catch (RemoteException e) {
			e.printStackTrace(System.err);
		}

		// Zeige Fenster mit Mehrspielerspielen an
		grafik.zeigeSpielBeitretenFenster(mehrspielerspiele);
	}

	/**
	 * @see IClient#spielBeenden()
	 */
	public void spielBeenden() {
		// Objekte fuer Spielfeldloesung, Spielerliste und Punktestand
		List<String> spielerliste = new ArrayList<String>();
		List<Integer> punktestand = new ArrayList<Integer>();

		// hole den Endstand des Spiels
		holeEndstand(spielerliste, punktestand);

		// Melde den Spieler vom Server ab
		spielerAbmelden();

		// zeige Endstand in Grafik an
		grafik.zeigeEndstand(spielerliste, punktestand);
	}

	/**
	 * @see IClient#spielBeitreten(int)
	 */
	public boolean spielBeitreten(int spielID) {
		try {
			// Setze die Spielattribute
			this.spielID = spielID;
			this.schwierigkeitsgrad = server.gibSchwierigkeitsgrad(spielID);
			this.strafzeit = server.gibStrafzeit(spielID);
			this.spielvariante = server.gibSpielvariante(spielID);

			// Versuche den Spieler anzumelden
			Serverantwort antwort = server.spielerAnmelden(spielID,
					spielername, spielerfarbe);

			// Werte die Serverantwort aus
			switch (antwort) {
			case spielerfarbeVergeben:
				grafik
						.zeigeFehlermeldung("Ihre Farbe ist bereits vergeben.");
				return false;

			case spielernameVergeben:
				grafik
						.zeigeFehlermeldung("Ihr Name ist bereits vergeben.");
				return false;

			case spielVoll:
				grafik.zeigeFehlermeldung("Das Spiel ist voll. Es koennen keine weiteren Spieler angemeldet werden.");
				return false;

			case spielerfarbeNichtVorhanden:
				grafik
						.zeigeFehlermeldung("Ihre Farbe ist nicht verfuegbar.");
				return false;

			case ok:
				if (spielmodus == Spielmodus.mehrspieler) {
					// Warte auf Spielstart (nur Mehrspielermodus)
					grafik.zeigeWartenAufSpielstartFenster(istMasterspieler);
					clientZustand = ClientZustand.WarteAufSpielstart;

				} else if (spielmodus == Spielmodus.einzelspieler) {
					// Starte das Spiel (Einzelspielermodus)
					spielStarten();
				}
				return true;
			}
		} catch (RemoteException e) {
			e.printStackTrace(System.err);
		}
		return false;
	}

	/**
	 * @see IClient#spielerAbmelden()
	 */
	public void spielerAbmelden() {
		try {
			// Melde den Spieler vom Server ab.
			server.spielerAbmelden(spielID, spielername);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// Beendet die Verbindung zum Server
		beendeSerververbindung();
	}

	/**
	 * @see IClient#spielStarten()
	 */
	public void spielStarten() {
		try {
			// Pruefe ob genug Spieler angemeldet sind
			boolean genuegendSpieler = server
					.genuegendSpielerAngemeldet(spielID);
			if (!genuegendSpieler) {
				grafik
						.zeigeFehlermeldung("Nicht genuegend Mitspieler angemeldet");
				return;
			}

			// Leite Anfrage zum Spielstart an den Server weiter
			Serverantwort antwort = server.spielStarten(spielID);
			if (antwort != Serverantwort.ok) {
				grafik.zeigeFehlermeldung("Fehler beim Spielstart");
			} else if (antwort == Serverantwort.ok) {
				clientZustand = ClientZustand.WarteAufSpielstart;
			}
		} catch (RemoteException e) {
			e.printStackTrace(System.err);
		}
	}

	/**
	 * @see IClient#starteClient()
	 */
	public void starteClient() {
		// Zeige das Hauptmenue an
		grafik.zeigeHauptmenue();

		// Hauptschleife des Spiels
		while (true) {
			switch (clientZustand) {
			case Menu:
				timeout(); // Warte eine Sekunde ab
				break;
			case WarteAufSpielstart:
				try {
					// Warte solange bis Spiel begonnen wurde oder
					// der Spielstart abgebrochen wurde
					boolean spielGestartet = warteAufspielstart();

					if (spielGestartet) {
						clientZustand = ClientZustand.SpielLaeuft;

						// Setze die Startbelegung in der Grafik
						ISpielfeld spielfeld = server.gibSpielfeld(spielID,
								spielername);
						setzeStartbelegungInGrafik(spielfeld);

						// Setze die Loesung in der Grafik
						ISpielfeld loesung = server
								.gibSpielfeldLoesung(spielID);
						setzeSpielfeldLoesungInGrafik(loesung);

						// Zeige das Spielfenster an
						grafik.zeigeSpielfenster();
					} else {
						// Wenn Spiel nicht erfolgreich gestartet wurde,
						// kehre zum Hauptmenue zurueck
						clientZustand = ClientZustand.Menu;
						grafik.zeigeHauptmenue();
					}

				} catch (RemoteException e) {
					e.printStackTrace(System.err);
				}
				break;

			case SpielLaeuft: // Spiel ist aktiv
				timeout(); // Warte 1 Sekunde

				if (serververbindungBesteht) {
					try {
						// Liste fuer angemeldete Spieler
						List<String> spielerliste = new ArrayList<String>();

						// Frage den Status des Spiels und die Spielerliste ab
						spielLaeuft = server.spielLaeuft(spielID, spielername);
						spielerliste.addAll(server.gibSpielerliste(spielID));

						// Benachrichtige Benutzer ueber abgemeldete Spieler
						pruefeAbmeldungen(spielerliste);

						if (spielLaeuft) {
							// Spiel laeuft

							// Listen fuer Punktestand und Spielerfarben
							List<Integer> punktestand = new ArrayList<Integer>();
							List<EnumContainer.Farbe> spielerfarben = new ArrayList<EnumContainer.Farbe>();

							// Aktualisiere den Punktestand
							punktestand.addAll(server.gibPunktestand(spielID));
							spielerfarben.addAll(server
									.gibSpielerfarben(spielID));

							// Aktualisiere Punktestand
							grafik.aktualisiereSpielstand(spielerliste,
									punktestand, spielerfarben);

						} else if (!spielLaeuft) {
							// Ist das Spiel beendet worden

							// Listen fuer den Endstand
							List<String> spielerEndstand = new ArrayList<String>();
							List<Integer> punkteEndstand = new ArrayList<Integer>();

							// Hole Endstand vom Server
							holeEndstand(spielerEndstand, punkteEndstand);

							// Melde Spieler ab / Setze Spielattribute zurueck
							spielerAbmelden();
							clientZustand = ClientZustand.Menu;
							this.istMasterspieler = false;
							this.spielerHistory.clear();

							// Zeige den Endstand & Schliesse das Spielfenster
							grafik.zeigeEndstand(spielerEndstand,
									punkteEndstand);
							grafik.spielfensterSchliessen();
						}
					} catch (RemoteException e) {
						// Die Serververbindung wurde unterbroechen
						// Kehre zum Hauptmenue zurueck
						grafik
								.zeigeFehlermeldung("Verbindung zum Server unerbrochen");

						// Setze Spielattribute zurueck
						clientZustand = ClientZustand.Menu;
						serververbindungBesteht = false;
						this.istMasterspieler = false;
						this.spielerHistory.clear();
						grafik.spielfensterSchliessen();
						grafik.zeigeHauptmenue();
					}
				} else {
					// Wenn keine Serververbindung besteht,
					// Setze Spielattribute zurueck
					clientZustand = ClientZustand.Menu;
					this.istMasterspieler = false;
					this.spielerHistory.clear();
				}
				break;
			}
		}
	}

	/**
	 * Testmethode zum Starten eines lokalen Servers und dem anschliessenden
	 * Verbinden.
	 * 
	 * @return ob der Server gestartet und zu ihm verbunden wurde
	 */
	protected boolean starteLokalenServerDummy() {
		spielmodus = Spielmodus.einzelspieler;

		if (lokalServer == null) {
			try {
				lokalServer = new ServerDummy("SudokuServer");
				serverStub = (IServer) UnicastRemoteObject.exportObject(
						lokalServer, lokalServerPort);
				serverRegistry = LocateRegistry.createRegistry(lokalServerPort);
				serverRegistry.bind("SudokuServer", serverStub);
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

		return verbindeMitServer("127.0.0.1");
	}

	/**
	 * Testmethode zum Starten eines Servers und dem anschliessenden Verbinden.
	 * 
	 * @return ob der Server gestartet und zu ihm verbunden wurde
	 */
	protected boolean starteServerDummy() {
		spielmodus = Spielmodus.mehrspieler;

		if (server == null) {
			try {
				server = new ServerDummy("SudokuServer");
				serverStub = (IServer) UnicastRemoteObject.exportObject(server,
						serverPort);
				serverRegistry = LocateRegistry.createRegistry(serverPort);
				serverRegistry.bind("SudokuServer", serverStub);
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

		return verbindeMitServer("127.0.0.1");
	}

	/**
	 * @see IClient#verbindeMitServer(String)
	 */
	public boolean verbindeMitServer(String serverAdresse) {
		// setze die Serveradresse
		this.serverAdresse = serverAdresse;

		try {
			/*
			 * Verbinde über Registry mit dem Server Prüfe vorher, ob es sich
			 * um einen lokalen oder einen Remote Server handelt
			 * (unterschiedliche Port Nummern
			 */
			if (spielmodus == Spielmodus.einzelspieler) {
				clientRegistry = LocateRegistry.getRegistry(this.serverAdresse,
						lokalServerPort);
			} else {
				clientRegistry = LocateRegistry.getRegistry(this.serverAdresse,
						serverPort);
			}

			server = (IServer) clientRegistry.lookup("SudokuServer");

			serververbindungBesteht = true;
			return true;

		} catch (Exception e) {
			//e.printStackTrace(System.err);
			grafik.zeigeFehlermeldung("Es konnte keine Verbindung mit diesem Server hergestellt werden");
			return false;
		}
	}

	/**
	 * Holt den Endstand des Spiels nach dessen Beendigung.
	 * 
	 * @param spielerEndstand
	 *            eine Liste mit den Namen aller Spieler, die am Spiel
	 *            teilgenommen haben
	 * @param punkteEndstand
	 *            eine Liste mit den Punkten der Spieler
	 */
	private void holeEndstand(List<String> spielerEndstand,
			List<Integer> punkteEndstand) {
		try {
			HashMap<String, Integer> endstand = server.gibEndstand(spielID);
			Set<Map.Entry<String, Integer>> endstandSet = endstand.entrySet();

			Iterator<Entry<String, Integer>> iterator = endstandSet.iterator();
			while (iterator.hasNext()) {
				Entry<String, Integer> eintrag = iterator.next();
				spielerEndstand.add(eintrag.getKey());
				punkteEndstand.add(eintrag.getValue());
			}

		} catch (RemoteException e) {
			// Die Serververbindung wurde unterbroechen
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Methode wandelt ein uebergebenes Spielfeld in ein Array um, welches die
	 * Spielloesung enthaelt.
	 * 
	 * @param spielfeld
	 *            das umzuwandelnde Spielfeld
	 */
	private void setzeSpielfeldLoesungInGrafik(ISpielfeld spielfeld) {
		// Berechne die Breite und Hoehe des Spielfeldes
		int spielfeldBreite = spielfeld.gibBlockBreite()
				* spielfeld.gibSpielfeldBreite();
		int spielfeldHoehe = spielfeld.gibBlockHoehe()
				* spielfeld.gibSpielfeldHoehe();

		// Initialisiere Array fuer Loesung
		Integer loesung[][] = new Integer[spielfeldBreite][spielfeldHoehe];

		// Laufe ueber das Spielfeld
		for (int i = 0; i < spielfeldHoehe; i++) {
			for (int j = 0; j < spielfeldBreite; j++) {

				// Hole Feld aus uebergebenen Spielfeld-Objekt
				IKoordinate koordinate = new Koordinate(j + 1, i + 1);
				IFeld feld = spielfeld.gibFeld(koordinate);

				// Speichere Ziffer in Loesungsarray
				loesung[i][j] = new Integer(feld.gibInhalt());

			}
		}

		// Setze die Loesung in der Grafik
		grafik.setzeLoesung(loesung);
	}

	/**
	 * Methode liesst die Startbelegung aus einem uebergebenen Spielfeld und
	 * fuegt ziffern und koordinaten in zwei Listen ein, welche der Grafik
	 * uebergeben werden.
	 * 
	 * @param spielfeld
	 *            das Startspielfeld
	 */
	private void setzeStartbelegungInGrafik(ISpielfeld spielfeld) {

		// Berechne die Breite und Hoehe des Spielfeldes
		int spielfeldBreite = spielfeld.gibBlockBreite()
				* spielfeld.gibSpielfeldBreite();
		int spielfeldHoehe = spielfeld.gibBlockHoehe()
				* spielfeld.gibSpielfeldHoehe();

		// Initialisiere Array fuer Startbelegung
		Integer startbelegung[][] = new Integer[spielfeldBreite][spielfeldHoehe];

		// Initialisiere Array fuer gesetzte Ziffern
		Integer gesetzteZiffern[][] = new Integer[spielfeldBreite][spielfeldHoehe];

		// Laufe ueber das Spielfeld und suche die Startbelegung heraus
		for (int i = 0; i < spielfeldHoehe; i++) {
			for (int j = 0; j < spielfeldBreite; j++) {

				// Hole Feld aus uebergebenen Spielfeld-Objekt
				IKoordinate koordinate = new Koordinate(j + 1, i + 1);
				IFeld feld = spielfeld.gibFeld(koordinate);

				// Pruefe ob Feld zur Startbelegung gehoert
				if (feld.istInStartbelegung()) {
					// Gehoert Feld zur Startbelegung, fuege es in Array ein
					startbelegung[i][j] = new Integer(feld.gibInhalt());
					gesetzteZiffern[i][j] = new Integer(0);
				} else {
					// Gehoert Feld nicht zur Startbelegung, setze es auf leer
					// im Array
					startbelegung[i][j] = new Integer(0);

					if (feld.istLeer() == false) {
						gesetzteZiffern[i][j] = new Integer(feld.gibInhalt());
					} else {
						gesetzteZiffern[i][j] = new Integer(0);
					}
				}
			}
		}

		// Setze die Startbelegung in der Grafik
		grafik.setzeStartbelegung(startbelegung);
		grafik.setzeGesetzteZiffern(gesetzteZiffern);
	}

	/**
	 * Liest anhand eines Dateinamen vom "Simple Sudoku"-Format ein Spielfeld
	 * mit eingetragenen Ziffern aus.
	 * 
	 * @param dateiname
	 *            Name der Datei zum Auslesen des Spielfeldes
	 * @return das Spielfeld mit den in der Datei vorgebenen Feldern
	 */
	private ISpielfeld simpleSudokuZuSpielfeld(String dateiname) {

		// erzeugen eines BufferedReader-Objektes aus dem Dateinamen
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(dateiname));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Erzeuge ein Standard Spielfeld
		ISpielfeld spielfeld = new Spielfeld_Standard();

		// Bestimme Breite und Hoehe des Sudokus
		int sudokuBreite = spielfeld.gibSpielfeldBreite()
				* spielfeld.gibBlockBreite();
		int sudokuHoehe = spielfeld.gibSpielfeldHoehe()
				* spielfeld.gibBlockHoehe();

		try {
			for (int i = 0; i < sudokuHoehe; i++) {

				// einlesen der naechsten Zeile der Datei
				String zeile = "";
				try {
					zeile = in.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}

				// Entferne alle horizontalen Trennzeichen aus Zeile
				zeile = zeile.replace("-", "");

				if (zeile.length() == 0) {
					// wenn Zeile = "---...----", dann Einlesen ueberspringen
					i--;
				} else {
					// ansonsten Zeichen einlesen
					int anzahlGeleseneZeichen = 0;
					for (int j = 0; j < sudokuBreite; j++) {
						IKoordinate koordinate = new Koordinate(j + 1, i + 1);
						IFeld feld = spielfeld.gibFeld(koordinate);

						// Entferne alle horizontalen Trennzeichen aus Zeile
						zeile = zeile.replace("|", "");
						String gelesenesZeichen = zeile.substring(
								anzahlGeleseneZeichen,
								anzahlGeleseneZeichen + 1);

						// Pruefe ob gelesenes Zeichen eine Ziffer ist
						if (!(gelesenesZeichen.equals("."))) {
							// Trage Ziffer in Spielfeld ein
							int ziffer = Integer.parseInt(gelesenesZeichen);
							feld.setzeInhalt(ziffer);
							feld.setzeStartbelegung(true);
						} else {
							// Trage keine Ziffer in Spielfeld ein
							spielfeld.setzeZiffer(koordinate, 0);
						}
						anzahlGeleseneZeichen++;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Datei hat falsches Format");
		}

		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return spielfeld;
	}

	/**
	 * Laedt ein Spielfeld aus einer Datei.
	 * 
	 * @param dateiname
	 *            der Name der einzulesenden Datei
	 * @return das ausgelesene Spielfeld
	 */
	private ISpielfeld spielfeldLaden(String dateiname) {
		ISpielfeld spielfeld = null;
		// Unterscheidung der Formate anhand der Endung
		if (dateiname.endsWith(".ss")) {
			// Endung ".ss" -> Simple Sudoku
			spielfeld = simpleSudokuZuSpielfeld(dateiname);
		} else if (dateiname.endsWith(".txt")) {
			// Endung ".txt" -> Web Friendly
			spielfeld = webFriendlyZuSpielfeld(dateiname);
		} else {
			grafik.zeigeFehlermeldung("Keine valide Spielfeld Datei gewaehlt");
		}

		return spielfeld;
	}

	/**
	 * Startet einen lokalen Server.
	 */
	private void starteLokalenServer() {
		// Pruefe ob bereits eine lokaler Server gestartet wurde
		if (lokalServer == null) {
			try {
				// Erstelle neue lokale Serverinstanz
				lokalServer = new Server("SudokuServer"); // Erstelle Instanz
				// des

				// Erstelle Server stub
				serverStub = (IServer) UnicastRemoteObject.exportObject(
						lokalServer, lokalServerPort);

				// Erstelle Registry
				serverRegistry = LocateRegistry.createRegistry(lokalServerPort);

				// registiere Server
				serverRegistry.bind("SudokuServer", serverStub);
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

	/**
	 * Erzwingt einen Timeout. Das Programm wartet fuer eine Sekunde.
	 */
	private void timeout() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Sleep Interrupted");
		}
	}

	/**
	 * Wartet, bis das Spiel begonnen wurde und signalisiert dies mit dem
	 * Rueckgabetyp true. Es wird false zurueckgegeben, wenn vom Server eine
	 * RemoteException gewurfen wurde.
	 * 
	 * @return true: Spiel begonnen; false: RemoteException vom Server
	 */
	private boolean warteAufspielstart() {
		try {
			// Warte bis Spielfeld generiert wurde / Spiel begonnen wurde
			while (true) {

				// Warte eine Sekunde
				timeout();
				if (serververbindungBesteht) {

					// Frage beim Server ab, ob das Spiel laeuft
					spielLaeuft = server.spielLaeuft(spielID, spielername);

					// Wenn Spiel laeuft, breche Abfrage ab und setze Spiel
					// aktiv
					if (spielLaeuft) {
						clientZustand = ClientZustand.SpielLaeuft;
						break;
					}
				} else {
					return false;
				}
			}
			return true;

		} catch (RemoteException e) {
			e.printStackTrace(System.err);
			return false;
		}
	}

	/**
	 * Liest anhand eines Dateinamen vom "Web Friendly"-Format ein Spielfeld mit
	 * eingetragenen Ziffern aus.
	 * 
	 * @param dateiname
	 *            der Name der Datei zum Auslesen des Spielfeldes
	 * @return das Spielfeld mit den in der Datei vorgebenen Feldern
	 */
	private ISpielfeld webFriendlyZuSpielfeld(String dateiname) {

		// erzeugen eines BufferedReader-Objektes aus dem Dateinamen
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(dateiname));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// einlesen der ersten Zeile der Datei
		String zeile = "";
		try {
			zeile = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Laenge der Zeile ermitteln
		int laenge = zeile.length();

		// leeres Spielfeld erzeugen
		ISpielfeld spielfeld = null;

		try {
			// Unterscheidung der Spielvarianten anhand der Ziffern im Spielfeld
			switch (laenge) {
			case 81:
				spielfeld = new Spielfeld_Standard();
				break;
			case 36:
				spielfeld = new Spielfeld_Fudschijma_2x3();
				break;
			default: // fehlerhafte Datei
			}

			int anzahlGeleseneZeichen = 0;

			// einlesen der Ziffern ins Spielfeld
			int sudokuBreite = spielfeld.gibSpielfeldBreite()
					* spielfeld.gibBlockBreite();
			int sudokuHoehe = spielfeld.gibSpielfeldHoehe()
					* spielfeld.gibBlockHoehe();

			for (int i = 0; i < sudokuHoehe; i++) {
				for (int j = 0; j < sudokuBreite; j++) {
					IKoordinate koordinate = new Koordinate(j + 1, i + 1);
					IFeld feld = spielfeld.gibFeld(koordinate);

					String gelesenesZeichen = zeile.substring(
							anzahlGeleseneZeichen, anzahlGeleseneZeichen + 1);
					if (!(gelesenesZeichen.equals("."))) {
						int ziffer = Integer.parseInt(gelesenesZeichen);
						spielfeld.setzeZiffer(koordinate, ziffer);
						feld.setzeStartbelegung(true);
					} else {
						feld.setzeInhalt(new Integer(0));
					}
					anzahlGeleseneZeichen++;
				}
			}
		} catch (Exception e) {
			System.out.println("Datei hat falsches Format");
		}

		// Schliessen des Einlesens
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return spielfeld;
	}

	/**
	 * Die Methode prueft, ob sich Spieler vom Spiel abgemeldet haben
	 * und informiert den Benutzer darueber.
	 * 
	 * @param spielerliste
	 * 			die Liste mit allen aktuell angemeldeten Spielern
	 */
	private void pruefeAbmeldungen(List<String> spielerliste) {
		if (spielerliste.size() == 1 && spielmodus == Spielmodus.mehrspieler) {
			// Wenn aktueller Spieler der letze Angemeldete ist.
			grafik
					.zeigeFehlermeldung("Alle Mitspieler haben sich abgemeldet. Das Spiel wird beendet.");
		} else if (spielerHistory != null) {
			// Durchlaufe aktuelle Spielerliste und vergleiche mit vorheriger
			for (String name : spielerHistory) {
				if (!spielerliste.contains(name)) {
					// Wenn sich ein Spieler abgemeldet hat, benachrichtige
					// Benutzer
					grafik.zeigeFehlermeldung(name
							+ " hat sich vom Spiel abgemeltet!");
				}
			}
		}
		// Ersetze die spielerHistory mit aktueller Spielerliste
		spielerHistory.clear();
		spielerHistory.addAll(spielerliste);
	}

}
