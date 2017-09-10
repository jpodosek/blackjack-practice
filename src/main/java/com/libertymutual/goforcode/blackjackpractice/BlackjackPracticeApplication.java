package com.libertymutual.goforcode.blackjackpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.libertymutual.goforcode.blackjackpractice.models.Deck;
import com.libertymutual.goforcode.blackjackpractice.models.Hand;

@SpringBootApplication
public class BlackjackPracticeApplication {

	
	
	public static void main(String[] args) {
		
		SpringApplication.run(BlackjackPracticeApplication.class, args);
		
//		Deck deck = new Deck();
//		
//		Hand hand = new Hand();
//		
//		hand.addCard(deck.drawCard());
//		hand.addCard(deck.drawCard());
//		
//		int handScore = hand.getHandScore();
//		System.out.println(handScore);
	}
}
