import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Match } from '../models/match.model';

const API_URL = 'http://localhost:8080/matches'

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  constructor(private httpClient: HttpClient) { }

  getMatches(){
    return this.httpClient.get<Match[]>(API_URL);
  }

  getMatchesAmount(){
    return this.httpClient.get<number>(API_URL+"/amount");
  }

}
