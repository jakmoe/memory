package game;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Board extends Pane{
	private int cardcount;
	private Card selCard;
	private Board InternalBoard = this;
	
    List<Card> cardList = new ArrayList<Card>();
    List<Integer> cardValues = new ArrayList<Integer>();
	
	public void Initialize(int PlayerCount) {
    	
    for (int i = 0; i < InternalBoard.getCardcount(); i++) {
	        cardValues.add(i);
	        }
      GameMaster.shuffleCards(cardValues);
      
      for (int val : cardValues) {
          Card c = new Card();
          Image img = null;
          c.fillCard(img);
          c.setCard_Id(val);
          c.setOnMouseClicked(new EventHandler<MouseEvent>()
          {
              @Override
              public void handle(MouseEvent t) {
          		if(!c.isMatched()){
              	  GameEventhandler.cardturn(c, InternalBoard);	
        		}
              }
          });
          cardList.add(c);
      }
      InternalBoard.getChildren().addAll(cardList);
    }
	
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
