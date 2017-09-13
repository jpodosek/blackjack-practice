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

	BlackJackController() {
		deck = new Deck();
		// cardsLeft = deck.getCardsLeft();
		player = new Player();
		dealer = new Player();
	}

	@GetMapping("")
	public String showDefault(Model model) {
		//// ˅˅ For testing purposes  ˅˅  ///
		player.setAccountBalance(0);
		deck = new Deck();
		//// ^^ *******************  ^^  ///
		
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

		player.setHand(playerHand); // add hands to player objects; only need to add player to model this way, and
									// it makes more intuitive sense
		dealer.setHand(dealerHand);
		// System.out.println("dealer hand size:" + dealerHand.getHandSize());
		// System.out.println("dealer hand - get cards:" + dealerHand.getCards());
		//*****Testing
		System.out.println("playerHand.getCards()" + playerHand.getCards());
		System.out.println("playerHand.getScore()" + playerHand.getHandScore());
		System.out.println("player.getPlayerHandScore()" + player.getPlayerHandScore());
		System.out.println("player.getHand().getHandScore()" + player.getHand().getHandScore());
		//
		
		model.addAttribute("player", player);
		model.addAttribute("dealer", dealer);
		model.addAttribute("deck", deck);

		return "round";
	}

	@PostMapping("hit")
	public String hit(Model model) {
		String pageDestination = null;
		playerHand.addCard(deck.drawCard());
		//player.setHand(playerHand);
		
		System.out.println("playerHand.getCards()" + playerHand.getCards());
		System.out.println("playerHand.getScore()" + playerHand.getHandScore());
		System.out.println("player.getPlayerHandScore()" + player.getPlayerHandScore());
		System.out.println("player.getHand().getHandScore()" + player.getHand().getHandScore());
		//BUST
		if (playerHand.getHandScore() > 21)	 {
				player.setBetAmount(0.00);
				double accountBalance = player.getAccountBalance() - player.getBetAmount();
				player.setAccountBalance(accountBalance);

				pageDestination = "gameover";
		} else {
				//follow happy path below back to round page	
			pageDestination = "round";
		}
			
		model.addAttribute("player", player);
		model.addAttribute("dealer", dealer);
		//model.addAttribute("playerHand", playerHand);
		model.addAttribute("deck", deck);
		return pageDestination;
	}

	@PostMapping("stay")
	public String stay(Model model) {

		// while dealer score is < 16
		// dealer adds cards
		// calculate dealer score

		model.addAttribute("player", player);
		model.addAttribute("dealer", dealer);
		//model.addAttribute("dealerHand", dealerHand);
		//model.addAttribute("playerHand", playerHand);
		model.addAttribute("deck", deck);

		return "round";
	}

}
