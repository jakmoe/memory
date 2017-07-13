package JSON;

/**
 * @author D067928
 *	Die Repräsentation eines Spielers im JSON Dokument. Enthält nicht alle Attribute von Player (wie z.B. einen eigenen Timer),
 *  aber einen Indikator ob der Spieler im Singleplayer oder Multiplayer gespielt hat.
 */
public class PlayerSave {
	@Override
	public String toString() {
		//Zum debuggen wird die toString überschrieben
		return singleplayer + "Name=" + name + ", Highscore=" + highscore + ", Mintime=" + mintime + ", Attempts=" + attempts;
	}

	private String name;
	private int highscore;
	private double mintime;
	private int id;
	private int attempts;
	private boolean singleplayer;

	/**
	 * Der Standardkonstruktor initialisiert mit Dummywerten
	 */
	public PlayerSave() {
		mintime = 0;
		highscore = 0;
		name = "name";
	}

	/**
	 * Der Parametrisierte Konstruktor übernimmt alle angegebenen Parameter
	 * @param id
	 * @param name
	 * @param highscore
	 * @param mintime
	 * @param attempts
	 * @param singleplayer
	 */
	public PlayerSave(int id, String name, int highscore, double mintime, int attempts, boolean singleplayer) {
		super();
		this.name = name;
		this.highscore = highscore;
		this.mintime = mintime;
		this.attempts = attempts;
		this.singleplayer = singleplayer;
	}

	/**
	 * Gibt einen DummySave ab. Verwendet zum Debuggen
	 * @param dummy_id 
	 * @return PlayerSave
	 */
	public static PlayerSave getDummy(int dummy_id) {
		PlayerSave dummy = new PlayerSave();
		dummy.setId(dummy_id);
		dummy.setHighscore(0);
		dummy.setMintime(0.0);
		dummy.setName("name");
		dummy.setSingleplayer(false);
		return dummy;
	}

	/**
	 * @return highscore
	 */
	public int getHighscore() {
		return highscore;
	}

	/**
	 * @return mintime
	 */
	public double getMintime() {
		return mintime;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param highscore
	 */
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	/**
	 * @param mintime
	 */
	public void setMintime(double mintime) {
		this.mintime = mintime;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return attempts
	 */
	public int getAttempts() {
		return attempts;
	}

	/**
	 * @param attempts
	 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	/**
	 * @return singleplayer - Boolean Wert. True falls im Einzelspieler erstellt, False falls nicht.
	 */
	public boolean isSingleplayer() {
		return singleplayer;
	}

	/**
	 * @param singleplayer - Boolean Wert. True falls im Einzelspieler erstellt, False falls nicht.
	 */
	public void setSingleplayer(boolean singleplayer) {
		this.singleplayer = singleplayer;
	}

}
