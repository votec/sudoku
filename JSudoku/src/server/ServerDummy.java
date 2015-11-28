package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.IKoordinate;
import common.ISpielfeld;
import common.ISpielinformation;
import common.ISpielstand;

/**
 * Die Klasse ServerDummy dient als Dummy-Klasse fuer die Klasse Server.
 * 
 * @author Arne Busch
 * 
 */
public class ServerDummy implements IServer {

	/** Diese Liste speichert die erfolgten Methodenaufrufe. */
	private List<String> erfolgteMethodenAufrufe;
	/** Der zugewiesene Name des Servers. */
	private String servername;

	/**
	 * Erzeugt einen neuen ServerDummy mit einem uebergebenen Namen.
	 * 
	 * @param servername
	 *            der Name des Servers
	 */
	public ServerDummy(String servername) {
		this.servername = servername;
		this.erfolgteMethodenAufrufe = new ArrayList<String>();
	}

	/**
	 * @see IServer#erstelleSpiel(ISpielstand)
	 */
	public int erstelleSpiel(ISpielstand spielstand) throws RemoteException {
		erfolgteMethodenAufrufe.add("erstelleSpiel1");
		return 0;
	}

	/**
	 * @see IServer#erstelleSpiel(common.EnumContainer.Spielmodus,
	 *      common.EnumContainer.Spielvariante,
	 *      common.EnumContainer.Schwierigkeitsgrad, int)
	 */
	public int erstelleSpiel(Spielmodus spielmodus,
			Spielvariante spielvariante, Schwierigkeitsgrad schwierigkeitsgrad,
			int strafzeit) throws RemoteException {
		erfolgteMethodenAufrufe.add("erstelleSpiel2");
		return 1;
	}

	/**
	 * @see IServer#erstelleSpiel(common.EnumContainer.Spielmodus,
	 *      common.EnumContainer.Spielvariante,
	 *      common.EnumContainer.Schwierigkeitsgrad, int, ISpielfeld)
	 */
	public int erstelleSpiel(Spielmodus spielmodus,
			Spielvariante spielvariante, Schwierigkeitsgrad schwierigkeitsgrad,
			int strafzeit, ISpielfeld spielfeld) throws RemoteException {
		erfolgteMethodenAufrufe.add("erstelleSpiel3");
		return 2;
	}

	/**
	 * @see IServer#genuegendSpielerAngemeldet(int)
	 */
	public boolean genuegendSpielerAngemeldet(int spielID)
			throws RemoteException {
		erfolgteMethodenAufrufe.add("genuegendSpielerAngemeldet");
		return false;
	}

	/**
	 * @see IServer#gibEndstand(int)
	 */
	public HashMap<String, Integer> gibEndstand(int spielID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibErfolgteMethodenAufrufe()
	 */
	public List<String> gibErfolgteMethodenAufrufe() throws RemoteException {
		return erfolgteMethodenAufrufe;
	}

	/**
	 * @see IServer#gibFeldLoesung(int, String, IKoordinate)
	 */
	public int gibFeldLoesung(int spielID, String spielername,
			IKoordinate koordinate) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see IServer#gibFeldMoeglichkeiten(int, String, IKoordinate)
	 */
	public List<Integer> gibFeldMoeglichkeiten(int spielID, String spielername,
			IKoordinate koordinate) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibKonfliktfelder(int, String)
	 */
	public List<IKoordinate> gibKonfliktfelder(int spielID, String spielername)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibMehrspielerspiele()
	 */
	public List<ISpielinformation> gibMehrspielerspiele()
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibPunktestand(int)
	 */
	public List<Integer> gibPunktestand(int spielID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibSchwierigkeitsgrad(int)
	 */
	public Schwierigkeitsgrad gibSchwierigkeitsgrad(int spielID)
			throws RemoteException {
		erfolgteMethodenAufrufe.add("gibSchwierigkeitsgrad");
		return Schwierigkeitsgrad.mittel;
	}

	/**
	 * @see IServer#gibServername()
	 */
	public String gibServername() throws RemoteException {
		return servername;
	}

	/**
	 * @see IServer#gibSpielerfarben(int)
	 */
	public List<Farbe> gibSpielerfarben(int spielID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibSpielerliste(int)
	 */
	public List<String> gibSpielerliste(int spielID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibSpielfeld(int, String)
	 */
	public ISpielfeld gibSpielfeld(int spielID, String spielername)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibSpielfeldLoesung(int)
	 */
	public ISpielfeld gibSpielfeldLoesung(int spielID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibSpielliste()
	 */
	public List<ISpiel> gibSpielliste() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibSpielstand(int)
	 */
	public ISpielstand gibSpielstand(int spielID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#gibSpielvariante(int)
	 */
	public Spielvariante gibSpielvariante(int spielID) throws RemoteException {
		erfolgteMethodenAufrufe.add("gibSpielvariante");
		return Spielvariante.standard;
	}

	/**
	 * @see IServer#gibStrafzeit(int)
	 */
	public int gibStrafzeit(int spielID) throws RemoteException {
		erfolgteMethodenAufrufe.add("gibStrafzeit");
		return 0;
	}

	/**
	 * @see IServer#loescheZiffer(int, String, IKoordinate)
	 */
	public Serverantwort loescheZiffer(int spielID, String spielername,
			IKoordinate koordinate) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#setzeServername(String)
	 */
	public void setzeServername(String name) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IServer#setzeZiffer(int, String, IKoordinate, int)
	 */
	public Serverantwort setzeZiffer(int spielID, String spielername,
			IKoordinate koordinate, int ziffer) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see IServer#spielerAbmelden(int, String)
	 */
	public void spielerAbmelden(int spielID, String spielername)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see IServer#spielerAnmelden(int, String, common.EnumContainer.Farbe)
	 */
	public Serverantwort spielerAnmelden(int spielID, String spielername,
			Farbe spielerfarbe) throws RemoteException {
		erfolgteMethodenAufrufe.add("spielerAnmelden");

		if (spielID == 1) {
			return Serverantwort.ok;
		}
		if (spielID == 2) {
			return Serverantwort.spielerfarbeVergeben;
		} else {
			return Serverantwort.spielernameVergeben;
		}
	}

	/**
	 * @see IServer#spielLaeuft(int, String)
	 */
	public boolean spielLaeuft(int spielID, String spielername)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see IServer#spielStarten(int)
	 */
	public Serverantwort spielStarten(int spielID) throws RemoteException {
		erfolgteMethodenAufrufe.add("spielStarten");
		return Serverantwort.zuWenigSpieler;
	}

}
