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
		
//		switch(rank)
//		{
//		case "ACE":
//			return 11;
//		case "2":
//			return 2;
//		case "3":
//			return 3;
//		case "4":
//			return 4;
//		case "5":
//			return 5;
//		case "6":
//			return 6;
//		case "7":
//			return 7;
//		case "8":
//			return 8;
//		case "9":
//			return 9;
//		case "10":
//			return 10;
//		case "JACK":
//			return 10;
//		case "QUEEN":
//			return 10;
//		case "KING":
//			return 10;
//		default:
//			System.err.println("Could not find value of card.");
//			return 0;
	
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
	
	@Override 
 	public String toString() { 
		return this.getRank() + " of " + this.getSuit(); 
 	} 
}
