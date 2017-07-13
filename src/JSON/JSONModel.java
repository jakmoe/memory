package JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author D067928
 *	Die 1:1 Repräsentation des JSON Dokuments. Beinhaltet alle zu speichernden Informationen wie die GameInfo als auch die 
 *	Spielersavegames für jede Schwierigkeit.
 */
public class JSONModel {
	private GameInfo gameInfo = new GameInfo();
	private ArrayList<PlayerSave> players_very_easy = new ArrayList<>();
	private ArrayList<PlayerSave> players_easy = new ArrayList<>();
	private ArrayList<PlayerSave> players_medium = new ArrayList<>();
	private ArrayList<PlayerSave> players_hard = new ArrayList<>();
	private ArrayList<PlayerSave> players_very_hard = new ArrayList<>();

	/**
	 * Setzt das Model zurück.
	 */
	public void resetModel() {
		players_very_easy.clear();
		players_easy.clear();
		players_medium.clear();
		players_hard.clear();
		players_very_hard.clear();
	}

	/**
	 * Updated das Model. Erlaubt maximal 50 Saves pro Schwierigkeit um die Größe des Dokumentes zu beschränken. Kann theoretisch
	 * unendlich viele Spieler fassen.
	 * @param ps PlayerSave ps.
	 */
	public void updateModel(PlayerSave ps) {
		//Falls der Spieler nie ein Paar gehabt haben sollte wird gar nicht erst geschrieben.
		if (ps.getHighscore() == 0) {
			return;
		}
		//Die SaveId wird inkrementiert
		ps.setId(getPlayers().size() + 1);
		getPlayers().add(ps);
		
		//Die Savegames werden alle nachdem selben Prinzip wie im WinStack sortiert. Wichtig: Es handelt sich um einen anderen Comparator
		// da der erste mit Player, dieser hier mit PlayerSave typisiert ist.
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
		// Schneidet das Array auf die Größe 50 zurück.
		shrinkTo(getPlayers(), 50);
	}

	/**
	 * Schneidet ein Array auf eine bestimmte Größe zurück
	 * @param list - die zu kürzende Liste
	 * @param newSize - die neue Größe
	 */
	public static void shrinkTo(List<PlayerSave> list, int newSize) {
		int size = list.size();
		if (newSize >= size)
			return;
		for (int i = newSize; i < size; i++) {
			list.remove(list.size() - 1);
		}
	}

	/**
	 * @return gameInfo
	 */
	public GameInfo getInfo() {
		return gameInfo;
	}

	/**
	 * @param ver - verändert die Version // Nicht benutzt, nur im Falle eines großen Updates zu verwenden
	 */
	public void changeVersion(double ver) {
		gameInfo.setVersion(ver);
	}

	/**
	 * @return players_* - Je nach Schwierigkeit in der gameInfo wird ein anderes Array geladen
	 */
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

	/**
	 * Je nach übergebenem schwierigkeitsgrad wird das korrespondierende Array zurückgegeben
	 * @param difficulty - gewollte Schwierigkeit
	 * @return players_*
	 */
	public ArrayList<PlayerSave> getPlayers(int difficulty) {
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
			return getPlayers();
		}
	}

	/**
	 * Setzt die Spieler des Schwierigkeitsgrades, der in gameInfo steht auf die neue Liste players.
	 * 
	 * @param players - die Liste mit der überschrieben werden soll
	 * @throws Exception - Falls keine Schwierigkeit mitgegeben wurde - Sollte nie vorkommen
	 */
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
