package com.mindGame.model;


public class GameDTO {
	private String gameId;
	private String username;
	
	public GameDTO(String username, String gameId) {
		this.username = username;
		this.gameId = gameId;
	}

	public String getGameId() {
		return gameId;
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



//	@Override
//	public String toString() {
//		return "{gameId:" + gameId + ", username:" + username + ", startTime:" + startTime + ", endTime:"
//				+ endTime + "}";
//	}

	
	
	
	

}
