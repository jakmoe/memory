package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import image.IMGhandler;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.DepthTest;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import start_MEMORY.Start;

public class BoardPane extends FlowPane {

	private int cardPairs;
	private double picSize = getPicSize(this.getPrefWidth(), this.getPrefHeight());
	private double offset;
	private List<Card> cardList = new ArrayList<Card>();
	private List<Integer> cardValues = new ArrayList<Integer>();

	public BoardPane() {
		super();
		BoardPane.this.setCache(true);
		BoardPane.this.setCacheShape(true);
		BoardPane.this.setAlignment(Pos.CENTER);
		offset = 0;
		Initialize(Start.getJhdl().getModel().getInfo().getCardcount());
	}

	public int getCardPairs() {
		return cardPairs;
	}

	public void Initialize(int cardPairs) {
		
		if (!cardValues.isEmpty()) {
			cardList.clear();
			cardValues.clear();
		}

		BoardPane.this.setCardPairs(cardPairs);
		for (int i = 1; i < BoardPane.this.getCardPairs() + 1; i++) {
			cardValues.add(i);
			cardValues.add(i);
		}
		Collections.shuffle(cardValues);
		for (int val : cardValues) {
			Card c = new Card(offset, offset, picSize, picSize);
			// coordinates must be adapted
			c.setFill(IMGhandler.getImage_card(0));
			c.setCacheHint(CacheHint.SPEED);
			c.setDepthTest(DepthTest.INHERIT);
			c.setCard_Id(val);
			c.setArcHeight(20);
			c.setArcWidth(20);
			c.setCache(true);
			c.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					if (!c.isMatched() & !c.isTurned() & !c.inAnimation()) {
						GameEventhandler.cardturn(c, BoardPane.this);
					}
				}
			});
			cardList.add(c);
		}
		BoardPane.this.getChildren().addAll(cardList);
		// Sprite sp = new Sprite(1, 100, 100);
		// BoardPane.this.getChildren().add(sp);
	}

	public void setCardPairs(int cardcount) {
		this.cardPairs = cardcount;
	}
	
	public double getPicSize(double width, double height) {
		switch (Start.getJhdl().getModel().getInfo().getDifficulty()) {
		case 1:
			BoardPane.this.setVgap(20);
			BoardPane.this.setHgap(40);
			return (width / height * 110 * 1.6);
		case 2:
			BoardPane.this.setVgap(20);
			BoardPane.this.setHgap(40);
			return (width / height * 110 * 1.25);
		case 3:
			BoardPane.this.setVgap(20);
			BoardPane.this.setHgap(40);
			return (width / height * 110 * 1.1);
		case 4:
			BoardPane.this.setVgap(10);
			BoardPane.this.setHgap(50);
			return (width / height * 110 * 0.85);
		case 5:
			BoardPane.this.setVgap(10);
			BoardPane.this.setHgap(50);
			return (width / height * 110 * 0.85);
		default:
			BoardPane.this.setVgap(20);
			BoardPane.this.setHgap(40);
			return (width / height * 110 * 1.25);
		}
	}
}
