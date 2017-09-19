package com.libertymutual.goforcode.blackjackpractice.models;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	
	private ArrayList<Card> hand;
	//int handScore;
	// private int handScore;

	public Hand() {
		hand = new ArrayList<Card>();
		//this.setHandScore(0); //reset handscore to 0;
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public void removeCard(Card card) {
		hand.remove(card);
	}

	public int getBestHandScore() {
		//this.setHandScore(0);
		int handScore = 0;
		
		for (Card card : hand) {
			handScore += card.getCardValue();
		}
		
		//check for aces
		for (Card card : hand) {
			if (handScore > 21 && card.getRank().equals("ACE")) 
					handScore -= 10;		
		}	
			
		return handScore;
		
		
		
//		for (Card card : hand) {
//
//			if (handScore > 21) {
//				if (card.getRank().equals("ACE")) 
//					handScore += card.getAceAlternateValue();
//			} else {
//				handScore += card.getCardValue();
//			}
//		}	
//		return handScore;
	}


	public ArrayList<Card> getCards() {
		return hand;
	}

	// returns number of cards in hand
	public int getHandSize() {
		return hand.size();
	}

	// @Override
	// public String toString() {
	// for (Card c : hand)
	// c.toString();
	//
	// return hand;
	// }

}
