package com.mindGame.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4201/" })
public class Controller {

	
	@Autowired
	Ultility ul;
	
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello thao";
	}
	
	private int[] getRandomNumber(int length, int min, int max) {
		int[] arrOfNum = new int[length];
		String url = "https://www.random.org/integers/?num=" + length + "&min=" + min + "&max=" + max + "&col=1&base=10&format=plain&rnd=new";
		RestTemplate restTemplate = new RestTemplate();
		String numbers = restTemplate.getForObject(url, String.class);
		String[] arr = numbers.split("\n");
		for (int i = 0; i < arr.length; i++) {
			arrOfNum[i] = Integer.parseInt(arr[i]);
		}

		return arrOfNum;
	}

	@PostMapping("/games")
	private GameDTO createNewGame( @RequestBody GameCreateRequestDTO gameRes, HttpServletResponse res) {
		Game game = null;
		GameDTO gameDTO = null;
		
		try {
			if (gameRes.getMax() <= gameRes.getMin()) {
				res.setStatus(400);
			} else {
				int[] numberOfDigits = getRandomNumber(gameRes.getNum(), gameRes.getMin(), gameRes.getMax());
				game =  ul.createGame(numberOfDigits);
				gameDTO = new GameDTO(game.getGameId(), game.getHints());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return gameDTO;

	}

	@PostMapping("/games/{gameId}/attempts")
	private Attempt attempt(@PathVariable String gameId, @RequestBody int[] playerGuess, HttpServletResponse res) {
		System.out.println(Arrays.toString(playerGuess));
		
		return ul.attempt(gameId, playerGuess);
	}
	
	
}
