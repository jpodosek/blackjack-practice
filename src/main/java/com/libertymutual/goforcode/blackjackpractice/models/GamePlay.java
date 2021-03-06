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
	private Deck deck;
	private List<Card> discardPile;
	private boolean isReshuffleNeeded;
	
	public GamePlay() {};

	public void setupGame(String playerName, double accountBalance) {
		deck = new Deck();
		isReshuffleNeeded = false;
		discardPile = new ArrayList<Card>();
		user.setPlayerName(playerName);
		user.setAccountBalance(accountBalance);
	}

	public void startNewRound(double betAmount) {
		this.setOutcomeText(null);
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
		user.getHand().addCard(deck.drawCard());
		user.setHand(userHand);
	}

	public boolean isUserBusted() {
		if (userHand.getBestHandScore() > 21) {
			setOutcomeText("You busted! You lose your bet.");
			user.setBetAmount(0.00);
			accountBalance = user.getAccountBalance() - user.getBetAmount();
			user.setAccountBalance(accountBalance);
			addCardsToDiscardPile();
			return true;
		}
		return false;
	}

	public void dealerHit() {

		while (dealerHand.getBestHandScore() < 16) {
			dealerHand.addCard(deck.drawCard());
		}
		dealer.setHand(dealerHand);
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

		this.addCardsToDiscardPile();
		user.setBetAmount(0.00);
		this.setOutcomeText(outcomeText);
	}

	public void addCardsToDiscardPile() {
		ArrayList<Card> currentUserHand = userHand.getCards();
		ArrayList<Card> currentDealerHand = dealerHand.getCards();

		for (Card card : currentUserHand) {
			discardPile.add(card);
		}

		for (Card card : currentDealerHand) {
			discardPile.add(card);
		}

		// userHand.clearHand();
		// dealerHand.clearHand();
		System.out.println(discardPile.size());
	}

	public void reshuffleDeck() {
		List<Card> currentDeck = deck.getCards();

		for (Card card : discardPile) {
			currentDeck.add(card);
		}
		discardPile = new ArrayList<Card>();
		deck.shuffle();
	}

	public void checkAndReshuffleIfNeeded() {
		if (deck.isReshuffleNeeded())
			reshuffleDeck();
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

	public double getBetAmount(Player player) {
		return player.getBetAmount();
	}

	public void setBetAmount(double betAmount, Player player) {
		player.setBetAmount(betAmount);
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

	public List<Card> getDiscardPile() {
		return discardPile;
	}

	public void setDiscardPile(List<Card> discardPile) {
		this.discardPile = discardPile;
	}

	public int getCardsLeft() {
		if (deck != null) {
			return deck.getCardsLeft();
		}
		return 0;
	}

}
