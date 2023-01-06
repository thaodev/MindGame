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
  selectedDigit : any;
  box: any;
  range: number[] = [];//range for 0->7
  //guess : number[] | null = null;
  guess: any = [];
  array: any = []; //number of boxes based on player's choice

  gameId: String | null = null;
  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    // this.guess?.push(this.digit1);
    // this.guess?.push(this.digit2);
    // this.guess?.push(this.digit3);
    // this.guess?.push(this.digit4);
    this.array = new Array(this.size);

    for (let i = 0 ; i <= 7; i++) {
      this.range.push(i);
    }
    //console.log("range size " + this.range.length);
  }
  counter() {
    console.log('size in counter ' + this.size);
    return new Array(this.size);
  }
  startGame(size: number) {
    this.size = size;
    this.array = [];
    console.log('size after clicking startGame' + size);
    this.retrieveGameId();
    //this.counter();
    for (let i = 1; i <= size; i++) {
      this.array.push('');
    }
  }

  retrieveGameId() {
    this.gameService.index().subscribe({
      next: (gameId) => {
        this.gameId = gameId;
        console.log(gameId);
      },
      error: (problem) => {
        console.error('GameComponent.checkAttempt(): error creating attempt');
        console.error(problem);
      },
    });
  }

  onKeyUp(digit: any){
    console.warn("player picked" + digit);
    this.guess.push(digit);
    console.log("guess size: " + this.guess.length);
  }
  checkAttempt() {
    console.log("player guess " + this.guess);
  }

  getData(digit:any) {
    console.warn("test key up: " + digit);
  }
}
