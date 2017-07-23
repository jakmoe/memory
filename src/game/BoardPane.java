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

/**
 * @author D067928
 * Dies ist eine angepasste Flowpane die benutzt wird um die Karten zu verwalten. Das BoardPane ordnet die Karten bereits in einer
 * festgelegten Größe an und verteilt sie entsprechend auf dem Board.
 */
public class BoardPane extends FlowPane {
	// Anzahl Kartenpaare
	private int cardPairs;
	
	//Bildgröße, dynamisch angepasst auf die Anzahl der Paare
	private double picSize = adjustSize(this.getPrefWidth(), this.getPrefHeight());
	
	//offset für debugging
	private double offset;
	
	//ArrayList mit Card's dynamisch gefüllt
	private List<Card> cardList = new ArrayList<Card>();
	
	//ArrayList mit Integerwerten, verwendet um Karten zu verwalten
	private List<Integer> cardValues = new ArrayList<Integer>();

	/*
	 *  Konstruktor, welcher Attribute wie Caching (Performance) und Alignment (Orientierung) setzt.
	 *  Er ruft die Methode Initialize, welche das Board befüllt und spielbereit macht.
	 */
	public BoardPane() {
		super();
		BoardPane.this.setCache(true);
		BoardPane.this.setCacheShape(true);
		BoardPane.this.setAlignment(Pos.CENTER);
		offset = 0;
		// Start.getJhdl().getModel().getInfo().getCardcount() nimmt die aus dem persistenten Save geladene Kartenzahl als Wert
		Initialize(Start.getJhdl().getModel().getInfo().getCardcount());
	}

	/**
	 * @return cardPairs - Anzahl der Kartenpaare
	 */
	public int getCardPairs() {
		return cardPairs;
	}

	/**
	 * Initialisiert das Board mit anfänglichen Werten und festgelegter Anzahl Kartenpaare
	 * @param cardPairs - nimmt die Kartenpaare entgegen
	 */
	public void Initialize(int cardPairs) {
		// falls das Array der Karten nicht leer ist, wird zurückgesetzt
		if (!cardValues.isEmpty()) {
			cardList.clear();
			cardValues.clear();
		}
		
		/*hier wird das cardPairs attribut gesetzt und in einer Schleife jeweils für jedes i zwei Mal i in cardValues
		eingefügt. Dies ist die Repräsentation von zwei gleichen Karten auf dem Board.*/
		BoardPane.this.setCardPairs(cardPairs);
		for (int i = 1; i < BoardPane.this.getCardPairs() + 1; i++) {
			cardValues.add(i);
			cardValues.add(i);
		}
		/*die ArrayListe mit den Werten wird geshuffled, also gemischt (bei jedem Durchlauf anders - random)
		 Anmerkung: NICHT komplett random, aber für diese Zwecke komplett ausreichend */
//		Collections.shuffle(cardValues);
		/*Es werden für jeden Wert neue Karten erstellt. Dabei wird val auf den zugehörigen Wert in cardValues gesetzt. Dies sorgt
		für je 2 gleiche Bilder beim selben Wert val. (immer genau 2 Karten) */
		for (int val : cardValues) {
			Card cardInstance = new Card(offset, offset, picSize, picSize);
			// Hier werden die Attribute für jede Karte gesetzt
			cardInstance.setFill(IMGhandler.getImage_card(0));
			cardInstance.setCacheHint(CacheHint.SPEED);
			cardInstance.setDepthTest(DepthTest.INHERIT);
			cardInstance.setCard_Id(val);
			cardInstance.setArcHeight(20);
			cardInstance.setArcWidth(20);
			cardInstance.setCache(true);
			/*es wird ein Mouseclick handler gesetzt, der die Karte auf einen Klick reagieren lässt. Dies wird im
			GameEventhandler über cardturn abgewickelt.*/
			cardInstance.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent t) {
					/*Ist die Karte bereits gepaart worden? Ist die Karte umgedreht worden? Ist die Karte gerade in keiner
					 Animation?*/
					if (!cardInstance.isMatched() & !cardInstance.isTurned() & !cardInstance.inAnimation()) {
						GameEventhandler.cardturn(cardInstance);
					}
				}
			});
			cardList.add(cardInstance);
		}
		//Schließlich werden alle Karten aus der cardList auf das Board hinzugefügt.
		BoardPane.this.getChildren().addAll(cardList);
	}
	
	
	/**
	 * @param pairs - Die anzahl der Paare;
	 */
	public void setCardPairs(int pairs) {
		this.cardPairs = pairs;
	}

	/**
	 * Passt die Kartengröße abhängig von der Schwierigkeit an, da diese an die Kartenpaare gekoppelt sind.
	 * Die Werte sind angepasst für ein Full HD Kartenboard mit den Themes die ausgeliefert sind.
	 * Die Werte sind hardcodiert, da keine feste Rechenvorschrift durch das Flowpane möglich ist. 
	 * 
	 * @param width - Die weite des Bildes / Karte
	 * @param height - die Höhe des Bildes / Karte
	 * @return Bildgröße / Kartengröße
	 */
	public double adjustSize(double width, double height) {
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
