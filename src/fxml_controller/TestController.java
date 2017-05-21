package fxml_controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;

//import game.Board;
import game.BoardVBOX;
import game.GameMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author D067928
 */
public class TestController implements Initializable {
    @FXML
    private BoardVBOX gamepane;
    
    @FXML
    private void gamestart(ActionEvent event) {
    	System.exit(0);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	GameMaster GM = new GameMaster();
    	GM.startGame(1, 16, gamepane);
    	//gamepane.Test();
    }    
}
