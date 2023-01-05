package com.mindGame.model;

import java.util.List;
import java.util.UUID;

public class Player {
	
	private String playerId;
	private String username;
	private Attempt[] attempt;
	
	public Player(String username) {
		this.playerId = UUID.randomUUID().toString();
		this.username = username;
		this.attempt = new Attempt[10];
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
	
	
	
	

}
