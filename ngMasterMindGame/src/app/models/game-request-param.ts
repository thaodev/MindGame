export class GameRequestParam {
  num: number;
  min: number;
  max: number;
  username: string;

  constructor (num: number, min: number, max : number, username: string) {
    this.num = num;
    this.min = min;
    this.max = max;
    this.username = username;
    }
}
