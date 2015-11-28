package grafik;

import java.awt.event.KeyEvent;

/**
 * Die Klasse SudokufeldListener dient als Superklasse fuer ActionListener und
 * stellt einige Methoden, z.B. zum Loeschen eines Feldes, bereit.
 * 
 * @author Thomas Fraenkler
 * 
 */
public abstract class SudokufeldListener {
	/**
	 * Liefert die gesetzte Zahl.
	 * 
	 * @param num
	 *            die gesetzte Zahl
	 */
	abstract void fieldSet(int num);

	/**
	 * Reagiert auf Tastatureingaben.
	 * 
	 * @param e
	 *            die entsprechende Taste
	 */
	abstract void onKeyEvent(KeyEvent e);

	/**
	 * Liefert die Koordinaten, in dem die Zahl geloescht wurde.
	 */
	abstract void fielddelete();

}
