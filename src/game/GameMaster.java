package game;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;

public class GameMaster {
	private static ArrayList<Player> playerAL = new ArrayList<Player>();
	private static Player PlayerInTurn;
	private static IntegerProperty playeridproperty = new SimpleIntegerProperty(0);
	private static int EndCheck;

	public static void doTurn(boolean scored, double newtime) {
		try {
			if (EndCheck <= 1) {
				GameOver();
			}
			if (scored) {
				PlayerInTurn.setHighscore(PlayerInTurn.getHighscore() + 1);
				PlayerInTurn.setCurrenttime(PlayerInTurn.getCurrenttime() + newtime);
				playerAL.get(PlayerInTurn.getId() - 1).CommitSafe();
				EndCheck--;
			} else {
				if (playerAL.indexOf(PlayerInTurn) <= playerAL.size() - 2) {
					setPlayerInTurn(playerAL.get(playerAL.indexOf(PlayerInTurn) + 1));
				} else {
					setPlayerInTurn(playerAL.get(0));
				}
			}
		} catch (Exception e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "GameMaster Error", "Something went wrong in the GameMaster", "Oops");
			exc.showdialog();
		}
	}

	public static void GameOver() {
		// still implementation for the final game with boardsize 9 needed

		for (int i = 1; i < playerAL.size(); i++) {
			playerAL.get(i).CommitSafe();
			if (PlayerInTurn.getMintime() >= PlayerInTurn.getCurrenttime() || PlayerInTurn.getMintime() == 0) {
				PlayerInTurn.setMintime(PlayerInTurn.getCurrenttime());
			}
		}
		
		System.out.println("GAME OVER DEBUG");
	}

	public static Player getPlayerInTurn() {
		return PlayerInTurn;
	}

	public static ArrayList<Player> getPlayers() {
		return playerAL;
	}

	public static void setPlayerInTurn(Player playerInTurn) {
		PlayerInTurn = playerInTurn;
		playeridproperty.set(PlayerInTurn.getId());
	}

	public static void startGame(int playercount, int boardsize) {
		playerAL.clear();
		// add players
		for (int i = 1; i <= playercount; i++) {
			playerAL.add(new Player(i));
		}

		// set the EndCheck to the Board size
		EndCheck = boardsize;

		// set the first player
		PlayerInTurn = playerAL.get(0);
	}

	public static IntegerProperty getPlayeridproperty() {
		return playeridproperty;
	}

	public static void setIdListener(ChangeListener<Number> changeListener) {
		playeridproperty.addListener(changeListener);
	}

}
