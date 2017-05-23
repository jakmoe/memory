package game;

import XML.PlayerSave;
import XML.XMLhandler;

public class Player {
	private double currenttime;

	private int id;
	private double mintime;
	private int highscore;
	private String name;
	private PlayerSave PlayerSafegame;

	public Player(int id) {
		super();
		PlayerSafegame = XMLhandler.readplayerinfo(id);
		this.id = id;
		this.mintime = PlayerSafegame.mintime;
		this.highscore = PlayerSafegame.highscore;
		this.name = PlayerSafegame.name;
		XMLhandler.writeplayerinfo(PlayerSafegame, id);
	}

	public void CommitSafe() {
		PlayerSafegame = null;
		PlayerSafegame.mintime = this.mintime;
		PlayerSafegame.highscore = this.highscore;
		PlayerSafegame.name = this.name;
		PlayerSafegame.id = this.id;
		XMLhandler.writeplayerinfo(PlayerSafegame, this.id);
	}

	public double getCurrenttime() {
		return currenttime;
	}

	public void setCurrenttime(double currenttime) {
		this.currenttime = currenttime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMintime() {
		return mintime;
	}

	public void setMintime(double maxtime) {
		this.mintime = maxtime;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
