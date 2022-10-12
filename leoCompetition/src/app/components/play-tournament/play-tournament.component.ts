import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/models/match.model';

@Component({
  selector: 'app-play-tournament',
  templateUrl: './play-tournament.component.html',
  styleUrls: ['./play-tournament.component.css']
})
export class PlayTournamentComponent implements OnInit {

  finishedAllMatches:Boolean=false;
  matches:Match[]=[];

  constructor() { }

  ngOnInit(): void {
  }

}
