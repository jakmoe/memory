package fxml_controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import sound.MP3handler;
import start_MEMORY.Start;

/**
 * @author D067928
 *	Ermöglicht das Verbinden der Lautstärke mit den UI-Slidern
 */
public class VolumeChangeListener implements ChangeListener<Number> {
	boolean bg;

	/**
	 * @return bg - Ist es Hintergrundmusik? Wenn nein ist es Effektlautstärke
	 */
	public boolean isBg() {
		return bg;
	}

	/**
	 * @param bg - Ist es Hintergrundmusik? Wenn nein ist es Effektlautstärke
	 */
	public void setBg(boolean bg) {
		this.bg = bg;
	}

	/* (non-Javadoc)
	 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		//setzt die Lautstärke entsprechend auf den neu geänderten Wert.
		double volume = newValue.doubleValue() / 100; // Werte werden anders gelesen als gespeichert.
		if (bg) {
			MP3handler.setVolumebg(volume);
			Start.getJhdl().getModel().getInfo().setVolume_effects(volume);
		} else {
			MP3handler.setVolumefx(volume);
			Start.getJhdl().getModel().getInfo().setVolume_music(volume);
		}
	}

}
