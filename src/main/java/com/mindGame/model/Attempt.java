package com.mindGame.model;

import java.util.UUID;

public class Attempt {
	private String attemptId;
	private String gameId;
	private int[] guess;
	private Feedback feedback;
	private int[] target;
	
	
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

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public int[] getTarget() {
		return target;
	}

	public void setTarget(int[] target) {
		this.target = target;
	}

	
	
	
	
	
}
