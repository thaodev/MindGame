package com.mindGame.model;

import java.util.Arrays;
import java.util.UUID;

public class Game {
	private String gameId;
	private int[] target;
	//private int maxAttempt;
	
	
	
	public Game(int[] target) {
		this.gameId = UUID.randomUUID().toString();
		this.target = target;
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
