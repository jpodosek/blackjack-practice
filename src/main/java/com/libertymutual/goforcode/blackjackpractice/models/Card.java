package com.libertymutual.goforcode.blackjackpractice.models;


public class Card {
	
	private String rank;
	private String suit;
	private int value;
	
	public Card (String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Card () {};

	public int getCardValue() {
		String rank = getRank();
		
		if (rank.equals("JACK") || rank.equals("QUEEN") || rank.equals("KING")) {
			value = 10;
		} else if (rank.equals("ACE")){
			value = 11;
		} else {
			value = Integer.parseInt(rank);
		}
		return value;
	}
	
	public int getAceAlternateValue() {
		//Only for Aces
		//dont want to do value=1 here bc all cards would have this value;
		return 1;
	}
	
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getRank() {
		return rank;
	}
	

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public String getSuitSymbol() {
		String unicodeSuit = "";
		switch (this.getSuit()) {
		case "CLUBS": unicodeSuit = "&clubs;";
				break;
		case "SPADES": unicodeSuit = "&spades;";
				break;
		case "DIAMONDS": unicodeSuit = "&diams;";
				break;
		case "HEARTS": unicodeSuit = "&hearts;";
				break;
		default: unicodeSuit = "";
				break;
		}
		return unicodeSuit;	
	}
	
	@Override 
 	public String toString() { 
		return this.getRank() + " of " + this.getSuit(); 
 	} 
}
