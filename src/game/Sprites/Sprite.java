package game.Sprites;

import java.util.ArrayList;

import image.IMGhandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Sprite extends Rectangle {
	private int current_sprite = 0;
	ArrayList<ImagePattern> sprites = new ArrayList<ImagePattern>();
	Timeline timeline = new Timeline();

	public Sprite(int id, int width, int height) {
		update(id);
		Sprite.this.setWidth(width);
		Sprite.this.setHeight(height);
		Sprite.this.setFill(sprites.get(0));
		start();
	}

	public void start() {
		EventHandler<ActionEvent> spritehandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (current_sprite > sprites.size() - 1) {
					current_sprite = 0;
				}
				Sprite.this.setFill(sprites.get(current_sprite));
				current_sprite++;
			};
		};
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.2), spritehandler));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	public void stop() {
		timeline.stop();
	}

	public void update(int id) {
		sprites = IMGhandler.getSprites(id);
	}
}
