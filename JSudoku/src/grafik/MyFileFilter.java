package grafik;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Die Klasse MyFileFilter filtert beim Auswaehlen einer
 * Spielstand-/Spielfelddatei im Auswahlfenster Dateien mit den Endungen ".sav",
 * ".ss" und ".txt" heraus.
 * 
 * @author Thomas Fraenkler
 * 
 */
public class MyFileFilter extends FileFilter {
	private String text;

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = Utils.getExtension(f);

		if (extension != null) {
			if (extension.equals(Utils.sav) || extension.equals(Utils.txt)
					|| extension.equals(Utils.ss))

				return true;
			else {
				return false;
			}
		}

		return false;
	}

	public String setDescription(String text) {
		return this.text = text;
	}

	@Override
	public String getDescription() {
		return text;
	}

}
