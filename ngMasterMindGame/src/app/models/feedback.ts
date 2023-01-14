export class Feedback {
  numberOfCorrectDigits: number;
	numberOfCorrectPos: number;
  content: string;

  constructor (numberOfCorrectDigits: number, numberOfCorrectPos: number, content: string) {
    this.numberOfCorrectDigits = numberOfCorrectDigits;
    this.numberOfCorrectPos = numberOfCorrectPos;
    this.content = content;
    }
}
