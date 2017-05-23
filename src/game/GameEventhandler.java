package game;

import image.IMGhandler;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.geometry.Point3D;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;
import sound.MP3handler;

public class GameEventhandler {
	public static TransitionRun TR = new TransitionRun();
	
	public static void fadein(Node n) {
		FadeTransition ft = new FadeTransition(Duration.millis(4000), n);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setInterpolator(Interpolator.EASE_BOTH);
		ft.play();
	}
	
	public static void fadeout(Node n) {
		FadeTransition ft = new FadeTransition(Duration.millis(4000), n);
		ft.setFromValue(1);
		ft.setToValue(0.5);
		ft.setInterpolator(Interpolator.EASE_BOTH);
		ft.play();
	}

	public static void cardturn(Card c, BoardPane internalBoard) {
		internalBoard.getSelCard();
		// if a match is made
		if (internalBoard.getSelCard() == null) {
			// turn Card Animation here
			flipCard(c).play();
			c.setTurned(true);
			internalBoard.setSelCard(c);
			// if no match was made
		} else if (internalBoard.getSelCard().getCard_Id() == c.getCard_Id()) {
			flipBack(internalBoard.getSelCard(), c).play();
			match(c, internalBoard.getSelCard());
			internalBoard.setSelCard(null);
			// if no card is selected - first card is then selected
		} else {
			flipBack(internalBoard.getSelCard(), c).play();
			internalBoard.getSelCard().setTurned(false);
			c.setTurned(false);
			internalBoard.setSelCard(null);
		}
		// Play Sound
		MP3handler.play(1);
		// Platform.runLater(task);

	}

	static void match(Card c1, Card c2) {
		c1.setMatched(true);
		c2.setMatched(true);
		fadeout(c1);
		fadeout(c2);
		
		
	}

	static Transition flipCard(Card c) {
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
				c.setFill(IMGhandler.getImage_card(c.getCard_Id()));
				TR.setAnim(turnBackAnimation);
				TR.run();
			}
		});
		ParallelTransition turn = new ParallelTransition(turnAnimation, zoomSeq);
		return turn;
	}

	static Transition flipBack(Card c1, Card c2) {

		RotateTransition c1turnAnimation = new RotateTransition(Duration.seconds(0.3), c1);
		c1turnAnimation.setToAngle(90);
		c1turnAnimation.setAxis(new Point3D(5, 5, 0));

		RotateTransition c1turnBackAnimation = new RotateTransition(Duration.seconds(0.3), c1);
		c1turnBackAnimation.setToAngle(0);
		c1turnBackAnimation.setAxis(new Point3D(5, 5, 0));

		c1turnAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c1.setFill(IMGhandler.getImage_card(0));
				TR.setAnim(c1turnBackAnimation);
				TR.run();
			}
		});

		RotateTransition c2turnAnimation = new RotateTransition(Duration.seconds(0.3), c2);
		c2turnAnimation.setToAngle(90);
		c2turnAnimation.setAxis(new Point3D(5, 5, 0));

		RotateTransition c2turnBackAnimation = new RotateTransition(Duration.seconds(0.3), c2);
		c2turnBackAnimation.setToAngle(0);
		c2turnBackAnimation.setAxis(new Point3D(5, 5, 0));

		c2turnAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c2.setFill(IMGhandler.getImage_card(0));
				TR.setAnim(c2turnBackAnimation);
				TR.run();
			}
		});
		ParallelTransition parallel = new ParallelTransition(c1turnAnimation, c2turnAnimation);
		SequentialTransition Seq = new SequentialTransition(flipCard(c2), parallel);
		return Seq;
	}
}
