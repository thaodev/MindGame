package com.mindGame.model;

import java.util.List;
import java.util.UUID;

public class Player {
	
	private String playerId;
	private String username;
	
	public Player(String username) {
		this.playerId = UUID.randomUUID().toString();
		this.username = username;
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
