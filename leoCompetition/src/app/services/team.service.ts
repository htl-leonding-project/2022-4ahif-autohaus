import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Team } from '../models/team.model';

const API_URL = 'http://localhost:8080/teams'
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
}
