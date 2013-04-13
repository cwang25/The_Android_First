package com.cj.numbermagic;
/**
 * The dummy magic core object.
 * @author Chi-Han Wang
 * @author Jonathan Poston
 *
 */
public class MagicCore {
	private int digitsTotal;
	private boolean even;
	private final int NINE = 9;
	private int answer;
	/**
	 * The constructor of the core object. Which will receive the number  then find the answer.
	 * @param number The number that user put in.
	 * @param even If the number is even then true otherwise false.
	 */
	public MagicCore(int number, boolean even){
		this.even = even;
		String tempnum = Integer.toString(number);
		digitsTotal = 0;
		for(int i = 0 ; i < tempnum.length() ; i++ ){
			int temp = (int)tempnum.charAt(i);
			digitsTotal += temp;
		}
		int remainder = digitsTotal/NINE;
		if(remainder == 0){
			if(even){
				answer = 0;
			} else {
				answer = 9;
			}
		} else {
			answer = NINE - remainder;
		}
		
	}
	/**
	 * Method to get the answer.
	 * @return anwer.
	 */
	public int getAnswer(){
		return answer;
	}

}
