package fxml_controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import sound.MP3handler;

public class HighscoreController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MP3handler.playbackground(1);
	}

}
