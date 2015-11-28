package client;

import grafik.SpielfeldGrafik;

import grafik.MyFileFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.*;
import common.EnumContainer.*;

/**
 * Die Klasse Grafik erzeugt die grafische Oberflaeche des Spiels. Vom Client
 * gesteuert erzeugt sie Menuefenster, durch die der Benutzer navigieren kann.
 * 
 * @author Thomas Fraenkler
 * 
 */
public class Grafik extends JFrame implements IGrafik {

	/** serial Version UID */
	private static final long serialVersionUID = -1404982855976971166L;
	/**  */
	private static Schwierigkeitsgrad schwierigkeitsgrad;
	/**  */
	private static String schwierigkeitsgrad2;
	/**  */
	private static Farbe spielerfarbe;
	/**  */
	private static String spielerfarbe2;
	/**  */
	private static String spielername;
	/**  */
	private static Spielmodus spielmodus;
	/**  */
	private static Spielvariante spielvariante;
	/**  */
	private static String spielvariante2;

	/**
	 * Gibt die Farbe des Spielers zurueck.
	 * 
	 * @return die Farbe des Spielers
	 */
	public static String gibSpielerfarbe() {
		return spielerfarbe2;
	}

	/**
	 * Gibt den Namen des Spielers zurueck.
	 * 
	 * @return der Name des Spielers
	 */
	public static String gibSpielername() {
		return spielername;
	}

	/**
	 * Setzt die Enter-Taste als Aktivierungstaste fuer einen fokussierten
	 * Button.
	 * 
	 * @param button
	 *            der Button, der bei Fokus ueber die Enter-Taste aktiviert
	 *            werden soll.
	 */
	public static void setzeEnterAlsAktivierungstaste(JButton button) {
		button.registerKeyboardAction(button.getActionForKeyStroke(KeyStroke
				.getKeyStroke(KeyEvent.VK_SPACE, 0, false)), KeyStroke
				.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
				JComponent.WHEN_FOCUSED);

		button.registerKeyboardAction(button.getActionForKeyStroke(KeyStroke
				.getKeyStroke(KeyEvent.VK_SPACE, 0, true)), KeyStroke
				.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
				JComponent.WHEN_FOCUSED);
	}

	/**  */
	GridLayout fudschijma2x3 = new GridLayout(2, 3);
	/**  */
	GridLayout fudschijma3x2 = new GridLayout(3, 2);
	/**  */
	GridLayout fudschijma3x4 = new GridLayout(4, 3);
	/**  */
	GridLayout fudschijma4x3 = new GridLayout(3, 4);
	/**  */
	GridLayout fudschijma4x4 = new GridLayout(4, 4);
	/**  */
	GridLayout standard = new GridLayout(3, 3);
	/**  */
	private int breite;
	/**  */
	private IClient client;
	/**  */
	private JComboBox combofarbe;
	/**  */
	private JComboBox comboschwierigkeit;
	/**  */
	private JComboBox combovariante;
	/**  */
	private String dateiname;
	/**  */
	private JTextField dateitextfeld;
	/**  */
	private JLabel farbe;
	/**  */
	private Integer gesetzteZiffern[][];
	/**  */
	private JTextField getname;
	/**  */
	private int groesse;
	/**  */
	private int hoehe;
	/**  */
	private Integer loesung[][];
	/**  */
	private boolean master;
	/**  */
	private boolean mehrspieler = false;
	/** das Menue */
	private Grafik menue;
	/**  */
	private JLabel name;
	/**  */
	private JPanel radioPanel1;
	/**  */
	private JPanel radioPanel2;
	/**  */
	private JLabel schwierigkeit;
	/**  */
	private SpielfeldGrafik spielfeldGrafik;
	/**  */
	private JLabel spielid;
	/**  */
	private Integer spielID;
	/**  */
	private JLabel spielvariantelabel;
	/**  */
	private Integer startbelegung[][] = new Integer[16][16];
	/**  */
	private Grafik startenmenue;
	/**  */
	private int strafe;
	/**  */
	private JLabel strafzeit;
	/**  */
	private JSlider strafzeitslider;

	/**
	 * @see IGrafik#aktualisiereSpielstand(List, List, List)
	 */
	public void aktualisiereSpielstand(List<String> spielerliste,
			List<Integer> punkte, List<EnumContainer.Farbe> farbe) {
		spielfeldGrafik.aktualisiereSpielstand(spielerliste, punkte, farbe);
	}

