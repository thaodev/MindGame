package com.mindGame.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mindGame.model.Attempt;
import com.mindGame.model.Game;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200/" })
public class Controller {
	
	private static List<Game> listOfGames = new ArrayList<>();
	private static Map<Game, List<Attempt>> outcome = new HashMap<>();
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello thao";
	}
	
	
	@PostMapping(value = "/games")
	private int[] createNewGame() {
		
		String url = "https://www.random.org/integers/?num=4&min=0&max=7&col=1&base=10&format=plain&rnd=new";
		RestTemplate restTemplate = new RestTemplate();
		String numbers = restTemplate.getForObject(url, String.class);
		String[] arr = numbers.split("\n");
		int[] arrOfNum = new int[arr.length];
		for (int i = 0 ; i < arr.length ; i++) {
			arrOfNum[i] = Integer.parseInt(arr[i]);
		}
		Game newGame = new Game(arrOfNum);
		System.out.println("game id: " + newGame.getGameId());
		System.out.println(Arrays.toString(newGame.getTarget()));
		
		return arrOfNum;
	}
}
