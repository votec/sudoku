package common;

/**
 * Die Klasse fuer ein Fudschijma-4x3-Spielfeld enthaelt eine Methode zum Erzeugen
 * von Spielfeldern.
 *
 * @author guest25
 *
 */
public class Spielfeld_Fudschijma_4x3 extends ASpielfeld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7973359950619509213L;

	/**
	 * Konstruktor
	 * Initialisiert die Hoehen und Breiten fuer das Sudoku.
	 */
	public Spielfeld_Fudschijma_4x3() {
		setzeSpielfeldBreite(3);
		setzeSpielfeldHoehe(4);
		setzeBlockBreite(4);
		setzeBlockHoehe(3);
		initSpielfeld();
	}

	/**
	 * @see ISpielfeld#generiereSpielfeld(common.EnumContainer.Schwierigkeitsgrad)
	 */
	@Override
	public void generiereSpielfeld(Schwierigkeitsgrad stufe) {
		switch (stufe) {
		case anfaenger: /* 75-69 */
			erzeugeSudoku(75 - (int) (Math.random() * 6));
			break;
		case mittel: /* 68-62 */
			erzeugeSudoku(68 - (int) (Math.random() * 6));
			break;
		case knifflig: /* 61 */
			erzeugeSudoku(61);
			break;
		case schwer: /* 56 */
			erzeugeSudoku(56);
			break;
		case profi: /* 52 */
			erzeugeSudoku(52);
		}
	}
}
