package com.mindGame.controllers;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(value = "/games/{num}/{min}/{max}")
	private Game createNewGame(@PathVariable int num, @PathVariable int min, @PathVariable int max) {

		int[] numberOfDigits = getRandomNumber(num, min, max);
		
		return ul.createGame(numberOfDigits);
	}

	@PostMapping(value = "/games/{gameId}/attempts")
	private Attempt attempt(@PathVariable String gameId, @RequestBody int[] playerGuess) {
		System.out.println(Arrays.toString(playerGuess));
		
		return ul.attempt(gameId, playerGuess);
	}
}
