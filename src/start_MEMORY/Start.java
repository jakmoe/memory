package start_MEMORY;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import JSON.JSONhandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Gruppe 6 - Memory DHBW Mannheim
 * 
 * Die Startklasse des Memoryspiels. Dies ist die erste Klasse die in der Applikationslogik aufgerufen wird. Sie extended Application
 * was JavaFX eine Schnittstelle ermöglicht.
 */
public class Start extends Application {
	//Jeder neue Spielstart erhält einen JSONhandler über den alle Savegames zugegriffen werden
	private static JSONhandler jhdl = new JSONhandler();
	//Jeder Spielstart erhält einen Gamemode. Standardmäßig auf 1, kann dieser bis zu 4 groß sein und die Spielerzahl angeben
	private static int gamemode = 1;
	//Die Stage. Dies ist die oberste UI Ebene von JavaFX in der sogenannte "Szenen" geladen werden.
	private static Stage stage;

	// Diese Methode wird beim Start der Applikation aufgerufen. Sie entält den launch(args) Aufruf welcher das JavaFX Umfeld startet.
	public static void main(String[] args) {
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		//erstellt einen FXML loader, FXML sind Dokumente über die eine Szene geladen werden kann, die aber vorher mit XML kodiert wurden.
		FXMLLoader loader = new FXMLLoader();
		//lädt das Startmenü in eine Szene welche dann auf die Stage gesetzt wird.
		loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		//Hier werden Attribute für die Stage gesetzt, welche unter anderem für den fensterlosen Fullscreen sorgen
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		setStage(stage);
		stage.show();
		//Jetzt wird, wenn bereits vorhanden, das Savegame geladen.
		jhdl.pull();
		// initialize MP3Handler - Hier wird die Standardlautstärke des letzten Starts ermittelt
		jhdl.readVolume();
	}

	/**
	 * @return jhdl - gibt den JSONhandler der Spielinstanz zurück - Hierüber sollten Speicherdaten gehandhabt werden
	 */
	public static JSONhandler getJhdl() {
		return jhdl;
	}

	/**
	 * @param jhdl - setzt den JSONhandler für die Spielinstanz
	 */
	public static void setJhdl(JSONhandler jhdl) {
		Start.jhdl = jhdl;
	}

	/**
	 * @return gamemode - von 1-4: Anzahl der aktuellen Spieler, standardmäßig 1
	 */
	public static int getGamemode() {
		return gamemode;
	}

	/**
	 * @param gamemode
	 */
	public static void setGamemode(int gamemode) {
		Start.gamemode = gamemode;
	}

	/**
	 * @return stage - Die oberste UI Ebene von JavaFX
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * @param stage - Die oberste UI Ebene von JavaFX
	 */
	public static void setStage(Stage stage) {
		Start.stage = stage;
	}
}
