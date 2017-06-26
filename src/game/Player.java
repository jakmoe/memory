package game;

import JSON.PlayerSave;
import start_MEMORY.Start;

public class Player {
	private double currenttime;
	private int id;
	private double mintime;
	private int highscore;
	private int attempts;
	private String name;
	private PlayerSave PlayerSafegame;

	public Player(double currenttime, int id, double mintime, int highscore, String name, int attempts) {
		super();
		this.currenttime = currenttime;
		this.id = id;
		this.mintime = mintime;
		this.highscore = highscore;
		this.name = name;
		this.attempts = attempts;
	}

	public void CommitSafe() {
		boolean spcheck = false;
		if (Start.getGamemode() == 1) {
			spcheck = true;
		}
		PlayerSafegame = new PlayerSave(this.id, this.name, this.highscore, this.mintime, this.attempts, spcheck);
		Start.getJhdl().writePlayerinfo(PlayerSafegame);
	}

	public double getCurrenttime() {
		return currenttime;
	}

	public int getHighscore() {
		return highscore;
	}

	public int getId() {
		return id;
	}

	public double getMintime() {
		return mintime;
	}

	public String getName() {
		return name;
	}

	public void setCurrenttime(double currenttime) {
		this.currenttime = currenttime;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMintime(double maxtime) {
		this.mintime = maxtime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

}
