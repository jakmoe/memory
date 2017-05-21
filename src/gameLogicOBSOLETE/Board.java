package gameLogicOBSOLETE;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("serial")
public class Board extends JFrame {

	private List<Card> cards;
	private Card selectedCard;
	private Card c1;
	private Card c2;
	private Timer t;

	public Board() {

		int pairs = 8;
		List<Card> cardsList = new ArrayList<Card>();
		List<Integer> cardVals = new ArrayList<Integer>();

		for (int i = 0; i < pairs; i++) {
			cardVals.add(i);
			cardVals.add(i);
		}
		Collections.shuffle(cardVals);

		for (int val : cardVals) {
			Card c = new Card();
			ImageIcon image = new ImageIcon("C:\\Users\\D067928\\workspace\\Memory\\src\\Media\\2.gif");
			image.setImage(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
			c.setIcon(image);
			c.setId(val);
			c.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					selectedCard = c;
					doTurn();
				}
			});
			cardsList.add(c);
		}
		this.cards = cardsList;
		// set up the timer
		t = new Timer(750, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				checkCards();
			}
		});

		t.setRepeats(false);

		// set up the board itself
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(4, 5));
		for (Card c : cards) {
			pane.add(c);
		}
		setTitle("Memory Match");
	}

	public void doTurn() {
		if (c1 == null && c2 == null) {
			c1 = selectedCard;
			c1.setText(String.valueOf(c1.getId()));
		}

		if (c1 != null && c1 != selectedCard && c2 == null) {
			c2 = selectedCard;
			c2.setText(String.valueOf(c2.getId()));
			t.start();

		}
	}

	public void checkCards() {
		if (c1.getId() == c2.getId()) {// match condition
			c1.setEnabled(false); // disables the button
			c2.setEnabled(false);
			c1.setMatched(true); // flags the button as having been matched
			c2.setMatched(true);

			if (this.isGameWon()) {
				JOptionPane.showMessageDialog(this, "You win!");
				System.exit(0);
			}
		} else {
			c1.setText(""); // 'hides' text
			c2.setText("");
		}
		c1 = null; // reset c1 and c2
		c2 = null;
	}

	public boolean isGameWon() {
		for (Card c : this.cards) {
			if (c.getMatched() == false) {
				return false;
			}
		}
		return true;
	}

}
