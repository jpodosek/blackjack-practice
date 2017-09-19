package com.libertymutual.goforcode.blackjackpractice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libertymutual.goforcode.blackjackpractice.models.Deck;
import com.libertymutual.goforcode.blackjackpractice.models.Hand;
import com.libertymutual.goforcode.blackjackpractice.models.Player;

@Controller
@RequestMapping("/")
public class BlackJackController {
	private Deck deck;
	private Hand playerHand, dealerHand;
	private Player player, dealer;
	//private boolean isUserTurn;
	private String outcome = null;

	BlackJackController() {
		deck = new Deck();
		// cardsLeft = deck.getCardsLeft();
		player = new Player();
		dealer = new Player();
	}

	@GetMapping("")
	public String showDefault(Model model) {
		//// ˅˅ For testing purposes ˅˅ ///
		player.setAccountBalance(0);
		deck = new Deck();
		//// ^^ ******************* ^^ ///
		return "default";
	}

	@PostMapping("setup")
	public String setUpGame(Model model, @RequestParam(name = "playerName") String playerName,
			@RequestParam(name = "accountBalance") double accountBalance) {
		player.setAccountBalance(accountBalance);
		player.setPlayerName(playerName);
		model.addAttribute("player", player);
		return "bet";

	}

	@PostMapping("round")
	public String startRound(Model model, @RequestParam(name = "betAmount") double betAmount) {
		//isUserTurn = true;
		player.setBetAmount(betAmount);
		// this.betAmount = betAmount;
		player.setAccountBalance(player.getAccountBalance() - betAmount); // subtract bet from player's account balance

		// Create hands and deal cards
		playerHand = new Hand();
		dealerHand = new Hand();
		playerHand.addCard(deck.drawCard()); // draw card from deck and add to hand
		dealerHand.addCard(deck.drawCard());
		playerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());

		player.setHand(playerHand);
		dealer.setHand(dealerHand);
		model.addAttribute("player", player);
		model.addAttribute("dealer", dealer);
		model.addAttribute("deck", deck);

		return "round";
	}

	@PostMapping("hit")
	public String hit(Model model) {
		String pageDestination = null;
		playerHand.addCard(deck.drawCard());
		player.setHand(playerHand);

		// BUST
		if (playerHand.getBestHandScore() > 21) {
			outcome = "You busted!!!";
			player.setBetAmount(0.00);
			double accountBalance = player.getAccountBalance() - player.getBetAmount();
			player.setAccountBalance(accountBalance);

			pageDestination = "gameover";
		} else {
			// follow happy path below back to round page
			pageDestination = "round";
		}
		model.addAttribute("outcome", outcome);
		model.addAttribute("player", player);
		model.addAttribute("dealer", dealer);
		model.addAttribute("deck", deck);
		System.out.print(pageDestination);
		return pageDestination;
	}

	@PostMapping("stay")
	public String stay(Model model) {
		//isUserTurn = false;
		while (dealerHand.getBestHandScore() < 16) {
			dealerHand.addCard(deck.drawCard());

		}
		dealer.setHand(dealerHand);

		// put check win logic method here
		if (dealerHand.getBestHandScore() > 21) {
			outcome = "Dealer busted! You earn 2 to 1"; // 2 to 1, so if you bet $20, the dealer gives you $20 more
			player.setAccountBalance(player.getAccountBalance() + 2 * (player.getBetAmount()));

		} else if (dealerHand.getBestHandScore() == playerHand.getBestHandScore()) {
			outcome = "Push! You keep your bet."; // both at 21 or less; same outcome

		} else if (dealerHand.getBestHandScore() > playerHand.getBestHandScore()) {
			outcome = "Dealer wins! You lose your bet."; // dealer wins, you lose your bet
			player.setAccountBalance(player.getAccountBalance() - (player.getBetAmount()));
		
		} else if (dealerHand.getBestHandScore() != 21 && playerHand.getBestHandScore() == 21) {
			outcome = "Blackjack! You earn 3 to 2"; // 3 to 2, so if you bet $20, the dealer pays you $30
			player.setAccountBalance(player.getAccountBalance() + 1.5 * (player.getBetAmount()));
		
		} else if (dealerHand.getBestHandScore() < playerHand.getBestHandScore()) {
			outcome = "Winner! You earn 2 to 1."; // 2 to 1, so if you bet $20, the dealer gives you $20 more
			player.setAccountBalance(player.getAccountBalance() + 2 * (player.getBetAmount()));
		
		} else {
			outcome = "this should be unreachable logic";
		}

		player.setBetAmount(0.00);

		model.addAttribute("outcome", outcome);
		model.addAttribute("player", player);
		model.addAttribute("dealer", dealer);
		model.addAttribute("deck", deck);

		return "gameover";
	}

	@GetMapping("gameover")
	public String endOfRound(Model model) {
		
		model.addAttribute("outcome", outcome);
		model.addAttribute("player", player);
		model.addAttribute("dealer", dealer);
		model.addAttribute("deck", deck);

		return "round";
	}

}
