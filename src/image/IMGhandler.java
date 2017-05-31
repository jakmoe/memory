package image;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class IMGhandler {

	private static ArrayList<ImagePattern> images = new ArrayList<ImagePattern>();

	public static Image getGameBackground() {
		URL url = IMGhandler.class.getResource("/image/board.jpg");
		Image img = new Image(url.toString(), 1400, 1200, false, true);
		return img;
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
}
