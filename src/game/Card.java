package game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Card extends Rectangle{
	private boolean turned;
	private boolean matched;
	private int card_id;
	
	public Card() {
		super();
	}

	public boolean isTurned() {
		return turned;
	}

	public void setTurned(boolean turned) {
		this.turned = turned;
	}

	public int getCard_Id() {
		return card_id;
	}

	public void setCard_Id(int card_id) {
		this.card_id = card_id;
	}
	
	public boolean isMatched() {
		return matched;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	public void fillCard(Image img){
		this.setFill(new ImagePattern(img));
	}
	
}
