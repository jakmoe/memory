package sound;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MP3handler {
	public static void play(int SoundID){
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
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
}
