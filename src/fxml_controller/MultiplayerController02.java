package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.ExceptionHandler;
import game.GameMaster;
import image.IMGhandler;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sound.MP3handler;
import start_MEMORY.Start;

/**
 * @author D067928
 * Die Klasse zuständig für den Spielstart im Mehrspieler. Fragt vorher die Namen ab.
 */
public class MultiplayerController02 implements Initializable {
	
	@FXML
	private Button gameStart;
	@FXML
	VBox textFieldArea;
	@FXML
	Text errorTxt;
	
	//setzt vorläufig die Spieleranzahl auf den Gamemode
	static int players = Start.getGamemode();
	
	TextField newField1 = new TextField();
	TextField newField2 = new TextField();
	TextField newField3 = new TextField();
	TextField newField4 = new TextField();
	
	
	Stage popupload;
	FXMLLoader loader = new FXMLLoader();
	@FXML
	private Button singleplayer;
	@FXML
	private AnchorPane AnchorPane;
	
	/**
	 * Dies akzualisiert die Eingabefelder, je nachdem wieviele Spieler momentan involviert sind
	 */
	private void AddTextFields() {	
		newField1.setVisible(false);
		newField2.setVisible(false);
		newField3.setVisible(false);
		newField4.setVisible(false);
		
		if (players >= 2) {
			newField1.setVisible(true);
			newField2.setVisible(true);
		}
		if (players >= 3) {
			newField3.setVisible(true);
		}
		if (players == 4) {
			 newField4.setVisible(true);
		}
	}
	
	/**
	 * Wenn ein Name eingegeben wird, wird Checknames ausgeführt, welche prüft ob die Namen nicht leer sind.
	 */
	@FXML
	private void checkNames (ActionEvent event) {
		//Exception only String?
		boolean canWeStart = false;
		
		if (players >= 2) {
			if (!newField1.getText().replaceAll(" ","").isEmpty() && !newField2.getText().replaceAll(" ","").isEmpty()) {
				canWeStart = true;
				GameMaster.setNames(newField1.getText());
				GameMaster.setNames(newField2.getText());
			}
			else
				canWeStart = false;
		}
		if (players >= 3) {
			if (!newField3.getText().replaceAll(" ","").isEmpty()) {
				GameMaster.setNames(newField3.getText());
			}
			else
				canWeStart = false;
		}
		if (players == 4) {
			if (!newField4.getText().replaceAll(" ","").isEmpty()) {
				GameMaster.setNames(newField4.getText());
			}
			else
				canWeStart = false;
		}
		if (canWeStart == true) {
			gameStart.setDisable(true);
			init_game();
		}
		else
			errorTxt.setVisible(true);
	}
	
	/**
	 * Hier wird das Spiel initialisiert. Das Laden wird in einen 2. Thread ausgelagert um den Ladebalken aktualisierbar
	 * zu gestalten.
	 */
	private void init_game() {
		//Zunächst wird der Ladebalken initialisiert.
		ProgressBar progressBar = new ProgressBar(0);
		progressBar.setPrefSize(400, 40);
		//Es wird ein neuer Service erstellt, welcher dafür sorgt dass JavaFX die Berechnung nicht im UI-Thread übernimmt
		//Ansonsten würde das UI bis zum fertiggeladenen Screen nicht mehr reagieren.
		Service<Void> sv = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				//Dies lädt alle Bilder
				return IMGhandler.initialize(Start.getJhdl().getModel().getInfo().getCardcount());
			}
		};
		//es wird ein Echtzeitupdater initialisiert, der regelmäßig den Fortschritt im Ladebalken aktualisiert und bei fertigem
		//Laden in die Spielszene switched
		AnimationTimer anitim = new AnimationTimer() {
			@Override
			public void handle(long now) {
				progressBar.setProgress(sv.getProgress());
				if (progressBar.getProgress() == 1) {
					this.stop();
					loader.setLocation(getClass().getResource("/fxml/UIGame/UIGame.fxml"));
					try {
						Parent root = loader.load();
						AnchorPane.getScene().setRoot(root);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		//Dies setzt einige Eigenschaften des Popups um den Ladebalken mit Infos an den User weiterzugeben
		if (popupload == null) {
			popupload = new Stage();
			popupload.setWidth(400);
			popupload.setHeight(100);
			popupload.centerOnScreen();
			popupload.initStyle(StageStyle.UTILITY);
			popupload.initModality(Modality.NONE);
			popupload.initOwner(AnchorPane.getScene().getWindow());
			VBox dialogVbox = new VBox(20);
			dialogVbox.getChildren().add(new Text("Loading your game..."));
			dialogVbox.getChildren().add(progressBar);
			dialogVbox.setAlignment(Pos.CENTER);
			Scene dialogScene = new Scene(dialogVbox, 300, 200);
			popupload.setScene(dialogScene);
			anitim.start();
			sv.start();
			sv.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					popupload.hide();
					anitim.stop();
					sv.reset();
				}
			});
			popupload.showAndWait();
		} else {
			anitim.start();
			sv.start();
			sv.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					popupload.hide();
					anitim.stop();
					sv.reset();
				}
			});
			popupload.showAndWait();
		}
	}
	/**
	 * Navigiert zurück zum Hauptmenü
	 */
	@FXML
	private void back (ActionEvent event) {
		loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
		try {
			MP3handler.stopbackground();
			Parent root = loader.load();
			AnchorPane.getScene().setRoot(root);
		} catch (IOException e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "Load Error",
					"Something went wrong loading the next screen", "Oops");
			exc.showdialog();
		}
	}
	
	/**
	 * Setzt die Textfelder zurück
	 */
	public void refresh() {
		textFieldArea.getChildren().clear();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Falls sich der Gamemode ändert werden auch die Textfelder refreshed (andere Spieleranzahl)
		Start.getGamemodeProp().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				players = Start.getGamemode();
				refresh();
			}
		});
		textFieldArea.getChildren().add(newField1);
		textFieldArea.getChildren().add(newField2);
		textFieldArea.getChildren().add(newField3);
		textFieldArea.getChildren().add(newField4);	
		AddTextFields();
	}
}