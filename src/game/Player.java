package game;

import JSON.JSONhandler;
import JSON.PlayerSave;
import start_MEMORY.Start;

public class Player {
	private double currenttime;

	private int id;
	private double mintime;
	private int highscore;
	private String name;
	private PlayerSave PlayerSafegame;
	private JSONhandler jhdl = Start.getJhdl();

	public Player(int id) {
		super();
		PlayerSafegame = jhdl.readPlayerinfo(id);
		this.id = id;
		this.mintime = PlayerSafegame.getMintime();
		this.highscore = PlayerSafegame.getHighscore();
		this.name = PlayerSafegame.getName();
		jhdl.writePlayerinfo(PlayerSafegame);
	}

	public void CommitSafe() {
		PlayerSafegame = new PlayerSave();
		PlayerSafegame.setMintime(this.mintime);
		PlayerSafegame.setHighscore(this.highscore);
		PlayerSafegame.setName(this.name);
		PlayerSafegame.setId(this.id);
		jhdl.writePlayerinfo(PlayerSafegame);
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
