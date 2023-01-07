package com.mindGame.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mindGame.model.Attempt;
import com.mindGame.model.Game;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4201/" })
public class Controller {

	private static List<Game> listOfGames = new ArrayList<>();

	private static Map<Game, List<Attempt>> outcome = new HashMap<>();
	// List<Attempt> attempts = new ArrayList<>();

	@RequestMapping("/hello")
	public String hello() {
		return "Hello thao";
	}

	@GetMapping(value = "/games/{num}")
	private Game createNewGame(@PathVariable int num) {

		String url = "https://www.random.org/integers/?num=" + num + "&min=0&max=7&col=1&base=10&format=plain&rnd=new";
		RestTemplate restTemplate = new RestTemplate();
		String numbers = restTemplate.getForObject(url, String.class);
		String[] arr = numbers.split("\n");
		int[] arrOfNum = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			arrOfNum[i] = Integer.parseInt(arr[i]);
		}

		Game newGame = new Game(arrOfNum);

		listOfGames.add(newGame);
		List<Attempt> att = new ArrayList<>();
		outcome.put(newGame, att);

		System.out.println("game id: " + newGame.getGameId());
		System.out.println(Arrays.toString(newGame.getTarget()));

		// return "{\"game_id\":\"" + newGame.getGameId() + "\"}";
		// return "{\"" + newGame.getGameId() + "\"}";
		return newGame;
	}

	@PostMapping(value = "/games/{gameId}/attempts")
	private Attempt attempt(@PathVariable String gameId, @RequestBody int[] playerGuess) {
		System.out.println(Arrays.toString(playerGuess));
		String feedback = "";
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
		outcome.get(game).add(newAttempt);

		// Compare attempt's guess vs target array
		if (target.length != playerGuess.length)
			feedback = "you cheated!";
		Map<Integer, Integer> map1 = new LinkedHashMap<>();
		Map<Integer, Integer> map2 = new LinkedHashMap<>();
		int countCorrectLocation = 0;
		int countCorrectGuess = 0;
		for (int i = 0; i < target.length; i++) {
			map1.put(target[i], map1.getOrDefault(target[i], 0) + 1);
			map2.put(playerGuess[i], map2.getOrDefault(playerGuess[i], 0) + 1);

			if (target[i] == playerGuess[i]) {
				countCorrectLocation++;
			}
		}
		for (int i : map2.keySet()) {
			if (map1.containsKey(i)) {
				countCorrectGuess += Math.min(map1.get(i), map2.get(i));
			}
		}
		// Get number of past attempts by game id
		// Else If the number of attempt reach max allowed
		if (outcome.get(game).size() > 10)
			feedback = "You lose";
		// If the use get it right
		else if (countCorrectLocation == target.length) {
			feedback = "You Won";
		}
		// Get current
		else if (countCorrectGuess == 0 && countCorrectLocation == 0) {
			feedback = "all incorrect";
		}
		// Else Return result

		else {
			feedback = "correct_nums = " + countCorrectGuess + ", correct_count = " + countCorrectLocation;
		}
		newAttempt.setFeedback(feedback);
		newAttempt.setGameId(gameId);
		return newAttempt;
	}
}
