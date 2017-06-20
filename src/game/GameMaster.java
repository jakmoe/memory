package game;

import java.util.ArrayList;

import fxml_controller.GameController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import start_MEMORY.Start;

public class GameMaster {
	private static ArrayList<Player> playerAL = new ArrayList<Player>();
	private static Player PlayerInTurn;
	private static IntegerProperty playeridproperty = new SimpleIntegerProperty(0);
	private static int EndCheck;
	private static String[] names = new String [Start.getGamemode()];
	private static int i = 0;

	public static void doTurn(boolean scored, double newtime) {
		try {
			if (scored) {
				PlayerInTurn.setHighscore(PlayerInTurn.getHighscore() + 1);
			} else {
				if (playerAL.indexOf(PlayerInTurn) <= playerAL.size() - 2) {
					setPlayerInTurn(playerAL.get(playerAL.indexOf(PlayerInTurn) + 1));
				} else {
					setPlayerInTurn(playerAL.get(0));
				}
			}
			PlayerInTurn.setMintime(PlayerInTurn.getCurrenttime() + newtime);
			PlayerInTurn.setAttempts(PlayerInTurn.getAttempts() + 1);
			if (EndCheck <= 1) {
				GameOver();
			}
			if (scored) {
				EndCheck--;
			}

		} catch (Exception e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "GameMaster Error",
					"Something went wrong in the GameMaster", "Oops");
			exc.showdialog();
		}
	}

	public static void GameOver() {
		GameEventhandler.getTimer().stop();
		// still implementation for the final game with boardsize 9 needed
		for (int i = 0; i < playerAL.size(); i++) {
			System.out.println("Name" + playerAL.get(i).getName());
			System.out.println("Time curr" + playerAL.get(i).getCurrenttime());
			System.out.println("Highscore" + playerAL.get(i).getHighscore());
			System.out.println("ID" + playerAL.get(i).getId());
			System.out.println("Mintime" + playerAL.get(i).getMintime());
			System.out.println("Attempts" + playerAL.get(i).getAttempts());
			playerAL.get(i).CommitSafe();
		}

		GameController.setWin_ind(true);
	}

	public static Player getPlayerInTurn() {
		return PlayerInTurn;
	}

	public static ArrayList<Player> getPlayers() {
		return playerAL;
	}

	public static void reset() {
		playerAL.clear();
	}

	public static void setPlayerInTurn(Player playerInTurn) {
		PlayerInTurn = playerInTurn;
		playeridproperty.set(PlayerInTurn.getId());
	}

	public static void startGame(int playercount, int boardsize) {
		reset();
		// add players
		for (int i = 1; i <= playercount; i++) {
			playerAL.add(new Player(0.0, i, 0.0, 0, names[i-1], 0));
		}

		// set the EndCheck to the Board size
		EndCheck = boardsize;

		// set the first player
		PlayerInTurn = playerAL.get(0);
	}
	
	public static void setNames(String name){
		names[i] = name;
		i++;
		if (i == names.length)
			i = 0;
	}
	
	public static IntegerProperty getPlayeridproperty() {
		return playeridproperty;
	}

	public static void setIdListener(ChangeListener<Number> changeListener) {
		playeridproperty.addListener(changeListener);
	}

}
