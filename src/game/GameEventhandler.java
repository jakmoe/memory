package game;

import image.IMGhandler;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.util.Duration;
import sound.MP3handler;
import start_MEMORY.Start;

public class GameEventhandler {
	private static Point3D axis = new Point3D(5, 5, 0);
	private static CustomAnimationTimer timer = new CustomAnimationTimer();
	private static Card c1;
	private static Card c2;
	
	public static void cardturn(Card c, BoardPane internalBoard) {
		Transition animation = null;
		if (c1 == null) {
			c1 = c;
			c1.lock();
			animation = flipCard(c1, 0);
			if (timer.getCurrent() == 0) {
				timer.start();
			}
			c.setTurned(true);
		} else if (c2 == null) {
			c2 = c;
			c2.lock();
		}
		if (c1 != null && c2 != null) {
			if (c1.getCard_Id() == c2.getCard_Id()) {
				GameMaster.doTurn(true, timer.getCurrent());
				animation = flipGrey(c1, c2);
				match(c1, c2);

				c1 = null;
				c2 = null;
			} else {

				GameMaster.doTurn(false, timer.getCurrent());
				if (Start.getGamemode() > 1) {
					timer.stop();
					timer.reset();
					timer.start();
				}
				animation = flipBack(c1, c2);
				
				c1.setTurned(false);
				c2.setTurned(false);

				c1 = null;
				c2 = null;
			}
		}
		animation.play();
		MP3handler.play(1);
	}

	public static CustomAnimationTimer getTimer() {
		return timer;
	}

	public static void reset(Node n) {
		n.setScaleX(1);
		n.setScaleY(1);
		n.setScaleZ(1);
		n.setLayoutX(0);
		n.setLayoutY(0);
		n.setTranslateX(0);
		n.setTranslateY(0);
	}

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

	static Transition flipBack(Card c1, Card c2) {
		c1.lock();
		c2.lock();
		// TransitionRun TR = new TransitionRun();

		RotateTransition c1turnAnimation = new RotateTransition(Duration.seconds(0.2), c1);
		c1turnAnimation.setToAngle(90);
		c1turnAnimation.setAxis(new Point3D(5, 5, 0));

		RotateTransition c1turnBackAnimation = new RotateTransition(Duration.seconds(0.2), c1);
		c1turnBackAnimation.setToAngle(0);
		c1turnBackAnimation.setAxis(new Point3D(5, 5, 0));

		c1turnAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c1.setFill(IMGhandler.getImage_card(0));
				// TR.setAnim(c1turnBackAnimation);
				// TR.run();
				c1turnBackAnimation.play();
			}
		});

		c1turnBackAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c1.unlock();
			}
		});

		RotateTransition c2turnAnimation = new RotateTransition(Duration.seconds(0.2), c2);
		c2turnAnimation.setToAngle(90);
		c2turnAnimation.setAxis(axis);

		RotateTransition c2turnBackAnimation = new RotateTransition(Duration.seconds(0.2), c2);
		c2turnBackAnimation.setToAngle(0);
		c2turnBackAnimation.setAxis(axis);

		c2turnAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c2.setFill(IMGhandler.getImage_card(0));
				c2turnBackAnimation.play();
			}
		});

		c2turnBackAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c2.unlock();
			}
		});

		ParallelTransition parallel = new ParallelTransition(c1turnAnimation, c2turnAnimation);
		SequentialTransition Seq;
		if (c1.getParent().getChildrenUnmodifiable().indexOf(c1) == c2.getParent().getChildrenUnmodifiable().indexOf(c2)
				- 1) {
			Seq = new SequentialTransition(flipCard(c2, 1), parallel);
		} else if (c1.getParent().getChildrenUnmodifiable().indexOf(c1) - 1 == c2.getParent().getChildrenUnmodifiable()
				.indexOf(c2)) {
			Seq = new SequentialTransition(flipCard(c2, -1), parallel);
		} else {
			Seq = new SequentialTransition(flipCard(c2, 0), parallel);
		}
		return Seq;
	}

	static Transition flipCard(Card c, int pos) {
		c.lock();
		// TransitionRun TR = new TransitionRun();

		ScaleTransition ScaleUp = new ScaleTransition(Duration.seconds(0.2), c);
		ScaleUp.setByX(0.6);
		ScaleUp.setByY(0.6);

		ScaleTransition ScaleDown = new ScaleTransition(Duration.seconds(0.2), c);
		ScaleDown.setByX(-0.6);
		ScaleDown.setByY(-0.6);

		PauseTransition pause = new PauseTransition(Duration.seconds(0.75));

		TranslateTransition moveAnimation = new TranslateTransition(Duration.seconds(0.2), c);
		moveAnimation.setToY(-100);
		TranslateTransition moveBackAnimation = new TranslateTransition(Duration.seconds(0.2), c);
		moveBackAnimation.setToY(0);
		if (pos < 0) {
			moveAnimation.setToZ(0);
			moveBackAnimation.setToZ(0);
		} else if (pos > 0) {
			moveAnimation.setToZ(-100);
			moveBackAnimation.setToZ(0);
		}

		SequentialTransition zoomSeq = new SequentialTransition(ScaleUp, pause, ScaleDown, moveBackAnimation);
		zoomSeq.setInterpolator(Interpolator.LINEAR);

		RotateTransition turnAnimation = new RotateTransition(Duration.seconds(0.2), c);
		turnAnimation.setToAngle(90);
		turnAnimation.setAxis(axis);

		RotateTransition turnBackAnimation = new RotateTransition(Duration.seconds(0.2), c);
		turnBackAnimation.setToAngle(0);
		turnBackAnimation.setAxis(axis);

		turnAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				c.setFill(IMGhandler.getImage_card(c.getCard_Id()));
				turnBackAnimation.play();
			}
		});

		turnAnimation.setInterpolator(Interpolator.LINEAR);
		ParallelTransition turn = new ParallelTransition(turnAnimation, zoomSeq, moveAnimation);
		return turn;
	}

	static Transition flipGrey(Card c1, Card c2) {
		c1.lock();
		c2.lock();
		TransitionRun TR = new TransitionRun();

		RotateTransition c1turnAnimation = new RotateTransition(Duration.seconds(0.2), c1);
		c1turnAnimation.setToAngle(90);
		c1turnAnimation.setAxis(axis);

		RotateTransition c1turnBackAnimation = new RotateTransition(Duration.seconds(0.2), c1);
		c1turnBackAnimation.setToAngle(0);
		c1turnBackAnimation.setAxis(axis);

		c1turnAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fadeout(c2);
				TR.setAnim(c1turnBackAnimation);
				TR.run();
			}
		});

		RotateTransition c2turnAnimation = new RotateTransition(Duration.seconds(0.2), c2);
		c2turnAnimation.setToAngle(90);
		c2turnAnimation.setAxis(axis);

		RotateTransition c2turnBackAnimation = new RotateTransition(Duration.seconds(0.2), c2);
		c2turnBackAnimation.setToAngle(0);
		c2turnBackAnimation.setAxis(axis);

		c2turnAnimation.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fadeout(c2);
				TR.setAnim(c2turnBackAnimation);
				TR.run();
			}
		});
		ParallelTransition parallel = new ParallelTransition();
		SequentialTransition Seq = new SequentialTransition(flipCard(c2, 1), parallel);

		Seq.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				fadeout(c2).play();
				fadeout(c1).play();
			}
		});

		SequentialTransition output = new SequentialTransition(Seq);
		return output;
	}

	static void match(Card c1, Card c2) {
		c1.setMatched(true);
		c2.setMatched(true);
	}

}
