package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import game.GameEventhandler;
import image.IMGhandler;
import javafx.animation.AnimationTimer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sound.MP3handler;
import start_MEMORY.Start;

public class MenuController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	Stage popupload;

	Alert multichoice;

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

	@FXML
	private void endprogram(ActionEvent event) {
		Start.getJhdl().commit();
		System.exit(0);
	}

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

	@FXML
	private void gamesingleplayer(ActionEvent event) {
		Start.setGamemode(1);
		init_game_sp();
	}

	@FXML
	private void gamemultiplayer(ActionEvent event) {
		init_game_mp();
	}

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MP3handler.playbackground(1);
	}

}
