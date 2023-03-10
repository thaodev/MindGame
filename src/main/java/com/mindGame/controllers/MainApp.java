package com.mindGame.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.mindGame.model.Attempt;
import com.mindGame.model.Feedback;
import com.mindGame.model.Game;
import com.mindGame.model.Hint;

@Component
public class MainApp {

	private List<Game> listOfGames = new ArrayList<>();

	private Map<Game, List<Attempt>> recordOfAttempts = new HashMap<>();

	public Game createGame(String username, int[] digits) {
		Game newGame = new Game(username, digits);

		listOfGames.add(newGame);
		List<Attempt> att = new ArrayList<>();
		recordOfAttempts.put(newGame, att);

		System.out.println("game id: " + newGame.getGameId());
		System.out.println(Arrays.toString(newGame.getTarget()));

		return newGame;
	}

	public Attempt attempt(String gameId, int[] playerGuess) {
		System.out.println(Arrays.toString(playerGuess));

		int numberOfCorrectDigit = 0;
		int numberOfCorrectPos = 0;
		// Get target array by game id
		int[] target = new int[4];
		Game game = null;
		for (Game g : listOfGames) {
			if (g.getGameId().equals(gameId)) {
				target = g.getTarget();
				game = g;
			}
		}
		// create new attempt
		Attempt newAttempt = new Attempt(gameId, playerGuess);
		recordOfAttempts.get(game).add(newAttempt);

		// Compare attempt's guess vs target array
		if (target.length != playerGuess.length) {
			numberOfCorrectDigit = -1;
			numberOfCorrectPos = -1;
		}
		Map<Integer, Integer> map1 = new LinkedHashMap<>();
		Map<Integer, Integer> map2 = new LinkedHashMap<>();

		// return number of correct postions
		for (int i = 0; i < target.length; i++) {
			map1.put(target[i], map1.getOrDefault(target[i], 0) + 1);
			map2.put(playerGuess[i], map2.getOrDefault(playerGuess[i], 0) + 1);

			if (target[i] == playerGuess[i]) {
				numberOfCorrectPos++;
			}
		}
		for (int i : map2.keySet()) {
			if (map1.containsKey(i)) {
				numberOfCorrectDigit += Math.min(map1.get(i), map2.get(i));
			}
		}
		// Get number of past attempts by game id
		// Else If the number of attempt reach max allowed
		//if player run out of guess and still wrong, tell them lose
		if (recordOfAttempts.get(game).size() > 10) {
			numberOfCorrectDigit = -1;
			numberOfCorrectPos = -1;
			newAttempt.setTarget(game.getTarget());
		}
//		on 10th attempt reveal correct target whether player guess right or wrong
		if (recordOfAttempts.get(game).size() == 10) {
			newAttempt.setTarget(game.getTarget());
		}

		Feedback feedback = new Feedback(numberOfCorrectDigit, numberOfCorrectPos);
		newAttempt.setFeedback(feedback);
		newAttempt.setGameId(gameId);
		return newAttempt;
	}

	public Hint retrieveHint(String gameId) {
		Hint hints = new Hint();
		for (Game g : listOfGames) {
			if (g.getGameId().equals(gameId)) {
				hints = g.getHints();
			}
		}

		return hints;
	}

	
}
