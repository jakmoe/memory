package game;

import java.util.ArrayList;
import java.util.Comparator;

import image.IMGhandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import start_MEMORY.Start;

public class WinStack extends HBox {
	public void initialize(ArrayList<Player> arrayList) {
		this.setPrefWidth(600);
		this.setPrefHeight(500);
		this.setSpacing(20);
		this.setLayoutX(650);
		this.setLayoutY(325);
		this.setAlignment(Pos.TOP_CENTER);

		arrayList.sort(new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				if (o1.getHighscore() > o2.getHighscore()) {
					return -1;
				} else if (o1.getHighscore() < o2.getHighscore()) {
					return 1;
				} else {
					if (o1.getAttempts() < o2.getAttempts()) {
						return -1;
					} else if (o1.getAttempts() > o2.getAttempts()) {
						return 1;
					} else {
						if (o1.getMintime() > o2.getMintime()) {
							return -1;
						} else if (o1.getMintime() < o2.getMintime()) {
							return 1;
						}
						return 0;
					}
				}
			}
		});

		if (Start.getGamemode() > 1) {
			int idx = 0;
			for (Player playerSave : arrayList) {
				idx++;
				VBox highscore = new VBox();
				highscore.setSpacing(10);
				this.getChildren().add(highscore);
				highscore.getChildren().add(new ImageView(IMGhandler.getWinNumber(idx)));
				highscore.setAlignment(Pos.TOP_CENTER);
				highscore.getChildren().add(new Label(playerSave.getName()));
				for (int i = 0; i < playerSave.getHighscore(); i++) {
					Rectangle scorerec = new Rectangle(this.getPrefWidth() / Start.getGamemode(),
							25 * 1 / Start.getJhdl().getModel().getInfo().getDifficulty());
					scorerec.setFill(new ImagePattern(IMGhandler.getStack()));
					highscore.getChildren().add(scorerec);
					scorerec.toFront();
				}
				highscore.getChildren().add(new Label(Double.toString(playerSave.getMintime()) + " Sekunden"));
				highscore.getChildren().add(new Label(Integer.toString(playerSave.getAttempts()) + " Versuche"));
			}
		}
	}
}
