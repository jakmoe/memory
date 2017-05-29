package fxml_controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import game.Board;
import game.BoardPane;
import game.GameMaster;
import game.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import sound.MP3handler;
import start_MEMORY.Start;

/**
 *
 * @author D067928
 */
public class GameController implements Initializable {

	FXMLLoader loader = new FXMLLoader();
	
	@FXML
	private Pane base;
	
	@FXML
	private VBox players;
	
	@FXML
	private Pane info;
	
	@FXML
	private HBox menu;

	@FXML
	private BoardPane gamepane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//Background bg = new Background(new BackgroundImage(IMGhandler.getGameBackground(), null, null, null, null));
		//base.setBackground(bg);
		
		base.getStylesheets().add("/fxml/UIGame/UIGame.css");
		
		GameMaster.startGame(Start.getGamemode(), Start.getJhdl().getModel().getInfo().getCardcount());
		initPlayers();
		initMenu();
		
		// Debug here needs to check if background is even running
		MP3handler.stopbackground();
		MP3handler.playbackground(2);
		
	}
	
	public void initMenu(){
		Button buttonMenu = new Button("Menu");
		
		buttonMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, 
                new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						loader.setLocation(getClass().getResource("/FXML/MainMenu/Menu.fxml"));
						try {
							MP3handler.stopbackground();
							Parent root = loader.load();
							buttonMenu.getScene().setRoot(root);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					});
		menu.getChildren().add(buttonMenu);
		
		Button buttonExit = new Button("Exit");
		buttonExit.addEventHandler(MouseEvent.MOUSE_CLICKED, 
                new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.exit(0);
					}
					});
		menu.getChildren().add(buttonExit);
		
		menu.setAlignment(Pos.CENTER);
	}
	
	
	public void initPlayers(){
		ArrayList<Player> playerAL = GameMaster.getPlayers();
		for (Player player : playerAL) {
			Circle circle = new Circle(100, Color.BLUE);
			switch (player.getId()) {
			case 1:
				circle.setFill(Color.AQUA);
				break;
			case 2:
				circle.setFill(Color.LIGHTGREEN);
				break;
			case 3:
				circle.setFill(Color.LIGHTYELLOW);
				break;
			case 4:
				circle.setFill(Color.ORANGERED);
				break;
			}
            circle.setStrokeType(StrokeType.INSIDE);
            circle.setStroke(Color.BLACK);
            if (player.getId() == GameMaster.getPlayerInTurn().getId()) {
                circle.setStrokeWidth(10);
			} else {
	            circle.setStrokeWidth(5);
			}
			Label playerlabel = new Label("Player " + player.getId());
			StackPane circlestack = new StackPane(circle, playerlabel);
			
			players.getChildren().add(circlestack);
		}
		players.setAlignment(Pos.CENTER);
		players.setSpacing(10);
		
		
		GameMaster.setIdListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (oldValue.intValue() <= playerAL.size() && newValue.intValue() != 1) {
					StackPane sp1 = (StackPane) players.getChildren().get(newValue.intValue()-1);
					Circle c1 = (Circle) sp1.getChildren().get(0);
					c1.setStrokeWidth(10);
					
					StackPane sp2 = (StackPane) players.getChildren().get(newValue.intValue()-2);
					Circle c2 = (Circle) sp2.getChildren().get(0);
					c2.setStrokeWidth(5);			
				}
				else {
					StackPane sp1 = (StackPane) players.getChildren().get(0);
					Circle c1 = (Circle) sp1.getChildren().get(0);
					c1.setStrokeWidth(10);
					
					StackPane sp2 = (StackPane) players.getChildren().get(playerAL.size()-1);
					Circle c2 = (Circle) sp2.getChildren().get(0);
					c2.setStrokeWidth(5);	
				}
			}
		});
	}
}
