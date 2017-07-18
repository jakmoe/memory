package start_MEMORY;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import JSON.JSONhandler;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Gruppe 6 - Memory DHBW Mannheim
 * 
 * Die Startklasse des Memoryspiels. Dies ist die erste Klasse die in der Applikationslogik aufgerufen wird. Sie extended Application
 * was JavaFX eine Schnittstelle erm�glicht.
 */
public class Start extends Application {
	//Jeder neue Spielstart erh�lt einen JSONhandler �ber den alle Savegames zugegriffen werden
	private static JSONhandler jhdl = new JSONhandler();
	//Jeder Spielstart erh�lt einen Gamemode. Standardm��ig auf 1, kann dieser bis zu 4 gro� sein und die Spielerzahl angeben
	private static IntegerProperty gamemodeProp = new SimpleIntegerProperty(1);
	//Die Stage. Dies ist die oberste UI Ebene von JavaFX in der sogenannte "Szenen" geladen werden.
	private static Stage stage;

	// Diese Methode wird beim Start der Applikation aufgerufen. Sie ent�lt den launch(args) Aufruf welcher das JavaFX Umfeld startet.
	public static void main(String[] args) {
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) throws Exception {
		//erstellt einen FXML loader, FXML sind Dokumente �ber die eine Szene geladen werden kann, die aber vorher mit XML kodiert wurden.
		FXMLLoader loader = new FXMLLoader();
		//l�dt das Startmen� in eine Szene welche dann auf die Stage gesetzt wird.
		loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		//Hier werden Attribute f�r die Stage gesetzt, welche unter anderem f�r den fensterlosen Fullscreen sorgen
		stage.setFullScreen(false);
		stage.setFullScreenExitHint("");
		stage.setResizable(true);
//		stage.initStyle(StageStyle.UNDECORATED);
		setStage(stage);
		stage.show();
		//Jetzt wird, wenn bereits vorhanden, das Savegame geladen.
		jhdl.pull();
		// initialize MP3Handler - Hier wird die Standardlautst�rke des letzten Starts ermittelt
		jhdl.readVolume();
	}

	/**
	 * @return jhdl - gibt den JSONhandler der Spielinstanz zur�ck - Hier�ber sollten Speicherdaten gehandhabt werden
	 */
	public static JSONhandler getJhdl() {
		return jhdl;
	}

	/**
	 * @param jhdl - setzt den JSONhandler f�r die Spielinstanz
	 */
	public static void setJhdl(JSONhandler jhdl) {
		Start.jhdl = jhdl;
	}

	/**
	 * @return gamemode - von 1-4: Anzahl der aktuellen Spieler, standardm��ig 1
	 */
	public static int getGamemode() {
		return gamemodeProp.get();
	}

	/**
	 * @param gamemode
	 */
	public static void setGamemode(int gamemode) {
		Start.gamemodeProp.set(gamemode);
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

	public static IntegerProperty getGamemodeProp() {
		return gamemodeProp;
	}

	public static void setGamemodeProp(IntegerProperty gamemodeProp) {
		Start.gamemodeProp = gamemodeProp;
	}
}
