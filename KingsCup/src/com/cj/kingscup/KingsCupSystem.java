package com.cj.kingscup;

import java.util.Random;

/**
 * The main system class that will hold mostly the all game's functions here.
 * Then let MainActivity class to call the method that will interact with user.
 * 
 * @author Chi-Han Wang
 * @author Jonathan Poston
 */
public class KingsCupSystem {
	private static final int CARDS_IN_SUITS = 13;
	private static final int NUM_OF_SUITS = 4;
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
	 * The index that will act like pointer for showing where the cards that
	 * user is picking.
	 */
	private int index;

	/**
	 * The object of the system, start on the whole system.
	 */
	public KingsCupSystem() {
		deck = new Card[CARDS_IN_DECK];
		char[] suits = { Card.CLUBS, Card.DIAMONDS, Card.HEARTS, Card.SPADES };
		for (int i = 0; i < NUM_OF_SUITS; i++) {
			for (int j = 0; j <= CARDS_IN_SUITS; j++) {
				Card temp = new Card(j+2, suits[i], null);
				deck[j+(12*(i))] = temp;
			}
		}
		shuffle();

	}

	/**
	 * shuffle the deck, which also means restart the game.
	 */
	public void shuffle() {
		// Create a new Random number generator
		Random rand = new Random();
		// swap cards at random places shuffle_swaps number of times
		for (int i = 0; i < shuffle_swaps; i++) {
			// pick two random numbers
			swap(rand.nextInt(CARDS_IN_DECK - 1),
					rand.nextInt(CARDS_IN_DECK - 1));
		}
		index = 0;
	}

	/**
	 * Swaps two cards A and B
	 * 
	 * @param int one of the cards to swap
	 * @param int one of the cards to swap
	 */
	public void swap(int A, int B) {
		Card temp = deck[A];
		deck[A] = deck[B];
		deck[B] = temp;
	}

	/**
	 * Gets the next card
	 */
	public Card getNextCard() {
		return deck[index++];

	}
}
