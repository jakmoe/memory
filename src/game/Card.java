package game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Card extends Rectangle {
	private boolean turned;
	private boolean matched;
	private boolean animationlock = false;
	private int card_id;

	public Card() {
		super();
	}

	public Card(double width, double height) {
		super(width, height);
	}

	public Card(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public Card(double width, double height, Paint fill) {
		super(width, height, fill);
	}

	public void fillCard(Image img) {
		this.setFill(new ImagePattern(img));
	}

	public int getCard_Id() {
		return card_id;
	}

	public boolean isMatched() {
		return matched;
	}

	public boolean isTurned() {
		return turned;
	}

	public void setCard_Id(int card_id) {
		this.card_id = card_id;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	public void setTurned(boolean turned) {
		this.turned = turned;
	}

	public boolean inAnimation() {
		return animationlock;
	}
	
	public void lock(){
		this.animationlock = true;
	}
	
	public void unlock(){
		this.animationlock = false;
	}
}
