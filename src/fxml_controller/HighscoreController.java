package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import JSON.PlayerSave;
import game.ExceptionHandler;
import game.HighscoreModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import sound.MP3handler;
import start_MEMORY.Start;

/**
 * @author D067928
 *	Dies ist die Controllerklasse für die Highscoretabelle.
 */
public class HighscoreController implements Initializable {
	FXMLLoader loader = new FXMLLoader();

	//5 TableView Elemente für jede Schwierigkeit
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

	//eine ToggleGroup um zwischen Ansichten zu wechseln
	@FXML
	ToggleGroup view;

	// ToggleButton um zu Selektieren ob man alle, nur Einzelspieler oder nur Mehrspieler Statistiken einsehen will.
	@FXML
	ToggleButton all;
	@FXML
	ToggleButton sp;
	@FXML
	ToggleButton mp;

	//Der Zurückbutton fürs Menü
	@FXML
	private Button back;
	
	/**
	 * Ermöglicht die Rücknavigation ins Hauptmenü
	 */
	@FXML
	private void backaction() {
		MenuHandler menhan = new MenuHandler();
		menhan.setBase(back);
	}

	/**
	 * Setzt das Model zurück, falls man alle Savegames zurücksetzen möchte
	 */
	@FXML
	private void resetaction() {
		Start.getJhdl().getModel().resetModel();
		Start.getJhdl().commit();
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			//spielt Musik ab
			MP3handler.stopbackground();
			MP3handler.playbackground(1);
			
			//Erstellt ein Model für jede Schwierigkeit
			ObservableList<HighscoreModel> list = veryeasy.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(1), list, 0);

			ObservableList<HighscoreModel> list2 = easy.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(2), list2, 0);

			ObservableList<HighscoreModel> list3 = medium.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(3), list3, 0);

			ObservableList<HighscoreModel> list4 = hard.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(4), list4, 0);

			ObservableList<HighscoreModel> list5 = veryhard.getItems();
			createModel(Start.getJhdl().getModel().getPlayers(5), list5, 0);

			//bei einer Änderung in der view, also wenn neue Daten geladen werden muss das Modell neu erstellt
			//werden. Dies ist nur mit dem UI gekoppelt, damit keine Performanceprobleme entstehen.
			view.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					try {
						if (view.getSelectedToggle() == all) {
							createModel(Start.getJhdl().getModel().getPlayers(1), list, 0);
							createModel(Start.getJhdl().getModel().getPlayers(2), list2, 0);
							createModel(Start.getJhdl().getModel().getPlayers(3), list3, 0);
							createModel(Start.getJhdl().getModel().getPlayers(4), list4, 0);
							createModel(Start.getJhdl().getModel().getPlayers(5), list5, 0);
						} else if (view.getSelectedToggle() == sp) {
							createModel(Start.getJhdl().getModel().getPlayers(1), list, 1);
							createModel(Start.getJhdl().getModel().getPlayers(2), list2, 1);
							createModel(Start.getJhdl().getModel().getPlayers(3), list3, 1);
							createModel(Start.getJhdl().getModel().getPlayers(4), list4, 1);
							createModel(Start.getJhdl().getModel().getPlayers(5), list5, 1);
						} else if (view.getSelectedToggle() == mp) {
							createModel(Start.getJhdl().getModel().getPlayers(1), list, 2);
							createModel(Start.getJhdl().getModel().getPlayers(2), list2, 2);
							createModel(Start.getJhdl().getModel().getPlayers(3), list3, 2);
							createModel(Start.getJhdl().getModel().getPlayers(4), list4, 2);
							createModel(Start.getJhdl().getModel().getPlayers(5), list5, 2);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "HighscoreController Error",
					"Something went wrong with reading out the List to the User Interface", "Oops");
			exc.showdialog();
		}
	}

	//Je nach view werden hier die Savegames ausgelesen und in die ObservableList (also das Sichtbare) geladen
	private void createModel(ArrayList<PlayerSave> players, ObservableList<HighscoreModel> data, int view) {
		data.clear();
		for (PlayerSave playerSave : players) {
			if (view == 0) { //alle
				data.add(new HighscoreModel(playerSave.getName(), playerSave.getMintime(), playerSave.getHighscore(),
						playerSave.getAttempts(), playerSave.isSingleplayer()));				
			} else if (view == 1) { //nur Einzelspieler
				if (playerSave.isSingleplayer() == true) {
					data.add(new HighscoreModel(playerSave.getName(), playerSave.getMintime(), playerSave.getHighscore(),
							playerSave.getAttempts(), playerSave.isSingleplayer()));
				}				
			} else if (view == 2) { //nur Mehrspieller
				if (playerSave.isSingleplayer() == false) {
					data.add(new HighscoreModel(playerSave.getName(), playerSave.getMintime(), playerSave.getHighscore(),
							playerSave.getAttempts(), playerSave.isSingleplayer()));
				}	
			}

		}
	}

}
