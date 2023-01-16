import { Hints } from "./hints";

export class Game {
  gameId: string;
  hints: Hints;
  username: string;
  target: number[];
  startTime: number;
  endTime:number;



  constructor (gameId : string, hints : Hints, username: string, target: number[], startTime : number, endTime:number) {
  this.gameId = gameId;
  this.hints = hints;
  this.username = username;
  this.target = target;
  this.startTime = startTime;
  this.endTime = endTime;
  }



}
