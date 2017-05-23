package game;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;

public class GameMaster {
	private static ArrayList<Player> playerAL = new ArrayList<Player>();
	private static Player PlayerInTurn;
	private static int EndCheck;

	public static void startGame(int playercount, int boardsize, BoardPane gamepane) {
		// add players
		for (int i = 1; i <= playercount; i++) {
			playerAL.add(new Player(i));
		}
		// initialize the board
		gamepane.Initialize(boardsize);

		// set the EndCheck to the Board size
		EndCheck = boardsize;

		// set the first player
		PlayerInTurn = playerAL.get(0);
		
		AnimationTimer timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				
			}
		};
		timer.start();
	}

	public static void doTurn(boolean scored, double newtime) {
		if (scored) {
			PlayerInTurn.setHighscore(PlayerInTurn.getHighscore() + 2);
			PlayerInTurn.setCurrenttime(PlayerInTurn.getCurrenttime() + newtime);
			EndCheck -= 1;
		} else {
			if (playerAL.indexOf(PlayerInTurn) + 1 <= playerAL.size() - 1) {
				PlayerInTurn = playerAL.get(playerAL.indexOf(PlayerInTurn) + 1);
			} else {
				PlayerInTurn = playerAL.get(0);
			}
		}
		if (EndCheck <= 0) {
			GameOver();
		}
	}

	public static void GameOver() {
		// still implementation for the final game with boardsize 9 needed

		for (int i = 1; i < playerAL.size(); i++) {
			playerAL.get(i).CommitSafe();
			if (PlayerInTurn.getMintime() >= PlayerInTurn.getCurrenttime() ||
				PlayerInTurn.getMintime() == 0) {
				
				PlayerInTurn.setMintime(PlayerInTurn.getCurrenttime());
			}
		}
	}

}
