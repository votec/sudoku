package common;

/**
 * Die Klasse fuer ein Fudschijma-2x3-Spielfeld enthaelt eine Methode zum
 * Erzeugen von Spielfeldern.
 * 
 * @author guest25
 * 
 */
public class Spielfeld_Fudschijma_2x3 extends ASpielfeld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4563292235752430375L;

	/**
	 * Konstruktor
	 * Initialisiert die Hoehen und Breiten fuer das Sudoku.
	 */
	public Spielfeld_Fudschijma_2x3() {
		setzeSpielfeldBreite(3);
		setzeSpielfeldHoehe(2);
		setzeBlockBreite(2);
		setzeBlockHoehe(3);
		initSpielfeld();
	}

	/**
	 * @see ISpielfeld#generiereSpielfeld(common.EnumContainer.Schwierigkeitsgrad)
	 */
	@Override
	public void generiereSpielfeld(Schwierigkeitsgrad stufe) {
		switch (stufe) {
		case anfaenger: /* 19-18 */
			erzeugeSudoku(19 - (int) (Math.random() * 1));
			break;
		case mittel: /* 17-16 */
			erzeugeSudoku(17 - (int) (Math.random() * 1));
			break;
		case knifflig: /* 15-14 */
			erzeugeSudoku(15 - (int) (Math.random() * 1));
			break;
		case schwer: /* 13-12 */
			erzeugeSudoku(13 - (int) (Math.random() * 1));
			break;
		case profi: /* 11-10 */
			erzeugeSudoku(11 - (int) (Math.random() * 1));
		}
	}
}
