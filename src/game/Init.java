package game;


import java.util.ArrayList;
import java.util.List;

import game.Card;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class Init {
	//gets Instance of Board
	Board GameBoard = new Board();
	
    List<Card> cardList = new ArrayList<Card>();
    List<Integer> cardValues = new ArrayList<Integer>(); 
	
    public void Initialize(){
    	
    for (int i = 0; i < GameBoard.getCardcount(); i++) {
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
              	  Eventhandler.cardturn(c, GameBoard);	
        		}
              }
          });
          cardList.add(c);
      }

      GameBoard.getChildren().addAll(cardList);
      
    }

}