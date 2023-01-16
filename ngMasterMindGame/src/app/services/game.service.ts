import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Attempt } from '../models/attempt';
import { Game } from '../models/game';
import { GameRequestParam } from '../models/game-request-param';
import { Hints } from '../models/hints';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private baseUrl = 'http://localhost:8080/';
  private url = this.baseUrl + 'api/games';
  constructor(private http: HttpClient) {}

  index(gameReqParam: GameRequestParam): Observable<Game> {
    return this.http.post<Game>(this.url,gameReqParam).pipe(
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

  retrieveHints(gameId: string): Observable<Hints> {
    return this.http
      .get<Hints>(this.url + '/' + gameId + '/hints')
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () =>
              new Error(
                'gameService.retrieveHints(): error retrieving hints of game: ' +
                  err
              )
          );
        })
      );
  }

  retrieveTopGame(): Observable<Game[]> {
    return this.http
      .get<Game[]>(this.url + '/topGame')
      .pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError(
            () =>
              new Error(
                'gameService.retrieveTopGame(): error retrieving top games: ' +
                  err
              )
          );
        })
      );
  }
  // retrieveTopGame(): Observable<Map<Game,number>> {
  //   return this.http
  //     .get<Map<Game,number>>(this.url + '/topGame')
  //     .pipe(
  //       catchError((err: any) => {
  //         console.error(err);
  //         return throwError(
  //           () =>
  //             new Error(
  //               'gameService.retrieveTopGame(): error retrieving top games: ' +
  //                 err
  //             )
  //         );
  //       })
  //     );
  // }
}
