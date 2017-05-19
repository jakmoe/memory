package game;

import javafx.scene.paint.Color;

public class GameEventhandler {
	public static void cardturn(Card c, Board b){
		if (b.getSelCard() == c) {
			match(c, b.getSelCard());
			
			b.setSelCard(null);
		}
		else if (b.getSelCard() == null) {
			b.setSelCard(c);
		}
		
		//turn Card Animation here
		c.setFill(Color.RED);
		//c.setTranslateZ(value);
		c.setMatched(true);
	}
	
	private static void match(Card c1, Card c2){
		System.out.println("Greyed out with c1 and c2");
		c1.setMatched(true);
		c2.setMatched(true);
	}
}
