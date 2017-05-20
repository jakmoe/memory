package game;


import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class BoardVBOX extends FlowPane {
	private int cardPairs = 36;
	private double picSize = (this.getPrefWidth() / this.getPrefHeight() * 120);
	private Card selCard;
	private BoardVBOX board = this;
	private double offset;
	
	ObservableList<Node> workingCollection;
	List<Card> cardList = new ArrayList<Card>();
	List<Integer> cardValues = new ArrayList<Integer>();
	
	public int getCardcount() {
		return cardPairs;
	}

	public Card getSelCard() {
		return selCard;
	}

	public void Initialize(int PlayerCount) {

		for (int i = 0; i < board.getCardcount(); i++) {
			cardValues.add(i);
			cardValues.add(i);
		}
		board.setVgap(50);
		board.setHgap(50);
		board.setAlignment(Pos.CENTER);
		offset = 0;
		GameMaster.shuffleCards(cardValues);
		
		for (int val : cardValues) {
			Card c = new Card(offset, offset, picSize, picSize);
			// coordinates must be adapted
			c.setFill(Color.BLUE);
			c.setCard_Id(val);
			c.setArcHeight(10);
			c.setArcWidth(10);
			c.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					if (!c.isMatched()) {
						GameEventhandler.cardturn(c, board);
					}
				}
			});
			cardList.add(c);
		}
		board.getChildren().addAll(cardList);
//		row.getChildren().addAll(cardList);
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
