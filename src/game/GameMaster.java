package game;

import java.util.ArrayList;

import fxml_controller.GameController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import start_MEMORY.Start;

/**
 * @author D067928
 *	Der GameMaster ist daf�r verantwortlich die Rundenverwaltung zu �bernehmen. So wird hier der Spieler an der Reihe gepuffert.
 *	Auch sind hier alle Spieler in einer ArrayList vorhanden.
 */
public class GameMaster {
	private static ArrayList<Player> playerAL = new ArrayList<Player>();
	private static Player PlayerInTurn;
	private static IntegerProperty playeridproperty = new SimpleIntegerProperty(0);
	private static int EndCheck;
	private static ArrayList<String> names = new ArrayList<String>();
	private static int i = 0;

	/**
	 * doTurn wird aufgerufen, falls eine Runde abgehandelt werden soll. Es kommt dann der n�chste Spieler im Spielerarray an die Reihe.
	 * @param scored - Gibt an ob ein Paar erzielt wurde oder nicht.
	 */
	public static void doTurn(boolean scored) {
		try {
			//Falls ein Paar erzielt wurde wird der Highscore inkrementiert.
			if (scored) {
				PlayerInTurn.setHighscore(PlayerInTurn.getHighscore() + 1);
			} else {
				//Falls mehr als 1 Spieler das Spiel spielt wird der Timer des Spielers an der Reihe gestoppt
				if (Start.getGamemode() > 1) {
					PlayerInTurn.stop();
				}
				// Falls der Spieler an der Reihe der letzte ist, wird statt dem (nichtvorhandenen) n�chsten der 1. Spieler gew�hlt.
				if (playerAL.indexOf(PlayerInTurn) <= playerAL.size() - 2) {
					setPlayerInTurn(playerAL.get(playerAL.indexOf(PlayerInTurn) + 1));
				} else {
					setPlayerInTurn(playerAL.get(0));
				}
			}
			//Falls das Spiel zu Ende ist, wird GameOver() aufgerufen.
			if (EndCheck <= 1) {
				GameOver();
			}
			//Wenn ein Paar erzielt wurde wird der EndCheck dekrementiert.
			if (scored) {
				EndCheck--;
			}
			
		} catch (Exception e) {
			//Im Fall eines Fehlers durch falsches Bef�llen des Arrays wird eine Exception geworfen.
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "GameMaster Error",
					"Something went wrong in the GameMaster", "Oops");
			exc.showdialog();
		}
	}

	/**
	 * Dies ist die Methode die aufgerufen wird, falls das Spiel zu Ende ist.
	 */
	public static void GameOver() {
		// Der Timer des letzten Spielers der an der Reihe war muss gestoppt werden
		getPlayerInTurn().stop();
		// Jeder Spieler wird abgespeichert (ins Savegame geschrieben)
		for (int i = 0; i < playerAL.size(); i++) {
			playerAL.get(i).CommitSafe();
		}
		// Der Win Indikator wird auf wahr gesetzt um im n�chsten frame den WinScreen anzuzeigen
		GameController.setWin_ind(true);
	}

	/**
	 * @return PlayerInTurn - Gibt den Spieler, der an der Reihe ist, als Objekt zur�ck.
	 */
	public static Player getPlayerInTurn() {
		return PlayerInTurn;
	}

	/**
	 * @return playerAL - gibt alle Spieler in der Spielreihenfolge als ArrayList zur�ck.
	 */
	public static ArrayList<Player> getPlayers() {
		return playerAL;
	}

	/**
	 * setzt alle Spieler zur�ck, indem die Listen geleert werden.
	 */
	public static void reset() {
		playerAL.clear();
	}

	/**
	 * @param playerInTurn - Setzt den Spieler der an der Reihe ist, sowie seine idproperty. Diese wird ben�tigt um in Echtzeit
	 * den richtigen Spieler auf dem User Interface zu updaten. (welcher �ber die idproperty gelesen werden kann). Properties
	 * sind Wrapperklassen im JavaFX Umfeld, welche f�r bestimmte Echtzeitupdate-operationen verwendet werden
	 */
	public static void setPlayerInTurn(Player playerInTurn) {
		PlayerInTurn = playerInTurn;
		playeridproperty.set(PlayerInTurn.getId());
	}

	/**
	 * Die Methode wird aufgerufen sobald das Spiel gestartet wird.
	 * @param playercount - Anzahl der Spieler
	 * @param boardsize - Gr��e des Boards
	 */
	public static void startGame(int playercount, int boardsize) {
		reset();
		// Addiert neue Spieler mit den Namen aus dem UI abh�ngig von der Spieleranzahl
		for (int i = 1; i <= playercount; i++) {
			playerAL.add(new Player(0.0, i, 0.0, 0, names.get(i-1), 0));
		}

		// EndCheck wird auf BoardGr��e gesetzt. Wenn alle Paare gefunden sind steht der EndCheck auf 0.
		EndCheck = boardsize;

		// Der erste Spieler f�ngt an.
		PlayerInTurn = playerAL.get(0);
	}
	
	/**
	 * Hier werden nacheinander Namen hineingeschrieben
	 * @param name - Name des Spielers
	 */
	public static void setNames(String name){
		names.add(name);
		i++;
		if (i == Start.getGamemode())
			i = 0;
	}
	
	/**
	 * @return playeridproperty - Gibt die Property f�r die Spieler ID des PlayerInTurn zur�ck
	 */
	public static IntegerProperty getPlayeridproperty() {
		return playeridproperty;
	}

	/**
	 * Hier kann der Property ein Listener hinzugef�gt werden, welcher ein Verhalten ausl�st sobald sich die property �ndert.
	 * So kann stets eine Logik ausgef�hrt werden wenn der PlayerInTurn gewechselt wird.
	 * @param changeListener - der zu addende Listener
	 */
	public static void setIdListener(ChangeListener<Number> changeListener) {
		playeridproperty.addListener(changeListener);
	}

}
