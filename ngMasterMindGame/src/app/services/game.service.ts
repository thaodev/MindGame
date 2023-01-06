import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Game } from '../models/game';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private baseUrl = 'http://localhost:8080/';
  private url = this.baseUrl + 'api/games';
  constructor(private http: HttpClient) {}

  index(): Observable<string> {
    return this.http.get<string>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error('GameService.index(): error retrieving game Id: ' + err)
        );
      })
    );
  }





}
