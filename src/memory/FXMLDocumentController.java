package memory;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;

import game.Board;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

/**
 *
 * @author D067928
 */
public class FXMLDocumentController implements Initializable {
	@FXML Group group;
	
    @FXML
    private Canvas canvas;
    
    @FXML
    private Board gamepane;
	
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
    	gamepane.Initialize(1);
    	//gamepane.layoutXProperty().bind(pane.widthProperty().subtract(results.widthProperty()).divide(2))
    	//gamepane.Test();
    }    
}
