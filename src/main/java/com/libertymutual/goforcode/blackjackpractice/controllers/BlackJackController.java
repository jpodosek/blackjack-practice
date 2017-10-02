package com.libertymutual.goforcode.blackjackpractice.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libertymutual.goforcode.blackjackpractice.models.GamePlay;
import com.libertymutual.goforcode.blackjackpractice.models.Player;

@Controller
@RequestMapping("/")
public class BlackJackController {
	private GamePlay gps;
	
	BlackJackController() {
		System.out.println("BlackJackController ran.");
		gps = new GamePlay();
		gps.createDealer();
		gps.createUser();	
	}

	@GetMapping("")
	public String playGame(Model model) {
		Player user = gps.getUser();
		Player dealer = gps.getDealer();
		double betAmount = user.getBetAmount();
		double accountBalance = user.getAccountBalance();
		
		model.addAttribute("preGame", user.getPlayerName() == null);
		model.addAttribute("gameOn", user.getPlayerName() != null);
		model.addAttribute("betState", betAmount == 0 && accountBalance > 0);
		model.addAttribute("roundState", betAmount != 0 && gps.getCardsLeft() > 0);	
		model.addAttribute("user", user);
		model.addAttribute("dealer", dealer);
		model.addAttribute("deck", gps.getDeck());
		model.addAttribute("currentBet", betAmount);	
		model.addAttribute("outcomeText", gps.getOutcomeText());
		model.addAttribute("showDealerHandScore", betAmount == 0 && user.getPlayerName() != null);
		System.out.print("betAmount: " + betAmount);
		System.out.println("user.getPlayerName(): " + user.getPlayerName());
		return "default";
	}

	@PostMapping("setup")
	public String setupGame(String playerName, double accountBalance) {
		//setupGame input parameters above will automagically bind to the html form input attributes w/ the same name
		gps.createUser();
		gps.setupGame(playerName, accountBalance);
		gps.createDealer();
		gps.setAccountBalance(0);
		return "redirect:/";
	}
	
	@PostMapping("bet")
	public String startRound(double betAmount) {
		gps.startNewRound(betAmount);
		return "redirect:/";
	}

	@PostMapping("hit")
	public String hit(Model model) {
		gps.hitMe();
		if (gps.isUserBusted())
			gps.checkAndReshuffleIfNeeded();	
		return "redirect:/";
	}

	@PostMapping("stay")
	public String stay(Model model) {	
		gps.dealerHit();
		gps.determineOutcome();
		gps.checkAndReshuffleIfNeeded();
		return "redirect:/";
	}
}
