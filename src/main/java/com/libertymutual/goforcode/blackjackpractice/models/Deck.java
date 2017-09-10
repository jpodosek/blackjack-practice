package com.libertymutual.goforcode.blackjackpractice.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> deck;

	String[] suits = { "CLUBS", "SPADES", "DIAMONDS", "HEARTS" };
	String[] ranks = { "ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING" };

	public Deck() {

		deck = new ArrayList<Card>();
		createDeck();
		shuffle();
		System.out.println(deck);
	}

	public void createDeck() {

		for (String suit : suits)
			for (String rank : ranks) {
				Card card = new Card(rank, suit);
				deck.add(card);
			}
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public Card drawCard() {
		Card card;
		if (deck.size() > 0) {
			card = deck.get(deck.size() - 1); // draw the last card from the deck;
			deck.remove(deck.size() - 1);
		} else {
			System.out.println("The deck ran out of cards!");
			return null;
		}
		return card;

	}

	public int getCardsLeft() {
		return deck.size();
	}


}
