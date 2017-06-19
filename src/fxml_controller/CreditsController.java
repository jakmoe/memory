package fxml_controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import sound.MP3handler;

/**
 *
 * @author D067928
 */
public class CreditsController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	@FXML
	private Button back;

	@FXML
	private void backaction() {
		loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
		try {
			MP3handler.stopbackground();
			Parent root = loader.load();
			back.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
