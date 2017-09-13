package com.libertymutual.goforcode.blackjackpractice.models;

public class Player {

	private String playerName;
	private double accountBalance;
	private double betAmount;
	private Hand hand;
	
	public Player() {
		 this.playerName = null;
		 this.accountBalance = 0.0;
		 this.betAmount = 0.0;
		}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(double betAmount) {
		this.betAmount = betAmount;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	public int getPlayerHandScore() {
		return hand.getHandScore();
	}
	
	
	
}
