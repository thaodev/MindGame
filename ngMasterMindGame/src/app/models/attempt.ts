import { Feedback } from './feedback';
export class Attempt {
  attemptId:string;
  feedback:Feedback;
  gameId:string;
  guess:number[];

  constructor (attemptId:string, feedback : Feedback, gameId : string, guess:number[]) {
    this.attemptId = attemptId;
    this.feedback = feedback;
    this.gameId = gameId;
    this.guess = guess;

    }
}
