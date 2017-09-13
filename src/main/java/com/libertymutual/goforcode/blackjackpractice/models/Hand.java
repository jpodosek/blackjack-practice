package com.libertymutual.goforcode.blackjackpractice.models;

import java.util.ArrayList;
import java.util.List;


public class Hand {
	private ArrayList<Card> hand ;
	//private int handScore;
	
	
	public Hand() {
		hand = new ArrayList<Card>();		
	}
	
	public void addCard(Card card) {	
		hand.add(card);		
	}
	
	public void removeCard(Card card) {
		hand.remove(card);
	}
	
	public int getHandScore() {
		int handScore = 0;
		for (Card card : hand) {	
			handScore += card.getCardValue();		
		}			
		return handScore;		
	}
	
	public ArrayList<Card> getCards() {
		return hand;
	}
	
	//returns number of cards in hand
	public int getHandSize() {
		return hand.size();
	}

//	@Override 
// 	public String toString() { 
//		for (Card c : hand) 
//		c.toString(); 
//		
//		return hand;
//	}	

}
