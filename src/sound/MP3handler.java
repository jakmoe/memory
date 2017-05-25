package sound;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MP3handler {
	private static MediaPlayer soundPlayer;
	private static MediaPlayer backgroundPlayer;
	public static void play(int SoundID) {
		String musicFile = "src\\sound\\";
		switch (SoundID) {
		case 1:
			musicFile += "01_button";
			break;
		default:
			musicFile += "00_idle";
			break;
		}
		musicFile += ".mp3";

		Media sound = new Media(new File(musicFile).toURI().toString());
		soundPlayer = new MediaPlayer(sound);
		soundPlayer.play();
	}
	
	public static void playbackground(int SoundID) {
		String musicFile = "src\\sound\\";
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
		
		Media sound = new Media(new File(musicFile).toURI().toString());
		backgroundPlayer = new MediaPlayer(sound);
		backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		backgroundPlayer.play();
	}
	
	public static void stop(){
		soundPlayer.stop();
	}
	
	public static void stopbackground(){
		backgroundPlayer.stop();
	}
}
