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
 * Der GameController ist die Schnittstelle vom User Interface zum Spiel. Es beinhält die Elemente des UI's
 * die zum Spielen benötigt werden und über diesen Screen wird das Spiel gespielt
 * 
 * @author D067928
 */
public class GameController implements Initializable {

	FXMLLoader loader = new FXMLLoader();

	private Button buttonExit = new Button("Exit");
	private Button buttonMenu = new Button("Menu");
	private Label timerlabel = new Label("-");

	@FXML
	private WinStack winstack; //Der winstack ist für die Anzeige beim Gewinnen vom Spiel zuständig

	@FXML
	private Button rematch, remenu;

	@FXML
	private Pane winbase, base, info; // Panes um die Elemente des UI's zu halten bzw. zu managen

	@FXML
	private VBox players; //Die Spieler

	@FXML
	private HBox menu; //Das Dropdown Menü

	@FXML
	private BoardPane gamepane; //Das Spielbrett

	private ArrayList<PlayerCircle> circleList = new ArrayList<PlayerCircle>();  //Die ArrayList von PlayerCircles

	private static boolean win_ind;

	/**
	 * @return win_ind - Prüfung ob die Wincondition erfüllt wurde
	 */
	public static boolean isWin_ind() {
		return win_ind; 
	}

	/**
	 * @param win_ind - Check der Wincondition. wird jedes Frame abgerufen und geprüft, called den WinStack
	 */
	public static void setWin_ind(boolean win_ind) {
		GameController.win_ind = win_ind;
	}

	public void rematch() {
		initialize(null, null);
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		base.getStylesheets().add("/fxml/UIGame/UIGame.css"); //Setzt das Stylesheet
		base.setCache(true); //cache Optionen
		base.setCacheHint(CacheHint.SPEED);
		//setzt den Spielhintergrund
		base.setBackground(new Background(new BackgroundImage(IMGhandler.getGameBackground(), null, null, null, null)));
		//started das Spiel
		GameMaster.startGame(Start.getGamemode(), Start.getJhdl().getModel().getInfo().getCardcount());
		//erstellt die Spieler
		initPlayers();
		//erstellt das DropDownMenü
		initMenu();
		//erstellt den Timer, der jedes Frame gerufen wird
		initTimer();
		//erstellt die Settings für das DropDownMenü
		initSettings();
		//setzt den Timer entsprechend auf
		setupTimer();

		// Hier wird Musik gestartet
		MP3handler.stopbackground();
		MP3handler.playbackground(2);

	}

	/**
	 * Erstellt Slider die wie in den Menüsettings an Hintergrund und Effektlautstärke gebunden sind
	 */
	private void initSettings() {
		//erstellt 2 neue Slider
		Slider fx = new Slider(0, 100, Start.getJhdl().getModel().getInfo().getVolume_effects());
		Slider bg = new Slider(0, 100, Start.getJhdl().getModel().getInfo().getVolume_music());
		//erstellt Label zum beschriften
		HBox volfx = new HBox(10, fx, new Label("Effects"));
		HBox volbg = new HBox(10, bg, new Label("Background"));
		//Erstellt CustomMenuItems um die Slider ins DropDown zu packen
		CustomMenuItem volumefx = new CustomMenuItem(volfx);
		CustomMenuItem volumebg = new CustomMenuItem(volbg);
		//das Menü verschwindet nicht bei Interaktion
		volumefx.setHideOnClick(false);
		volumebg.setHideOnClick(false);

		//schreibt die Items in einen Menubutton
		MenuButton mb = new MenuButton();
		mb.getItems().add(volumebg);
		mb.getItems().add(volumefx);
		mb.getItems().add(new CustomMenuItem(buttonMenu, false));
		mb.getItems().add(new CustomMenuItem(buttonExit, false));
		mb.setLayoutX(1660);
		mb.setLayoutY(0);
		mb.setGraphic(new ImageView(IMGhandler.getSettings()));
		mb.setBackground(Background.EMPTY);

		//setzt 2 ChangeListener um die Lautstärke zu kontrollieren
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

	/**
	 * Setzt das Timerlabel, eine Anzeige wie viel Zeit der Spieler gerade in seiner RUnde verbringt
	 */
	private void initTimer() {
		// timer to count the time you spent on your turn
		timerlabel.setStyle("-fx-text-fill: white");
		timerlabel.setScaleX(3.5);
		timerlabel.setScaleY(3.5);
		timerlabel.setLayoutX(1690);
		timerlabel.setLayoutY(1040);
		base.getChildren().add(timerlabel);
	}

	/**
	 * Setzt den Framecheck auf. Jedes Frame wird darin die Methode handle(long now) abgerufen. Es ermöglicht
	 * "Echtzeit"Updates auf dem UI
	 */
	private void setupTimer() {
		AnimationTimer anitim = new AnimationTimer() {
			@Override
			public void handle(long now) {
				//Falls der win_ind gesetzt ist wird der WinScreen eingeblendet. Das Spiel ist dann vorbei
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
				//Hier wird für alle Inhalte in circleList eine Updatemethode gerufen um die Timer zu refreshen.
				for (int i = 0; i < circleList.size(); i++) {
					GameMaster.getPlayerInTurn().setMintime(GameMaster.getPlayerInTurn().getSavedtime() + 
															GameMaster.getPlayerInTurn().getTimer().getCurrent());
					circleList.get(i).updateLabel();
				}
				timerlabel.setText(Double
						.toString(Math.round(GameMaster.getPlayerInTurn().getTimer().getCurrent() * 10.0) / 10.0));
			}
		};
		//startet den Framecheck. Ab hier läuft jedes Frame die Methode
		anitim.start();
	}

	/**
	 * Gibt die 2 Button Interaktionen für den Menübutton und den Exitbutton im Dropdownmenü
	 */
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

	/**
	 * Initialisiert die Spieler und gibt ihnen einen ChangeListener mit, der ein Update des Hintergrundes,
	 * also leuchtend oder nicht leuchtend, ermöglicht.
	 */
	public void initPlayers() {
		// Es werden für alle Spieler PlayerCircle als UI Repräsentation erstellt und der circleList angehängt
		ArrayList<Player> playerAL = GameMaster.getPlayers();
		for (Player player : playerAL) {
			PlayerCircle pc = new PlayerCircle(player);
			circleList.add(pc);
		}
		players.getChildren().addAll(circleList);
		players.setAlignment(Pos.CENTER);
		players.setSpacing(10);

		// Hier wird der Check implementiert. Sobald ein anderer Spieler an der Reihe ist, wird changed() aufgerufen.
		GameMaster.setIdListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//Es muss durch alle Nodes durchgegangen werden, weil keine Pufferung existiert
				for (Node n : base.getChildren()) {
					//Es wird geprüft ob das Objekt eine VBox ist und dem entsprechenden player-Objekt entspricht
					if (n.getClass() == VBox.class && n.getId() == players.getId()) {
						try {
							//Falls der Spieler der Letzte ist wird der Erste wieder an die Reihe gesetzt
							//Ansonsten kommt immer der nächste Spieler dran
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