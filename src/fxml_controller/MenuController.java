package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import image.IMGhandler;
import javafx.animation.AnimationTimer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;

public class MenuController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	Popup popupload;
	
	@FXML
	private AnchorPane anchor;
	@FXML
	private Button singleplayer;
	@FXML
	private Button settings;
	@FXML
	private Button end;

	@FXML
	private void gamesingleplayer(ActionEvent event) {
		
		ProgressBar progressBar = new ProgressBar(0);
		Service<Void> sv = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return IMGhandler.initialize(16);
			}
		};
		sv.addEventHandler(ActionEvent.ANY, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				
			}
		});
		AnimationTimer anitim = new AnimationTimer() {
			@Override
			public void handle(long now) {
					progressBar.setProgress(sv.getProgress());
					if (progressBar.getProgress() == 1) {
						this.stop();
						loader.setLocation(getClass().getResource("/FXML/Game.fxml"));
						try {
							Parent root = loader.load();
							singleplayer.getScene().setRoot(root);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				//progbar.setProgress(1);
			}
		};
		if (popupload == null) {
			popupload = new Popup();
		}
		popupload.getContent().add(progressBar);
		popupload.show(anchor, anchor.getWidth()/2 - progressBar.getWidth(), anchor.getHeight()/2 - progressBar.getHeight());
		anitim.start();
		sv.start();
		sv.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				popupload.hide();
				popupload.getContent().clear();
			}
		});
		

	}

	@FXML
	private void gamesettings(ActionEvent event) {
		loader.setLocation(getClass().getResource("/FXML/Settings.fxml"));
		try {
			Parent root = loader.load();
			singleplayer.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void endprogram(ActionEvent event) {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
