package game;


import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Board extends Pane {
	private int xPointer = 0;
	private int yPointer = 0;
	private int cardPairs = 16;
	private double picSize = (this.getPrefWidth() / this.getPrefHeight() * 120);
	private Card selCard;
	private Board InternalBoard = this;
	private int index = 0;
	private double offset;

	List<Card> cardList = new ArrayList<Card>();
	List<Integer> cardValues = new ArrayList<Integer>();

	public int getCardcount() {
		return cardPairs;
	}

	public Card getSelCard() {
		return selCard;
	}

	public void Initialize(int PlayerCount) {
		for (int i = 0; i < InternalBoard.getCardcount(); i++) {
			cardValues.add(i);
			cardValues.add(i);
		}
		offset = 0;
		GameMaster.shuffleCards(cardValues);
		for (int val : cardValues) {
			index++;
			Card c = new Card(xPointer + offset, yPointer, picSize, picSize);
			// coordinates must be adapted
			xPointer += (picSize + 10);
			if (index % 9 == 0) {
				xPointer = 0;
				yPointer += (picSize + 10);
			}
			
			c.setFill(Color.BLUE);
			// Image img = null;
			// c.fillCard(img, val); Error image must be non null
			c.setCard_Id(val);
			c.setArcHeight(10);
			c.setArcWidth(10);
			c.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					if (!c.isMatched()) {
						GameEventhandler.cardturn(c, InternalBoard);
					}
				}
			});
			cardList.add(c);
		}
		InternalBoard.getChildren().clear();
		InternalBoard.getChildren().addAll(cardList);
	}

	public void setCardcount(int cardcount) {
		this.cardPairs = cardcount;
	}

	public void setSelCard(Card selCard) {
		this.selCard = selCard;
	}

	public void Test() {
		this.getChildren().add(new Button("Test"));
	}

}
