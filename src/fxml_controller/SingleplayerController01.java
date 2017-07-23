package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.ExceptionHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import sound.MP3handler;
import start_MEMORY.Start;

public class SingleplayerController01 implements Initializable {
	
	@FXML
	private AnchorPane Anchor;
	
	@FXML 
	private Button StartButton;


	@FXML
	private ToggleGroup difficulty;
	
	FXMLLoader loader = new FXMLLoader();
	
	//Set the number of players and redirect to the next scene
	@FXML
	private void handleButton (ActionEvent event) {
		StartButton.setDisable(true);
		Start.setGamemode(1);
		loadNextScene();
	}
	
	/**
	 * Ermöglicht Navigation zurück ins Hauptmenü
	 */
	@FXML
	private void back() {
		MenuHandler menhan = new MenuHandler();
		menhan.setBase(Anchor);
	}
		
	/**
	 * Lädt die nächste Szene
	 */
	private void loadNextScene() {
		loader.setLocation(getClass().getResource("/fxml/Singleplayer/Singleplayer02.fxml"));
		try {
			MP3handler.stopbackground();
			Parent root = loader.load();
			Anchor.getScene().setRoot(root);
		} catch (IOException e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "Load Error",
					"Something went wrong loading the next screen", "Oops");
			exc.showdialog();
		}
	}
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Verbindet die Schwierigkeit mit den gespeicherten Daten um Änderungen anzuzeigen und zu übernehmen
		int difficulty_id = 1;
		for (Toggle toggle : difficulty.getToggles()) {
			if (Start.getJhdl().getModel().getInfo().getDifficulty() == difficulty_id) {
				difficulty.selectToggle(toggle);
			}
			toggle.setUserData(difficulty_id);
			difficulty_id++;
		}
		difficulty.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (difficulty.getSelectedToggle() != null) {
					Start.getJhdl().getModel().getInfo().setDifficulty((int) new_toggle.getUserData());
				}
			}
		});
	}

}