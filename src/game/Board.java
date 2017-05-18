package game;

import javafx.scene.layout.Pane;

public class Board extends Pane{
	private int cardcount;
	private Card selCard;
	
	public Card getSelCard() {
		return selCard;
	}

	public void setSelCard(Card selCard) {
		this.selCard = selCard;
	}

	public int getCardcount() {
		return cardcount;
	}

	public void setCardcount(int cardcount) {
		this.cardcount = cardcount;
	}
	
	
}
