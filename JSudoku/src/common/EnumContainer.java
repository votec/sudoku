package common;

/**
 * Das Interface EnumContainer dient dazu, alle vorkommenden oeffentlichen
 * Enumerationen zu speichern.
 * 
 * @author Arne Busch
 * 
 */
public interface EnumContainer {

	/** Alle von den Spielern waehlbaren Farben. */
	public enum Farbe {
		rot, orange, gelb, gruen, blau, pink, schwarz, weiss
	}

	/**
	 * Serverantworten dienen als Fehlercodes zur Identifikation moeglicherweise
	 * aufgetretener Fehler oder Ausnahmen.
	 */
	public enum Serverantwort {
		ok, spielVoll, spielernameVergeben, spielerfarbeVergeben, spielerfarbeNichtVorhanden, feldLeer, feldNichtLeer, zifferFalsch, zuWenigSpieler, spielfeldGeloest, spielfeldFalschGeloest, feldInStartbelegung
	}

	/** Alle von den Spielern waehlbaren Schwierigkeitsgrade. */
	public enum Schwierigkeitsgrad {
		anfaenger, mittel, knifflig, schwer, profi
	}

	/** Alle von den Spielern waehlbaren Spielmodi. */
	public enum Spielmodus {
		einzelspieler, mehrspieler
	}

	/** Alle von den Spielern waehlbaren Spielvarianten. */
	public enum Spielvariante {
		standard, fudschijama4x4, fudschijama4x3, fudschijama2x3, comparison
	}
}
