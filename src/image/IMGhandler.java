package image;

import javafx.scene.image.Image;

public class IMGhandler {
	public static Image getImage_card(int id) {
		Image img = null;
		switch (id) {
		case 1:
			img = new Image("/image/1.jpg");
			break;
		case 2:
			img = new Image("/image/2.jpg");
			break;
		default:
			img = new Image("/image/1.jpg");
			break;
		}
		return img;
	}
}
