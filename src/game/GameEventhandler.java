package game;


import image.IMGhandler;
import javafx.animation.FadeTransition;
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
	private static double time;
	public static Transition fadein(Node n) {
		TransitionRun TR = new TransitionRun();
		FadeTransition ft = new FadeTransition(Duration.millis(4000), n);
		ft.setFromValue(0);
		ft.setToValue(1);
		TR.setAnim(ft);
		TR.run();
		return ft;
	}
	
	public static Transition fadeout(Node n) {
		TransitionRun TR = new TransitionRun();
		FadeTransition ft = new FadeTransition(Duration.millis(2000), n);
		ft.setFromValue(1);
		ft.setToValue(0.4);
		TR.setAnim(ft);
		TR.run();
		return ft;
	}

	public static void time(){
		
	}
	
	public static void cardturn(Card c, BoardPane internalBoard) {
		CustomAnimationTimer timer = new CustomAnimationTimer();
		// if a match is made
		if (internalBoard.getSelCard() == null) {
			// turn Card Animation here
			flipCard(c).play();
			timer.start();
			c.setTurned(true);
			internalBoard.setSelCard(c);
			// if no match was made
		} else if (internalBoard.getSelCard().getCard_Id() == c.getCard_Id()) {
			//Turn handling here
			GameMaster.doTurn(true, time);
			timer.stop();
			timer.reset();
			//could go into a pool of cards for each player
			Transition greyanim = flipGrey(internalBoard.getSelCard(), c);
			greyanim.play();
			match(c, internalBoard.getSelCard());
			internalBoard.setSelCard(null);
			// if no card is selected - first card is then selected
		} else {
			timer.stop();
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
	}

	static Transition flipCard(Card c) {
		
		TransitionRun TR = new TransitionRun();
		
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
		TransitionRun TR = new TransitionRun();

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
	
	static Transition flipGrey(Card c1, Card c2) {
		TransitionRun TR = new TransitionRun();

		RotateTransition c1turnAnimation = new RotateTransition(Duration.seconds(0.3), c1);
		c1turnAnimation.setToAngle(90);
		c1turnAnimation.setAxis(new Point3D(5, 5, 0));

		RotateTransition c1turnBackAnimation = new RotateTransition(Duration.seconds(0.3), c1);
		c1turnBackAnimation.setToAngle(0);
		c1turnBackAnimation.setAxis(new Point3D(5, 5, 0));

		c1turnAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fadeout(c2);
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
				fadeout(c2);
				TR.setAnim(c2turnBackAnimation);
				TR.run();
			}
		});
		ParallelTransition parallel = new ParallelTransition();
		SequentialTransition Seq = new SequentialTransition(flipCard(c2), parallel);
		
		Seq.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {			
				fadeout(c2).play();
				fadeout(c1).play();
			}
		});
		return Seq;
	}
}
