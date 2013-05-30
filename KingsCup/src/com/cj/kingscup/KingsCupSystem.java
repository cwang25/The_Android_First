package com.cj.kingscup;

import java.util.Random;

/**
 * The main system class that will hold mostly the all game's functions here. Then let MainActivity class to call the method that will inteact with user.
 * @author Chi-Han Wang
 * @author Jonathan Poston
 */
public class KingsCupSystem {
	/**
	 * The array of the cards, The deck that will be used.
	 */
	private Card[] deck;
	/**
	 * The time of the shuffle swap higher is better.
	 */
	private int shuffle_swaps = 8000;
	/**
	 * The total number of the cards in the deck.
	 */
	public final int CARDS_IN_DECK = 52;
	/**
	 * The index that will act like pointer for showing where the cards that user is picking.
	 */
	private int index;
	
	/**
	 * The object of the system, start on the whole system.
	 */
	public KingsCupSystem(){
		
	}
	
	

	/**
	 * shuffle the deck, which also means restart the game.
	 */
	public void shuffle(){
		Random rand = new Random();
		for(int i = 0 ; i < shuffle_swaps ; i ++){
			int rand1 = rand.nextInt(CARDS_IN_DECK-1);
			int rand2 = rand.nextInt(CARDS_IN_DECK-1);
			Card temp = deck [rand1];
			deck [rand1] = deck [rand2];
			deck [rand2] = temp;
			index = 0;
			
		}
	}
}
