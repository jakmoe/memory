package game;

import java.util.ArrayList;

import image.IMGhandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import start_MEMORY.Start;

public class WinStack extends HBox {
	public void initialize(ArrayList<Player> arrayList) {
		this.setPrefWidth(600);
		this.setPrefHeight(500);
		this.setSpacing(20);
		this.setLayoutX(600);
		this.setLayoutY(400);

		if (Start.getGamemode() > 1) {
			for (Player playerSave : arrayList) {
				VBox highscore = new VBox();
				highscore.setSpacing(20);
				this.getChildren().add(highscore);
				highscore.getChildren().add(new ImageView(IMGhandler.getWinNumber(playerSave.getId())));
				highscore.getChildren().add(new Label(playerSave.getName()));
				for (int i = 0; i < playerSave.getHighscore(); i++) {
					Rectangle scorerec = new Rectangle(this.getPrefWidth() / arrayList.size(), 30);
					highscore.getChildren().add(scorerec);
					scorerec.toFront();
				}
				highscore.getChildren().add(new Label(Double.toString(playerSave.getMintime())));
			}
		}
	}
}
