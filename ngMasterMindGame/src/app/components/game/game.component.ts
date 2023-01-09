import { Hints } from './../../models/hints';
import { Attempt } from './../../models/attempt';
import { Component, OnInit, ViewChild } from '@angular/core';
import { GameService } from 'src/app/services/game.service';
import { Game } from 'src/app/models/game';
import { CountdownComponent } from 'ngx-countdown';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent implements OnInit {
 //@ViewChild('cd', { static: false }) private countdown: CountdownComponent;
   //this.countdown.begin();
  isNewGame: boolean = false;
  size: number = 4;
  selectedDigit: any;
  box: any;
  range: number[] = []; //range for 0->7
  guess: number[] = [];
  //guess: any = [];
  digits: any = []; //number of boxes based on player's choice
  attemptArr: Attempt[] = []; //number of attempts that player can have
  attemptCount: number = 1;

  gameId: string | any;
  feedback: string | any;
  attempt = {} as Attempt;

  isHintsClicked : boolean = false;

  hints = {} as Hints;
  timeData : number = 10;
  event : any;


  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.startGame(this.size);
    this.digits = new Array(this.size);
    console.log('test attemptArr ' + this.attemptArr.length);
    for (let i = 0; i <= 7; i++) {
      this.range.push(i);
    }
    this.attemptArr = []; //a new array of attempt at each new game;
    this.attemptArr.push(this.attempt);//add 1st object attempt to array
  }
  counter() {
    console.log('size in counter ' + this.size);
    return new Array(this.size);
  }
  startGame(size: number) {
    this.timeData = 15;
    this.guess = [];
    console.warn('current guess: ' + this.guess);
    this.size = size;
    this.digits = []; //reset number of boxes after player starting a new game
    this.attemptArr = []; //reset array of attempt after each new game;
    this.attemptArr.push(this.attempt);//add 1st object attempt to array
    console.log('size after clicking startGame: ' + size);
    this.retrieveGameId();
    for (let i = 1; i <= size; i++) {
      this.digits.push('');
    }
    this.isHintsClicked = false;
    //this.countdown.restart();
    console.log(this.timeData);
  }

  retrieveGameId() {
    this.gameService.index(this.size).subscribe({
      next: (game) => {
        this.gameId = game.gameId;
        this.hints = game.hints;
        console.log(this.hints);
        console.log('response from index: ' + this.gameId);
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
  attemptCountFun(i:number){
    return new Array(i);
  }

  checkAttempt() {
    this.attempt = {} as Attempt;
    if (this.guess.length < this.digits.length) {
      alert('array of guess is incomplete');
    }
    for (let i = 0; i < this.guess.length; i++) {
      // if (this.guess[i] == 'undefined') {
      //   this.guess.splice(i,1)
      //   alert('number is incomplete');
      // }
    }
    console.log('player guess' + this.guess);
    this.gameService.checkAttempt(this.gameId, this.guess).subscribe({
      next: (result) => {
        console.log(result);
        console.log(result.feedback);
        this.feedback = result.feedback;
        if (this.feedback != 'You Won' && this.attemptArr.length < 10) {
          //console.log(this.attemptArr.length);
          //console.log(this.attemptArr);
          if (this.attemptArr.length == 1 && this.attemptArr[0].feedback == null) {
            this.attemptArr[0].feedback = result.feedback;
            this.attemptArr[0].attemptId = result.attemptId;
            this.attemptArr.length++;
          console.log(this.attemptArr.length);
          } else {
            this.attemptArr.push(result);
            // for(let att of this.attemptArr) {
            //   if(att == null) {
            //     att = result;
            //     att.feedback = result.feedback;
            //     att.attemptId = result.attemptId;
            //   }
            // }


          }
          this.attemptCount++;
          // console.log(this.attemptArr);
          // console.log(this.attemptArr.length);
          // console.log(this.attemptArr?.[0]);
          // console.log(this.attemptArr[0].feedback);
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

  hintsClicked() {
    this.isHintsClicked = true;
  }

  handleEvent(event: { action: string; }) {
    if (event.action == 'done') {
      alert("You lose");
      // //this.restart('cd');
      // if (confirm("You lose")) {
      //   this.timeData = 10;
      // }
      {leftTime:this.timeData};
      this.startGame(this.size);

    }
  }

  restart(comp:CountdownComponent){
    comp.restart();
  }
}
