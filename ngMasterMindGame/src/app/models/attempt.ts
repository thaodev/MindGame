export class Attempt {
  attemptId:string;
  feedback:string;
  gameId:string;
  guess:number[];

  constructor (attemptId:string, feedback : string, gameId : string, guess:number[]) {
    this.attemptId = attemptId;
    this.feedback = feedback;
    this.gameId = gameId;
    this.guess = guess;

    }
}
