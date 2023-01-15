package com.mindGame.model;

import java.time.LocalTime;

public class GameDTO {
	private String gameId;
	private String username;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public GameDTO(String username, String gameId, LocalTime startTime) {
		this.username = username;
		this.gameId = gameId;
		this.startTime = startTime;
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

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "GameDTO [gameId=" + gameId + ", username=" + username + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}

	
	
	
	

}
