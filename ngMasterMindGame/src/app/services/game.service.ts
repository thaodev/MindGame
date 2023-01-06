import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Attempt } from '../models/attempt';
import { Game } from '../models/game';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private baseUrl = 'http://localhost:8080/';
  private url = this.baseUrl + 'api/games';
  constructor(private http: HttpClient) {}

  index(num:number): Observable<Game> {
    return this.http.get<Game>(this.url +"/" +num).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('GameService.index(): error retrieving game Id: ' + err)
        );
      })
    );
  }

  checkAttempt(gameId: string, guess: number[]): Observable<Attempt> {
    return this.http
      .post<Attempt>(this.url + '/' + gameId + '/attempts', guess)
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () =>
              new Error(
                'gameService.checkAttempt(): error checking player guess: ' +
                  err
              )
          );
        })
      );
  }
}
