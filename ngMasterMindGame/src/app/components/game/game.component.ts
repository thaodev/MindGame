import { Attempt } from './../../models/attempt';
import { Component, OnInit } from '@angular/core';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent implements OnInit {
  isNewGame: boolean = false;
  size: number = 4;
  selectedDigit: any;
  box: any;
  range: number[] = []; //range for 0->7
  guess : number[] =[];
  //guess: any = [];
  array: any = []; //number of boxes based on player's choice
  attemptArr: Attempt[] = new Array(0);//number of attempts that player can have

  gameId: string | any;
  feedback: string | any;
  attempt = {} as Attempt;
  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.startGame(this.size);
    this.array = new Array(this.size);

    for (let i = 0; i <= 7; i++) {
      this.range.push(i);
    }
    //console.log("range size " + this.range.length);
  }
  counter() {
    console.log('size in counter ' + this.size);
    return new Array(this.size);
  }
  startGame(size: number) {
    this.guess = [];
    console.warn('current guess: ' + this.guess);
    this.size = size;
    this.array = [];//reset number of boxes after player starting a new game
    this.attemptArr = new Array(1);//reset array of attempt after each new game;
    console.log('size after clicking startGame: ' + size);
    this.retrieveGameId();
    for (let i = 1; i <= size; i++) {
      this.array.push('');
    }
  }

  retrieveGameId() {
    this.gameService.index(this.size).subscribe({
      next: (game) => {
        this.gameId = game.gameId;

        console.log("response from index: " + this.gameId);
        // this.gameId = JSON.parse('{"game_id":"18cb016d-4078-4820-9ee7-210d6e6b6d35"}');
        // console.log(this.gameId.game_id);
      },
      error: (problem) => {
        console.error('GameComponent.checkAttempt(): error creating attempt');
        console.error(problem);
      },
    });
  }

  getData(digit: any) {
    console.warn('player picked' + digit);
    if (digit != 'undefined') {
      this.guess.push(digit);
    }
    console.log('guess size: ' + this.guess.length);
  }

  checkAttempt() {
    this.attempt ={} as Attempt;
    if (this.guess.length < this.array.length) {
      alert('array of guess is incomplete');
    }
    for (let i = 0; i < this.guess.length; i++) {
      // if (this.guess[i] == 'undefined') {
      //   this.guess.splice(i,1)
      //   alert('number is incomplete');
      // }
    }
    console.log("player guess"  + this.guess);
    this.gameService.checkAttempt(this.gameId, this.guess).subscribe({
      next: (result) => {
        console.log(result);
        console.log(result.feedback);
        this.feedback = result.feedback;
        this.attempt.feedback = result.feedback;
        this.attempt.attemptId = result.attemptId;
        this.attempt.guess = result.guess;
        this.attempt.gameId = result.gameId;
        if (this.feedback != "You Won" && this.attemptArr.length < 10) {
          //this.feedback = "";
          this.attemptArr.push(this.attempt);
          console.log(this.attemptArr);
          console.log(this.attemptArr.length);
          console.log(this.attemptArr?.[1]);
          console.log(this.attemptArr[1].feedback)
        }
        this.guess = [];
      },
      error: (nojoy) => {
        console.error(
          'gameHttpComponent.checkAttempt(): error checking player attempt:'
        );
        console.error(nojoy);
      },
    });

  }
}
