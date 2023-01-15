package com.mindGame.model;

import java.time.LocalTime;

public class GameDTO {
	private String gameId;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public GameDTO(String gameId, LocalTime startTime) {
		this.gameId = gameId;
		this.startTime = startTime;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
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

	
	
	
	

}
