package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sound.MP3handler;
import start_MEMORY.Start;

/**
 * @author D067928
 * Die Controller-Klasse für das Hauptmenü
 */
public class MenuController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	Stage popupload;


	//Buttons für die Navigation
	@FXML
	private AnchorPane anchor;
	@FXML
	private Button singleplayer;
	@FXML
	private Button multiplayer;
	@FXML
	private Button settings;
	@FXML
	private Button highscores;
	@FXML
	private Button credits;
	@FXML
	private Button end;

	/**
	 * Beendet das Programm.
	 */
	@FXML
	private void endprogram(ActionEvent event) {
		Start.getJhdl().commit();
		System.exit(0);
	}

	/**
	 * Lädt die Settingsszene von FXML in die Stage
	 */
	@FXML
	private void gamesettings(ActionEvent event) {
		loader.setLocation(getClass().getResource("/fxml/Settings/Settings.fxml"));
		try {
			Parent root = loader.load();
			singleplayer.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Lädt die Highscoreszene von FXML in die Stage
	 */
	@FXML
	private void highscores(ActionEvent event) {
		loader.setLocation(getClass().getResource("/fxml/Highscore/Highscore.fxml"));
		try {
			Parent root = loader.load();
			singleplayer.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Lädt die Creditsszene von FXML in die Stage
	 */
	@FXML
	private void credits(ActionEvent event) {
		loader.setLocation(getClass().getResource("/fxml/Credits/Credits.fxml"));
		try {
			Parent root = loader.load();
			singleplayer.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Startet das Spiel im Einzelspielermodus
	 */
	@FXML
	private void gamesingleplayer(ActionEvent event) {
		Start.setGamemode(1);
		init_game_sp();
	}

	/**
	 * Startet das Spiel im Mehrspielermodus
	 */
	@FXML
	private void gamemultiplayer(ActionEvent event) {
		init_game_mp();
	}

	/**
	 * Lädt die erste Mehrspielerszene
	 */
	private void init_game_mp() {
		loader.setLocation(getClass().getResource("/fxml/Multiplayer/Multiplayer01.fxml"));
		try {
			Parent root = loader.load();
			singleplayer.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Lädt die erste Einzelspielerszene
	 */
	private void init_game_sp() {
		loader.setLocation(getClass().getResource("/fxml/Singleplayer/Singleplayer01.fxml"));
		try {
			Parent root = loader.load();
			singleplayer.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MP3handler.playbackground(1); //startet die Musik
	}

}
