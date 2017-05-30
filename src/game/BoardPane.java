package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import image.IMGhandler;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.DepthTest;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.FlowPane;
import start_MEMORY.Start;

public class BoardPane extends FlowPane {

	private int cardPairs;
	private double picSize = (this.getPrefWidth() / this.getPrefHeight() * 110 * 1.25);
	private Card selCard;
	private double offset;
	private int ani_idx;
	List<Card> cardList = new ArrayList<Card>();
	List<Integer> cardValues = new ArrayList<Integer>();

	public BoardPane() {
		super();
		BoardPane.this.setBackground(
				new Background(new BackgroundImage(IMGhandler.getGameBackground(), null, null, null, null)));
		BoardPane.this.setCache(true);
		BoardPane.this.setCacheShape(true);
		BoardPane.this.setStyle("-fx-border-color: Blue");
		BoardPane.this.setVgap(20);
		BoardPane.this.setHgap(40);
		BoardPane.this.setAlignment(Pos.CENTER);
		offset = 0;
		if (cardValues.isEmpty()) {
			picSize = Start.getJhdl().getModel().getInfo().getPicSize(this.getPrefWidth(), this.getPrefHeight());
			Initialize(Start.getJhdl().getModel().getInfo().getCardcount());
		}
	}

	public int getCardPairs() {
		return cardPairs;
	}

	public Card getSelCard() {
		return selCard;
	}

	public void Initialize(int cardPairs) {
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
			// c.setManaged(true);
			c.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					//added handler for bugfix
					if (!c.isMatched() && !c.isTurned() && ani_idx < 2) {
						Transition animation = GameEventhandler.cardturn(c, BoardPane.this);
						animation.play();
						ani_idx++;
						if (ani_idx == 2) {
							animation.setOnFinished(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent event) {
									ani_idx = 0;	
								}
							});
						}
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

	public void setSelCard(Card selCard) {
		this.selCard = selCard;
	}

}
