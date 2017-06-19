package fxml_controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import sound.MP3handler;
import start_MEMORY.Start;

public class VolumeChangeListener implements ChangeListener<Number> {
	boolean bg;
	
	
	public boolean isBg() {
		return bg;
	}


	public void setBg(boolean bg) {
		this.bg = bg;
	}


	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		double volume = newValue.doubleValue() / 100;
		if (bg) {
			MP3handler.setVolumefx(volume);
			Start.getJhdl().getModel().getInfo().setVolume_effects(volume);
		} else {
			MP3handler.setVolumebg(volume);
			Start.getJhdl().getModel().getInfo().setVolume_music(volume);
		}
	}

}
