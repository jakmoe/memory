package fxml_controller;

import java.net.URL;
import java.util.ResourceBundle;

import image.IMGhandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import start_MEMORY.Start;

/**
 * @author D067928
 * Dies ist die SettingsController Klasse um das Einstellungsmenü zu behandeln.
 */
public class SettingsController implements Initializable {

	FXMLLoader loader = new FXMLLoader();

	@FXML
	Slider soundmusic;

	@FXML
	Slider soundeffects;

	@FXML
	Button buttonMenu;

	@FXML
	ToggleGroup difficulty;

	@FXML
	ToggleGroup theme;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Hier werden die Handler gesetzt um das UI mit den gespeicherten Daten zu verbinden und Anzeigen ermöglicht.
		MenuHandler mh = new MenuHandler();
		mh.setBase(buttonMenu);
		buttonMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, mh);

		soundmusic.setValue(Start.getJhdl().getModel().getInfo().getVolume_music() * 100);
		VolumeChangeListener vcl = new VolumeChangeListener();
		vcl.setBg(true);
		soundmusic.valueProperty().addListener(vcl);

		soundeffects.setValue(Start.getJhdl().getModel().getInfo().getVolume_effects() * 100);
		VolumeChangeListener vcl2 = new VolumeChangeListener();
		vcl2.setBg(false);
		soundeffects.valueProperty().addListener(vcl2);

		//Hier wird ermöglicht, dass auf jede Änderung durch einen Listener auch eine Änderung in gespeicherten Daten folgt.
		int theme_id = 1;
		for (Toggle toggle : theme.getToggles()) {
			if (IMGhandler.getTheme() == theme_id) {
				theme.selectToggle(toggle);
			}
			toggle.setUserData(theme_id);
			theme_id++;
		}
		theme.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (theme.getSelectedToggle() != null) {
					IMGhandler.setTheme((int) new_toggle.getUserData());
				}
			}
		});

		int difficulty_id = 1;
		for (Toggle toggle : difficulty.getToggles()) {
			if (Start.getJhdl().getModel().getInfo().getDifficulty() == difficulty_id) {
				difficulty.selectToggle(toggle);
			}
			toggle.setUserData(difficulty_id);
			difficulty_id++;
		}
		difficulty.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (difficulty.getSelectedToggle() != null) {
					Start.getJhdl().getModel().getInfo().setDifficulty((int) new_toggle.getUserData());
				}
			}
		});
	}

}
