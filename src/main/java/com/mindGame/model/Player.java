package com.mindGame.model;

import java.util.List;
import java.util.UUID;

public class Player {
	
	private String playerId;
	private String username;
	private int score;
	
	public Player(String username) {
		this.playerId = UUID.randomUUID().toString();
		this.username = username;
		this.score = 10;
	}

	public String getPlayerId() {
		return playerId;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	

}
