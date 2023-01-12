export class GameRequestParam {
  num: number;
  min: number;
  max: number;

  constructor (num: number, min: number, max : number) {
    this.num = num;
    this.min = min;
    this.max = max;
    }
}
