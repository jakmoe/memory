package game;

import JSON.PlayerSave;
import start_MEMORY.Start;

/**
 * @author D067928
 *	Die Spielerklasse welche die Kerninformationen enthält die für einen Spieler benötigt werden.
 */
public class Player {
	private int id;
	private double mintime;
	private double savedtime;
	private int highscore;
	private int attempts;
	private String name;
	private PlayerSave PlayerSafegame;
	private CustomAnimationTimer timer = new CustomAnimationTimer();

	/**
	 * @return CustomAnimationTimer - Gibt den individuellen Rundentimer für einen Spieler zurück
	 */
	public CustomAnimationTimer getTimer() {
		return timer;
	}
	
	/**
	 * Startet den Timer, nützlich um nicht über getTimer() zu gehen
	 */
	public void start() {
		timer.start();
	}
	
	/**
	 *  Stoppt den Timer, nützlich um nicht über getTimer() zu gehen
	 */
	public void stop() {
		timer.stop();
	}
	
	/**
	 * Standard Spieler Konstruktor. Ein Timer ist bereits standardmäßig initialisiert.
	 * @param currenttime
	 * @param id
	 * @param mintime
	 * @param highscore
	 * @param name
	 * @param attempts
	 */
	public Player(double currenttime, int id, double mintime, int highscore, String name, int attempts) {
		super();
		this.id = id;
		this.mintime = mintime;
		this.highscore = highscore;
		this.name = name;
		this.attempts = attempts;
	}

	/**
	 * Speichert die relevanten zu sichernden Werte des Players in einem neuen PlayerSafegame um sie dem JSON Handler zum schreiben
	 * zu übergeben.
	 */
	public void CommitSafe() {
		boolean spcheck = false;
		// Falls der Spieler alleine spielt, wird ein Flag für die spätere Highscoreanzeige gesetzt.
		if (Start.getGamemode() == 1) {
			spcheck = true;
		}
		PlayerSafegame = new PlayerSave(this.id, this.name, this.highscore, this.mintime, this.attempts, spcheck);
		// Schreibt die entsprechenden infos in das neu erstellte PlayerSafegame
		Start.getJhdl().writePlayerinfo(PlayerSafegame);
	}

	/**
	 * @return highscore
	 */
	public int getHighscore() {
		return highscore;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
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
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param maxtime
	 */
	public void setMintime(double maxtime) {
		this.mintime = maxtime;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return savedtime - Speicherwert damit bei einem Neustart des timers die vorherige Zeit nicht verlorengeht
	 */
	public double getSavedtime() {
		return savedtime;
	}

	/**
	 * @param savedtime - Speicherwert damit bei einem Neustart des timers die vorherige Zeit nicht verlorengeht
	 */
	public void setSavedtime(double savedtime) {
		this.savedtime = savedtime;
	}

}
