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
	private double accountBalance;
	private double betAmount;
	private Player player, dealer;
	private Hand playerHand, dealerHand;
	private int cardsLeft;
	private int playerHandScore;
	private Model model;
	
	
	BlackJackController() {
		deck = new Deck();
		cardsLeft = deck.getCardsLeft();
		player = new Player();
		dealer = new Player();
	}
	
	@GetMapping("")
	public String showDefault(Model model) {
		return "default";
			
	}
	
	@PostMapping("setup")
	public String setUpGame(Model model, @RequestParam(name = "playerName") String playerName, @RequestParam(name = "accountBalance") double accountBalance  ) {
		player.setAccountBalance(accountBalance);
		this.accountBalance = player.getAccountBalance();
		player.setPlayerName(playerName);
		this.playerName = player.getPlayerName();
		model.addAttribute("playerName", this.playerName);
		model.addAttribute("accountBalance", this.accountBalance);
		return "bet";
			
	}
	
	@PostMapping("round")
	public String startRound(Model model, @RequestParam(name = "betAmount") double betAmount  ) {	
		this.betAmount = betAmount;	
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
		
//		model.addAttribute("playerName", playerName);
//		model.addAttribute("playerHand", playerHand);
//		model.addAttribute("dealerHand", dealerHand);
//		model.addAttribute("betAmount", this.betAmount);
//		model.addAttribute("playerHandScore", playerHandScore);
//		model.addAttribute("accountBalance", accountBalance);
//		model.addAttribute("cardsLeft", cardsLeft);
		storeGameInfo(model);
		return "round";
			
	}
		
		@PostMapping("hit")
		public String hit(Model model) {
			
			playerHand.addCard(deck.drawCard());
			cardsLeft = deck.getCardsLeft();
			playerHandScore = playerHand.getHandScore();
			
		
			
//			model.addAttribute("playerName", playerName);
//			model.addAttribute("playerHand", playerHand);
//			model.addAttribute("dealerHand", dealerHand);
//			model.addAttribute("betAmount", betAmount);
//			model.addAttribute("playerHandScore", playerHandScore);
//			model.addAttribute("accountBalance", accountBalance);
//			model.addAttribute("cardsLeft", cardsLeft);
			storeGameInfo(model);
			return "round";
		}
		
		
		@PostMapping("stay")
		public String stay(Model model) {
			
			//while dealer score is < 16
			//dealer adds cards
			//calculate dealer score
			
			cardsLeft = deck.getCardsLeft();
			playerHandScore = playerHand.getHandScore();
			
	
//			model.addAttribute("playerName", playerName);
//			model.addAttribute("playerHand", playerHand);
//			model.addAttribute("dealerHand", dealerHand);
//			model.addAttribute("betAmount", betAmount);
//			model.addAttribute("playerHandScore", playerHandScore);
//			model.addAttribute("accountBalance", accountBalance);
//			model.addAttribute("cardsLeft", cardsLeft);
			storeGameInfo(model);
			return "round";
		}
		
		public void storeGameInfo(Model model) {
			//passing in a model to function will set replace model for entire class;
			this.model = model; 
			model.addAttribute("playerName", playerName);
			model.addAttribute("playerHand", playerHand);
			model.addAttribute("dealerHand", dealerHand);
			model.addAttribute("betAmount", betAmount);
			model.addAttribute("playerHandScore", playerHandScore);
			model.addAttribute("accountBalance", accountBalance);
			model.addAttribute("cardsLeft", cardsLeft);
			
		}
		
		
}
