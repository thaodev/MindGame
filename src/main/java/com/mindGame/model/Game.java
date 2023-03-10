package com.mindGame.model;

import java.time.LocalTime;
import java.util.UUID;

public class Game {
	private String gameId;
	private int[] target;
	private Hint hints;
	private String username;
	
	
	public Game(String username, int[] target) {
		this.gameId = UUID.randomUUID().toString();
		this.username = username;
		this.target = target;
		this.hints = createHint(target);
		
	}
	
	
	private Hint createHint(int[] target) {
		Hint hints = new Hint();
		int sum = 0;
		boolean isFirstEven = false;
		boolean isThirdDividedByThreeEqually = false;
		if (target[0] % 2 == 0) {
			isFirstEven = true;
		}
		if (target[2] % 3== 0) {
			isThirdDividedByThreeEqually = true;
		}
		for (int i =0 ; i < target.length; i++) {
			sum += target[i];
			
		}
		hints.setSumDigit(sum);
		hints.setIsFirstEven(isFirstEven);
		hints.setIsThirdDivisibleByThree(isThirdDividedByThreeEqually);
		System.out.println(isThirdDividedByThreeEqually + " " + target[2]);
		return hints;
	}

	public Hint getHints() {
		return hints;
	}

	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGameId() {
		return gameId;
	}


	public int[] getTarget() {
		return target;
	}

	public void setTarget(int[] target) {
		this.target = target;
	}


	@Override
	public String toString() {
		return "Game [gameId=" + gameId + "]";
	}
	
	
	
}
