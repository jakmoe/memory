package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import image.IMGhandler;
import javafx.animation.AnimationTimer;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sound.MP3handler;
import start_MEMORY.Start;

public class MenuController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	Stage popupload;

	Alert multichoice;

	@FXML
	private AnchorPane anchor;
	@FXML
	private Button singleplayer;
	@FXML
	private Button multiplayer;
	@FXML
	private Button settings;
	@FXML
	private Button end;

	@FXML
	private void endprogram(ActionEvent event) {
		Start.getJhdl().commit();
		System.exit(0);
	}

	@FXML
	private void gamesettings(ActionEvent event) {
		loader.setLocation(getClass().getResource("/FXML/Settings/Settings.fxml"));
		try {
			Parent root = loader.load();
			singleplayer.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void gamesingleplayer(ActionEvent event) {
		Start.setGamemode(1);
		init_game();
	}

	@FXML
	private void gamemultiplayer(ActionEvent event) {
		if (multichoice == null) {
			multichoice = new Alert(AlertType.CONFIRMATION);
			multichoice.initStyle(StageStyle.UTILITY);
			multichoice.initModality(Modality.NONE);
			multichoice.initOwner(anchor.getScene().getWindow());
			multichoice.setTitle("Playerchoice");
			multichoice.setHeaderText("How many Players?");
			multichoice.setContentText("Please choose.");

			ButtonType buttonTypeTwo = new ButtonType("2");
			ButtonType buttonTypeThree = new ButtonType("3");
			ButtonType buttonTypeFour = new ButtonType("4");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			multichoice.getButtonTypes().setAll(buttonTypeTwo, buttonTypeThree, buttonTypeFour, buttonTypeCancel);

			Optional<ButtonType> result = multichoice.showAndWait();
			if (result.get() == buttonTypeTwo) {
				Start.setGamemode(2);
			} else if (result.get() == buttonTypeThree) {
				Start.setGamemode(3);
			} else if (result.get() == buttonTypeFour) {
				Start.setGamemode(4);
			} else {
				// ... user chose CANCEL or closed the dialog
				multichoice.close();
			}
		} else {
			multichoice.showAndWait();
		}

		init_game();
	}

	private void init_game() {

		ProgressBar progressBar = new ProgressBar(0);
		progressBar.setPrefSize(400, 40);
		Service<Void> sv = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return IMGhandler.initialize(Start.getJhdl().getModel().getInfo().getCardcount());
			}
		};
		AnimationTimer anitim = new AnimationTimer() {
			@Override
			public void handle(long now) {
				progressBar.setProgress(sv.getProgress());
				if (progressBar.getProgress() == 1) {
					this.stop();
					loader.setLocation(getClass().getResource("/FXML/UIGame/UIGame.fxml"));
					try {
						Parent root = loader.load();
						singleplayer.getScene().setRoot(root);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		if (popupload == null) {
			popupload = new Stage();
			popupload.setWidth(400);
			popupload.setHeight(100);
			popupload.centerOnScreen();
			popupload.initStyle(StageStyle.UTILITY);
			popupload.initModality(Modality.NONE);
			popupload.initOwner(anchor.getScene().getWindow());
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MP3handler.playbackground(1);
	}

}
