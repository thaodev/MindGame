package com.mindGame.model;

public class Hint {
	private String gameId;

	private int sumDigit;
	private boolean isFirstEven;
	private boolean isThirdDivisibleByThree;
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public int getSumDigit() {
		return sumDigit;
	}
	public void setSumDigit(int sumDigit) {
		this.sumDigit = sumDigit;
	}
	public boolean getIsFirstEven() {
		return isFirstEven;
	}
	public void setIsFirstEven(boolean isFirstEven) {
		this.isFirstEven = isFirstEven;
	}
	public boolean getIsThirdDivisibleByThree() {
		return isThirdDivisibleByThree;
	}
	public void setIsThirdDivisibleByThree(boolean isThird) {
		this.isThirdDivisibleByThree = isThird;
	}
	
	

}
