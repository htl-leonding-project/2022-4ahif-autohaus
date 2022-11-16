import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Team } from '../models/team.model';
import {environment} from "../../environments/environment";

const API_URL = environment.URL_BASE_URL + '/teams'

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private httpClient: HttpClient) { }

  getTeams(){
    return this.httpClient.get<Team[]>(API_URL);
  }

  getTeamsAmount(){
    return this.httpClient.get<number>(API_URL+"/amount");
  }

  saveTeam(newTeam: Team){
    return this.httpClient.post<Team>(API_URL, newTeam, httpOptions);
  }
}
