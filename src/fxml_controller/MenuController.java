package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MenuController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	@FXML
	private Button singleplayer;
	@FXML
	private Button settings;
	@FXML
	private Button end;

	@FXML
	private void gamesingleplayer(ActionEvent event) {
		loader.setLocation(getClass().getResource("/FXML/Game.fxml"));
		try {
			Parent root = loader.load();
			singleplayer.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void gamesettings(ActionEvent event) {
		loader.setLocation(getClass().getResource("/FXML/Settings.fxml"));
		try {
			Parent root = loader.load();
			singleplayer.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void endprogram(ActionEvent event) {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
