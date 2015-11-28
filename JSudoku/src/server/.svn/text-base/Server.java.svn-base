package server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import common.*;

/**
 * Die Klasse Server repraesentiert einen Server, auf dem Sudokuspiele laufen.
 * Ein Server kann mehrere Spiele verwalten.
 * 
 * @author Heiko Schroeder
 * 
 */
public class Server implements IServer {

	/**
	 * Startet einen Server auf dem eigenen Rechner.
	 * 
	 * @param args
	 *            die Argumente der Kommandozeile
	 */
	public static void main(String[] args) {
		// Erstellen des Servers
		Server server = new Server("Spielserver 1");

		try {
			// die Nummer des Verbindungsports auf dem eigenen Rechner
			int port = 8080;
			// Erstellen des Server Stubs
			IServer stub = (IServer) UnicastRemoteObject.exportObject(server,
					port);
			// den Server registieren
			Registry registry = LocateRegistry.createRegistry(port);
			registry.bind("SudokuServer", stub);
			// ausgeben, dass der Server registriert wurde und bereit ist
			System.err.println("Server ready");
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		/*
		 * The following are not directly related to RMI. They actually just
		 * keep the server alive, without them server execution would finish
		 * immediately after the try/catch block is finished.
		 */
		// den Server starten
		server.starteServer();
	}

	/** Der zugewiesene Name des Servers. */
	private String servername;

	/** Zuordnung der IP-Adresse auf den Spielernamen. */
	// private HashMap<String, String> ipAufName;
	private HashMap<String, String> spielerAufName;

	/** Zuordnung vom IP-Adressen zum involvierten Spiel. */
	// private HashMap<String, Integer> ipAufSpiel;
	/** Zuordnung der Spieler Identifikation zum involvierten Spiel. */
	private HashMap<String, Integer> spielerAufSpiel;

	/**
	 * Zuordnung von Spieler Identifikation zum Datum, an dem der Client das
	 * letzte mal Kontakt zum Server aufgenommen hat.
	 */
	private HashMap<String, Date> spielerAufZeit;

	/**
	 * Liste mit den IP-Adressen der Clients, welche sich ueber die
	 * spielerAnmelden(...)-Methode an einem Spiel angemeldet haben.
	 */
	private List<String> spielerListe;

	/** Liste mit Spielen, welche auf dem Server laufen. */
	private List<ISpiel> spielliste;

	/**
	 * Erzeugt einen neuen Server mit einem uebergebenen Namen.
	 * 
	 * @param servername
	 *            der Name des Servers
	 */
	public Server(String servername) {
		this.spielliste = new ArrayList<ISpiel>();
		this.servername = servername;
		this.spielerListe = new ArrayList<String>();
		this.spielerAufZeit = new HashMap<String, Date>();
		this.spielerAufSpiel = new HashMap<String, Integer>();
		this.spielerAufName = new HashMap<String, String>();

		statusmeldung("Server wurde initialisiert");
	}

	/**
	 * @see IServer#erstelleSpiel(ISpielstand)
	 */
	public synchronized int erstelleSpiel(ISpielstand spielstand)
			throws RemoteException {

		statusmeldung("Spielstand wurde von " + gibClientIP() + " fortgesetzt");

		// Erstelle ein neues Objekt vom Typ Spiel und speichere dies in der
		// spielliste
		ISpiel spiel = new Spiel(spielstand);
		spielliste.add(spiel);

		// Liefere ID des neu generierten Spiels zurueck.
		return spiel.gibSpielID();
	}

	/**
	 * @see IServer#erstelleSpiel(common.EnumContainer.Spielmodus,
	 *      common.EnumContainer.Spielvariante,
	 *      common.EnumContainer.Schwierigkeitsgrad, int)
	 */
	public synchronized int erstelleSpiel(Spielmodus spielmodus,
			Spielvariante spielvariante, Schwierigkeitsgrad schwierigkeitsgrad,
			int strafzeit) throws RemoteException {

		// Erstelle ein neues Objekt vom Typ Spiel und speichere dies in der
		// spielliste
		ISpiel spiel = new Spiel(spielmodus, spielvariante, schwierigkeitsgrad,
				strafzeit);
		int spielID = spiel.gibSpielID();
		spiel.generiereSpielfeld();
		spielliste.add(spiel);
		statusmeldung("Spiel wurde von " + gibClientIP() + " erstellt");

		// Liefere ID des neu generierten Spiels zurueck.
		return spielID;
	}

	/**
	 * @see IServer#erstelleSpiel(common.EnumContainer.Spielmodus,
	 *      common.EnumContainer.Spielvariante,
	 *      common.EnumContainer.Schwierigkeitsgrad, int, ISpielfeld)
	 */
	public synchronized int erstelleSpiel(Spielmodus spielmodus,
			Spielvariante spielvariante, Schwierigkeitsgrad schwierigkeitsgrad,
			int strafzeit, ISpielfeld spielfeld) throws RemoteException {

		// Erstelle ein neues Objekt vom Typ Spiel und speichere dies in der
		// spielliste
		ISpiel spiel = new Spiel(spielmodus, spielvariante, schwierigkeitsgrad,
				strafzeit);
		int spielID = spiel.gibSpielID();

		spiel.generiereSpielfeld(spielfeld);

		spielliste.add(spiel);

		statusmeldung("Spiel wurde von " + gibClientIP() + " erstellt");

		// Liefere ID des neu generierten Spiels zurueck.
		return spielID;
	}

	/**
	 * @see IServer#genuegendSpielerAngemeldet(int)
	 */
	public synchronized boolean genuegendSpielerAngemeldet(int spielID)
			throws RemoteException {

		// Iteriere ueber die Spielliste und suche das Spiel mit der ID
		// "spielID"
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();
			if (spiel.gibSpielID() == spielID) {
				return spiel.genuegendSpielerAngemeldet();
			}
		}
		return false;
	}

