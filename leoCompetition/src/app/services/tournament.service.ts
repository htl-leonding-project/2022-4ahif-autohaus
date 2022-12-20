import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Match } from '../models/match.model';
import { Team } from '../models/team.model';
import { Tournament } from '../models/tournament.model';
import {environment} from "../../environments/environment";

const API_URL = environment.URL_BASE_URL + '/tournaments'

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

  getMatchesForTournament(name: String) {
    return this.httpClient.get<Match[]>(API_URL+"/matches/"+name);
  }

  setUpMatchesForTournament(name: String, teams: Team[]){
    return this.httpClient.post<Team[]>(API_URL+"/set-matches/"+name, teams, httpOptions);
  }

  finishMatch(tournamentName: string, match: Match){
    return this.httpClient.post<Tournament>(API_URL+"/finishMatche/"+tournamentName, match, httpOptions);
  }

  isLastMatchDone(tournamentName: string){
    return this.httpClient.get<boolean>(API_URL+"/finished/"+tournamentName)
  }

  exists(name: string){
    return this.httpClient.get<boolean>(API_URL+"/exists/"+name);
  }

  createDiagram(name:string){
    return this.httpClient.get(API_URL+"/generate/"+name)
  }

  deleteTournament(id:number){
    return this.httpClient.delete(API_URL+"/"+id)
  }

  getTeams(tournamentName:string){
    return this.httpClient.get<Team[]>(API_URL+"/teams/"+tournamentName);
  }
}
