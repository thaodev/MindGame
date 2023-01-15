import { Hints } from "./hints";

export class Game {
  gameId: string;
  hints: Hints;
  username: string;
  target: number[];



  constructor (gameId : string, hints : Hints, username: string, target: number[]) {
  this.gameId = gameId;
  this.hints = hints;
  this.username = username;
  this.target = target;
  }



}
