package com.libertymutual.goforcode.blackjackpractice.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service // this will allow dependency injection, so every controller has the same
			// GamPlay() instance
public class GamePlay {
	private Player user;
	private Player dealer;
	private Hand userHand;
	private Hand dealerHand;
	private String outcomeText = "";
	private double accountBalance;
	private double betAmount;
	private int dScore, pScore;

	public GamePlay() {
	};

	private Deck deck; // make deck static if using multiple GamePlay instances

	public GamePlay(Player player, Player dealer, Hand playerHand, Hand dealerHand) {
		this.user = player;
		this.dealer = dealer;
		this.userHand = playerHand;
		this.dealerHand = dealerHand;
	}

	public GamePlay(Deck deck, Player player, Player dealer, Hand playerHand, Hand dealerHand) {
		this.deck = deck;
		this.user = player;
		this.dealer = dealer;
		this.userHand = playerHand;
		this.dealerHand = dealerHand;
	}

	public GamePlay(Hand playerHand, Hand dealerHand) {
		this.userHand = playerHand;
		this.dealerHand = dealerHand;
	}

	public GamePlay(Player player, Player dealer) {
		this.user = player;
		this.dealer = dealer;
	}

	public void setupGame(String playerName, double accountBalance) {
		deck = new Deck();
		user.setPlayerName(playerName);
		user.setAccountBalance(accountBalance);
	}

	public void startNewRound(double betAmount) {
		// ArrayList<Hand> hands = new ArrayList<Hand>();
		user.setBetAmount(betAmount);
		user.setAccountBalance(user.getAccountBalance() - betAmount); // subtract bet from player's account balance

		// Create hands and deal cards
		userHand = new Hand();
		dealerHand = new Hand();
		userHand.addCard(deck.drawCard()); // draw card from deck and add to hand
		dealerHand.addCard(deck.drawCard());
		userHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());

		user.setHand(userHand);
		dealer.setHand(dealerHand);
	}

	public void hitMe() {
		userHand.addCard(deck.drawCard());
		user.setHand(userHand);
	}

	public boolean isUserBusted() {
		if (userHand.getBestHandScore() > 21) {
			setOutcomeText("You busted!!!");
			user.setBetAmount(0.00);
			accountBalance = user.getAccountBalance() - user.getBetAmount();
			user.setAccountBalance(accountBalance);
			return true;
		}
		return false;
	}

	public void determineOutcome() {
		accountBalance = user.getAccountBalance();
		betAmount = user.getBetAmount();
		pScore = userHand.getBestHandScore();
		dScore = dealerHand.getBestHandScore();

		if (dScore > 21) {
			outcomeText = "Dealer busted! You earn 2 to 1"; // 2 to 1, so if you bet $20, the dealer gives you $20 more
			user.setAccountBalance(accountBalance + (2 * betAmount));

		} else if (dScore == pScore) {
			outcomeText = "Push! You keep your bet."; // both at 21 or less; same outcome

		} else if (dScore > pScore) {
			outcomeText = "Dealer wins! You lose your bet."; // dealer wins, you lose your bet
			user.setAccountBalance(accountBalance - betAmount);

		} else if (dScore != 21 && pScore == 21) {
			outcomeText = "Blackjack! You earn 3 to 2"; // 3 to 2, so if you bet $20, the dealer pays you $30
			user.setAccountBalance(accountBalance + (1.5 * betAmount));

		} else if (dScore < pScore) {
			outcomeText = "Winner! You earn 2 to 1."; // 2 to 1, so if you bet $20, the dealer gives you $20 more
			user.setAccountBalance(accountBalance + (2 * betAmount));

		} else {
			outcomeText = "this should be unreachable logic";
		}

		user.setBetAmount(0.00);
		setOutcome(outcomeText);

	}

	public void createDeck() {
		deck = new Deck();
		deck.shuffle();
	}

	public void createUser() {
		user = new Player();
	}

	public void createDealer() {
		dealer = new Player();
	}

	public String getOutcome() {
		return outcomeText;
	}

	public void setOutcome(String outcome) {
		this.outcomeText = outcome;
	}

	public Player getUser() {
		return user;
	}

	public void setUser(Player player) {
		this.user = player;
	}

	public Player getDealer() {
		return dealer;
	}

	public void setDealer(Player dealer) {
		this.dealer = dealer;
	}

	public Hand getUserHand() {
		return userHand;
	}

	public void setUserHand(Hand playerHand) {
		this.userHand = playerHand;
	}

	public Hand getDealerHand() {
		return dealerHand;
	}

	public void setDealerHand(Hand dealerHand) {
		this.dealerHand = dealerHand;
	}

	public String getOutcomeText() {
		return outcomeText;
	}

	public void setOutcomeText(String outcomeText) {
		this.outcomeText = outcomeText;
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

	public int getdScore() {
		return dScore;
	}

	public void setdScore(int dScore) {
		this.dScore = dScore;
	}

	public int getpScore() {
		return pScore;
	}

	public void setpScore(int pScore) {
		this.pScore = pScore;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
}
