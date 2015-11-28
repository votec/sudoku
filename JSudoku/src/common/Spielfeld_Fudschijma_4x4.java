package common;

/**
 * Die Klasse fuer ein Fudschijma-4x4-Spielfeld enthaelt eine Methode zum Erzeugen
 * von Spielfeldern.
 *
 * @author guest25
 *
 */
public class Spielfeld_Fudschijma_4x4 extends ASpielfeld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1258655286388223950L;

	/**
	 * Konstruktor
	 * Initialisiert die Hoehen und Breiten fuer das Sudoku.
	 */
	public Spielfeld_Fudschijma_4x4() {
		setzeSpielfeldBreite(4);
		setzeSpielfeldHoehe(4);
		setzeBlockBreite(4);
		setzeBlockHoehe(4);
		initSpielfeld();
	}

	/**
	 * @see ISpielfeld#generiereSpielfeld(common.EnumContainer.Schwierigkeitsgrad)
	 */
	@Override
	public void generiereSpielfeld(Schwierigkeitsgrad stufe) {
		switch (stufe) {
		case anfaenger: /* 134-123 */
			erzeugeSudoku(134 - (int) (Math.random() * 11));
			break;
		case mittel: /* 120-111 */
			erzeugeSudoku(120 - (int) (Math.random() * 9));
			break;
		case knifflig: /* 107-95 */
			erzeugeSudoku(107 - (int) (Math.random() * 12));
			break;
		case schwer: /* 92-82 */
			erzeugeSudoku(92 - (int) (Math.random() * 10));
			break;
		case profi: /* 79-70 */
			erzeugeSudoku(79 - (int) (Math.random() * 9));
		}
	}
}
