package com.libertymutual.goforcode.blackjackpractice.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libertymutual.goforcode.blackjackpractice.models.Deck;
import com.libertymutual.goforcode.blackjackpractice.models.GamePlay;
import com.libertymutual.goforcode.blackjackpractice.models.Hand;
import com.libertymutual.goforcode.blackjackpractice.models.Player;

@Controller
@RequestMapping("/")
public class BlackJackController {
	private GamePlay gamePlayService;
	private Deck deck;
	private Hand playerHand, dealerHand;
	private Player user, dealer;
	private String outcome = null;
	private GamePlay gamePlay;

	BlackJackController(GamePlay gamePlayService) {
		this.gamePlayService = gamePlayService;
	}

	@GetMapping("")
	public String showDefault(Model model) {
		//// ˅˅ For testing purposes ˅˅ ///
		gamePlayService = new GamePlay();
		gamePlayService.createUser();
		gamePlayService.createDealer();
		gamePlayService.getUser().setAccountBalance(0);
		return "default";
	}

	@PostMapping("setup")
	public String setupGame(Model model, @RequestParam(name = "playerName") String playerName,
										 @RequestParam(name = "accountBalance") double accountBalance) {

		gamePlayService.setupGame(playerName, accountBalance);
		model.addAttribute("user", gamePlayService.getUser());
		return "bet";
	}

	@PostMapping("round")
	public String startRound(Model model, @RequestParam(name = "betAmount") double betAmount) {
		
		gamePlayService.startNewRound(betAmount);

		model.addAttribute("playerHand", gamePlayService.getUserHand());
		model.addAttribute("dealerHand", gamePlayService.getDealerHand());
		model.addAttribute("user", gamePlayService.getUser());
		model.addAttribute("dealer", gamePlayService.getDealer());
		model.addAttribute("deck", gamePlayService.getDeck());

		return "round";
	}

	@PostMapping("hit")
	public String hit(Model model) {
		String pageDestination = null;
		gamePlayService.hitMe();

		if (gamePlayService.isUserBusted()) {
			gamePlayService.checkAndReshuffleIfNeeded();
			pageDestination = "gameover";
		} else {
			// follow happy path below back to round page
			pageDestination = "round";
		}
		
		
		
		model.addAttribute("outcomeText", gamePlayService.getOutcomeText());
		model.addAttribute("user", gamePlayService.getUser());
		model.addAttribute("dealer", gamePlayService.getDealer());
		model.addAttribute("deck", gamePlayService.getDeck());
		return pageDestination;
	}

	@PostMapping("stay")
	public String stay(Model model) {
		
		gamePlayService.dealerHit();
		gamePlayService.determineOutcome();
		gamePlayService.checkAndReshuffleIfNeeded();
		model.addAttribute("outcomeText", gamePlayService.getOutcome());
		model.addAttribute("user", gamePlayService.getUser());
		model.addAttribute("dealer", gamePlayService.getDealer());
		model.addAttribute("deck", gamePlayService.getDeck());

		return "gameover";
	}

//	@GetMapping("gameover")
//	public String endOfRound(Model model) {
//		
//		model.addAttribute("outcome", outcome);
//		model.addAttribute("user", user);
//		model.addAttribute("dealer", dealer);
//		model.addAttribute("deck", deck);
//
//		return "round";
//	}

}
