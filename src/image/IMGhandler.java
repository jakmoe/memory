package image;

import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class IMGhandler {

	private static ArrayList<ImagePattern> images = new ArrayList<ImagePattern>();

	public static Image getGameBackground(){
		Image img = new Image("/image/game_background.gif", 1400, 1200, false, true);
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
					Image img = new Image("/image/cards/" + i + ".jpg", 500, 500, true, true);
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
	
	public static ArrayList<ImagePattern> getSprites(int id){
		ArrayList<ImagePattern> sprites = new ArrayList<ImagePattern>();
		switch (id) {
		case 1:
			for (int i = 0; i < 6; i++) {
				Image img = new Image("/image/sprites/gm_sprite_" + i +".png");
				sprites.add(new ImagePattern(img));
			}
			break;
		default:
			break;
		}
		return sprites;
		
	}
}
