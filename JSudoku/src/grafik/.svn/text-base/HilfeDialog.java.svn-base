package grafik;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import client.Grafik;

/**
 * Die Klasse HilfeDialog beschreibt einen Hilfedialog mit den Spielregeln und
 * der Steuerung, der waehrend eines laufenden Spiels aufgerufen werden kann.
 * 
 * @author Thomas Fraenkler
 * 
 */
public class HilfeDialog extends JFrame implements ActionListener {

	/** serial Version UID */
	private static final long serialVersionUID = -2257622439195536787L;

	/**
	 * Erzeugt einen Hilfedialog und zeigt diesen an.
	 */
	public HilfeDialog() {
		JLabel ueberschrift = new JLabel("Steuerung");
		JTextPane hilfetext = new JTextPane();
		StyledDocument doc = hilfetext.getStyledDocument();
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton zurueck = new JButton("Schliessen");

		zurueck.addActionListener(this);
		Grafik.setzeEnterAlsAktivierungstaste(zurueck);

		southPanel.add(zurueck);

		fuegeStylesZuDokumentHinzu(doc);
		erzeugeText(doc);

		hilfetext.setEditable(false);
		hilfetext.setAutoscrolls(true);

		ueberschrift.setFont(new Font("Arial", Font.BOLD, 20));

		setLayout(new BorderLayout());
		setTitle("Hilfe");
		setPreferredSize(new Dimension(450, 600));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		add(ueberschrift, BorderLayout.NORTH);
		add(hilfetext, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		pack();
		toFront();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		dispose();
	}

	/**
	 * Erzeugt den Hilfetext im uebergebenen Dokument.
	 * 
	 * @param doc
	 *            das Dokument, in das der Hilfetext geschrieben werden soll
	 */
	private void erzeugeText(StyledDocument doc) {
		try {
			// Enter / linke Maustaste
			doc.insertString(doc.getLength(), "Enter / linke Maustaste:\n", doc
					.getStyle("large"));
			doc.insertString(doc.getLength(), "Leeres Feld:\n", doc
					.getStyle("bold"));
			doc.insertString(doc.getLength(),
					"Die Anzeige der moeglichen Ziffern einschalten.\n", doc
							.getStyle("regular"));
			doc.insertString(doc.getLength(), "Feld mit Moeglichkeiten:\n", doc
					.getStyle("bold"));
			doc.insertString(doc.getLength(),
					"Eine kleine Ziffer als Feldinhalt setzen.\n", doc
							.getStyle("regular"));
			doc.insertString(doc.getLength(), "Gesetztes Feld:\n", doc
					.getStyle("bold"));
			doc.insertString(doc.getLength(), "Den Feldinhalt loeschen.\n\n",
					doc.getStyle("regular"));

			// Leertaste / rechte Maustaste
			doc.insertString(doc.getLength(),
					"Leertaste / rechte Maustaste:\n", doc.getStyle("large"));
			doc.insertString(doc.getLength(), "Leeres / Gesetztes Feld:\n", doc
					.getStyle("bold"));
			doc.insertString(doc.getLength(),
					"Popupmenue aufrufen mit den Optionen ", doc
							.getStyle("regular"));
			doc.insertString(doc.getLength(), "'Feldloesung', 'Feld pruefen' ",
					doc.getStyle("italic"));
			doc.insertString(doc.getLength(), "und ", doc.getStyle("regular"));
			doc.insertString(doc.getLength(), "'Feldmoeglichkeiten'.\n", doc
					.getStyle("italic"));
			doc.insertString(doc.getLength(), "Feld mit Moeglichkeiten:\n", doc
					.getStyle("bold"));
			doc.insertString(doc.getLength(),
					"Eine kleine Ziffer ein-/ausblenden.\n\n", doc
							.getStyle("regular"));

			// Pfeiltasten
			doc.insertString(doc.getLength(), "Pfeiltasten:\n", doc
					.getStyle("large"));
			doc.insertString(doc.getLength(),
					"Durch die einzelnen Felder navigieren.\n\n", doc
							.getStyle("regular"));

			// Tab
			doc.insertString(doc.getLength(), "Tab (Shift+Tab):\n", doc
					.getStyle("large"));
			doc.insertString(doc.getLength(), "Leeres / Gesetztes Feld:\n", doc
					.getStyle("bold"));
			doc
					.insertString(
							doc.getLength(),
							"Innerhalb eines Blocks ein Feld weiter (zurueck) bzw. zum naechsten (vorigen) Block gehen.\n",
							doc.getStyle("regular"));
			doc.insertString(doc.getLength(), "Feld mit Moeglichkeiten:\n", doc
					.getStyle("bold"));
			doc
					.insertString(
							doc.getLength(),
							"Eine kleine Ziffer weiter (zurueck) bzw. zum naechsten (vorigen) Feld gehen.\n\n",
							doc.getStyle("regular"));

			// Tasten 1-9 und A-G
			doc.insertString(doc.getLength(), "Tasten 1-9 und A-G:\n", doc
					.getStyle("large"));
			doc
					.insertString(
							doc.getLength(),
							"Die der Taste entsprechende Ziffer als Feldinhalt setzen. Die Tasten A-G entsprechen (bis auf G) ihren Werten im Hexdezimalsystem (A=10, B=11, C=12, D=13, E=14, F=15, G=16).\n\n",
							doc.getStyle("regular"));
		} catch (BadLocationException ble) {
			System.err
					.println("Der Text konnte nicht in das TextPane eingesetzt werden.");
		}
	}

	/**
	 * Fuegt Styledefinitionen zu einem Dokument hinzu.
	 * 
	 * @param doc
	 *            das Dokument, dem die Styleinformationen hinzugefuegt werden
	 *            sollen.
	 */
	private void fuegeStylesZuDokumentHinzu(StyledDocument doc) {
		// Initialize some styles.
		Style def = StyleContext.getDefaultStyleContext().getStyle(
				StyleContext.DEFAULT_STYLE);

		Style regular = doc.addStyle("regular", def);
		StyleConstants.setFontFamily(def, "SansSerif");

		Style bold = doc.addStyle("bold", regular);
		StyleConstants.setBold(bold, true);

		Style s = doc.addStyle("italic", regular);
		StyleConstants.setItalic(s, true);

		s = doc.addStyle("small", regular);
		StyleConstants.setFontSize(s, 10);

		s = doc.addStyle("large", bold);
		StyleConstants.setFontSize(s, 16);
	}
}
