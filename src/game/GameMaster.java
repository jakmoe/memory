package game;

import java.util.ArrayList;

public class GameMaster {
	private ArrayList<Player> playerAL = new ArrayList<Player>();
	private Player PlayerInTurn;
	private int EndCheck;

	public void startGame(int playercount, int boardsize, BoardPane gamepane) {
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
	}

	public void doTurn(boolean scored, int time) {
		if (scored) {
			PlayerInTurn.setHighscore(PlayerInTurn.getHighscore() + 2);
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

	public void GameOver() {
		// still implementation for the final game with boardsize 9 needed

		for (int i = 1; i < playerAL.size(); i++) {
			playerAL.get(i).CommitSafe();
		}
	}

}
