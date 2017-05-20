package game;

import image.IMGhandler;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Point3D;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import sound.MP3handler;

public class GameEventhandler {
	public static void cardturn(Card c, BoardVBOX internalBoard) {
		if (internalBoard.getSelCard() == c) {
			match(c, internalBoard.getSelCard());
			internalBoard.setSelCard(null);
		} else if (internalBoard.getSelCard() == null) {
			internalBoard.setSelCard(c);
		}
		
		// turn Card Animation here
		flipCard(c);
		//internalBoard.getChildren().add(idx, c);
		
		// Play Sound
		MP3handler.play(1);
		
		c.setMatched(true);
	}

	private static void match(Card c1, Card c2) {
		System.out.println("Greyed out with c1 and c2");
		c1.setMatched(true);
		c2.setMatched(true);
	}

	private static void flipCard(Card c) {
		ScaleTransition ScaleUp = new ScaleTransition(Duration.seconds(0.3), c);
		ScaleUp.setByX(0.6);
		ScaleUp.setByY(0.6);
		
		ScaleTransition ScaleDown = new ScaleTransition(Duration.seconds(0.3), c);
		ScaleDown.setByX(-0.6);
		ScaleDown.setByY(-0.6);

		PauseTransition pause = new PauseTransition(Duration.seconds(1));

		SequentialTransition zoomSeq = new SequentialTransition(ScaleUp, pause, ScaleDown);

		RotateTransition turnAnimation = new RotateTransition(Duration.seconds(0.3), c);
		turnAnimation.setToAngle(90);
		turnAnimation.setAxis(new Point3D(5, 5, 0));

		RotateTransition turnBackAnimation = new RotateTransition(Duration.seconds(0.3), c);
		turnBackAnimation.setToAngle(0);
		turnBackAnimation.setAxis(new Point3D(5, 5, 0));

		turnAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c.setFill(new ImagePattern(IMGhandler.getImage_card(c.getCard_Id())));
				turnBackAnimation.play();
			}
		});
		
		turnAnimation.play();
		zoomSeq.play();
		c.setFill(Color.RED);
	}
}
