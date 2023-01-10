export class Feedback {
  numberOfCorrectDigits: number;
	numberOfCorrectPos: number;

  constructor (numberOfCorrectDigits: number, numberOfCorrectPos: number) {
    this.numberOfCorrectDigits = numberOfCorrectDigits;
    this.numberOfCorrectPos = numberOfCorrectPos;
    }
}
