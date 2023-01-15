import { GameRequestParam } from './../../models/game-request-param';
import { Hints } from './../../models/hints';
import { Attempt } from './../../models/attempt';
import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { GameService } from 'src/app/services/game.service';
import { Game } from 'src/app/models/game';
import {
  CountdownComponent,
  CountdownConfig,
  CountdownEvent,
} from 'ngx-countdown';
import { Feedback } from 'src/app/models/feedback';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent implements OnInit {
  @ViewChild('cd', { static: false }) private countdown!: CountdownComponent;
  //this.countdown.begin();
  isUsernameInputShown : boolean = true;
  username: String | any;
  isNewGame: boolean = false;
  min: number = 0;
  max: number = 7;
  size: number = 4;
  selectedDigit: any;
  box: any;
  range: number[] = []; //range for 0->7
  minMaxRange: number[] = []; //range for  min and max
  guess: number[] = [];
  digits: any = []; //number of boxes based on player's choice
  attemptArr: Attempt[] = []; //number of attempts that player can have
  attemptCount: number = 1;
  remaningAttempt: number = 10;
  gameRequestParam = {} as GameRequestParam;
  gameId: string | any;
  feedback = {} as Feedback;
  correctNumber: number = 0;
  correctPosition: number = 0;
  attempt = {} as Attempt;

  isHintsClicked: boolean = false;
  isTopGameShown : boolean = false;
  duration: number = 0;

  topGameMap : Map<Game,number> = new Map<Game,number>();
  topGame: Game[] = [];
  target: number[] = [];
  isTargetshown: boolean = false;

  hints = {} as Hints;
  event: any;
  error: string | any;

  config: CountdownConfig = { leftTime: 1100, format: 'm:s' };

  constructor(
    private gameService: GameService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.isTargetshown = false;
    this.startGame(this.username, this.size);
    for (let i = 0; i <= 9; i++) {
      this.minMaxRange.push(i);
    }
    this.digits = new Array(this.size);
    console.log('test attemptArr ' + this.attemptArr.length);

    // this.attemptArr = []; //a new array of attempt at each new game;
    //this.attemptArr.push(this.attempt);//add 1st object attempt to array
  }

  retrieveUsername(username: string){
    this.username = username;
    this.isUsernameInputShown = false;
    this.startGame(this.username, this.size);
  }
  counter() {
    console.log('size in counter ' + this.size);
    return new Array(this.size);
  }
  startGame(username: string, size: number) {
    this.isTargetshown = false;
    this.isNewGame = true;
    this.target =[];
    this.range = [];
    for (let i = this.min; i <= this.max; i++) {
      this.range.push(i);
    }
    this.guess = [];
    console.warn('current guess: ' + this.guess);
    this.size = size;
    this.digits = []; //reset number of boxes after player starting a new game
    this.attemptArr = []; //reset array of attempt after each new game;
    this.attemptArr.push(this.attempt); //add 1st object attempt to array


    console.log('size after clicking startGame: ' + size);
    if (this.max <= this.min) {
      this.error = 'Max has to be greater than min. Please try again!';
    } else {
      this.error = '';
      this.retrieveGameId();
    }

    for (let i = 1; i <= size; i++) {
      this.digits.push('');
    }
    this.isHintsClicked = false;
    setTimeout(() => {
      this.config = { leftTime: 1100, format: 'm:s' };
      this.countdown.restart();
    }, 0);
  }

  retrieveGameId() {

    this.gameRequestParam.num = this.size;
    this.gameRequestParam.min = this.min;
    this.gameRequestParam.max = this.max;
    this.gameRequestParam.username = this.username;
    this.gameService.index(this.gameRequestParam).subscribe({
      next: (gameDTO) => {
        this.gameId = gameDTO.gameId;

      },
      error: (error) => {
        if (error === '400') {
          console.log(error);
          this.error = 'max has to be greater than min';
        }
        console.error('GameComponent.checkAttempt(): error creating attempt');
        console.error(error);
      },
    });
  }

  getData(digit: any, j : number) {
    console.warn('player picked' + digit);
    if (digit != 'undefined') {
      this.guess[j] = digit;
    }
    console.log('guess size: ' + this.guess.length);
  }
  attemptCountFun(i: number) {
    return new Array(i);
  }

  checkAttempt() {
    this.attempt = {} as Attempt;
    if (this.guess.length < this.digits.length) {
      alert('array of guess is incomplete');
    }

    console.log('player guess' + this.guess);
    this.gameService.checkAttempt(this.gameId, this.guess).subscribe({
      next: (result) => {
        console.log(result);
        console.log(result.feedback);
        this.feedback = result.feedback;
        let content = `${result.feedback.numberOfCorrectDigits} correct number and ${result.feedback.numberOfCorrectPos} correct location`;
        if (
          this.feedback.numberOfCorrectPos < this.size &&
          this.attemptArr.length <= 10
        ) {
          const length = this.attemptArr.length;
          this.attemptArr[length - 1].feedback = result.feedback;

          if (
            result.feedback.numberOfCorrectDigits == 0 &&
            result.feedback.numberOfCorrectPos == 0
          ) {
            content = 'all incorrect';
          } else if (
            result.feedback.numberOfCorrectDigits == -1 &&
            result.feedback.numberOfCorrectPos == -1 || this.attemptArr.length == 10 && this.feedback.numberOfCorrectPos < this.size
          ) {
            content = 'YOU LOSE!';
            this.isTargetshown = true;
            this.target = result.target;
          }
          this.attemptArr[length - 1].feedback.content = content;
          if (this.attemptArr.length != 10) {
            const newAttempt: Attempt = {
              ...result,
              feedback: {} as Feedback,
            };
            this.attemptArr.push(newAttempt);
          }
        } else if (this.attemptArr.length >= 11) {
          alert('You lose');
          this.startGame(this.username, this.size);
        } else if (result.feedback.numberOfCorrectPos == 4){
          const length = this.attemptArr.length;
          this.attemptArr[length - 1].feedback = result.feedback;

          this.attemptArr[length - 1].feedback.content = 'YOU WIN!';
          this.countdown.stop();
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
    this.isHintsClicked = !this.isHintsClicked;
    this.gameService.retrieveHints(this.gameId).subscribe({
      next: (hints) => {
        this.hints = hints;
        console.log("inside retrieve hints");
      },
      error: (error) => {
        console.error('GameComponent.retrieveHints(): error retrieve hints');
        console.error(error);
      },
    })
    console.log(this.hints);
    console.log('response from index: ' + this.gameId);
    // this.gameId = JSON.parse('{"game_id":"18cb016d-4078-4820-9ee7-210d6e6b6d35"}');
    // console.log(this.gameId.game_id);
  }

  handleEvent(event: CountdownEvent) {
    if (event.action == 'done') {
      this.isNewGame = false;
      setTimeout(() => {
        alert('You lose');
        this.startGame(this.username, this.size);
      }, 0);
    }
  }

  showTopGame(){
    this.isTopGameShown = true;
    this.gameService.retrieveTopGame().subscribe({
      next: (result) => {
        this.topGameMap = result;
        console.log(this.topGameMap);
        // this.topGameMap.forEach((value, key) =>{
        //   console.log("key : " + key + " and value is: " + value );
        // });
        // this.topGameMap.forEach(function(value,key) {
        //   console.log(`key is: ${key} and value is: ${value}` );
        // })
        //this.duration =
        // for (const key of result.keys()) {
        //   console.log(key.username);
        // }

        this
        console.log("inside showTopGame()");
      },
      error: (error) => {
        console.error('GameComponent.retrieveTopGame(): error retrieve topGame');
        console.error(error);
      },
    })
  }
  restart(comp: CountdownComponent) {
    comp.restart();
  }
}
