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

/**
 * @author D067928
 *	Der MultiplayerController01 prüft hauptsächlich die Spieleranzahl
 */
public class MultiplayerController01 implements Initializable {
	
	@FXML
	private AnchorPane Anchor;
	
	@FXML 
	private Button two;

	@FXML
	private ToggleGroup difficulty;
	
	FXMLLoader loader = new FXMLLoader();
	
	//Je nach Spieleranzahl wird der Gamemode anders gesetzt und die nächste Szene geladen
	@FXML
	private void handleButtonTwo (ActionEvent event) {
		Start.setGamemode(2);
		loadNextScene();
	}
	@FXML
	private void handleButtonThree (ActionEvent event) {
		Start.setGamemode(3);
		loadNextScene();
	}
	@FXML
	private void handleButtonFour (ActionEvent event) {
		Start.setGamemode(4);
		loadNextScene();
	}
	
	@FXML
	private void back (ActionEvent event) {
		MenuHandler menhan = new MenuHandler();
		menhan.setBase(Anchor);
	}
		
	private void loadNextScene() {
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(getClass().getResource("/fxml/Multiplayer/Multiplayer02.fxml"));
		try {
			MP3handler.stopbackground();
			Parent root2 = loader2.load();
			Anchor.getScene().setRoot(root2);
		} catch (IOException e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "Load Error",
					"Something went wrong loading the next screen", "Oops");
			exc.showdialog();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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