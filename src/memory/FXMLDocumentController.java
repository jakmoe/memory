package memory;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gameLogic.Board;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    private StackPane stackp;
	
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
        JPanel panel = new JPanel();
        Board b = new Board();
        b.setPreferredSize(new Dimension(1000, 1000)); //need to use this instead of setSize
        b.setLocation(500, 250);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.pack();
        b.setVisible(true);
        b.setIconImage(new ImageIcon("C:\\Users\\D067928\\workspace\\Memory\\src\\Media\\1.jpg").getImage());
    
        panel.add(b);
        swingnode.setContent(panel);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
