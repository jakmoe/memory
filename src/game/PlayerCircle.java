package game;

import image.IMGhandler;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author D067928
 *	Die User Interface - Repräsentation eines Spielers. Es besteht aus einem Spieler-Objekt (Player) sowie einem Circle und einem
 *	Label, die auf dem UI rechts im Spiel angezeigt werden könnnen. Es ist eine Erweiterung des StackPanes, welches alle Objekte
 *  übereinander "stacked" also stapelt, was das verwalten auf dem UI vereinfacht, weil es wie 1 einziges Objekt angezeigt wird.
 *  
 *  Anmerkung: Da die Bilder für die Spieler ein Pentagon sind werden sie mit einem Kreis (was diesem Nahe kommt) verwaltet.
 */
public class PlayerCircle extends StackPane {
	private Player player;

	private Circle circle = new Circle(100, Color.BLUE);

	private Label playerlabel = new Label();

	/** 
	 * Der Standardkonstruktor für einen PlayerCircle, welcher immer voraussetzt dass bereits ein Player erzeugt wurde.
	 * @param pl - Player, der angezeigt werden soll
	 */
	public PlayerCircle(Player pl) {
		super();
		//setzt den Spieler
		setPlayer(pl);
		//setzt die Labelinformationen
		playerlabel.setText(pl.getName() + "\nHighscore " + pl.getHighscore() + "\nTime " + pl.getMintime()
				+ "\nAttempts " + pl.getAttempts());

		//Zeigt an ob der Spieler entweder an der Reihe ist (aufleuchtend) oder nicht.
		circle.setFill(IMGhandler.getPlayer(false));
		if (pl.getId() == GameMaster.getPlayerInTurn().getId()) {
			circle.setFill(IMGhandler.getPlayer(true));
		} else {
			circle.setFill(IMGhandler.getPlayer(false));
		}
		
		//Added doe Objekte zur Childrenlist der StackPane-Logik (um sie sichtbar aufeinander gestapelt) zu zeigen
		this.getChildren().add(circle);
		this.getChildren().add(playerlabel);
	}

	/**
	 * Führt ein komplettes Update inklusive Imagereload aus, sodass möglicher Spielerwechsel angezeigt wird aber auch die neue Zeit.
	 * Wird nur beim tatsächlichen Spielewechsel über den Changelistener gerufen
	 */
	public void update() {
		playerlabel.setText(player.getName() + "\nHighscore " + player.getHighscore() + "\nTime "
				+ Math.floor(player.getMintime()*10)/10 + "\nAttempts " + player.getAttempts());

		if (player.getId() == GameMaster.getPlayerInTurn().getId()) {
			circle.setFill(IMGhandler.getPlayer(true));
		} else {
			circle.setFill(IMGhandler.getPlayer(false));
		}
	}
	
	/**
	 * Führt nur ein Update des Labels aus, welches jedes Frame auf dem UI gerufen wird. Hier wird kein Imageupdate aufgerufen,
	 * weil dies zu Performanceproblemen führen würde.
	 */
	public void updateLabel() {
		playerlabel.setText(player.getName() + "\nHighscore " + player.getHighscore() + "\nTime "
				+ Math.floor(player.getMintime()*10)/10 + "\nAttempts " + player.getAttempts());
	}

	/**
	 * @return Player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Setzt den Spieler und updated einmal um die Änderungen sichtbar zu machen
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
		update();
	}

	/**
	 * @return circle
	 */
	public Circle getCircle() {
		return circle;
	}

	/**
	 * @param circle
	 */
	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	/**
	 * @return playerlabel
	 */
	public Label getPlayerlabel() {
		return playerlabel;
	}

	/**
	 * @param playerlabel
	 */
	public void setPlayerlabel(Label playerlabel) {
		this.playerlabel = playerlabel;
		updateLabel();
	}

}
