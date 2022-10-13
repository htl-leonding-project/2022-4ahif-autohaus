import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Team } from '../models/team.model';
import { Tournament } from '../models/tournament.model';

const API_URL = 'http://localhost:8080/tournaments'

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

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

  saveTournament(tournamentName: String, teams: Team[]){
    return this.httpClient.post<Tournament>(API_URL+"/"+tournamentName, teams, httpOptions);
  }
}
