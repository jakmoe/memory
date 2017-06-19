package game;

import image.IMGhandler;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerCircle extends StackPane {
	private Player player;

	private Circle circle = new Circle(100, Color.BLUE);
	
	private Label playerlabel = new Label();
	
	public PlayerCircle(Player pl) {
		super();
		setPlayer(pl);
		playerlabel.setText("Player " + pl.getId() + "\nHighscore " + pl.getHighscore()
		+ "\nTime " + pl.getMintime() + "\nAttempts " + pl.getAttempts());
		
		circle.setFill(IMGhandler.getPlayer(false));
		if (pl.getId() == GameMaster.getPlayerInTurn().getId()) {
			circle.setFill(IMGhandler.getPlayer(true));
		} else {
			circle.setFill(IMGhandler.getPlayer(false));
		}
		
		this.getChildren().add(circle);
		this.getChildren().add(playerlabel);
	}
	
	public void update() {
		playerlabel.setText("Player " + player.getId() + "\nHighscore " + player.getHighscore()
		+ "\nTime " + player.getMintime() + "\nAttempts " + player.getAttempts());
		
		circle.setFill(IMGhandler.getPlayer(false));
		if (player.getId() == GameMaster.getPlayerInTurn().getId()) {
			circle.setFill(IMGhandler.getPlayer(true));
		} else {
			circle.setFill(IMGhandler.getPlayer(false));
		}
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		update();
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public Label getPlayerlabel() {
		return playerlabel;
	}

	public void setPlayerlabel(Label playerlabel) {
		this.playerlabel = playerlabel;
	}
	
}
