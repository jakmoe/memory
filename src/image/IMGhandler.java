package image;

import java.net.URL;
import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class IMGhandler {

	private static ArrayList<ImagePattern> images = new ArrayList<ImagePattern>();
	private static int theme = 1;
	
	public static Image getGameBackground() {
		URL url = null;
		switch (theme) {
		case 1:
			url = IMGhandler.class.getResource("/image/background/light.jpg");
			break;
		case 2:
			url = IMGhandler.class.getResource("/image/background/dark.jpg");
			break;
		case 3:
			url = IMGhandler.class.getResource("/image/background/cute.jpg");
			break;
		default:
			break;
		}
		Image img = new Image(url.toString(), 1920, 1080, true, true);
		return img;
	}
	
	public static Image getWinScreen(){
		URL url = IMGhandler.class.getResource("/image/winscreen/win.png");
		Image img = new Image(url.toString(), 1920, 1080, true, true);
		return img;
	}
	
	public static ImagePattern getPlayer(boolean active) {
		URL url = null;
		if (active) {
			url = IMGhandler.class.getResource("/image/player/player_x.png");
		} else {
			url = IMGhandler.class.getResource("/image/player/player.png");
		}
		Image img = new Image(url.toString());
		return new ImagePattern(img);
	}

	public static ImagePattern getImage_card(int id) {
		return images.get(id);
	}

	public static Task<Void> initialize(int cardcount) {
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// Background work
				int i;
				for (i = 0; i < 38; i++) {
					if (isCancelled()) {
						break;
					}
					URL url = IMGhandler.class.getResource("/image/cards/" + i + ".jpg");
					Image img = new Image(url.toURI().toString(), 500, 500, true, true);
					ImagePattern imgp = new ImagePattern(img);
					images.add(imgp);
					updateProgress(i, cardcount);
				}
				// Keep with the background work
				return null;
			}
		};
		return task;
	}

	public static ArrayList<ImagePattern> getSprites(int id) {
		ArrayList<ImagePattern> sprites = new ArrayList<ImagePattern>();
		switch (id) {
		case 1:
			for (int i = 0; i < 6; i++) {
				URL url = IMGhandler.class.getResource("/image/sprites/gm_sprite_" + i + ".png");
				Image img = new Image(url.toString());
				sprites.add(new ImagePattern(img));
			}
			break;
		default:
			break;
		}
		return sprites;

	}

	public static int getTheme() {
		return theme;
	}

	public static void setTheme(int theme) {
		IMGhandler.theme = theme;
	}
}
