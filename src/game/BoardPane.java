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

	public TransitionRun TR = new TransitionRun();
	private int cardPairs;
	private double picSize = (this.getPrefWidth() / this.getPrefHeight() * 110 * 1.25);
	private Card selCard;
	private double offset;
	
	
	
	//ObservableList<Node> workingCollection;
	List<Card> cardList = new ArrayList<Card>();
	List<Integer> cardValues = new ArrayList<Integer>();

	public BoardPane() {
		super();
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
			//c.setManaged(true);
			c.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					if (!c.isMatched() && !c.isTurned()) {
						GameEventhandler.cardturn(c, BoardPane.this);
					}
				}
			});
			cardList.add(c);
		}

		BoardPane.this.getChildren().addAll(cardList);

//		Sprite sp = new Sprite(1, 100, 100);
//		BoardPane.this.getChildren().add(sp);
		
//      Performance Test with ImageView
//		ImageView imgv = new ImageView(new Image("/image/0.jpg", picSize, picSize, true, true));
//		imgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent t) {
//					GameEventhandler.flipImageView(imgv, picSize).play();
//			}
//		});
//		BoardPane.this.getChildren().add(imgv);
	}

	public void setCardPairs(int cardcount) {
		this.cardPairs = cardcount;
	}

	public void setSelCard(Card selCard) {
		this.selCard = selCard;
	}

}
