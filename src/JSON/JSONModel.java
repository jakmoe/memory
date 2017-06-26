package JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import start_MEMORY.Start;

public class JSONModel {
	private GameInfo gameInfo = new GameInfo();
	private ArrayList<PlayerSave> players_very_easy = new ArrayList<>();
	private ArrayList<PlayerSave> players_easy = new ArrayList<>();
	private ArrayList<PlayerSave> players_medium = new ArrayList<>();
	private ArrayList<PlayerSave> players_hard = new ArrayList<>();
	private ArrayList<PlayerSave> players_very_hard = new ArrayList<>();

	public void resetModel() {
		players_very_easy.clear();
		players_easy.clear();
		players_medium.clear();
		players_hard.clear();
		players_very_hard.clear();
	}

	public void updateModel(PlayerSave ps) {
		if (ps.getMintime() == 0) {
			return;
		}
		ps.setId(getPlayers().size() + 1);
		getPlayers().add(ps);
		getPlayers().sort(new Comparator<PlayerSave>() {
			@Override
			public int compare(PlayerSave o1, PlayerSave o2) {
				if (o1.getHighscore() > o2.getHighscore()) {
					return -1;
				} else if (o1.getHighscore() < o2.getHighscore()) {
					return 1;
				} else {
					if (o1.getAttempts() < o2.getAttempts()) {
						return -1;
					} else if (o1.getAttempts() > o2.getAttempts()) {
						return 1;
					} else {
						if (o1.getMintime() > o2.getMintime()) {
							return -1;
						} else if (o1.getMintime() < o2.getMintime()) {
							return 1;
						}
						return 0;
					}
				}
			}
		});
		shrinkTo(getPlayers(), 10);
	}

	public static void shrinkTo(List<PlayerSave> list, int newSize) {
		int size = list.size();
		if (newSize >= size)
			return;
		for (int i = newSize; i < size; i++) {
			list.remove(list.size() - 1);
		}
	}

	public GameInfo getInfo() {
		return gameInfo;
	}

	public void changeVersion(double ver) {
		gameInfo.setVersion(ver);
	}

	public ArrayList<PlayerSave> getPlayers() {
		int difficulty = gameInfo.getDifficulty();
		
		switch (difficulty) {
		case 1:
			return players_very_easy;
		case 2:
			return players_easy;
		case 3:
			return players_medium;
		case 4:
			return players_hard;
		case 5:
			return players_very_hard;
		default:
			return null;
		// Exception here
		}
	}

	public ArrayList<PlayerSave> getPlayers(int difficulty) throws Exception {
		
		
		switch (difficulty) {
		case 1:
			return players_very_easy;
		case 2:
			return players_easy;
		case 3:
			return players_medium;
		case 4:
			return players_hard;
		case 5:
			return players_very_hard;
		default:
			throw new Exception("No difficulty was given to the method.");
		}
	}

	public void setPlayers(ArrayList<PlayerSave> players) throws Exception {
		int difficulty = gameInfo.getDifficulty();
		switch (difficulty) {
		case 1:
			this.players_very_easy = players;
			break;
		case 2:
			this.players_easy = players;
			break;
		case 3:
			this.players_medium = players;
			break;
		case 4:
			this.players_hard = players;
			break;
		case 5:
			this.players_very_hard = players;
			break;
		default:
			throw new Exception("No difficulty was given to the method.");
		}
	}
}
