package fxml_controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.ExceptionHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import sound.MP3handler;

/**
 *
 * @author D067928
 * Credits.fxml
 * Dies ist der FXML Controller für den Creditsscreen. Er beinhält nur ein Bild (das bereits im fxml Dokument
 * initialisiert wird. 
 */
public class CreditsController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	@FXML
	private Button back; //der Zurückbutton

	/**
	 * Ermöglicht die Rücknavigation ins Hauptmenü
	 */
	@FXML
	private void backaction() {
		loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
		try {
			MP3handler.stopbackground();
			Parent root = loader.load();
			back.getScene().setRoot(root);
		} catch (IOException e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "Load Error",
					"Something went wrong loading the next screen", "Oops");
			exc.showdialog();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
