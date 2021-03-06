package grafik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.KeyStroke;
import javax.swing.OverlayLayout;
import javax.swing.Timer;

import common.EnumContainer;
import common.IKoordinate;
import common.Koordinate;
import common.EnumContainer.Farbe;

import client.Client;
import client.IClient;
import client.IGrafik;

/**
 * Die Klasse SpielfeldGrafik erzeugt die grafische Anzeige eines Sudokufeldes.
 * 
 * @author Thomas Fraenkler
 * 
 */
public class SpielfeldGrafik extends javax.swing.JFrame {

	/** serial Version UID */
	private static final long serialVersionUID = -5295035289823856073L;

	/**
	 * Die Klasse MenueListener ist ein ActionListener fuer das Menue und
	 * reagiert auf die Interaktion mit den Menuepunkten.
	 * 
	 * @author Arne Busch
	 * 
	 */
	private class MenueListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("Spielstand_speichern")) {
				grafik.zeigeSpeichernDialog(false);
			}
			if (arg0.getActionCommand().equals("Spielfeld_speichern")) {
				grafik.zeigeSpeichernDialog(true);
			}
			if (arg0.getActionCommand().equals("Beenden")) {
				dispose();
			}
			if (arg0.getActionCommand().equals("Moegliche_Ziffern_anzeigen")) {
				moeglichkeithilfe++;
				zeigeAlleMoeglichkeiten();
			}
			if (arg0.getActionCommand().equals("Moegliche_Ziffern_ausblenden")) {
				alleMoeglichkeitenAusblenden();
			}
			if (arg0.getActionCommand().equals("Spielfeld_pruefen")) {
				konflikthilfe++;
				client.gibKonfliktfelder();
			}
			if (arg0.getActionCommand().equals("Hilfe")) {
				HilfeDialog hilfe = new HilfeDialog();
			}
		}

	}

	/**  */
	private int breite;
	/** */
	private Client client;
	/** */
	private boolean comparison = false;
	/** */
	private Sudokufeld[][] feld = new Sudokufeld[16][16];
	/** */
	private JPanel feldOverlay;
	/** */
	private JFrame Frame;
	/** */
	private Integer gesetzteZiffern[][];
	/** */
	private IGrafik grafik;
	/** */
	private int groesse;
	/** */
	private int hoehe;
	/** */
	private int konflikthilfe = 0;
	/** */
	private Integer loesung[][];
	/** */
	private int loesungshilfe = 0;
	/**  */
	private MenueListener menueListener;
	/** */
	private int moeglichkeithilfe = 0;
	/** */
	private Integer startbelegung[][];

	/**
	 * Initialisiert die Klassenvariablen und das Startspielfeld anhand der
	 * uebergebenen Werte.
	 * 
	 * @param grafik
	 *            die Instanz der Grafikanzeige
	 * @param client
	 *            der zugehoerige Client
	 * @param startbelegung
	 *            die Startbelegung
	 * @param loesung
	 *            die Loesung des Spielfeldes
	 * @param gesetzteZiffern
	 *            die vom Spieler bereits gesetzten Ziffern
	 */
	public SpielfeldGrafik(IGrafik grafik, IClient client,
			Integer[][] startbelegung, Integer[][] loesung,
			Integer[][] gesetzteZiffern) {
		this.grafik = grafik;
		this.startbelegung = startbelegung;
		this.loesung = loesung;
		this.gesetzteZiffern = gesetzteZiffern;
		this.client = (Client) client;
		this.groesse = grafik.gibSpielfeldgroesse();
		this.menueListener = new MenueListener();
		this.setLayout(new BorderLayout());
		JPanel menue = erstelleMenueleiste();
		final JPanel tabelle = new JPanel();
		final JPanel spielfeldpanel = new JPanel();
		JPanel aktpanel = new JPanel();
		aktpanel.setPreferredSize(new Dimension(280, 500));
		final OverlayLayout layout = new OverlayLayout(spielfeldpanel);
		spielfeldpanel.setLayout(layout);
		erzeugeSpielfeld(tabelle, spielfeldpanel);
		// oben das Menue
		this.add(menue, BorderLayout.NORTH);
		// links der Spielstand
		this.add(aktpanel, BorderLayout.WEST);
		// in der Mitte das Spielfeld
		this.add(spielfeldpanel, BorderLayout.CENTER);

		if (comparison) {
			int delay = 500;
			Timer t = new Timer(delay, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tabelle.repaint();
					feldOverlay.repaint();
				}
			});
			t.start();
		}

		this.setTitle("jSudoku");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Implementiert die entsprechenden Listener.
	 */
	public void addListener() {
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				int x = breite * (i % breite) + j % breite;
				int y = hoehe * (i / breite) + j / breite;

				if (groesse == 6 || groesse == 12) {
					x = hoehe * (i % breite) + j % hoehe;
					y = breite * (i / breite) + j / hoehe;
				}

				final int festx = x;
				final int festy = y;
				feld[festx][festy]
						.addSudokufeldListener(new SudokufeldListener() {
							/**
							 * @see Sudokufeldlistener#fielddelete()
							 */
							void fielddelete() {
								IKoordinate koordinate = new Koordinate(
										festx + 1, festy + 1);
								client.loescheZiffer(koordinate);
								// System.out.println("x " + x + ", y " + y);
							}

							/**
							 * @see Sudokufeldlistener#fieldSet(int num)
							 */
							@Override
							void fieldSet(int num) {
								IKoordinate koordinate = new Koordinate(
										festx + 1, festy + 1);
								client.setzeZiffer(koordinate, num);
							}

							/**
							 * @see Sudokufeldlistener#onKeyEvent()
							 */
							@Override
							void onKeyEvent(KeyEvent e) {

								if (e.getKeyCode() == KeyEvent.VK_UP) {
									for (int x = 0; x < groesse; x++) {
										for (int y = 0; y < groesse; y++) {
											feld[x][y].getComponent(0)
													.setBackground(Color.white);
										}
									}

									if (festy > 0) {
										feld[festx][festy - 1].setFocus();
										feld[festx][festy - 1].getComponent(0)
												.setBackground(Color.yellow);

									} else {
										feld[festx][festy].setFocus();
										feld[festx][festy].getComponent(0)
												.setBackground(Color.yellow);
									}
								}

								if (e.getKeyCode() == KeyEvent.VK_DOWN) {
									for (int x = groesse - 1; x > -1; x--) {
										for (int y = groesse - 1; y > -1; y--) {
											feld[x][y].getComponent(0)
													.setBackground(Color.white);

										}
									}
									if (festy < (groesse) - 1) {
										feld[festx][festy + 1].setFocus();
										feld[festx][festy + 1].getComponent(0)
												.setBackground(Color.yellow);

									} else {
										feld[festx][festy].setFocus();
										feld[festx][festy].getComponent(0)
												.setBackground(Color.yellow);
									}
								}

								if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
									for (int x = groesse - 1; x > -1; x--) {
										for (int y = groesse - 1; y > -1; y--) {

											feld[x][y].getComponent(0)
													.setBackground(Color.white);

										}
									}
									if (festx < (groesse) - 1) {
										feld[festx + 1][festy].setFocus();
										feld[festx + 1][festy].getComponent(0)
												.setBackground(Color.yellow);

									} else {
										feld[festx][festy].setFocus();
										feld[festx][festy].getComponent(0)
												.setBackground(Color.yellow);
									}
								}

								if (e.getKeyCode() == KeyEvent.VK_LEFT) {
									for (int x = 0; x < groesse; x++) {
										for (int y = 0; y < groesse; y++) {

											feld[x][y].getComponent(0)
													.setBackground(Color.white);

										}
									}
									if (festx > 0) {
										feld[festx - 1][festy].setFocus();
										feld[festx - 1][festy].getComponent(0)
												.setBackground(Color.yellow);

									} else {
										feld[festx][festy].setFocus();
										feld[festx][festy].getComponent(0)
												.setBackground(Color.yellow);
									}
								}

							}
						});
			}
		}
	}

	/**
	 * Zeigt in allen nicht ausgefüllten Feldern den EmptyView an.
	 * 
	 */

	private void alleMoeglichkeitenAusblenden() {
		List<IKoordinate> koordinaten = client.gibKoordinatenLeererFelder();
		for (Iterator<IKoordinate> it = koordinaten.iterator(); it.hasNext();) {
			IKoordinate koordinate = it.next();
			int x = koordinate.gibX() - 1;
			int y = koordinate.gibY() - 1;

			feld[x][y].setEmptyView();
		}

	}

	/**
	 * Aktualisiert den Spielstand.
	 * 
	 * @param spielerliste
	 *            die Liste mit den Namen der Spieler
	 * @param punkte
	 *            die Liste mit den Punkten der Spieler
	 * @param farbe
	 *            die Liste mit den Farben der Spieler
	 */
	public void aktualisiereSpielstand(List<String> spielerliste,
			List<Integer> punkte, List<Farbe> farbe) {
		JPanel aktpanel = new JPanel();

		aktpanel.setPreferredSize(new Dimension(280, 500));
		aktpanel.setLayout(new GridLayout(0, 3, 10, 10));
		aktpanel.add(new JPanel());
		aktpanel.add(new JPanel());
		aktpanel.add(new JPanel());

		int maxpunkte = 0;

		for (int i = 0; i < punkte.size(); i++) {
			Integer fs = punkte.get(i);

			if (fs > maxpunkte) {
				maxpunkte = fs;
			}
		}

		for (int i = 0; i < farbe.size(); i++) {
			Farbe sf = farbe.get(i);
			JPanel farbe1 = new JPanel();

			if (sf == Farbe.blau)
				farbe1.setBackground(Color.blue);
			if (sf == Farbe.gelb)
				farbe1.setBackground(Color.yellow);
			if (sf == Farbe.gruen)
				farbe1.setBackground(Color.green);
			if (sf == Farbe.pink)
				farbe1.setBackground(Color.pink);
			if (sf == Farbe.orange)
				farbe1.setBackground(Color.orange);
			if (sf == Farbe.rot)
				farbe1.setBackground(Color.red);
			if (sf == Farbe.schwarz)
				farbe1.setBackground(Color.black);
			if (sf == Farbe.weiss)
				farbe1.setBackground(Color.white);

			aktpanel.add(farbe1);
			String sn = spielerliste.get(i);
			JLabel name1 = new JLabel();
			name1.setFont(new Font("Arial", Font.BOLD, 18));
			name1.setText(sn);
			Integer fs = punkte.get(i);

			if (fs == maxpunkte) {
				name1.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			} else {
				name1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			}

			JProgressBar progressbar = new JProgressBar(0, groesse * groesse);
			progressbar.setValue(fs);
			progressbar.setStringPainted(true);

			aktpanel.add(name1);
			aktpanel.add(progressbar);
		}

		int anzahl = 45 - (farbe.size() * 3);

		for (int j = 0; j < anzahl; j++) {
			JPanel buffer2 = new JPanel();
			aktpanel.add(buffer2);
		}

		if (grafik.gibSpielmodus() == EnumContainer.Spielmodus.einzelspieler) {
			aktpanel.add(new JLabel("Pruefungen: "));
			aktpanel.add(new JLabel("" + konflikthilfe));
			aktpanel.add(new JPanel());
			aktpanel.add(new JLabel("Feld loesen"));
			aktpanel.add(new JLabel("" + loesungshilfe));
			aktpanel.add(new JPanel());
			aktpanel.add(new JLabel("Moeglickeiten: "));
			aktpanel.add(new JLabel("" + moeglichkeithilfe));
			aktpanel.add(new JPanel());
		}

		aktpanel.add(new JLabel("Spielvariante: "));
		aktpanel.add(new JLabel(grafik.gibSpielvariante()));
		aktpanel.add(new JPanel());
		aktpanel.add(new JLabel("Schwierigkeit: "));
		aktpanel.add(new JLabel(grafik.gibSchwierigkeitsgrad()));

		this.add(aktpanel, BorderLayout.WEST);
		this.validate();
	}

	/**
	 * Oeffnet einen Dialog, ob das Spiel beendet werden soll. Im positiven Fall
	 * wird das Fenster geschlossen und der Spieler vom Spiel abgemeldet.
	 */
	public void dispose() {
		int n = JOptionPane.showConfirmDialog(Frame,
				"Wollen Sie das Spiel wirklich beenden?", "Spiel beenden",
				JOptionPane.YES_NO_OPTION);

		if (n == JOptionPane.YES_OPTION) {
			client.spielBeenden();
			super.dispose();
		}
	}

	/**
	 * Entsperrt ein Feld.
	 */
	public void entsperren() {
		for (int x = 0; x < groesse; x++) {
			for (int y = 0; y < groesse; y++) {
				feld[x][y].getComponent(0).setVisible(true);
			}
		}
	}

	/**
	 * Inkrementiert die Konflikthilfe.
	 */
	public void erhoeheKonflikthilfe() {
		konflikthilfe++;
	}

	/**
	 * Inkrementiert die Loesungshilfe.
	 */
	public void erhoeheLoesungshilfe() {
		loesungshilfe++;
	}

	/**
	 * Inkrementiert die Moeglichkeitenhilfe.
	 */
	public void erhoeheMoeglichkeithilfe() {
		moeglichkeithilfe++;
	}

	/**
	 * Erzeugt das entsprechende Spielfeld.
	 * 
	 * @param tabelle
	 *            das Spielfeld als Tabelle
	 * @param spielfeldpanel
	 *            das Panel, in das das Spielfeld eingebettet ist
	 */
	public void erzeugeSpielfeld(JPanel tabelle, JPanel spielfeldpanel) {
		GridLayout standard = new GridLayout(3, 3);
		GridLayout fudschijma2x3 = new GridLayout(2, 3);
		GridLayout fudschijma3x2 = new GridLayout(3, 2);
		GridLayout fudschijma3x4 = new GridLayout(4, 3);
		GridLayout fudschijma4x3 = new GridLayout(3, 4);
		GridLayout fudschijma4x4 = new GridLayout(4, 4);

		// Comparison
		if (groesse == 100) {
			groesse = 9;
			comparison = true;
		}

		// Standard 3x3 und Comparison 3x3
		if (groesse == 9 || groesse == 100) {
			tabelle.setPreferredSize(new Dimension(800, 650));
			tabelle.setLayout(standard);
			hoehe = groesse / 3;
			breite = groesse / hoehe;
		}

		// Fujiyama 3x2
		if (groesse == 6) {
			tabelle.setPreferredSize(new Dimension(800, 650));
			tabelle.setLayout(fudschijma2x3);
			hoehe = groesse / 3;
			breite = groesse / hoehe;
		}

		// Fujiyama 3x4
		if (groesse == 12) {
			tabelle.setPreferredSize(new Dimension(800, 650));
			tabelle.setLayout(fudschijma3x4);
			hoehe = groesse / 3;
			breite = groesse / hoehe;
		}

		// Fujiyama 4x4
		if (groesse == 16) {
			tabelle.setPreferredSize(new Dimension(1000, 900));
			tabelle.setLayout(fudschijma4x4);
			hoehe = groesse / 4;
			breite = groesse / hoehe;
		}

		tabelle.setBorder(BorderFactory.createLineBorder(Color.black, 3));

		for (int i = 0; i < groesse; i++) {
			JPanel block = new JPanel();

			// block.setPreferredSize(new Dimension(180, 180));
			if (groesse == 9) {
				block.setLayout(standard);
			}
			if (groesse == 6) {
				block.setLayout(fudschijma3x2);
			}
			if (groesse == 12) {
				block.setLayout(fudschijma4x3);
			}
			if (groesse == 16) {
				block.setLayout(fudschijma4x4);
			}
			block.setBorder(BorderFactory.createLineBorder(Color.black, 3));

			for (int j = 0; j < groesse; j++) {
				// standard
				int x = breite * (i % breite) + j % breite;
				int y = hoehe * (i / breite) + j / breite;

				if (groesse == 6 || groesse == 12) {
					x = hoehe * (i % breite) + j % hoehe;
					y = breite * (i / breite) + j / hoehe;
				}

				Sudokufeld f = new Sudokufeld(this, startbelegung[y][x],
						client, gesetzteZiffern[y][x]);

				this.feld[x][y] = f;

				block.add(f);
			}

			tabelle.add(block);
		}

		addListener();

		spielfeldpanel.add(tabelle);

		// falls die Spielvariante Comparison ist, ein Overlaypanel erzeugen und
		// ueber das Spielfeld legen
		if (comparison) {
			feldOverlay = erzeugeFeldoverlay();
			spielfeldpanel.add(feldOverlay);
		}
	}

	/**
	 * Holt die entsprechende Loesung und zeigt sie an.
	 * 
	 * @param eventbutton
	 *            der Button des zugehoerigen Feldes
	 */
	public void gibFeldloesung(Component eventbutton) {
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				if (feld[i][j].getComponent(0) == eventbutton) {
					IKoordinate koordinate = new Koordinate();
					koordinate.setzeX(i + 1);
					koordinate.setzeY(j + 1);
					int num = client.gibFeldLoesung(koordinate);

					if (num != 0) {
						client.setzeZiffer(koordinate, num);
					}
				}
			}
		}
	}

	/**
	 * Zeigt alle noch moeglichen Zahlen in dem entsprechendem Feld an.
	 * 
	 * @param eventbutton
	 *            der Button des zugehoerigen Feldes
	 */
	public void gibFeldMoeglichkeiten(Component eventbutton) {
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				if (feld[i][j].getComponent(0) == eventbutton) {
					IKoordinate koordinate = new Koordinate();
					koordinate.setzeX(i + 1);
					koordinate.setzeY(j + 1);
					List<Integer> zahlen = client
							.gibFeldMoeglichkeiten(koordinate);

					if (zahlen.size() == 0) {
						zeigeFehlermeldung("Feld schon belegt!");
					} else {
						feld[i][j].setGridView2(zahlen);
					}
				}
			}
		}
	}

	/**
	 * @see IGrafik#gibSpielfeldgroesse()
	 */
	public int gibGroesse() {
		return groesse;
	}

	/**
	 * Gibt die Anzahl der aufgerufenen Konflikthilfe zurueck
	 * 
	 * @return konflikthilfe
	 */
	public int gibKonflikthilfe() {
		return konflikthilfe;
	}

	/**
	 * Gibt die Anzahl der aufgerufenen Loesungshilfe zurueck
	 * 
	 * @return loesungshilfe
	 */
	public int gibLoesungshilfe() {
		return loesungshilfe;
	}

	/**
	 * @see IGrafik#istMehrspielerspiel()
	 */
	public boolean gibMehrspieler() {
		return grafik.istMehrspielerspiel();
	}

	/**
	 * Gibt die Anzahl der aufgerufenen Moeglichkeitshilfe zurueck
	 * 
	 * @return moeglichkeitshilfe
	 */
	public int gibMoeglichkeithilfe() {
		return moeglichkeithilfe;
	}

	/**
	 * @see IGrafik#loescheZiffer(IKoordinate)
	 * 
	 * @param koordinate
	 */
	public void loescheZiffer(IKoordinate koordinate) {
		int x = koordinate.gibX() - 1;
		int y = koordinate.gibY() - 1;
		feld[x][y].setEmptyView();
	}

	/**
	 * @see IGrafik#markiereKonfliktfelder(List)
	 */
	public void markiereKonfliktfelder(List<IKoordinate> konfliktfelder) {
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				feld[i][j].getComponent(0).setBackground(Color.WHITE);
			}
		}
		for (Iterator<IKoordinate> it = konfliktfelder.iterator(); it.hasNext();) {
			IKoordinate koordinate = it.next();
			int x = koordinate.gibX() - 1;
			int y = koordinate.gibY() - 1;
			feld[x][y].getComponent(0).setBackground(Color.red);

		}
	}

	/**
	 * @see IGrafik#setzeZiffer(IKoordinate, int)
	 */
	public void setzeZiffer(IKoordinate koordinate, int num) {
		int x = koordinate.gibX() - 1;
		int y = koordinate.gibY() - 1;
		feld[x][y].setNumberView(num);
	}

	/**
	 * Sperrt ein Feld.
	 */
	public void sperren() {
		for (int x = 0; x < groesse; x++) {
			for (int y = 0; y < groesse; y++) {
				feld[x][y].getComponent(0).setVisible(false);
			}
		}
	}

	/**
	 * Schliesst das Spielfenster.
	 */
	public void spielfensterSchliessen() {
		setVisible(false);
	}

	/**
	 * Vergleicht die gesetzte Zahl mit der Loesung und faerbt sie gruen, falls
	 * sie richtig ist.
	 * 
	 * @param eventbutton
	 *            der zugehoerige Button
	 */
	public void vergleichen(Component eventbutton) {
		for (int i = 0; i < groesse; i++) {
			for (int j = 0; j < groesse; j++) {
				if (feld[i][j].getComponent(0) == eventbutton) {
					IKoordinate koordinate = new Koordinate(i + 1, j + 1);
					int loesung = client.gibFeldLoesung(koordinate);

					if (feld[i][j].getNumber() == loesung) {
						feld[i][j].getComponent(0).setBackground(Color.green);
					}
				}
			}
		}
	}

	/**
	 * Zeigt in allen nicht ausgefuellten Feldern die moeglichen Ziffern an.
	 * 
	 */
	private void zeigeAlleMoeglichkeiten() {
		List<IKoordinate> koordinaten = client.gibKoordinatenLeererFelder();
		for (Iterator<IKoordinate> it = koordinaten.iterator(); it.hasNext();) {
			IKoordinate koordinate = it.next();
			int x = koordinate.gibX() - 1;
			int y = koordinate.gibY() - 1;
			List<Integer> zahlen = client.gibFeldMoeglichkeiten(koordinate);

			feld[x][y].setGridView2(zahlen);
		}
	}

	/**
	 * @see IGrafik#zeigeFehlermeldung(String)
	 */
	public void zeigeFehlermeldung(String fehler) {
		grafik.zeigeFehlermeldung(fehler);
	}

	/**
	 * Erstellt die Menueleiste.
	 */
	private JPanel erstelleMenueleiste() {
		JPanel menue = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JMenuBar menueleiste = new JMenuBar();
		JMenu spielmenue = new JMenu("Spiel");
		JMenu hilfemenue = new JMenu("Hilfe");
		JMenuItem spielSpeichern = erstelleMenuepunkt("Spielstand speichern",
				KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK),
				"Spielstand_speichern", (grafik.istMaster() || !grafik
						.istMehrspielerspiel()));
		JMenuItem spielfeldSpeichern = erstelleMenuepunkt(
				"Spielfeld speichern", KeyStroke.getKeyStroke(KeyEvent.VK_T,
						InputEvent.CTRL_MASK), "Spielfeld_speichern", ((!grafik
						.istMehrspielerspiel() || grafik.istMaster()) && grafik
						.gibSpielvariante() == "Standard"));
		JMenuItem beenden = erstelleMenuepunkt("Beenden", KeyStroke
				.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK), "Beenden",
				true);
		JMenuItem moeglichkeitenAnzeigen = erstelleMenuepunkt(
				"Moegliche Ziffern anzeigen", KeyStroke.getKeyStroke(
						KeyEvent.VK_M, InputEvent.CTRL_MASK),
				"Moegliche_Ziffern_anzeigen", !grafik.istMehrspielerspiel());
		JMenuItem moeglichkeitenAusblenden = erstelleMenuepunkt(
				"Moegliche Ziffern ausblenden", KeyStroke.getKeyStroke(
						KeyEvent.VK_N, InputEvent.CTRL_MASK),
				"Moegliche_Ziffern_ausblenden", !grafik.istMehrspielerspiel());
		JMenuItem pruefen = erstelleMenuepunkt("Spielfeld pruefen", KeyStroke
				.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK),
				"Spielfeld_pruefen", !grafik.istMehrspielerspiel());
		JMenuItem hilfe = erstelleMenuepunkt("Hilfe", KeyStroke.getKeyStroke(
				KeyEvent.VK_H, InputEvent.CTRL_MASK), "Hilfe", true);

		spielmenue.setMnemonic(KeyEvent.VK_S);
		spielmenue.add(spielSpeichern);
		spielmenue.add(spielfeldSpeichern);
		spielmenue.add(beenden);

		hilfemenue.setMnemonic(KeyEvent.VK_H);
		hilfemenue.add(moeglichkeitenAnzeigen);
		hilfemenue.add(moeglichkeitenAusblenden);
		hilfemenue.add(pruefen);
		hilfemenue.add(hilfe);

		menueleiste.add(spielmenue);
		menueleiste.add(hilfemenue);

		menue.add(menueleiste);

		return menue;
	}

	/**
	 * Erstellt einen Menuepunkt.
	 * 
	 * @param text
	 *            der Name des Menuepunktes
	 * @param tastenkuerzel
	 *            das Tastenkuerzel fuer den Menuepunkt
	 * @param actionCommand
	 *            der Kommandotext des Menuepunktes
	 * @param istAktiv
	 *            gibt an, ob der Menuepunkt anwaehlbar ist
	 * @return der Menuepunkt
	 */
	private JMenuItem erstelleMenuepunkt(String text, KeyStroke tastenkuerzel,
			String actionCommand, boolean istAktiv) {
		JMenuItem menuepunkt = new JMenuItem(text);

		if (tastenkuerzel != null) {
			menuepunkt.setAccelerator(tastenkuerzel);
		}

		menuepunkt.addActionListener(menueListener);
		menuepunkt.setActionCommand(actionCommand);
		menuepunkt.setEnabled(istAktiv);

		return menuepunkt;
	}

	/**
	 * Erzeugt ein Overlaypanel und gibt dieses zurueck.
	 * 
	 * @return das Overlaypanel
	 */
	private JPanel erzeugeFeldoverlay() {
		final Image arrowUp = getToolkit().getImage("kleinersenkrecht.JPG");
		final Image arrowDown = getToolkit().getImage("groessersenkrecht.JPG");
		final Image arrowLeft = getToolkit().getImage("kleinerwaagerecht.JPG");
		final Image arrowRight = getToolkit()
				.getImage("groesserwaagerecht.JPG");

		JPanel feldOverlay = new JPanel() {
			/** serial Version UID */
			private static final long serialVersionUID = 8784048402065734000L;

			public void paint(Graphics g) {
				int w = breite * breite;
				int h = hoehe * hoehe;

				for (int i = 0; i < h; i++) {
					for (int j = 0; j < w; j++) {
						int x = this.getWidth() / w * (j + 1);
						int y = this.getHeight() / h * (i + 1);

						if (j < w - 1 && j % 3 != 2) {
							if (loesung[i][j] > loesung[i][j + 1]) {
								g.drawImage(arrowRight, x - 8, y - 50, this);
							}

							if (loesung[i][j] < loesung[i][j + 1]) {
								g.drawImage(arrowLeft, x - 8, y - 50, this);
							}
						}

						if (i < h - 1 && (i % 3) != 2) {
							if (loesung[i][j] > loesung[i + 1][j]) {
								g.drawImage(arrowDown, x - 55, y - 8, this);
							}

							if (loesung[i][j] < loesung[i + 1][j]) {
								g.drawImage(arrowUp, x - 55, y - 8, this);
							}
						}
					}
				}
			}
		};

		feldOverlay.setBackground(new Color(1, 1, 1, 0));

		return feldOverlay;
	}

}