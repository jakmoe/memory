package memory;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author D067928
 */
public class FXMLDocumentController implements Initializable {
	@FXML Group group;
	
    @FXML
    private Canvas canvas;
	
    @FXML
    private Label label;
    
    @FXML
    private void handlecanvas(ActionEvent event) {
    	canvas.setRotate(canvas.getRotate()+10);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	drawShapes(gc);
    }    

    private void drawShapes(GraphicsContext gc) {
        
        gc.setFill(Color.CADETBLUE);
        gc.fillOval(30, 30, 50, 50);
        
        gc.setFill(Color.DARKRED);
        gc.fillOval(110, 30, 50, 50);
        
        gc.setFill(Color.STEELBLUE);
        gc.fillOval(190, 30, 50, 50);    
        
        gc.setFill(Color.BURLYWOOD);
        gc.fillOval(30, 110, 50, 50); 
        
        gc.setFill(Color.LIGHTSEAGREEN);
        gc.fillOval(110, 110, 50, 50);  
        
        gc.setFill(Color.CHOCOLATE);
        gc.fillOval(190, 110, 50, 50);          
    }
}
