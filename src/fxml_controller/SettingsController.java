package fxml_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import sound.MP3handler;
import start_MEMORY.Start;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				loader.setLocation(getClass().getResource("/fxml/MainMenu/Menu.fxml"));
				try {
					Start.getJhdl().commit();
					MP3handler.stopbackground();
					Parent root = loader.load();
					buttonMenu.getScene().setRoot(root);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		soundmusic.setValue(Start.getJhdl().getModel().getInfo().getVolume_music() * 100);
		soundmusic.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				double volume = newValue.doubleValue() / 100;
				MP3handler.setVolumebg(volume);
				Start.getJhdl().getModel().getInfo().setVolume_music(volume);
			}
		});

		soundeffects.setValue(Start.getJhdl().getModel().getInfo().getVolume_effects() * 100);
		soundeffects.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				double volume = newValue.doubleValue() / 100;
				MP3handler.setVolumefx(volume);
				Start.getJhdl().getModel().getInfo().setVolume_effects(volume);
			}
		});

		int id = 1;
		for (Toggle toggle : difficulty.getToggles()) {
			if (Start.getJhdl().getModel().getInfo().getDifficulty() == id) {
				difficulty.selectToggle(toggle);
			}
			toggle.setUserData(id);
			id++;
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
