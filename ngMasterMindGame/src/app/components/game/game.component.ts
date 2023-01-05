import { Component, OnInit } from '@angular/core';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  isNewGame:boolean = false;
  digit1: any;
  digit2: any;
  digit3: any;
  digit4: any;
  guess : number[] | null = null;
  gameId: String | null = null;
  constructor(private gameService: GameService) {

   }

  ngOnInit(): void {
    this.guess?.push(this.digit1);
    this.guess?.push(this.digit2);
    this.guess?.push(this.digit3);
    this.guess?.push(this.digit4);
  }


  startGame() {
    this.gameService.index().subscribe({
      next: (gameId) => {
        this.gameId = gameId;
        console.log(gameId);

      },
      error: (problem) => {
        console.error(
          'GameComponent.checkAttempt(): error creating attempt'
        );
        console.error(problem);
      },
    });
  }



}
