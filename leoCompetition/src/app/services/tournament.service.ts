import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Tournament } from '../models/tournament.model';

const API_URL = 'http://localhost:8080/tournaments'

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

  constructor(private httpClient: HttpClient) { }

  getTournaments(){
    return this.httpClient.get<Tournament[]>(API_URL);
  }

  getTournamentsAmount(){
    return this.httpClient.get<number>(API_URL+"/amount");
  }
}
