import { Hints } from "./hints";

export class Game {
  gameId: string;
  hints: Hints;


  constructor (gameId : string, hints : Hints) {
  this.gameId = gameId;
  this.hints = hints;
  }



}
