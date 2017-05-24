package fxml_controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import game.Board;
import game.BoardPane;
import game.GameMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author D067928
 */
public class GameController implements Initializable {

	FXMLLoader loader = new FXMLLoader();

	@FXML
	private Button button;

	@FXML
	private BoardPane gamepane;

	
	@FXML
	private void test(ActionEvent event) {
		
	}
	
	@FXML
	private void back(ActionEvent event) {
		loader.setLocation(getClass().getResource("/FXML/Menu.fxml"));
		try {
			Parent root = loader.load();
			gamepane.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gamepane.setCache(false);
		gamepane.setCacheShape(true);
		gamepane.setCacheHint(CacheHint.SCALE_AND_ROTATE);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		GameMaster.startGame(1, 16);
		// gamepane.Test();
	}
}
