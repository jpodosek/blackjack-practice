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
	private String playerName;
	private Double accountBalance;
	private Player player, dealer;
	private Hand playerHand, dealerHand;
	private int cardsLeft;
	
	BlackJackController() {
		deck = new Deck();
		cardsLeft = deck.getCardsLeft();
		player = new Player();
		dealer = new Player();
//		playerHand = new Hand();
//		
//		playerHand.addCard(deck.drawCard());
//		playerHand.addCard(deck.drawCard());
//		
//		playerHand.getHandScore();
//		System.out.println(playerHand.getHandScore());
	}
	
	@GetMapping("")
	public String showDefault(Model model) {
		//model.addAttribute("showUserNameInput", showUserNameInput);
		//model.addAttribute("name", playerName);
		//model.addAttribute("welcome", "hello");
		return "default";
			
	}
	
	@PostMapping("setup")
	public String setUpGame(Model model, @RequestParam(name = "playerName") String playerName, @RequestParam(name = "accountBalance") double accountBalance  ) {
		player.setAccountBalance(accountBalance);
		this.accountBalance = player.getAccountBalance();
		player.setPlayerName(playerName);
		this.playerName = player.getPlayerName();
		model.addAttribute("playerName", playerName);
		model.addAttribute("accountBalance", accountBalance);
		return "bet";
			
	}
	
	@PostMapping("round")
	public String playRound(Model model, @RequestParam(name = "betAmount") double betAmount  ) {
		int playerHandScore;
		
		accountBalance -= betAmount;
		
		//Create hands and deal cards
		playerHand = new Hand();
		dealerHand = new Hand();
		playerHand.addCard(deck.drawCard()); //draw card from deck and add to hand
		dealerHand.addCard(deck.drawCard());
		playerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());
		
		cardsLeft = deck.getCardsLeft();
		
		playerHandScore = playerHand.getHandScore();	
		
		model.addAttribute("playerName", playerName);
		model.addAttribute("playerHand", playerHand);
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("betAmount", betAmount);
		model.addAttribute("playerHandScore", playerHandScore);
		model.addAttribute("accountBalance", accountBalance);
		model.addAttribute("cardsLeft", cardsLeft);
		return "round";
			
	}
//	
//	@GetMapping("round")
//	public String playRound1(Model model) {
//		model.addAttribute("playerName", playerName);
//		model.addAttribute("betAmount", betAmount);
//		model.addAttribute("playerHand", playerHand);
//		model.addAttribute("playerHandScore", playerHandScore);
//		model.addAttribute("accountBalance", accountBalance);
//		return "round";
//	}	
	
}
