import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private baseUrl = 'http://localhost:8080/';
  private url = this.baseUrl + 'api/games';
  constructor() { }
}
