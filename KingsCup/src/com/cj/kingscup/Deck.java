package com.cj.kingscup;

import java.util.Random;


/** This class file will generate the Deck of poker cards.
*@author Chi-Han Wang
*@author Jonathan Boston
*/
public class Deck{
	/** The class constant of the cards in deck.
	*/
	public final int CARDS_IN_DECK = 52;
	/** The class constant of times cards should switch positions during the shuffling og th deck.
	*/
	public final int SHUFFLE_SWAPS = 8000;
	/** The instance field of the array of card objects.
	*/
	private Card[] manager;
	/** The instance field of the index of the next card.
	*/
	private int index;
	/** The instance field of the seed.
	*/
	private int seed;
	/** This method will generate the deck of cards.
	*@param seed The seed that will used for shuffle method.
	*/
	public Deck(int seed){
		this.seed = seed;
		manager = new Card [CARDS_IN_DECK];
		for(int i = 0; i < CARDS_IN_DECK;){
			for( int j = Card.LOWEST_VALUE ; j <= Card.HIGHEST_VALUE ; j ++){
				manager[i] = new Card ( j , Card.CLUBS);
				i++;
			}
			for( int k = Card.LOWEST_VALUE ; k <= Card.HIGHEST_VALUE ; k++){
				manager [i] = new Card ( k , Card.DIAMONDS);
				i++;
			}
			for( int l = Card.LOWEST_VALUE ; l <= Card.HIGHEST_VALUE ; l++){
				manager [i] = new Card ( l , Card.SPADES);
				i++;
			}
			for( int m = Card.LOWEST_VALUE ; m <= Card.HIGHEST_VALUE ; m++){
				manager [i] = new Card ( m , Card.HEARTS);
				i++;
			}
		}
	}
	
	
	
	/** This method will shuffle the deck.
	*/
	public void shuffle(){
		if( seed == -1){
			Random rand = new Random();
			for(int i = 0 ; i < SHUFFLE_SWAPS ; i ++){
				int rand1 = rand.nextInt(CARDS_IN_DECK-1);
				int rand2 = rand.nextInt(CARDS_IN_DECK-1);
				Card temp = manager [rand1];
				manager [rand1] = manager [rand2];
				manager [rand2] = temp;
				index = 0;
				
			}
			
			
		}else{
			Random rand = new Random(seed);
			for(int i = 0 ; i < SHUFFLE_SWAPS ; i ++){
				int rand1 = rand.nextInt(CARDS_IN_DECK-1);
				int rand2 = rand.nextInt(CARDS_IN_DECK-1);
				Card temp = manager [rand1];
				manager [rand1] = manager [rand2];
				manager [rand2] = temp;
				index = 0;
				
			}
		}
	
	}
	/** Thie method will return the next card.
	*/
	public Card nextCard(){
		index ++;
		return manager [index] ; 
	
	}
	/** This method will turn the deck object into string format.
	*/
	public String toString(){
		String s = "";
		for( int i = 0 ; i < CARDS_IN_DECK ; i ++){
			Card tempcard = manager[i];
			s += tempcard.toString()+"\t";
		}
		
		return s;
	
	}
	/** This method will test the deck object file.
	*@param args The command argument not used.
	*/
	public static void main (String[] args){
	Deck t = new Deck (500);
	System.out.println(t.toString());
	t.shuffle();
	System.out.println(t.toString());
	
	}
	
	



}
