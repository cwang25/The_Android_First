package com.cj.numbermagic;

public class MagicCore {
	private int digitsTotal;
	private boolean even;
	private final int NINE = 9;
	private int answer;
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
	
	public int getAnswer(){
		return answer;
	}

}
