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
import game.ExceptionHandler;
import game.GameMaster;
import game.Player;
import image.IMGhandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
		base.getStylesheets().add("/fxml/UIGame/UIGame.css");
		base.setCache(true);
		base.setCacheHint(CacheHint.SPEED);
		base.setBackground(
				new Background(new BackgroundImage(IMGhandler.getGameBackground(), null, null, null, null)));
		GameMaster.startGame(Start.getGamemode(), Start.getJhdl().getModel().getInfo().getCardcount());
		initPlayers();
		initMenu();

		// Debug here needs to check if background is even running
		MP3handler.stopbackground();
		MP3handler.playbackground(2);
	}
	
	public void initMenu() {
		Button buttonMenu = new Button("Menu");

		buttonMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
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
		buttonExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
		});
		menu.getChildren().add(buttonExit);

		menu.setAlignment(Pos.CENTER);

//		menu.setStyle("-fx-border-color: Blue");
	}

	public void initPlayers() {
		ArrayList<Player> playerAL = GameMaster.getPlayers();
		for (Player player : playerAL) {
			// this is just debug coding
			Circle circle = new Circle(100, Color.BLUE);
			circle.setFill(IMGhandler.getPlayer(false));
			if (player.getId() == GameMaster.getPlayerInTurn().getId()) {
				circle.setFill(IMGhandler.getPlayer(true));
			} else {
				circle.setFill(IMGhandler.getPlayer(false));
			}
			Label playerlabel = new Label("Player " + player.getId() + 
										  "\nHighscore " + player.getHighscore() +
										  "\nTime " + player.getMintime());
			StackPane circlestack = new StackPane(circle, playerlabel);

			players.getChildren().add(circlestack);
		}
		players.setAlignment(Pos.CENTER);
		players.setSpacing(10);

		//subject to change: Here the new handling for game avatars has to be implemented
		GameMaster.setIdListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				for (Node n : base.getChildren()) {
					if (n.getClass() == VBox.class && n.getId() == players.getId()) {
						try {
							if (((VBox) n).getChildren().size() > 1 & playerAL.size() > 1) {
								if (oldValue.intValue() <= playerAL.size()) {
									if (oldValue.intValue() == 0) {
										oldValue = 1;
									}
									if (newValue.intValue() == 0) {
										newValue = 1;
									}
									StackPane sp1 = null;
									sp1 = (StackPane) ((VBox) n).getChildren().get(newValue.intValue() - 1);
									Circle c1 = (Circle) sp1.getChildren().get(0);
									c1.setFill(IMGhandler.getPlayer(true));

									StackPane sp2 = null;
									sp2 = (StackPane) ((VBox) n).getChildren().get(oldValue.intValue() - 1);
									Circle c2 = (Circle) sp2.getChildren().get(0);
									c2.setFill(IMGhandler.getPlayer(false));
								} 
							}
						} catch (Exception e) {
							ExceptionHandler exc = new ExceptionHandler(e, "Error", "Listener Error", "Something went wrong with a Change Listener", "Oops");
							exc.showdialog();
						}			
					}
				}

			}
		});
	}
}
