import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Group } from '../models/group.model';

const API_URL = 'http://localhost:8080/groups'

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  constructor(private httpClient: HttpClient) { }

  getGroups(){
    return this.httpClient.get<Group[]>(API_URL);
  }

  getGroupsAmount(){
    return this.httpClient.get<number>(API_URL+"/amount");
  }
}
