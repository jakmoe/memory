package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import game.GameEventhandler;
import game.GameMaster;
import image.IMGhandler;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
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
import start_MEMORY.Start;

public class MultiplayerController02 implements Initializable {
	
	@FXML
	VBox textFieldArea;
	@FXML
	Text errorTxt;
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
	
	private void AddTextFields() {	

		if (players >= 2) {
			textFieldArea.getChildren().add(newField1);
			textFieldArea.getChildren().add(newField2);
		}
		if (players >= 3) {
			textFieldArea.getChildren().add(newField3);
		}
		if (players == 4) {
			 textFieldArea.getChildren().add(newField4);
		}
	}
	
	@FXML
	private void checkNames (ActionEvent event) {
		//Exception only String?
		boolean canWeStart = false;
		if (players >= 2) {
			if (!newField1.getText().isEmpty() && !newField2.getText().isEmpty()) {
				canWeStart = true;
				GameMaster.setNames(newField1.getText());
				GameMaster.setNames(newField2.getText());
			}
			else
				canWeStart = false;
		}
		if (players >= 3) {
			if (!newField3.getText().isEmpty()) {
				canWeStart = true;
				GameMaster.setNames(newField3.getText());
			}
			else
				canWeStart = false;
		}
		if (players == 4) {
			if (!newField4.getText().isEmpty()) {
				canWeStart = true;
				GameMaster.setNames(newField4.getText());
			}
			else
				canWeStart = false;
		}
		if (canWeStart == true) { 
			//call method init_game()
			init_game();
			canWeStart = true;
		}
		else
			errorTxt.setVisible(true);
	}
	//Copied from MenuController - doesn't work yet
	private void init_game() {
		GameEventhandler.getTimer().stop();
		GameEventhandler.getTimer().reset();
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

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			AddTextFields();
	}
}