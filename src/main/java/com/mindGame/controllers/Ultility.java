package com.mindGame.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mindGame.model.Attempt;
import com.mindGame.model.Feedback;
import com.mindGame.model.Game;
import com.mindGame.model.Hint;

@Component
public class Ultility {
	
	private static List<Game> listOfGames = new ArrayList<>();
	

	private static Map<Game, List<Attempt>> recordOfAttempts = new HashMap<>();
	
	

	public Game createGame(int[] digits) {
		Game newGame = new Game(digits);

		listOfGames.add(newGame);
		List<Attempt> att = new ArrayList<>();
		recordOfAttempts.put(newGame, att);

		System.out.println("game id: " + newGame.getGameId());
		System.out.println(Arrays.toString(newGame.getTarget()));
		
		return newGame;
	}
	
	public Attempt attempt(String gameId,int[] playerGuess) {
		System.out.println(Arrays.toString(playerGuess));
		
		int numberOfCorrectDigit = 0;
		int numberOfCorrectPos = 0;
		// Get target array by game id
		int[] target = new int[4];
		Game game = null;
		for (Game g : listOfGames) {
			if (g.getGameId().equals(gameId)) {
				target = g.getTarget();
				//System.out.println("Target is:" + Arrays.toString(target));
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
		//return number of correct postions
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
		if (recordOfAttempts.get(game).size() >= 10) {
			numberOfCorrectDigit = -1;
			numberOfCorrectPos = -1;
			newAttempt.setTarget(game.getTarget());
		}
		//********** NO LONGER NECESSARY *******
//		// If the use get it right - no longer necessary
//		else if (numberOfCorrectPos == target.length) {
//			numberOfCorrectDigit = target.length;
//			numberOfCorrectPos = target.length;
//		}
//		// Get current
//		else if (countCorrectGuess == 0 && countCorrectLocation == 0) {
//			feedback = "all incorrect";
//		}
//		// Else Return result
//
//		else {
//			feedback = "correct_nums = " + countCorrectGuess + ", correct_count = " + countCorrectLocation;
//		}
		Feedback feedback = new Feedback(numberOfCorrectDigit, numberOfCorrectPos);
		newAttempt.setFeedback(feedback);
		newAttempt.setGameId(gameId);
		return newAttempt;
	}
	
	
//	public Hint createHint(String gameId) {
//		Hint hints = new Hint();
//		int sum = 0;
//		boolean isFirstEven = false;
//		boolean isThirdDividedByThreeEqually = false;
//		if (target[0] % 2 == 0) {
//			isFirstEven = true;
//		}
//		if (target[2] % 3== 0) {
//			isThirdDividedByThreeEqually = true;
//		}
//		for (int i =0 ; i < target.length; i++) {
//			sum += target[i];
//			
//		}
//		hints.setSumDigit(sum);
//		hints.setIsFirstEven(isFirstEven);
//		hints.setIsThirdDivisibleByThree(isThirdDividedByThreeEqually);
//		
//		return hints;
//	}
}
