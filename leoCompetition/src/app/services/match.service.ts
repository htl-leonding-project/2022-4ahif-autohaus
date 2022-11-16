import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Match } from '../models/match.model';
import {environment} from "../../environments/environment";

const API_URL = environment.URL_BASE_URL + '/matches'

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

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

  updateMatch(match: Match){
    return this.httpClient.patch<Match>(API_URL+"/update", match, httpOptions);
  }

}
