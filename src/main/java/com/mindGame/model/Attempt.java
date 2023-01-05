package com.mindGame.model;

import java.util.UUID;

public class Attempt {
	private String attemptId;
	private Game game;
	private int[] guess;
	private Player player;
	
	
	public Attempt(Game game, int[] guess, Player player) {
		this.attemptId = UUID.randomUUID().toString();
		this.game = game;
		this.guess = guess;
		this.player = player;
	}

	public String getAttemptId() {
		return attemptId;
	}
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public int[] getGuess() {
		return guess;
	}
	public void setGuess(int[] guess) {
		this.guess = guess;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	
	
}
