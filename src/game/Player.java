package game;

import XML.PlayerSave;
import XML.XMLhandler;

public class Player {
	private int id;
	private int maxtime;
	private int highscore;
	private String name;
	private PlayerSave PlayerSafegame;
	
	public Player(int id) {
		super();
		
		String dynString = "Player_" + id;
		PlayerSafegame = XMLhandler.readplayerinfo(dynString);
		
		if (PlayerSafegame == null) {
			//XMLhanlder.writeplayerinfo(id);
		}
		
		this.id = id;
		this.maxtime   = PlayerSafegame.maxtime;
		this.highscore = PlayerSafegame.highscore;
		this.name      = PlayerSafegame.name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxtime() {
		return maxtime;
	}
	public void setMaxtime(int maxtime) {
		this.maxtime = maxtime;
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
