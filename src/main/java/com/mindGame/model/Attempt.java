package com.mindGame.model;

import java.util.UUID;

public class Attempt {
	private String attemptId;
	private String gameId;
	private int[] guess;
	private String feedback;
	
	
	public Attempt(String gameId, int[] guess) {
		this.attemptId = UUID.randomUUID().toString();
		this.gameId = gameId;
		this.guess = guess;
	}

	public String getAttemptId() {
		return attemptId;
	}
	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public int[] getGuess() {
		return guess;
	}
	public void setGuess(int[] guess) {
		this.guess = guess;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	
	
	
	
	
}
