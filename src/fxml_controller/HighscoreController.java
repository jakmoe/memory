package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import JSON.PlayerSave;
import game.ExceptionHandler;
import game.HighscoreModel;
import image.IMGhandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import sound.MP3handler;
import start_MEMORY.Start;

public class HighscoreController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	@FXML
	private TableView<HighscoreModel> veryeasy;
	@FXML
	private TableView<HighscoreModel> easy;
	@FXML
	private TableView<HighscoreModel> medium;
	@FXML
	private TableView<HighscoreModel> hard;
	@FXML
	private TableView<HighscoreModel> veryhard;

	@FXML
	ToggleGroup view;

	@FXML
	ToggleButton all;
	@FXML
	ToggleButton sp;
	@FXML
	ToggleButton mp;

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
			ObservableList<HighscoreModel> list = veryeasy.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(1), list);

			ObservableList<HighscoreModel> list2 = easy.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(2), list2);

			ObservableList<HighscoreModel> list3 = medium.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(3), list3);

			ObservableList<HighscoreModel> list4 = hard.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(4), list4);

			ObservableList<HighscoreModel> list5 = veryhard.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(5), list5);

			view.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					try {
						if (view.getSelectedToggle() == all) {
							setView(0);
						} else if (view.getSelectedToggle() == sp) {
							setView(1);
						} else if (view.getSelectedToggle() == mp) {
							setView(2);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				private void setView(int i) throws Exception {
					switch (i) {
					case 0:
						break;
					case 1:
						break;
					case 2:
						break;
					default:
						break;
					}
				}
			});

		} catch (Exception e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "HighscoreController Error",
					"Something went wrong with reading out the List to the User Interface", "Oops");
			exc.showdialog();
		}
	}

	private void createModel(ArrayList<PlayerSave> players, ObservableList<HighscoreModel> data) {
		for (PlayerSave playerSave : players) {
			data.add(new HighscoreModel(playerSave.getName(), playerSave.getMintime(), playerSave.getHighscore(),
					playerSave.getAttempts(), playerSave.isSingleplayer()));
		}
	}

}
