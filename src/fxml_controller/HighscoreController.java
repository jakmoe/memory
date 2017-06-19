package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import JSON.PlayerSave;
import game.ExceptionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import sound.MP3handler;
import start_MEMORY.Start;

public class HighscoreController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	@FXML
	private VBox veryeasy;
	@FXML
	private VBox easy;
	@FXML
	private VBox medium;
	@FXML
	private VBox hard;
	@FXML
	private VBox veryhard;

	@FXML
	private Button back;

	@FXML
	private void backaction() {
		loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
		try {
			MP3handler.stopbackground();
			Parent root = loader.load();
			back.getScene().setRoot(root);
		} catch (IOException e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "Load Error",
					"Something went wrong loading the next screen", "Oops");
			exc.showdialog();
		}
	}

	@FXML
	private void resetaction() {
		Start.getJhdl().getModel().resetModel();
		Start.getJhdl().commit();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			MP3handler.playbackground(1);
			ObservableList<PlayerSave> list = FXCollections.observableArrayList(Start.getJhdl().getModel().getPlayers(1));
			ListView<PlayerSave> tw = new ListView<PlayerSave>(list);
			veryeasy.getChildren().add(tw);
			veryeasy.setMinHeight(700);
			veryeasy.setAlignment(Pos.TOP_CENTER);
			tw.setMinHeight(700);

			ObservableList<PlayerSave> list2 = FXCollections.observableArrayList(Start.getJhdl().getModel().getPlayers(2));
			ListView<PlayerSave> tw2 = new ListView<PlayerSave>(list2);
			easy.getChildren().add(tw2);
			easy.setMinHeight(700);
			easy.setAlignment(Pos.TOP_CENTER);
			tw2.setMinHeight(700);

			ObservableList<PlayerSave> list3 = FXCollections.observableArrayList(Start.getJhdl().getModel().getPlayers(3));
			ListView<PlayerSave> tw3 = new ListView<PlayerSave>(list3);
			medium.getChildren().add(tw3);
			medium.setMinHeight(700);
			medium.setAlignment(Pos.TOP_CENTER);
			tw3.setMinHeight(700);

			ObservableList<PlayerSave> list4 = FXCollections.observableArrayList(Start.getJhdl().getModel().getPlayers(4));
			ListView<PlayerSave> tw4 = new ListView<PlayerSave>(list4);
			hard.getChildren().add(tw4);
			hard.setMinHeight(700);
			hard.setAlignment(Pos.TOP_CENTER);
			tw4.setMinHeight(700);

			ObservableList<PlayerSave> list5 = FXCollections.observableArrayList(Start.getJhdl().getModel().getPlayers(5));
			ListView<PlayerSave> tw5 = new ListView<PlayerSave>(list5);
			veryhard.getChildren().add(tw5);
			veryhard.setMinHeight(700);
			veryhard.setAlignment(Pos.TOP_CENTER);
			tw5.setMinHeight(700);
		} catch (Exception e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "HighscoreController Error",
					"Something went wrong with reading out the List to the User Interface", "Oops");
			exc.showdialog();
		}
	}

}
