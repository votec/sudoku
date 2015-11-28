package common;

/**
 * Die Klasse fuer ein Standard-Spielfeld enthaelt eine Methode zum Erzeugen
 * von Spielfeldern.
 *
 * @author guest25
 *
 */
public class Spielfeld_Standard extends ASpielfeld {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5240946581031308357L;

	/**
	 * Konstruktor zum Erzeugen eines Standard-Sudoku
	 */
	public Spielfeld_Standard() {
		setzeSpielfeldBreite(3);
		setzeSpielfeldHoehe(3);
		setzeBlockBreite(3);
		setzeBlockHoehe(3);
		initSpielfeld();
	}

	/**
	 * @see ISpielfeld#generiereSpielfeld(common.EnumContainer.Schwierigkeitsgrad)
	 */
	@Override
	public void generiereSpielfeld(Schwierigkeitsgrad stufe) {
		switch (stufe) {
		case anfaenger: /* 42-39 */
			erzeugeSudoku(42 - (int) (Math.random() * 3));
			break;
		case mittel: /* 38-35 */
			erzeugeSudoku(38 - (int) (Math.random() * 3));
			break;
		case knifflig: /* 34-30 */
			erzeugeSudoku(34 - (int) (Math.random() * 4));
			break;
		case schwer: /* 29-26 */
			erzeugeSudoku(29 - (int) (Math.random() * 3));
			break;
		case profi: /* 25-22 */
			erzeugeSudoku(25 /*- (int) (Math.random() * 3)*/);
		}
	}
}
