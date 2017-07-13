package sound;

import java.net.URISyntaxException;
import java.net.URL;

import game.ExceptionHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author D067928
 * Der Soundhandler, welcher dafür verwendet wird die Hintergrundmusik und die Geräusche beim klicken auf eine Karte abzuspielen.
 */
public class MP3handler {
	private static MediaPlayer soundPlayer;
	private static MediaPlayer backgroundPlayer;

	//für beide Werte gilt: 0.0 ist 0% Laustärke, 1.0 ist 100%
	private static double volumebg = 0.5;
	private static double volumefx = 0.5;

	/**
	 * Spielt entweder einen leeren Sound ab (debugging) oder den Klicksound für eine Karte ( soundID = 1)
	 * @param soundID
	 */
	public static void play(int SoundID) {
		String musicFile = "";
		switch (SoundID) {
		case 1:
			musicFile += "01_button";
			break;
		default:
			musicFile += "00_idle";
			break;
		}
		musicFile += ".mp3";
		URL url = MP3handler.class.getResource(musicFile);
		//erzeugt ein Sound Medium aus der URL und setzt das Volume auf volumefx. Spielt es dann ab
		Media sound = new Media(url.toString());
		soundPlayer = new MediaPlayer(sound);
		soundPlayer.setVolume(volumefx);
		soundPlayer.play();
	}

	public static void playbackground(int SoundID) {
		String musicFile = "";
		switch (SoundID) {
		default:
			musicFile += "00_idle";
			break;
		case 1:
			musicFile += "bensound-clearday";
			break;
		case 2:
			musicFile += "bensound-funkyelement";
			break;
		}
		musicFile += ".mp3";
		URL url = MP3handler.class.getResource(musicFile);
		Media sound = null;
		try {
			sound = new Media(url.toURI().toString());
		} catch (URISyntaxException e) {
			ExceptionHandler exc = new ExceptionHandler(e, "Error", "Sound Error",
					"Something went wrong with the Sounds", "Oops");
			exc.showdialog();
		}
		backgroundPlayer = new MediaPlayer(sound);
		//erzeugt ein Sound Medium aus der URL und setzt das Volume auf volumebg. Spielt es dann ab
		backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		backgroundPlayer.setVolume(volumebg);
		backgroundPlayer.play();
	}

	/**
	 * Stoppt die Effektmusik eines Buttons
	 */
	public static void stop() {
		soundPlayer.stop();
	}

	/**
	 * Stoppt die Hintergrundmusik
	 */
	public static void stopbackground() {
		backgroundPlayer.stop();
	}

	/**
	 * @return volumebg - gibt die Hintergrundlautstärke zurück
	 */
	public static double getVolumebg() {
		return volumebg;
	}

	/**
	 * @param volumebg - setzt die Lautstärke der Hintergrundmusik
	 */
	public static void setVolumebg(double volumebg) {
		MP3handler.volumebg = volumebg;
		if (!(backgroundPlayer == null)) {
			backgroundPlayer.setVolume(volumebg);
		}
	}

	/**
	 * @return volumefx - gibt die Effektlautstärke zurück
	 */
	public static double getVolumefx() {
		return volumefx;
	}

	/**
	 * @param volumefx - setzt die Lautstärke der Effekte
	 */
	public static void setVolumefx(double volumefx) {
		MP3handler.volumefx = volumefx;
		if (!(soundPlayer == null)) {
			soundPlayer.setVolume(volumefx);
		}
	}
}