	/**
	 * @see IServer#gibEndstand(int)
	 */
	public HashMap<String, Integer> gibEndstand(int spielID) {
		statusmeldung("Client " + gibClientIP() + " hat Endstand abgefragt.");

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Punktestand zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibEndstand();
			}
		}
		
		HashMap<String, Integer> leererEndstand = new HashMap<String, Integer>();
		return leererEndstand;
	}

	/**
	 * @see IServer#gibErfolgteMethodenAufrufe()
	 */
	public List<String> gibErfolgteMethodenAufrufe() {
		return null;
	}

	/**
	 * @see IServer#gibFeldLoesung(int, String, IKoordinate)
	 */
	public synchronized int gibFeldLoesung(int spielID, String spielername,
			IKoordinate koordinate) throws RemoteException {
		// Statusausgabe
		statusmeldung("Client " + gibClientIP()
				+ " hat sich Feld Loesung geholt.");

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Feldloesung zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibFeldLoesung(koordinate);
			}
		}
		return 0;
	}

	/**
	 * @see IServer#gibFeldMoeglichkeiten(int, String, IKoordinate)
	 */
	public synchronized List<Integer> gibFeldMoeglichkeiten(int spielID,
			String spielername, IKoordinate koordinate) throws RemoteException {
		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Moeglichkeiten fuer Feld
			// zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibFeldMoeglichkeiten(spielername, koordinate);
			}
		}
		return null;
	}

	/**
	 * @see IServer#gibKonfliktfelder(int, String)
	 */
	public synchronized List<IKoordinate> gibKonfliktfelder(int spielID,
			String spielername) throws RemoteException {
		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Moeglichkeiten fuer Feld
			// zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibKonfliktfelder(spielername);
			}
		}
		return null;
	}

	/**
	 * @see IServer#gibMehrspielerspiele()
	 */
	public synchronized ArrayList<ISpielinformation> gibMehrspielerspiele()
			throws RemoteException {
		statusmeldung(gibClientIP()
				+ " hat Liste von Mehrspielerspielen angefordert");

		// Liste um Spielinformationen aller noch nicht gestarteten
		// Mehrspielerspiele zu sammeln
		ArrayList<ISpielinformation> infoListe = new ArrayList<ISpielinformation>();

		// Iteriere ueber spielliste
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel noch nicht gestartet, erstelle ein
			// Spielinformationsobjekt
			// und fuege es der infoListe hinzu.
			if (spiel.spielLaeuft() == false) {

				// Prüfe ob es sich um ein Mehrspielerspiel handelt.
				if (spiel.gibSpielmodus() == Spielmodus.mehrspieler) {

					ISpielinformation spielInfo = new Spielinformation();
					Integer spielID = spiel.gibSpielID();

					// Setze Attribute der Spielinfomation
					// Name, Schwierigkeitsgrad, SpielID, Spielvariante,
					// Strafzeit
					spielInfo.setzeName("Mehrspielerspiel "
							+ spielID.toString());
					spielInfo.setzeSchwierigkeitsgrad(spiel
							.gibSchwierigkeitsgrad());
					spielInfo.setzeSpielID(spielID);
					spielInfo.setzeSpielvariante(spiel.gibSpielvariante());
					spielInfo.setzeStrafzeit(spiel.gibStrafzeit());

					// Iteriere ueber die Spielernamen des Spiels und fuege
					// diese
					// zur Spielinformation hinzu.
					List<String> spielerListe = spiel.gibSpielerliste();
					for (Iterator<String> spielerIt = spielerListe.iterator(); spielerIt
							.hasNext();) {
						spielInfo.fuegeSpielernamenHinzu(spielerIt.next());
					}

					// Fuege Objekt der Gesamtliste von Spielinfotmationen auf
					// diesem Server hinzu.
					infoListe.add(spielInfo);
				}
			}
		}
		return infoListe;
	}

	/**
	 * @see IServer#gibPunktestand(int)
	 */
	public synchronized List<Integer> gibPunktestand(int spielID)
			throws RemoteException {
		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Punktestand zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibPunktestand();
			}
		}
		List<Integer> leereListe = new ArrayList<Integer>();
		return leereListe;
	}

	/**
	 * @see IServer#gibSchwierigkeitsgrad(int)
	 */
	public synchronized Schwierigkeitsgrad gibSchwierigkeitsgrad(int spielID)
			throws RemoteException {
		// Statusausgabe
		statusmeldung("Client " + gibClientIP()
				+ " hat Schwierigkeitsgrad abgefragt.");

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Feldloesung zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibSchwierigkeitsgrad();
			}
		}
		return null;
	}

	/**
	 * @see IServer#gibServername()
	 */
	public String gibServername() throws RemoteException {
		statusmeldung("Client " + gibClientIP() + " hat Servername abgefragt");
		return servername;
	}

	/**
	 * @see IServer#gibSpielerfarben(int)
	 */
	public synchronized ArrayList<EnumContainer.Farbe> gibSpielerfarben(
			int spielID) throws RemoteException {
		ArrayList<EnumContainer.Farbe> spielerFarben = new ArrayList<EnumContainer.Farbe>();

		// Iteriere ueber die Liste der Spiele um das
		// Spiel mit ID = spielID zu finden
		Iterator<ISpiel> it = spielliste.iterator();
		while (it.hasNext()) {
			ISpiel spiel = it.next();
			if (spiel.gibSpielID() == spielID) {
				// Fuege Farben zur lokalen spielerFarben Liste hinzu
				spielerFarben.addAll(spiel.gibSpielerfarbenliste());
			}
		}

		return spielerFarben;
	}

	/**
	 * @see IServer#gibSpielerliste(int)
	 */
	public synchronized List<String> gibSpielerliste(int spielID)
			throws RemoteException {
		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Liste der Spieler zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibSpielerliste();
			}
		}
		List<String> leereListe = new ArrayList<String>();
		return leereListe;
	}

	/**
	 * @see ISpiel#gibSpielfeld(String)
	 */
	public synchronized ISpielfeld gibSpielfeld(int spielID, String spielername)
			throws RemoteException {
		statusmeldung(gibClientIP() + " hat Spielfeld angefordert");

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Spielfeld des Spielers
			// zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibSpielfeld(spielername);
			}
		}
		return null;
	}

	/**
	 * @see IServer#gibSpielfeldLoesung(int)
	 */
	public synchronized ISpielfeld gibSpielfeldLoesung(int spielID)
			throws RemoteException {
		statusmeldung(gibClientIP() + " hat Spielfeldloesung angefordert");

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Spielfeldloesung zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibSpielfeldLoesung();
			}
		}
		return null;
	}

	/**
	 * @see IServer#gibSpielliste()
	 */
	public synchronized List<ISpiel> gibSpielliste() throws RemoteException {
		return spielliste;
	}

	/**
	 * @see IServer#gibSpielstand(int)
	 */
	public synchronized ISpielstand gibSpielstand(int spielID)
			throws RemoteException {

		statusmeldung(gibClientIP() + " hat Spielstand angefordert");

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Spielstand zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibSpielstand();
			}
		}
		return null;
	}

	/**
	 * @see IServer#gibSpielvariante(int)
	 */
	public synchronized Spielvariante gibSpielvariante(int spielID)
			throws RemoteException {
		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Spielvariante zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibSpielvariante();
			}
		}
		return null;
	}

	/**
	 * @see IServer#gibStrafzeit(int)
	 */
	public synchronized int gibStrafzeit(int spielID) throws RemoteException {
		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib Strafzeit zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.gibStrafzeit();
			}
		}
		return 0;
	}

	/**
	 * @see IServer#loescheZiffer(int, String, IKoordinate)
	 */
	public synchronized Serverantwort loescheZiffer(int spielID,
			String spielername, IKoordinate koordinate) throws RemoteException {

		statusmeldung(gibClientIP() + " hat Ziffer geloescht");

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, loesche Ziffer und
			// gib Serverantwort zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.loescheZiffer(spielername, koordinate);
			}
		}
		return null;
	}

	/**
	 * @see IServer#setzeServername(String)
	 */
	public void setzeServername(String name) throws RemoteException {
		servername = name;
	}

	/**
	 * @see IServer#setzeZiffer(int, String, IKoordinate, int)
	 */
	public synchronized Serverantwort setzeZiffer(int spielID,
			String spielername, IKoordinate koordinate, int ziffer)
			throws RemoteException {

		// Statusausgabe
		statusmeldung("Client " + gibClientIP() + " hat Ziffer gesetzt.");

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, setze Ziffer und
			// gib die Serverantwort zurueck
			if (spiel.gibSpielID() == spielID) {
				return spiel.setzeZiffer(spielername, koordinate, ziffer);
			}
		}
		return null;
	}

	/**
	 * @see IServer#spielerAbmelden(int, String)
	 */
	public synchronized void spielerAbmelden(int spielID, String spielername)
			throws RemoteException {
		statusmeldung(gibClientIP() + " hat sich abgemeldet");

		// Entferne auch client aus Pruefliste
		String clientIp = gibClientIP();
		spielerListe.remove(clientIp + "_" + spielername);
		spielerAufSpiel.remove(clientIp + "_" + spielername);
		spielerAufName.remove(clientIp + "_" + spielername);
		spielerAufZeit.remove(clientIp + "_" + spielername);

		ISpiel zuLoeschendesSpiel = null;
		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, melde Spieler ab.
			if (spiel.gibSpielID() == spielID) {
				spiel.spielerAbmelden(spielername);
				if (spiel.gibSpielerliste().size() == 0) {
					zuLoeschendesSpiel = spiel;
				}
			}
		}
		if (zuLoeschendesSpiel != null) {
			statusmeldung("Spiel wurde entfernt. Keine angemeldeten Spieler");
			spielliste.remove(zuLoeschendesSpiel);
		}
	}

	/**
	 * @see IServer#spielerAnmelden(int, String, common.EnumContainer.Farbe)
	 */
	public synchronized Serverantwort spielerAnmelden(int spielID,
			String spielername, Farbe spielerfarbe) throws RemoteException {

		statusmeldung(gibClientIP() + " hat sich angemeldet");

		// Trage Client in lokale Preueflisten ein und
		// mappe IP Adresse auf Name und SpielID
		String clientIP = gibClientIP();
		spielerListe.add(clientIP + "_" + spielername);
		spielerAufZeit.put(clientIP + "_" + spielername, gibAktuelleZeit());
		spielerAufSpiel.put(clientIP + "_" + spielername, spielID);
		spielerAufName.put(clientIP + "_" + spielername, spielername);

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, melde Spieler an.
			if (spiel.gibSpielID() == spielID) {
				return spiel.spielerAnmelden(spielername, spielerfarbe);
			}
		}
		return null;
	}

	/**
	 * @see IServer#spielLaeuft(int, String)
	 */
	public synchronized boolean spielLaeuft(int spielID, String spielername)
			throws RemoteException {

		// statusmeldung(gibClientIP() + " hat abgefragt ob Spiel laeuft");

		// Notiere Aufruf
		this.spielerAufZeit.put(gibClientIP() + "_" + spielername,
				gibAktuelleZeit());

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, gib zurueck ob Spiel laeuft.
			if (spiel.gibSpielID() == spielID) {
				return spiel.spielLaeuft();
			}
		}
		return false;
	}

	/**
	 * @see IServer#spielStarten(int)
	 */
	public synchronized Serverantwort spielStarten(int spielID)
			throws RemoteException {

		statusmeldung(gibClientIP() + " hat Spiel gestartet");

		// Iteriere ueber spielliste und suche Spiel mit ID spielID
		for (Iterator<ISpiel> it = spielliste.iterator(); it.hasNext();) {
			ISpiel spiel = it.next();

			// Wenn Spiel mit spielID gefunden, starte Spiel
			if (spiel.gibSpielID() == spielID) {
				return spiel.spielStarten();
			}
		}
		return null;
	}

	/**
	 * Methode startet den Server im dedizierten Modus.
	 * 
	 * Im dedizierten Modus werden die Verbindung zu den Clients geprueft.
	 * Meldet sich ein Client nicht mehr innerhalb einer bestimmten Zeitspanne,
	 * so wird er automatisch vom Spiel abgemeldet.
	 */
	public void starteServer() {
		System.err.println("Press Ctrl+C for stopping the server\n");

		do {
			// Warte timeout ab
			timeout();
			pruefeClientTimeout();
		} while (true);
	}

	/**
	 * Liefert die aktuelle Zeit in einem Date Objekt zurueck
	 * 
	 * @return die aktuelle Zeit als Date Objekt
	 */
	private Date gibAktuelleZeit() {
		Calendar jetzt = Calendar.getInstance();
		Date zeit = jetzt.getTime();
		return zeit;
	}

	/**
	 * Gibt die IP Adresse des Client der die Methode aufgerufen hat zurueck.
	 * 
	 * @return IP Adresse des Client als String
	 */
	private String gibClientIP() {
		String clientIP = "";
		try {
			clientIP = (String) RemoteServer.getClientHost();
		} catch (ServerNotActiveException e) {
			// NOP
		}
		return clientIP;
	}

	/**
	 * Prueft, ob der Client sich eine gewisse Zeit nicht gemeldet hat und
	 * meldet ihn ab, wenn er ein bestimmtes Zeitlimit ueberschritten hat.
	 */
	private synchronized void pruefeClientTimeout() {
		List<String> ipLoeschen = new ArrayList<String>();
		// Iteriere durch IP Liste
		Iterator<String> it = spielerListe.iterator();
		while (it.hasNext()) {

			// Nimm IP aus clientIp Liste und hole die Zeit der
			// letzten Rueckmeldung am Server.
			String client = it.next();
			Date letzteAnfrage = spielerAufZeit.get(client);

			// Berechne Zeit, die sich der Client nicht mehr gemeldet hat
			long delta = gibAktuelleZeit().getTime() - letzteAnfrage.getTime();

			// Wenn Client sich 60 Sekunden nicht mehr gemeldet hat, kick ihn
			if (delta > 60000) {

				// Suche Spiel heraus, in dem sich Client befand
				int clientSpielID = spielerAufSpiel.get(client);
				String clientName = spielerAufName.get(client);

				// Gib Systemmeldung aus
				statusmeldung("Zeitueberschreitung von Client:" + clientName
						+ "in Spiel: " + clientSpielID + " . Melde Spieler ab.");

				// Merke Client IP zum loeschen vor.
				ipLoeschen.add(client);

				try {
					// Melde Spieler vom Spiel ab.
					spielerAbmelden(clientSpielID, clientName);
				} catch (RemoteException e) {
					// NOP
				}
			}
		}

		// Iteriere ueber die Liste, mit den IPs welche zum loeschen
		// vorgemerkt sind, und loesche diese.
		Iterator<String> delIt = ipLoeschen.iterator();
		while (delIt.hasNext()) {
			spielerListe.remove(delIt.next());
		}
	}

	/**
	 * Methode gibt eine Statusmeldung auf der Kommandozeile aus. Es wird
	 * zusaetzlich die aktuelle Zeit angefuegt.
	 * 
	 * @param meldung
	 *            String mit auszugebender Meldung
	 */
	private void statusmeldung(String meldung) {
		Calendar jetzt = Calendar.getInstance();
		Date zeit = jetzt.getTime();
		System.out.println(zeit.toString() + " : " + meldung);
	}

	/**
	 * Methode erzwingt einen Timeout. Das Programm wartet fuer 5 Sekunden.
	 */
	private void timeout() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("Sleep Interrupted");
		}
	}

}