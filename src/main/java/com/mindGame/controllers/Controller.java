package com.mindGame.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.mindGame.model.GameCreateRequestDTO;
import com.mindGame.model.GameDTO;
import com.mindGame.model.Hint;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4201/" })
public class Controller {

	@Autowired
	MainApp ul;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello thao";
	}

	private int[] getTargetNumbers(int length, int min, int max) {
		int[] arrOfNum = new int[length];
		String url = "https://www.random.org/integers/?num=" + length + "&min=" + min + "&max=" + max
				+ "&col=1&base=10&format=plain&rnd=new";
		RestTemplate restTemplate = new RestTemplate();
		String numbers = restTemplate.getForObject(url, String.class);
		String[] arr = numbers.split("\n");
		for (int i = 0; i < arr.length; i++) {
			arrOfNum[i] = Integer.parseInt(arr[i]);
		}

		return arrOfNum;
	}

	@PostMapping("/games")
	public GameDTO createNewGame(@RequestBody GameCreateRequestDTO gameRes, HttpServletResponse res) {
		Game game = null;
		GameDTO gameDTO = null;

		try {
			if (gameRes.getMax() <= gameRes.getMin()) {
				res.setStatus(400);
			} else {
				int[] target = getTargetNumbers(gameRes.getNum(), gameRes.getMin(), gameRes.getMax());
				game = ul.createGame(gameRes.getUsername(), target);
				gameDTO = new GameDTO(game.getUsername(), game.getGameId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return gameDTO;

	}

	@PostMapping("/games/{gameId}/attempts")
	public Attempt attempt(@PathVariable String gameId, @RequestBody int[] playerGuess, HttpServletResponse res) {
		System.out.println(Arrays.toString(playerGuess));

		return ul.attempt(gameId, playerGuess);
	}

	@GetMapping("games/{gameId}/hints")
	public Hint retrieveHint(@PathVariable String gameId, HttpServletResponse res) {
		Hint hints = null;

		try {
			hints = ul.retrieveHint(gameId);
		} catch (Exception e) {
			res.setStatus(400);
		}

		return hints;

	}



}
