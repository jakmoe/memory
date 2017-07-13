package JSON;

/**
 * @author D067928
 * Eine Zusammenfassung übergreifender Spielinformationen die beim Spielstart benötigt werden
 */
public class GameInfo {
	private double version;
	private double volume_music;
	private double volume_effects;
	private int difficulty;


	/**
	 * Der Standardkonstruktor. Aufgerufen wenn das Spiel keine Info finden sollte oder falls es zum ersten Mal gestartet wird.
	 */
	public GameInfo() {
		version = 1.0;
		volume_music = 0.5;
		volume_effects = 0.5;
		difficulty = 3;
	}

	/**
	 * @return version
	 */
	public double getVersion() {
		return version;
	}

	/**
	 * @param version
	 */
	public void setVersion(double version) {
		this.version = version;
	}

	/**
	 * @return volume_effects
	 */
	public double getVolume_effects() {
		return volume_effects;
	}

	/**
	 * @param volume_effects
	 */
	public void setVolume_effects(double volume_effects) {
		this.volume_effects = volume_effects;
	}

	/**
	 * @return volume_music
	 */
	public double getVolume_music() {
		return volume_music;
	}

	/**
	 * @param volume_music
	 */
	public void setVolume_music(double volume_music) {
		this.volume_music = volume_music;
	}

	/**
	 * @return difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @return cardcount - Je nachdem wie schwer das Spiel ist (von 1-5) werden unterschiedliche Kartenpaare zurückgegeben.
	 * 	Der Standardfall ohne Wertübergabe beträgt 3 also 24.
	 */
	public int getCardcount() {
		switch (difficulty) {
		case 1:
			return 8;
		case 2:
			return 16;
		case 3:
			return 24;
		case 4:
			return 32;
		case 5:
			return 36;
		default:
			return 24;
		}
	}
}
