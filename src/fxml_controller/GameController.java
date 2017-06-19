package fxml_controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import game.BoardPane;
import game.ExceptionHandler;
import game.GameEventhandler;
import game.GameMaster;
import game.Player;
import game.PlayerCircle;
import game.WinStack;
import image.IMGhandler;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sound.MP3handler;
import start_MEMORY.Start;

/**
 * test
 * @author D067928
 */
public class GameController implements Initializable {

	FXMLLoader loader = new FXMLLoader();

	private Button buttonExit = new Button("Exit");
	private Button buttonMenu = new Button("Menu");
	private Label timerlabel = new Label("-");

	@FXML
	private WinStack winstack;

	@FXML
	private Button rematch, remenu;

	@FXML
	private Pane winbase, base, info;

	@FXML
	private VBox players;

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

	public void rematch() {
		initialize(null, null);
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
		initSettings();
		setupTimer();

		// Debug here needs to check if background is even running
		MP3handler.stopbackground();
		MP3handler.playbackground(2);

	}

	private void initSettings() {
		Slider fx = new Slider(0, 100, Start.getJhdl().getModel().getInfo().getVolume_effects());
		Slider bg = new Slider(0, 100, Start.getJhdl().getModel().getInfo().getVolume_music());

		HBox volfx = new HBox(10, fx, new Label("Effects"));
		HBox volbg = new HBox(10, bg, new Label("Background"));

		CustomMenuItem volumefx = new CustomMenuItem(volfx);
		CustomMenuItem volumebg = new CustomMenuItem(volbg);

		volumefx.setHideOnClick(false);
		volumebg.setHideOnClick(false);

		MenuButton mb = new MenuButton();
		mb.getItems().add(volumebg);
		mb.getItems().add(volumefx);
		mb.getItems().add(new CustomMenuItem(buttonMenu, false));
		mb.getItems().add(new CustomMenuItem(buttonExit, false));
		mb.setLayoutX(1660);
		mb.setLayoutY(0);
		mb.setGraphic(new ImageView(IMGhandler.getSettings()));
		mb.setBackground(Background.EMPTY);

		VolumeChangeListener vcl = new VolumeChangeListener();
		bg.setValue(Start.getJhdl().getModel().getInfo().getVolume_music() * 100);
		vcl.setBg(true);
		bg.valueProperty().addListener(vcl);

		VolumeChangeListener vcl2 = new VolumeChangeListener();
		vcl2.setBg(false);
		fx.setValue(Start.getJhdl().getModel().getInfo().getVolume_effects() * 100);
		fx.valueProperty().addListener(vcl2);

		base.getChildren().add(mb);
	}

	private void initTimer() {
		timerlabel.setScaleX(3.5);
		timerlabel.setScaleY(3.5);
		timerlabel.setLayoutX(1690);
		timerlabel.setLayoutY(1040);
		base.getChildren().add(timerlabel);
	}

	private void setupTimer() {
		AnimationTimer anitim = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (win_ind && winbase.isDisabled()) {
					ImageView winbaseimg = new ImageView(IMGhandler.getWinScreen());
					winbaseimg.setPreserveRatio(true);
					winbase.getChildren().add(winbaseimg);
					winbaseimg.toBack();
					winstack.initialize(GameMaster.getPlayers());
					win_ind = false;
					winbase.setOpacity(0);
					FadeTransition fadein = new FadeTransition(Duration.seconds(0.4), winbase);
					fadein.setToValue(1);
					fadein.play();
					winbase.setDisable(false);
				}
				// debug time
				timerlabel.setText(Double.toString(Math.round(GameEventhandler.getTimer().getCurrent() * 10.0) / 10.0));
			}
		};
		anitim.start();
	}

	public void initMenu() {
		MenuHandler mh = new MenuHandler();
		mh.setBase(base);
		buttonMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, mh);
		buttonMenu.setText("Menu");

		ExitHandler ex = new ExitHandler();
		ex.setBase(base);
		buttonExit.addEventHandler(MouseEvent.MOUSE_CLICKED, ex);
		menu.setAlignment(Pos.CENTER);
	}

	public void initPlayers() {
		ArrayList<Player> playerAL = GameMaster.getPlayers();
		for (Player player : playerAL) {
			PlayerCircle pc = new PlayerCircle(player);
			players.getChildren().add(pc);
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
									PlayerCircle sp1 = null;
									sp1 = (PlayerCircle) ((VBox) n).getChildren().get(newValue.intValue() - 1);
									sp1.update();

									PlayerCircle sp2 = null;
									sp2 = (PlayerCircle) ((VBox) n).getChildren().get(oldValue.intValue() - 1);
									sp2.update();
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
