package com.cj.kingscup;

/** This class will make a card with suit and value.
*@author Chi-Han Wang
*@author Jonathan Boston
*/
public class Card implements Comparable {
   /**
   * Used for sorting the cards in a player's hand in a game of Poker.
   * Cards are sorted first by value, then by suit.
   * @param o The Card object to which this Card is being compared.
   * @return negative value, if this Card should be before the other Card,
   *         positive value, if this Card should be after the other Card,
   *         0, if this Card has the same suit and value as the other Card,
   *         1, if the object is not a Card
   */
  public int compareTo(Object o) {
    if(o instanceof Card) {
      Card other = (Card)o;

      if (value != other.value) {
        return value - other.value;
      }
      else {
        return suit - other.suit;
      }
    }
    return 1; //Should not reach here.
  }
  /** The class constant of Clubs.
  */
  public static final char CLUBS = 'c';
  /** The class constant of Diamonds.
  */
  public static final char DIAMONDS = 'd';
  /** The class constant of Spades.
  */
  public static final char SPADES = 's';
  /** The class constant of Hearts.
  */
  public static final char HEARTS = 'h';
  /** The class constant of lowest value of the cards.
  */
  public static final int LOWEST_VALUE = 2;
  /** The class constant of highest value of the cards.
  */
  public static final int HIGHEST_VALUE = 14;	
  /** The instance field of card value.
  */
  private int value;
  /** The instance field of card suit.
  */
  private char suit;
  /** This method will generate a card object.
  *@param value The value of the card.
  *@param suit The suit of the card.
  */
  public Card(int value, char suit){
    if((value > HIGHEST_VALUE || value < LOWEST_VALUE)){
      throw new IllegalArgumentException("Invalid value");
    }
    if(suit != 'c'&& suit != 'd' && suit !='s' && suit != 'h') {
      throw new IllegalArgumentException("Invalid suit");
    }
    this.value = value;
    this.suit = suit;
  }
  /** This method will offer the suit of the card.
  */
  public char getSuit(){
    return suit;
  
  }
  /** This method will offer the value of the card.
  */
  public int getValue(){
    return value;
  
  }
  /** This method will transform the card object into the string format.
  */
  public String toString(){
    String s = "";
    s+=suit;
	  s+=value;
    return s;
  
  }
  /** This method will test the program to see if it runs correctly.
  *@param args The command argument not used.
  */
  public static void main (String[] args){
    Card hand = new Card ( 13, CLUBS);
	  System.out.println(hand.toString());
  
  
  }
}




