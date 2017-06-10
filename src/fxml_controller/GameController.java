package fxml_controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import JSON.PlayerSave;
import game.BoardPane;
import game.ExceptionHandler;
import game.GameEventhandler;
import game.GameMaster;
import game.Player;
import image.IMGhandler;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sound.MP3handler;
import start_MEMORY.Start;

/**
 *
 * @author D067928
 */
public class GameController implements Initializable {

	FXMLLoader loader = new FXMLLoader();

	private Button buttonMenu = new Button("Menu");
	private Label timerlabel = new Label("-");

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

	private static boolean win_ind;

	public static boolean isWin_ind() {
		return win_ind;
	}

	public static void setWin_ind(boolean win_ind) {
		GameController.win_ind = win_ind;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		base.getStylesheets().add("/fxml/UIGame/UIGame.css");
		base.setCache(true);
		base.setCacheHint(CacheHint.SPEED);
		base.setBackground(new Background(new BackgroundImage(IMGhandler.getGameBackground(), null, null, null, null)));
		GameMaster.startGame(Start.getGamemode(), Start.getJhdl().getModel().getInfo().getCardcount());
		initPlayers();
		initMenu();
		initTimer();

		setupTimer();

		// Debug here needs to check if background is even running
		MP3handler.stopbackground();
		MP3handler.playbackground(2);

	}

	private void initTimer() {
		timerlabel.setScaleX(4);
		timerlabel.setScaleY(4);
		timerlabel.setLayoutX(base.getPrefWidth() / 2);
		timerlabel.setLayoutY(40);
		base.getChildren().add(timerlabel);
	}

	private void setupTimer() {
		AnimationTimer anitim = new AnimationTimer() {
			ImageView winbaseimg = null;
			Pane winbase = null;

			@Override
			public void handle(long now) {
				if (win_ind && winbase == null) {
					winbaseimg = new ImageView(IMGhandler.getWinScreen());
					winbase = new Pane(winbaseimg);
					winbase.setPrefHeight(base.getPrefHeight());
					winbase.setPrefWidth(base.getPrefWidth());
					winbaseimg.setPreserveRatio(true);
					winbaseimg.setOpacity(0);
					FadeTransition fadein = new FadeTransition(Duration.seconds(0.4), winbaseimg);
					fadein.setToValue(1);
					
					winbaseimg.toFront();

					ArrayList<PlayerSave> highscoreAL = Start.getJhdl().getModel().getPlayers();
					VBox highscores = new VBox();
					for (PlayerSave playerSave : highscoreAL) {
						Button player = new Button(playerSave.getName() + "-" +
												 playerSave.getHighscore() + "-" +
												 playerSave.getMintime());
						highscores.getChildren().add(player);
					}
					winbase.getChildren().add(highscores);
					highscores.toFront();
					
					base.getChildren().add(winbase);
					win_ind = false;
					fadein.play();
				}
				// debug time
				timerlabel.setText(Double.toString(Math.round(GameEventhandler.getTimer().getCurrent() * 10.0) / 10.0));
			}
		};
		anitim.start();
	}

	public void initMenu() {
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
		buttonMenu.setGraphic(new ImageView(IMGhandler.getArrow(1, 60, 60, true)));
		buttonMenu.setText("");
		base.getChildren().add(buttonMenu);

		Button buttonExit = new Button("Exit");
		buttonExit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Exit");
				alert.setHeaderText("Do you really want to exit the program?");
				alert.initStyle(StageStyle.TRANSPARENT);
				alert.initOwner(base.getScene().getWindow());
				alert.initModality(Modality.WINDOW_MODAL);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					System.exit(0);
				}
			}
		});
		menu.getChildren().add(buttonExit);

		menu.setAlignment(Pos.CENTER);

		// menu.setStyle("-fx-border-color: Blue");
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
			Label playerlabel = new Label("Player " + player.getId() + "\nHighscore " + player.getHighscore()
					+ "\nTime " + player.getMintime());
			StackPane circlestack = new StackPane(circle, playerlabel);

			players.getChildren().add(circlestack);
		}
		players.setAlignment(Pos.CENTER);
		players.setSpacing(10);

		// subject to change: Here the new handling for game avatars has to be
		// implemented
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
							ExceptionHandler exc = new ExceptionHandler(e, "Error", "Listener Error",
									"Something went wrong with a Change Listener", "Oops");
							exc.showdialog();
						}
					}
				}

			}
		});
	}
}
