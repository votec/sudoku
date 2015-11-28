package grafik;

import java.io.File;

/**
 * Die Klasse Utils ist eine Hilfsklasse fuer das Auswaehlen und Herausfiltern
 * von Dateien mit bestimmten Endungen.
 * 
 * @author Thomas Fraenkler
 * 
 */
public class Utils {

	/**  */
	public final static String sav = "sav";
	/**  */
	public final static String ss = "ss";
	/**  */
	public final static String txt = "txt";

	/**
	 * Gibt die Dateiendung einer Datei zurueck.
	 * 
	 * @param f
	 *            die Datei
	 * @return die Dateiendung
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}

		return ext;
	}
}