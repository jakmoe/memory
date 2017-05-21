package game;

import java.util.ArrayList;

public class GameMaster {
	private ArrayList<Player> playerAL = new ArrayList<Player>();
	private Player PlayerInTurn;

	public void startGame(int playercount, int boardsize, BoardVBOX gamepane) {
		// add players
		for (int i = 1; i <= playercount; i++) {
			playerAL.add(new Player(i));
		}
		// initialize the board
    	gamepane.Initialize(boardsize);
		
    	// set the first player
		PlayerInTurn = playerAL.get(0);
	}

	public void doTurn(boolean scored, int time) {
		if (scored) {
			PlayerInTurn.setHighscore(PlayerInTurn.getHighscore() + 1);
		} else {
			if (playerAL.indexOf(PlayerInTurn) + 1 <= playerAL.size() - 1) {
				PlayerInTurn = playerAL.get(playerAL.indexOf(PlayerInTurn) + 1);
			} else {
				PlayerInTurn = playerAL.get(0);
			}
		}
	}

	public void GameOver() {
		for (int i = 1; i < playerAL.size(); i++) {
			playerAL.get(i).CommitSafe();
		}
	}

}