	/**
	 * @see IGrafik#erstelleFarbenAuswahl()
	 */
	public void erstelleFarbenAuswahl() {
		// spielerfarbe
		String[] farbenauswahl = { "blau", "rot", "gelb", "pink", "orange",
				"schwarz", "weiss", "gruen" };
		combofarbe = new JComboBox(farbenauswahl);
		combofarbe.setBackground(Color.white);
		combofarbe.setPreferredSize(new Dimension(100, 20));
		spielerfarbe2 = (String) combofarbe.getSelectedItem();
		setzeFarbenwahl();
		combofarbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				spielerfarbe2 = (String) cb.getSelectedItem();
				setzeFarbenwahl();
			}
		});
	}

	/**
	 * @see IGrafik#erstelleSchwierigkeitenAuswahl()
	 */
	public void erstelleSchwierigkeitenAuswahl() {
		// schwierigkeit
		String[] schwierigkeit = { "Anfaenger", "Mittel", "Knifflig", "Schwer",
				"Profi" };
		comboschwierigkeit = new JComboBox(schwierigkeit);
		comboschwierigkeit.setBackground(Color.white);
		schwierigkeitsgrad2 = (String) comboschwierigkeit.getSelectedItem();
		setzeSchwierigkeitswahl();
		comboschwierigkeit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				schwierigkeitsgrad2 = (String) cb.getSelectedItem();
				setzeSchwierigkeitswahl();
			}
		});
	}

	/**
	 * @see IGrafik#erstelleUntermenue
	 */
	public void erstelleUntermenue() {
		getname = new JTextField();
		getname.setPreferredSize(new Dimension(200, 20));
		name = new JLabel();
		name.setText("Name: ");
		farbe = new JLabel();
		farbe.setText("Farbe: ");
		final JLabel spielvariantenlabel = new JLabel();
		spielvariantenlabel.setText("Spielvariante: ");
		final JLabel schwierigkeitsgradlabel = new JLabel();
		schwierigkeitsgradlabel.setText("Schwierigkeitsgrad: ");
		// VariantenBOX
		erstelleVariantenAuswahl();
		// FarbenBOX
		erstelleFarbenAuswahl();
		// SchwierigkeitsBOX
		erstelleSchwierigkeitenAuswahl();
		final JPanel generierenpanel = new JPanel(new GridLayout(2, 2));
		generierenpanel.add(spielvariantenlabel);
		generierenpanel.add(combovariante);
		generierenpanel.add(schwierigkeitsgradlabel);
		generierenpanel.add(comboschwierigkeit);
		final JLabel dateinamelabel = new JLabel();
		dateinamelabel.setText("Dateiname: ");
		final JPanel ladenpanel = new JPanel();
		dateitextfeld = new JTextField();
		final JButton durchsuchenbutton = new JButton("Durchsuchen");
		setzeEnterAlsAktivierungstaste(durchsuchenbutton);
		durchsuchenbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				MyFileFilter filter = new MyFileFilter();
				filter.setDescription(".txt oder .ss");
				filter.getDescription();
				fc.setFileFilter(filter);
				fc.addChoosableFileFilter(new MyFileFilter());
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					dateiname = fc.getSelectedFile().getPath();
					System.out.println("You chose to open this file: "
							+ dateiname);
					dateitextfeld.setText(dateiname);
				}
			}
		});
		// Voreinstellung: generieren enabled, laden disabled
		dateitextfeld.setEnabled(false);
		durchsuchenbutton.setEnabled(false);
		dateinamelabel.setEnabled(false);
		dateitextfeld.setPreferredSize(new Dimension(100, 20));
		ladenpanel.add(dateinamelabel);
		ladenpanel.add(dateitextfeld);
		ladenpanel.add(durchsuchenbutton);
		ladenpanel.setVisible(true);
		generierenpanel.setVisible(true);
		JRadioButton generierenButton = new JRadioButton("Spielfeld generieren");
		generierenButton.setActionCommand("generieren");
		generierenButton.setSelected(true);
		JRadioButton ladenButton = new JRadioButton("Spielfeld laden");
		ladenButton.setActionCommand("laden");
		ButtonGroup group = new ButtonGroup();
		group.add(generierenButton);
		group.add(ladenButton);
		LineBorder border = new LineBorder(Color.LIGHT_GRAY, 3, true);
		radioPanel1 = new JPanel();
		radioPanel1.setLayout(new FlowLayout());
		radioPanel1.setPreferredSize(new Dimension(320, 120));
		radioPanel1.setBorder(border);
		radioPanel1.add(generierenButton);

		for (int i = 0; i < 10; i++) {
			radioPanel1.add(new JPanel());
		}

		radioPanel1.add(generierenpanel);
		radioPanel2 = new JPanel();
		radioPanel2.setLayout(new FlowLayout());
		radioPanel2.setPreferredSize(new Dimension(320, 120));
		radioPanel2.setBorder(border);
		radioPanel2.add(ladenButton);
		// spielfeld generieren , laden disabled
		generierenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dateitextfeld.setEnabled(false);
				durchsuchenbutton.setEnabled(false);
				dateinamelabel.setEnabled(false);
				spielvariantenlabel.setEnabled(true);
				combovariante.setEnabled(true);
				comboschwierigkeit.setEnabled(true);
				schwierigkeitsgradlabel.setEnabled(true);
			}
		});
		// Spielfeld laden , generieren disabled
		ladenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				spielvariantenlabel.setEnabled(false);
				combovariante.setEnabled(false);
				comboschwierigkeit.setEnabled(false);
				schwierigkeitsgradlabel.setEnabled(false);
				dateitextfeld.setEnabled(true);
				durchsuchenbutton.setEnabled(true);
				dateinamelabel.setEnabled(true);
			}
		});

		for (int i = 0; i < 14; i++) {
			radioPanel2.add(new JPanel());
		}

		radioPanel2.add(ladenpanel);
	}

	/**
	 * @see IGrafik#erstelleVariantenAuswahl()
	 */
	public void erstelleVariantenAuswahl() {
		// variante
		String[] variante = { "Standard", "Fudschijma2x3", "Fudschijma4x3",
				"Fudschijma4x4", "Comparison" };
		combovariante = new JComboBox(variante);
		combovariante.setBackground(Color.white);
		spielvariante2 = (String) combovariante.getSelectedItem();
		setzeSpielvariantenwahl();
		combovariante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				spielvariante2 = (String) cb.getSelectedItem();
				setzeSpielvariantenwahl();
			}
		});
	}

	/**
	 * @see IGrafik#gibSchwierigkeitsgrad()
	 */
	public String gibSchwierigkeitsgrad() {
		return schwierigkeitsgrad2;
	}

	/**
	 * @see IGrafik#gibSpielfeldgroesse()
	 */
	public int gibSpielfeldgroesse() {
		return groesse;
	}

	/**
	 * @see IGrafik#gibSpielmodus()
	 */
	public EnumContainer.Spielmodus gibSpielmodus() {
		return spielmodus;
	}

	/**
	 * @see IGrafik#gibSpielvariante()
	 */
	public String gibSpielvariante() {
		return spielvariante2;
	}

	/**
	 * @see IGrafik#gibStrafzeit()
	 */
	public int gibStrafzeit() {
		return strafe;
	}

	/**
	 * @see IGrafik#istMehrspielerspiel()
	 */
	public boolean istMaster() {
		return master;
	}

	/**
	 * @see IGrafik#istMehrspielerspiel()
	 */
	public boolean istMehrspielerspiel() {
		return mehrspieler;
	}

	/**
	 * @see IGrafik#loescheZiffer(IKoordinate)
	 */
	public void loescheZiffer(IKoordinate koordinate) {
		spielfeldGrafik.loescheZiffer(koordinate);
	}

	/**
	 * @see IGrafik#markiereKonfliktfelder(List)
	 */
	public void markiereKonfliktfelder(List<IKoordinate> konfliktfelder) {
		spielfeldGrafik.markiereKonfliktfelder(konfliktfelder);
	}

	/**
	 * @see IGrafik#setzeClient(IClient)
	 */
	public void setzeClient(IClient client) {
		this.client = client;
	}

	/**
	 * @see IGrafik#setzeFarbenwahl()
	 */
	public void setzeFarbenwahl() {
		if (spielerfarbe2.equals("blau")) {
			spielerfarbe = EnumContainer.Farbe.blau;
		} else if (spielerfarbe2.equals("rot")) {
			spielerfarbe = EnumContainer.Farbe.rot;
		} else if (spielerfarbe2.equals("gelb")) {
			spielerfarbe = EnumContainer.Farbe.gelb;
		} else if (spielerfarbe2.equals("weiss")) {
			spielerfarbe = EnumContainer.Farbe.weiss;
		} else if (spielerfarbe2.equals("schwarz")) {
			spielerfarbe = EnumContainer.Farbe.schwarz;
		} else if (spielerfarbe2.equals("orange")) {
			spielerfarbe = EnumContainer.Farbe.orange;
		} else if (spielerfarbe2.equals("pink")) {
			spielerfarbe = EnumContainer.Farbe.pink;
		} else if (spielerfarbe2.equals("gruen")) {
			spielerfarbe = EnumContainer.Farbe.gruen;
		}
	}

	/**
	 * @see IGrafik#setzeGesetzteZiffern(Integer[][])
	 */
	public void setzeGesetzteZiffern(Integer[][] gesetzteZiffern) {
		this.gesetzteZiffern = gesetzteZiffern;
	}

	/**
	 * @see IGrafik#setzeGroesse(int)
	 */
	public void setzeGroesse(int groesse) {
		this.groesse = groesse;
	}

	/**
	 * @see IGrafik#setzeIstMehrspielerspiel(boolean)
	 */
	public void setzeIstMehrspielerspiel(boolean mehrspieler) {
		this.mehrspieler = mehrspieler;
	}

	/**
	 * @see IGrafik#setzeLoesung(Integer[][])
	 */
	public void setzeLoesung(Integer[][] loesung) {
		this.loesung = loesung;
	}

	/**
	 * @see IGrafik#setzeSchwierigkeitswahl()
	 */
	public void setzeSchwierigkeitswahl() {
		if (schwierigkeitsgrad2.equals("Anfaenger")) {
			schwierigkeitsgrad = EnumContainer.Schwierigkeitsgrad.anfaenger;
		} else if (schwierigkeitsgrad2.equals("Knifflig")) {
			schwierigkeitsgrad = EnumContainer.Schwierigkeitsgrad.knifflig;
		} else if (schwierigkeitsgrad2.equals("Mittel")) {
			schwierigkeitsgrad = EnumContainer.Schwierigkeitsgrad.mittel;
		} else if (schwierigkeitsgrad2.equals("Profi")) {
			schwierigkeitsgrad = EnumContainer.Schwierigkeitsgrad.profi;
		} else if (schwierigkeitsgrad2.equals("Schwer")) {
			schwierigkeitsgrad = EnumContainer.Schwierigkeitsgrad.schwer;
		}
	}

	/**
	 * @see IGrafik#setzeSpielvariantenwahl()
	 */
	public void setzeSpielvariantenwahl() {
		if (spielvariante2.equals("Standard")) {
			spielvariante = EnumContainer.Spielvariante.standard;
		}
		if (spielvariante2.equals("Comparison")) {
			spielvariante = EnumContainer.Spielvariante.comparison;
		}
		if (spielvariante2.equals("Fudschijma2x3")) {
			spielvariante = EnumContainer.Spielvariante.fudschijama2x3;
		}
		if (spielvariante2.equals("Fudschijma4x3")) {
			spielvariante = EnumContainer.Spielvariante.fudschijama4x3;
		}
		if (spielvariante2.equals("Fudschijma4x4")) {
			spielvariante = EnumContainer.Spielvariante.fudschijama4x4;
		}
	}

	/**
	 * @see IGrafik#setzeStartbelegung(Integer[][])
	 */
	public void setzeStartbelegung(Integer[][] startbelegung) {
		this.startbelegung = startbelegung;
	}

	/**
	 * @see IGrafik#setzeZiffer(IKoordinate, int)
	 */
	public void setzeZiffer(IKoordinate koordinate, int ziffer) {
		spielfeldGrafik.setzeZiffer(koordinate, ziffer);
	}

	/**
	 * @see IGrafik#spielfensterSchliessen()
	 */
	public void spielfensterSchliessen() {
		spielfeldGrafik.spielfensterSchliessen();
	}

	/**
	 * @see IGrafik#verdeckeSpielfeld()
	 */
	public void verdeckeSpielfeld() {
		spielfeldGrafik.sperren();
		int delay = strafe * 1000;
		Timer t = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spielfeldGrafik.entsperren();
			}
		});
		t.setRepeats(false);
		t.start();
	}

	
	/**
	 * @see IGrafik#zeigeEinzelspielermenue()
	 */
	public void zeigeEinzelspielermenue() {
		final Grafik menue2 = new Grafik();
		menue2.setPreferredSize(new Dimension(500, 500));
		LayoutManager layout = new FlowLayout();
		menue2.setLayout(layout);
		// untermenue is das gleiche wie bei Mehrspieler spiel erstellen
		erstelleUntermenue();
		menue2.add(name);
		menue2.add(getname);

		for (int i = 0; i < 70; i++) {
			menue2.add(new JPanel());
		}

		menue2.add(radioPanel1);

		for (int i = 0; i < 40; i++) {
			menue2.add(new JPanel());
		}

		menue2.add(radioPanel2);
		// Spiel erstellenButton
		JButton erstellenbutton = new JButton("Spiel erstellen");
		setzeEnterAlsAktivierungstaste(erstellenbutton);
		erstellenbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hole + setze Spielername und Farbe
				spielername = getname.getText();

				if (!pruefeSpielername(spielername)) {
					zeigeFehlermeldung("Sie muessen einen gueltigen nichtleeren Namen eingeben.\nUngueltige Zeichen: \" \\ /");
				} else {
					client.setzeSpielername(spielername);
					client.setzeSpielerfarbe(spielerfarbe);
					// Setze Spielmodus = Einzelspieler
					spielmodus = EnumContainer.Spielmodus.einzelspieler;

					// Erstelle neues Spielfeld
					if (!dateitextfeld.isEnabled()) {
						client.erstelleSpiel(spielmodus, spielvariante,
								schwierigkeitsgrad, strafe);
					} else {
						// Dateiname der gewaehlten Datei
						String dateiname = dateitextfeld.getText();
						client.erstelleSpiel(spielmodus, strafe, dateiname);
					}
					setzeSpielvariante(spielvariante.toString());
					menue2.setVisible(false);
				}
			}
		});

		for (int i = 0; i < 85; i++) {
			menue2.add(new JPanel());
		}

		JButton abbrechen = new JButton("Abbrechen");
		setzeEnterAlsAktivierungstaste(abbrechen);
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zeigeHauptmenue();
				menue2.dispose();
			}
		});
		menue2.add(abbrechen);
		menue2.add(erstellenbutton);
		menue2.setTitle("Spiel erstellen");
		menue2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menue2.pack();
		menue2.setVisible(true);
	}

	/**
	 * @see IGrafik#zeigeEndstand(List, List)
	 */
	public void zeigeEndstand(List<String> spielerliste, List<Integer> punkte) {
		final Grafik endstand = new Grafik();
		endstand.setLayout(new BorderLayout());
		JPanel spielfeldpanel = new JPanel();
		JPanel tabelle = new JPanel();

		// Comparison
		if (groesse == 9 || groesse == 100) {
			groesse = 9;
			tabelle.setPreferredSize(new Dimension(600, 600));
			tabelle.setLayout(standard);
			hoehe = groesse / 3;
			breite = groesse / hoehe;
		}

		// Fujiyama 3x2
		if (groesse == 6) {
			tabelle.setPreferredSize(new Dimension(540, 300));
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
			tabelle.setPreferredSize(new Dimension(1000, 700));
			tabelle.setLayout(fudschijma4x4);
			hoehe = groesse / 4;
			breite = groesse / hoehe;
		}

		// spielfeldpanel.setBorder(BorderFactory.createLineBorder(Color.black,
		// 4));
		tabelle.setBorder(BorderFactory.createLineBorder(Color.black, 4));

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

				JButton f = new JButton("" + loesung[y][x]);
				f.setBackground(Color.white);
				f.setForeground(Color.BLUE);
				f.setFont(new Font("Arial", Font.BOLD, 18));
				block.add(f);
			}

			tabelle.add(block);
		}

		spielfeldpanel.add(tabelle);
		JPanel aktpanel = new JPanel();
		aktpanel.setLayout(new GridLayout(0, 2));
		int maxpunkte = 0;

		for (int i = 0; i < punkte.size(); i++) {
			Integer fs = punkte.get(i);
			if (fs > maxpunkte) {
				maxpunkte = fs;
			}

		}

		for (int i = 0; i < spielerliste.size(); i++) {
			String sn = spielerliste.get(i);
			JLabel name1 = new JLabel();
			name1.setFont(new Font("Arial", Font.BOLD, 16));
			name1.setText(sn);
			Integer fs = punkte.get(i);
			if (fs == maxpunkte) {
				name1.setBorder(BorderFactory.createLineBorder(Color.red, 2));
			} else {
				name1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
			}
			aktpanel.add(name1);

			JProgressBar progressbar = new JProgressBar(0, groesse * groesse);
			progressbar.setValue(fs);
			progressbar.setStringPainted(true);
			aktpanel.add(progressbar);
		}

		int anzahl = 30 - (spielerliste.size() * 2);

		for (int j = 0; j < anzahl; j++) {
			JPanel buffer2 = new JPanel();

			aktpanel.add(buffer2);
		}
		if (gibSpielmodus() == EnumContainer.Spielmodus.einzelspieler) {
			aktpanel.add(new JLabel("Pruefungen: "));
			aktpanel.add(new JLabel("" + spielfeldGrafik.gibKonflikthilfe()));

			aktpanel.add(new JLabel("Feldloesungen"));
			aktpanel.add(new JLabel("" + spielfeldGrafik.gibLoesungshilfe()));

			aktpanel.add(new JLabel("Moeglichkeiten: "));
			aktpanel
					.add(new JLabel("" + spielfeldGrafik.gibMoeglichkeithilfe()));

		}

		aktpanel.add(new JLabel("Spielvariante: "));
		aktpanel.add(new JLabel(gibSpielvariante()));

		aktpanel.add(new JLabel("Schwierigkeit: "));
		aktpanel.add(new JLabel(gibSchwierigkeitsgrad()));
		JPanel endstandpanel = new JPanel();
		endstandpanel.setLayout(new BorderLayout());
		JLabel endstandlabel = new JLabel("Endstand");
		endstandlabel.setFont(new Font("Arial", Font.BOLD, 22));
		JButton wiedersehen = new JButton("Schliessen");
		setzeEnterAlsAktivierungstaste(wiedersehen);
		wiedersehen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				endstand.dispose();
				zeigeHauptmenue();
			}
		});
		endstandpanel.add(endstandlabel, BorderLayout.NORTH);
		endstandpanel.add(aktpanel, BorderLayout.CENTER);
		endstandpanel.add(wiedersehen, BorderLayout.SOUTH);
		// endstand.add(endstandlabel, BorderLayout.NORTH);
		endstand.add(endstandpanel, BorderLayout.WEST);
		endstand.add(spielfeldpanel, BorderLayout.CENTER);
		// endstand.add(wiedersehen, BorderLayout.SOUTH);
		endstand.setTitle("Endstand");
		endstand.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endstand.pack();
		endstand.setVisible(true);
	}

	/**
	 * @see IGrafik#zeigeFehlermeldung(String)
	 */
	public void zeigeFehlermeldung(String fehlermeldung) {
		JOptionPane.showMessageDialog(menue, fehlermeldung);
	}

	/**
	 * @see IGrafik#zeigeHauptmenue()
	 */
	public void zeigeHauptmenue() {
		// Hauptmenue
		menue = new Grafik();
		menue.setPreferredSize(new Dimension(200, 250));
		menue.setLayout(new GridLayout(4, 0, 10, 10));
		JButton einzelspieler = new JButton("Einzelspieler");
		setzeEnterAlsAktivierungstaste(einzelspieler);
		einzelspieler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.setzeEinzelspielermodus();
				setzeIstMehrspielerspiel(false);
				menue.setVisible(false);
			}
		});
		JButton mehrspieler = new JButton("Mehrspieler");
		setzeEnterAlsAktivierungstaste(mehrspieler);
		mehrspieler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeigeServerconnectDialog(client, false);
				setzeIstMehrspielerspiel(true);
				client.setzeMehrspielermodus();
				menue.setVisible(false);
			}
		});
		JButton laden = new JButton("Spiel Laden");
		setzeEnterAlsAktivierungstaste(laden);
		laden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menue.zeigeSpielmodusMenueAuswahl(client);
				menue.setVisible(false);
			}
		});
		JButton beenden = new JButton("Programm Beenden");
		setzeEnterAlsAktivierungstaste(beenden);
		beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menue.add(einzelspieler);
		menue.add(mehrspieler);
		menue.add(laden);
		menue.add(beenden);
		menue.setTitle("Hauptmenue");
		menue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menue.pack();
		menue.setVisible(true);
	}

	/**
	 * @see IGrafik#zeigeMehrspielermenue(IClient)
	 */
	public void zeigeMehrspielermenue(final IClient client) {
		final Grafik menue2 = new Grafik();
		menue2.setPreferredSize(new Dimension(500, 550));
		LayoutManager layout = new FlowLayout();
		menue2.setLayout(layout);

		for (int i = 0; i < 30; i++) {
			menue2.add(new JPanel());
		}

		erstelleUntermenue();
		menue2.add(name);
		menue2.add(getname);

		for (int i = 0; i < 46; i++) {
			menue2.add(new JPanel());
		}

		menue2.add(farbe);
		menue2.add(combofarbe);

		for (int i = 0; i < 70; i++) {
			menue2.add(new JPanel());
		}

		menue2.add(radioPanel1);

		for (int i = 0; i < 40; i++) {
			menue2.add(new JPanel());
		}

		menue2.add(radioPanel2);
		JLabel strafzeitlabel = new JLabel("Strafzeit: ");
		strafzeitslider = new JSlider(0, 60);
		strafzeitslider.setMajorTickSpacing(30);
		strafzeitslider.setMinorTickSpacing(5);
		strafzeitslider.setPaintTicks(true);
		strafzeitslider.setPaintLabels(true);
		strafe = (int) strafzeitslider.getValue();
		strafzeitslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				strafe = (int) ((JSlider) e.getSource()).getValue();
			}
		});
		JLabel sekundenlabel = new JLabel("Sekunden");

		for (int i = 0; i < 10; i++) {
			menue2.add(new JPanel());
		}

		menue2.add(strafzeitlabel);
		menue2.add(strafzeitslider);
		menue2.add(sekundenlabel);
		// Spiel erstellen
		JButton erstellenbutton = new JButton("Spiel erstellen");
		setzeEnterAlsAktivierungstaste(erstellenbutton);
		erstellenbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Setze die Spielattribute
				spielername = getname.getText();

				if (!pruefeSpielername(spielername)) {
					zeigeFehlermeldung("Sie muessen einen gueltigen Namen eingeben.\nUngueltige Zeichen: \" \\ /");
				} else {
					client.setzeSpielername(spielername);
					client.setzeSpielerfarbe(spielerfarbe);
					spielmodus = EnumContainer.Spielmodus.mehrspieler;

					// Erstelle neues Spielfeld
					if (!dateitextfeld.isEnabled()) {
						client.erstelleSpiel(spielmodus, spielvariante,
								schwierigkeitsgrad, strafe);
					} else {
						// Dateiname der gewaehlten Datei
						String dateiname = dateitextfeld.getText();
						client.erstelleSpiel(spielmodus, strafe, dateiname);
					}

					menue2.setVisible(false);
				}
			}
		});

		for (int i = 0; i < 70; i++) {
			menue2.add(new JPanel());
		}

		JButton abbrechen = new JButton("Abbrechen");
		setzeEnterAlsAktivierungstaste(abbrechen);
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.beendeSerververbindung();
				zeigeHauptmenue();
				menue2.dispose();
			}
		});
		menue2.add(abbrechen);
		menue2.add(erstellenbutton);
		menue2.setTitle("Spiel erstellen");
		menue2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menue2.pack();
		menue2.setVisible(true);
	}

	/**
	 * @see IGrafik#zeigeMehrspielerMenueAuswahl(IClient)
	 */
	public void zeigeMehrspielerMenueAuswahl(final IClient client) {
		final JDialog dialog = new JDialog();
		LayoutManager layout = new FlowLayout();
		dialog.setLayout(layout);
		JButton erstellen = new JButton("Spiel erstellen");
		setzeEnterAlsAktivierungstaste(erstellen);
		erstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeigeMehrspielermenue(client);
				dialog.setVisible(false);
			}
		});
		JButton beitreten = new JButton("Spiel beitreten");
		setzeEnterAlsAktivierungstaste(beitreten);
		beitreten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.spielAuswaehlen();
				dialog.setVisible(false);
			}
		});
		JButton abbrechen = new JButton("Abbrechen");
		setzeEnterAlsAktivierungstaste(abbrechen);
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.beendeSerververbindung();
				zeigeHauptmenue();
				dialog.dispose();
			}
		});

		dialog.add(erstellen);
		dialog.add(beitreten);
		dialog.add(abbrechen);
		dialog.setTitle("Menue");
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * @see IGrafik#zeigeServerconnectDialog(IClient, boolean)
	 */
	public boolean zeigeServerconnectDialog(final IClient client,
			final boolean laden) {
		final JDialog dialog = new JDialog();
		LayoutManager layout = new FlowLayout();
		dialog.setLayout(layout);
		dialog.setTitle("Serververbindung herstellen");
		final JTextField iptext = new JTextField("127.0.0.1");
		iptext.setPreferredSize(new Dimension(200, 30));
		JLabel iplabel = new JLabel("IP: ");
		JButton ok = new JButton("Ok");
		setzeEnterAlsAktivierungstaste(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Wenn IP Adresse gueltig, gehe zur Mehrspielerauswahl
				String ipAdresse = iptext.getText();

				if (laden) {
					if (client.verbindeMitServer(ipAdresse) == true) {
						zeigeSpielLadenDialog(client,
								EnumContainer.Spielmodus.mehrspieler);
						dialog.setVisible(false);
					}
				} else {
					if (client.verbindeMitServer(ipAdresse) == true) {
						zeigeMehrspielerMenueAuswahl(client);
						dialog.setVisible(false);
					}
				}
			}
		});
		JButton abbrechen = new JButton("Abbrechen");
		setzeEnterAlsAktivierungstaste(abbrechen);
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zeigeHauptmenue();
				dialog.dispose();
			}
		});

		dialog.add(iplabel);
		dialog.add(iptext);
		dialog.add(abbrechen);
		dialog.add(ok);
		dialog.pack();
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setVisible(true);

		return false;
	}

	/**
	 * @see IGrafik#zeigeSpeichernDialog(boolean)
	 */
	public void zeigeSpeichernDialog(final boolean spielfeld) {
		final JDialog dialog = new JDialog();
		LayoutManager layout = new FlowLayout();
		dialog.setLayout(layout);
		dialog.setTitle("Spiel speichern");
		final JTextField nametext = new JTextField("");
		nametext.setPreferredSize(new Dimension(100, 30));
		JLabel dateiname = new JLabel("Dateiname: ");
		JButton ok = new JButton("Ok");
		setzeEnterAlsAktivierungstaste(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = nametext.getText();
				if (spielfeld) {
					client.speichereSpielfeld(name);
				} else
					client.speichereSpiel(name);
				dialog.setVisible(false);
			}
		});
		JButton schliessen = new JButton("Schliessen");
		setzeEnterAlsAktivierungstaste(schliessen);
		schliessen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.setVisible(false);
			}
		});
		dialog.add(dateiname);
		dialog.add(nametext);
		dialog.add(ok);
		dialog.add(schliessen);
		dialog.pack();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * @see IGrafik#zeigeSpielBeitretenFenster(List)
	 */
	public void zeigeSpielBeitretenFenster(
			final List<ISpielinformation> information) {
		final Grafik beitretenmenue = new Grafik();
		beitretenmenue.setPreferredSize(new Dimension(550, 420));
		LayoutManager layout = new FlowLayout();
		LineBorder border = new LineBorder(Color.LIGHT_GRAY, 3, true);
		beitretenmenue.setLayout(layout);
		getname = new JTextField();
		getname.setPreferredSize(new Dimension(200, 20));
		name = new JLabel();
		name.setText("Name: ");
		farbe = new JLabel();
		farbe.setText("Farbe: ");
		erstelleFarbenAuswahl();
		// Definition der Labels
		JLabel spielidlabel = new JLabel("Spiel ID: ");
		spielid = new JLabel();
		JLabel spielvariantelabeltext = new JLabel("Spielvariante: ");
		spielvariantelabel = new JLabel();
		JLabel schwierigkeitsgradlabel = new JLabel("Schwierigkeitsgrad: ");
		schwierigkeit = new JLabel();
		JLabel strafzeitlabel = new JLabel("Strafzeit: ");
		strafzeit = new JLabel();
		// Panel spielliste
		JPanel spielliste = new JPanel();
		spielliste.setBorder(border);
		// Panel Spieloptionen
		JPanel spieloptionen = new JPanel();
		spieloptionen.setBorder(border);
		spieloptionen.setPreferredSize(new Dimension(270, 300));
		spieloptionen.setLayout(new GridLayout(5, 2));
		final ListModel listModel = new DefaultListModel();
		final JList liste = new JList(listModel);
		// fuellt Jlist und added Listener
		fuegeListenFunktionenHinzu(spielliste, information, liste, listModel);
		spieloptionen.add(spielidlabel);
		spieloptionen.add(spielid);
		spieloptionen.add(spielvariantelabeltext);
		spieloptionen.add(spielvariantelabel);
		spieloptionen.add(schwierigkeitsgradlabel);
		spieloptionen.add(schwierigkeit);
		spieloptionen.add(strafzeitlabel);
		spieloptionen.add(strafzeit);
		beitretenmenue.add(name);
		beitretenmenue.add(getname);
		beitretenmenue.add(combofarbe);
		beitretenmenue.add(spielliste);
		beitretenmenue.add(spieloptionen);

		// BUTTON: Abbrechen --> zum hauptmenue
		JButton abbrechen = new JButton("Abbrechen");
		setzeEnterAlsAktivierungstaste(abbrechen);
		abbrechen.setPreferredSize(new Dimension(230, 50));
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.beendeSerververbindung();
				zeigeHauptmenue();
				beitretenmenue.setVisible(false);
			}
		});

		// BUTTON: Beitreten --> ausgewaehltem Spiel beitreten
		JButton beitreten = new JButton("Beitreten");
		setzeEnterAlsAktivierungstaste(beitreten);
		beitreten.setPreferredSize(new Dimension(270, 50));
		beitreten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				spielername = getname.getText();

				if (liste.getModel().getSize() == 0) {
					zeigeFehlermeldung("Momentan gibt es auf dem Server keine Spiele, denen Sie beitreten koennen.");
				} else if (liste.getSelectedValue() == null) {
					zeigeFehlermeldung("Sie haben kein Spiel ausgewaehlt.");
				} else if (!pruefeSpielername(spielername)) {
					zeigeFehlermeldung("Sie muessen einen gueltigen Namen eingeben.\nUngueltige Zeichen: \" \\ /");
				} else {
					schwierigkeitsgrad2 = schwierigkeit.getText();
					spielID = new Integer(spielid.getText()).intValue();
					strafe = new Integer(strafzeit.getText()).intValue();
					setzeSpielvariante(spielvariantelabel.getText());
					client.setzeSpielername(spielername);
					client.setzeSpielerfarbe(spielerfarbe);
					if (client.spielBeitreten(spielID) == true) {
						beitretenmenue.setVisible(false);
					}
				}
			}
		});

		beitretenmenue.add(abbrechen);
		beitretenmenue.add(beitreten);
		beitretenmenue.setTitle("Spiel beitreten");
		beitretenmenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		beitretenmenue.pack();
		beitretenmenue.setVisible(true);
	}

	/**
	 * @see IGrafik#zeigeSpielfenster()
	 */
	public void zeigeSpielfenster() {
		if (this.startenmenue != null) {
			this.startenmenue.setVisible(false);
		}

		spielfeldGrafik = new SpielfeldGrafik(this, client, startbelegung,
				loesung, gesetzteZiffern);
	}

	/**
	 * @see IGrafik#zeigeSpielLadenDialog
	 */
	public void zeigeSpielLadenDialog(final IClient client,
			final Spielmodus spielmodus) {
		final JDialog dialog = new JDialog();
		LayoutManager layout = new FlowLayout();
		dialog.setLayout(layout);
		final JTextField datei = new JTextField("");
		datei.setPreferredSize(new Dimension(200, 30));
		dateiname = datei.getText();
		JButton durchsuchen = new JButton("Durchsuchen...");
		setzeEnterAlsAktivierungstaste(durchsuchen);
		durchsuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				MyFileFilter filter = new MyFileFilter();
				filter.setDescription(".sav");
				filter.getDescription();
				fc.setFileFilter(filter);
				fc.addChoosableFileFilter(new MyFileFilter());
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					dateiname = fc.getSelectedFile().getName();
					System.out.println("You chose to open this file: "
							+ dateiname);
					datei.setText(dateiname);
				}
			}
		});
		JButton laden = new JButton("Spiel Laden");
		setzeEnterAlsAktivierungstaste(laden);
		laden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// spiel laden beim client?
				client.setzeGespeichertesSpielFort(dateiname, spielmodus);
				dialog.setVisible(false);
			}
		});
		JButton abbrechen = new JButton("Abbrechen");
		setzeEnterAlsAktivierungstaste(abbrechen);
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (spielmodus == EnumContainer.Spielmodus.mehrspieler)
					client.beendeSerververbindung();
				zeigeHauptmenue();
				dialog.dispose();
			}
		});
		dialog.add(datei);
		dialog.add(durchsuchen);
		dialog.add(laden);
		dialog.add(abbrechen);
		dialog.setTitle("Server verbinden");
		dialog.pack();
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * @see IGrafik#zeigeSpielmodusMenueAuswahl(IClient)
	 */
	public void zeigeSpielmodusMenueAuswahl(final IClient client) {
		final JDialog dialog = new JDialog();
		LayoutManager layout = new FlowLayout();
		dialog.setLayout(layout);
		JButton einzel = new JButton("Einzelspielerspiel laden");
		setzeEnterAlsAktivierungstaste(einzel);
		einzel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeigeSpielLadenDialog(client,
						EnumContainer.Spielmodus.einzelspieler);
				dialog.setVisible(false);
			}
		});
		JButton mehr = new JButton("Mehrspielerspiel laden");
		setzeEnterAlsAktivierungstaste(mehr);
		mehr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeigeServerconnectDialog(client, true);
				dialog.setVisible(false);
			}
		});
		JButton abbrechen = new JButton("Abbrechen");
		setzeEnterAlsAktivierungstaste(abbrechen);
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zeigeHauptmenue();
				dialog.setVisible(false);
			}
		});
		dialog.add(einzel);
		dialog.add(mehr);
		dialog.add(abbrechen);
		dialog.setTitle("Spielmodusauswahl");
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * @see IGrafik#zeigeWartenAufSpielstartFenster(boolean)
	 */
	public void zeigeWartenAufSpielstartFenster(boolean istMasterspieler) {
		// Aktualisiere lokale Spielinformationen
		ISpielinformation information = client.gibSpielinformation();
		spielID = information.gibSpielID();
		strafe = information.gibStrafzeit();
		spielvariante2 = information.gibSpielvariante();
		setzeSpielvariante(information.gibSpielvariante());
		master = istMasterspieler;
		startenmenue = new Grafik();
		startenmenue.setPreferredSize(new Dimension(550, 420));
		LineBorder border = new LineBorder(Color.LIGHT_GRAY, 3, true);
		startenmenue.setLayout(new FlowLayout());
		// Panel spielerliste
		JPanel spielerlistepanel = new JPanel();
		spielerlistepanel.setBorder(border);
		spielerlistepanel.setPreferredSize(new Dimension(230, 300));
		// Panel Spieloptionen
		JPanel spieloptionen = new JPanel();
		spieloptionen.setBorder(border);
		spieloptionen.setPreferredSize(new Dimension(270, 300));
		spieloptionen.setLayout(new GridLayout(5, 2));
		JLabel spiellistelabel = new JLabel("Spielerliste");
		spiellistelabel.setPreferredSize(new Dimension(220, 20));
		spiellistelabel.setHorizontalAlignment(SwingConstants.LEFT);
		final ListModel listModel = new DefaultListModel();
		// alle Spieler im Spiel anzeigen
		List<String> spielerliste = client.gibSpielerliste();

		for (Iterator<String> it = spielerliste.iterator(); it.hasNext();) {
			String spieler = it.next();
			((DefaultListModel) listModel).addElement(spieler);
		}

		// Listener in Liste fuer Spieler
		JList liste = new JList(listModel);
		liste.setPreferredSize(new Dimension(220, 220));
		liste.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane listScroller = new JScrollPane(liste);
		listScroller
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		liste.setSelectedIndex(0);
		spielerlistepanel.add(spiellistelabel);
		spielerlistepanel.add(liste);
		JButton aktualisieren = new JButton("Spielerliste aktualisieren");
		setzeEnterAlsAktivierungstaste(aktualisieren);
		aktualisieren.setPreferredSize(new Dimension(220, 30));
		aktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((DefaultListModel) listModel).clear();
				List<String> spielerliste = client.gibSpielerliste();

				for (Iterator<String> it = spielerliste.iterator(); it
						.hasNext();) {
					String spieler = it.next();
					((DefaultListModel) listModel).addElement(spieler);
				}
			}
		});
		spielerlistepanel.add(aktualisieren);
		JLabel spielidlabel = new JLabel("Spiel ID: ");
		JLabel spielvariantelabeltext = new JLabel("Spielvariante: ");
		JLabel schwierigkeitsgradlabel = new JLabel("Schwierigkeitsgrad: ");
		JLabel strafzeitlabel = new JLabel("Strafzeit: ");
		spielid = new JLabel();
		spielid.setText(spielID.toString());
		spielvariantelabel = new JLabel();
		spielvariantelabel.setText(gibSpielvariante());
		schwierigkeit = new JLabel();
		schwierigkeit.setText(gibSchwierigkeitsgrad());
		strafzeit = new JLabel();
		strafzeit.setText("" + strafe);
		spieloptionen.add(spielidlabel);
		spieloptionen.add(spielid);
		spieloptionen.add(spielvariantelabeltext);
		spieloptionen.add(spielvariantelabel);
		spieloptionen.add(schwierigkeitsgradlabel);
		spieloptionen.add(schwierigkeit);
		spieloptionen.add(strafzeitlabel);
		spieloptionen.add(strafzeit);
		startenmenue.add(spielerlistepanel);
		startenmenue.add(spieloptionen);
		// abbrechen --> zum hauptmenue
		JButton abbrechen = new JButton("Abbrechen");
		setzeEnterAlsAktivierungstaste(abbrechen);
		abbrechen.setPreferredSize(new Dimension(230, 50));
		abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.spielerAbmelden();
				startenmenue.setVisible(false);
				zeigeHauptmenue();
			}
		});
		// starten enabled es sei denn admin --> spiel starten
		JButton starten = new JButton("Starten");
		setzeEnterAlsAktivierungstaste(starten);
		starten.setEnabled(true);

		if (master == false) {
			starten.setEnabled(false);
		}

		starten.setPreferredSize(new Dimension(270, 50));
		starten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Mehrspielerspiel starten
				if (spielvariante == Spielvariante.fudschijama2x3) {
					setzeGroesse(6);
				}
				if (spielvariante == Spielvariante.standard
						|| spielvariante == Spielvariante.comparison) {
					setzeGroesse(9);
				}
				if (spielvariante == Spielvariante.fudschijama4x3) {
					setzeGroesse(12);
				}
				if (spielvariante == Spielvariante.fudschijama4x4) {
					setzeGroesse(16);
				}
				if (spielvariante == Spielvariante.comparison) {
					setzeGroesse(100);
				}
				client.spielStarten();
				dispose();
			}
		});
		startenmenue.add(abbrechen);
		startenmenue.add(starten);
		startenmenue.setTitle("Spiel starten");
		startenmenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startenmenue.pack();
		this.startenmenue.setVisible(true);
	}

	/**
	 * Fuegt der JList ihre Funktionen hinzu.
	 * 
	 * @param spielliste
	 *            das JPanel
	 * @param information
	 *            die Spielinformationen
	 * @param liste
	 *            die JList
	 * @param listModel
	 *            das listModel
	 */
	private void fuegeListenFunktionenHinzu(JPanel spielliste,
			final List<ISpielinformation> information, final JList liste,
			final ListModel listModel) {

		spielliste.setPreferredSize(new Dimension(230, 300));
		JLabel spiellistelabel = new JLabel("Spielliste");
		spiellistelabel.setPreferredSize(new Dimension(220, 20));
		spiellistelabel.setHorizontalAlignment(SwingConstants.LEFT);

		for (Iterator<ISpielinformation> it = information.iterator(); it
				.hasNext();) {
			ISpielinformation spiel = it.next();
			((DefaultListModel) listModel).addElement(spiel.gibSpielID());
		}

		ListSelectionModel listSelectionModel = liste.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						ListSelectionModel lsm = (ListSelectionModel) e
								.getSource();

						if (!lsm.isSelectionEmpty()) {
							// Find out which indexes are selected.
							int minIndex = lsm.getMinSelectionIndex();
							int maxIndex = lsm.getMaxSelectionIndex();

							for (int i = minIndex; i <= maxIndex; i++) {
								if (lsm.isSelectedIndex(i)) {
									liste.setSelectedIndex(i);
									String gewaehlteSpieID = (String) liste
											.getSelectedValue().toString();

									// Durchlaufe Spielliste
									for (Iterator<ISpielinformation> it = information
											.iterator(); it.hasNext();) {
										ISpielinformation spiel = it.next();
										Integer spielID = spiel.gibSpielID();
										String idString = spielID.toString();

										// Setze Spielinformationen in Panel
										if (idString.equals(gewaehlteSpieID)) {
											spielid.setText(""
													+ spiel.gibSpielID());
											spielvariantelabel.setText(""
													+ spiel.gibSpielvariante()
															.toString());
											schwierigkeit
													.setText(""
															+ spiel
																	.gibSchwierigkeitsgrad()
																	.toString());
											strafzeit.setText(""
													+ spiel.gibStrafzeit());
											break;
										}
									}
								}
							}
						}
					}
				});
		liste.setPreferredSize(new Dimension(220, 220));
		liste.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		liste.setSelectedIndex(0);
		spielliste.add(spiellistelabel);
		spielliste.add(liste);
		// BUTTON zum aktualisieren der Spieleliste
		JButton aktualisieren = new JButton("Spielliste aktualisieren");
		setzeEnterAlsAktivierungstaste(aktualisieren);
		aktualisieren.setPreferredSize(new Dimension(220, 30));
		aktualisieren.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((DefaultListModel) listModel).clear();
				// Hole neue Mehrspielerliste vom Client
				List<ISpielinformation> aktuelleInformation = client
						.gibMehrspielerspiele();

				// Schreibe neue Liste in ListModel
				for (Iterator<ISpielinformation> it = aktuelleInformation
						.iterator(); it.hasNext();) {
					ISpielinformation spiel = it.next();
					((DefaultListModel) listModel).addElement(spiel
							.gibSpielID());
					spielid.setText("" + spiel.gibSpielID());
					spielvariantelabel.setText(""
							+ spiel.gibSpielvariante().toString());
					schwierigkeit.setText(""
							+ spiel.gibSchwierigkeitsgrad().toString());
					strafzeit.setText("" + spiel.gibStrafzeit());
				}
			}
		});
		spielliste.add(aktualisieren);

		// Fuege Spielinformationen dem Panel hinzu
		if (information.size() > 0) {
			// ID umformen
			String gewaehlteSpieID = (String) liste.getSelectedValue()
					.toString();

			// Durchlaufe Spielliste
			for (Iterator<ISpielinformation> it = information.iterator(); it
					.hasNext();) {
				ISpielinformation spiel = it.next();
				Integer spielID = spiel.gibSpielID();
				String idString = spielID.toString();

				// Setze Spielinformationen in Panel
				if (idString.equals(gewaehlteSpieID)) {
					spielid.setText("" + spiel.gibSpielID());
					spielvariantelabel.setText(""
							+ spiel.gibSpielvariante().toString());
					schwierigkeit.setText(""
							+ spiel.gibSchwierigkeitsgrad().toString());
					strafzeit.setText("" + spiel.gibStrafzeit());
					break;
				}
			}
		}
	}

	/**
	 * Prueft, ob ein Name leer ist oder ungueltige Zeichen enthaelt.
	 * 
	 * @param name
	 *            der zu pruefende Name
	 * @return true: der Name enthaelt KEINE ungueltigen Zeichen; false: der
	 *         Name enthaelt ungueltige Zeichen
	 */
	private boolean pruefeSpielername(String name) {
		return !(name.equals("") || name.contains("\"") || name.contains("\\") || name
				.contains("/"));
	}

	/**
	 * Setzt die Spielvariante des Spiels.
	 * 
	 * @param variante
	 *            die Spielvariante
	 */
	private void setzeSpielvariante(String variante) {
		if (variante.equals("standard")) {
			setzeGroesse(9);
			spielvariante = EnumContainer.Spielvariante.standard;
		} else if (variante.equals("fudschijama2x3")) {
			setzeGroesse(6);
			spielvariante = EnumContainer.Spielvariante.fudschijama2x3;
		} else if (variante.equals("fudschijama4x3")) {
			setzeGroesse(12);
			spielvariante = EnumContainer.Spielvariante.fudschijama4x3;
		} else if (variante.equals("fudschijama4x4")) {
			setzeGroesse(16);
			spielvariante = EnumContainer.Spielvariante.fudschijama4x4;
		} else if (variante.equals("comparison")) {
			setzeGroesse(100);
			spielvariante = EnumContainer.Spielvariante.comparison;
		}
	}

}
