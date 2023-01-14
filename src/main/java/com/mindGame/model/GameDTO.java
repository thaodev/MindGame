package com.mindGame.model;

public class GameDTO {
	private String gameId;
	private Hint hints;
	
	public GameDTO(String gameId, Hint hints) {
		this.gameId = gameId;
		this.hints = hints;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Hint getHints() {
		return hints;
	}

	public void setHints(Hint hints) {
		this.hints = hints;
	}
	
	
	
	

}
