package game;

import JSON.JSONhandler;
import JSON.PlayerSave;

public class Player {
	private double currenttime;

	private int id;
	private double mintime;
	private int highscore;
	private String name;
	private PlayerSave PlayerSafegame;
	private JSONhandler jhdl = new JSONhandler();
	public Player(int id) {
		super();
		PlayerSafegame = jhdl.readinfo(id);
		this.id = id;
		this.mintime = PlayerSafegame.getMintime();
		this.highscore = PlayerSafegame.getHighscore();
		this.name = PlayerSafegame.getName();
		jhdl.writeinfo(PlayerSafegame);
	}

	public void CommitSafe() {
		PlayerSafegame = null;
		PlayerSafegame.setMintime(this.mintime);
		PlayerSafegame.setHighscore(this.highscore);
		PlayerSafegame.setName(this.name);
		PlayerSafegame.setId(this.id);
		jhdl.writeinfo(PlayerSafegame);
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

}
