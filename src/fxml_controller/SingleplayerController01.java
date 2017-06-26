package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import game.ExceptionHandler;
import game.GameMaster;
import game.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sound.MP3handler;
import start_MEMORY.Start;

public class SingleplayerController01 implements Initializable {
	
	@FXML
	private AnchorPane Anchor;
	
	@FXML 
	private Button start;

	
	FXMLLoader loader = new FXMLLoader();
	
	//Set the number of players and redirect to the next scene
	@FXML
	private void handleButton (ActionEvent event) {
		Start.setGamemode(1);
		loadNextScene();
	}
	
	@FXML
	private void back() {
		loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}