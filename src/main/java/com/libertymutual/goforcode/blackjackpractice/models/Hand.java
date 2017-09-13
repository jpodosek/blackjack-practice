package com.libertymutual.goforcode.blackjackpractice.models;

import java.util.ArrayList;
import java.util.List;


public class Hand {
	private ArrayList<Card> hand ;
	private int handScore;
	
	
	public Hand() {
		hand = new ArrayList<Card>();
		handScore = 0;
		
	}
	
	public void addCard(Card card) {	
		hand.add(card);		
	}
	
	public void removeCard(Card card) {
		hand.remove(card);
	}
	
	public int getHandScore() {
		for (Card card : hand) {	
			handScore += card.getCardValue();		
		}			
		return handScore;		
	}
	
	public void setHandScore(int handScore) {
		this.handScore = handScore;
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
