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
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author D067928
 */
public class TestController implements Initializable {
	@FXML
	public static Pane drawpane;
	
    @FXML
    private Canvas canvas;
    
    @FXML
    private BoardVBOX gamepane;
	
    @FXML
    private Label label;
    
    @FXML
    private SwingNode swingnode;
    
    @FXML
    private void handlecanvas(ActionEvent event) {
    	canvas.setRotate(canvas.getRotate()+10);
    }

    @FXML
    private void gamestart(ActionEvent event) {
    	System.exit(0);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	gamepane.Initialize(1, 16);
    	//gamepane.Test();
    }    
}
