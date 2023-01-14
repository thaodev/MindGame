import { Hints } from "./hints";

export class Game {
  gameId: string;
  hints: Hints;
  target: number[];



  constructor (gameId : string, hints : Hints, target: number[]) {
  this.gameId = gameId;
  this.hints = hints;
  this.target = target;
  }



}
