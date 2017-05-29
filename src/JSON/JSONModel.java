package JSON;

import java.util.ArrayList;

public class JSONModel {
	private GameInfo gameInfo = new GameInfo();
	private ArrayList<PlayerSave> players = new ArrayList<>();

	public void addDummy(int id, PlayerSave dummy) {
		if (dummy == null) {
			dummy = new PlayerSave();
			dummy.setHighscore(20);
			dummy.setMintime(20.0);
			dummy.setName("John");
			dummy.setId(id);
		}
		players.add(dummy);
	}

	public GameInfo getInfo() {
		return gameInfo;
	}

	public void changeVersion(double ver) {
		gameInfo.setVersion(ver);
	}

	public void fillModel(ArrayList<PlayerSave> posts) {
		this.players = posts;
	}

	public PlayerSave getPlayer(int id) {
		for (PlayerSave playerSave : players) {
			if (playerSave.getId() == id) {
				return playerSave;
			}
		}
		return null;
	}

	public boolean newPlayer(PlayerSave ps) {
		boolean found = false;
		for (PlayerSave playerSave : players) {
			if (playerSave.getId() == ps.getId()) {
				playerSave.setId(ps.getId());
				playerSave.setHighscore(ps.getHighscore());
				playerSave.setMintime(ps.getMintime());
				playerSave.setName(ps.getName());
				found = true;
			}
		}
		if (!found) {
			players.add(ps);
		}
		return found;
	}

	public void removePlayer(int id) {
		boolean all_removed = false;
		while (!all_removed) {
			for (PlayerSave playerSave : players) {
				if (playerSave.getId() == id) {
					all_removed = !(players.remove(playerSave));
				}
			}
		}
	}

	public ArrayList<PlayerSave> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<PlayerSave> players) {
		this.players = players;
	}
}
