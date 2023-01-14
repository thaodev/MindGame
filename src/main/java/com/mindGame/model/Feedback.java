package com.mindGame.model;

public class Feedback {	
	private int numberOfCorrectDigits;
	private int numberOfCorrectPos;
	
	
	public Feedback(int numberOfCorrectDigits, int numberOfCorrectPos) {
		this.numberOfCorrectDigits = numberOfCorrectDigits;
		this.numberOfCorrectPos = numberOfCorrectPos;
	}

	public int getNumberOfCorrectDigits() {
		return numberOfCorrectDigits;
	}

	public void setNumberOfCorrectDigits(int numberOfCorrectDigits) {
		this.numberOfCorrectDigits = numberOfCorrectDigits;
	}

	public int getNumberOfCorrectPos() {
		return numberOfCorrectPos;
	}

	public void setNumberOfCorrectPos(int numberOfCorrectPos) {
		this.numberOfCorrectPos = numberOfCorrectPos;
	}


	

}
