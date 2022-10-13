import { Component, OnInit } from '@angular/core';
import { Match } from 'src/app/models/match.model';
import { MatchService } from 'src/app/services/match.service';

@Component({
  selector: 'app-play-tournament',
  templateUrl: './play-tournament.component.html',
  styleUrls: ['./play-tournament.component.css']
})
export class PlayTournamentComponent implements OnInit {

  finishedAllMatches:Boolean=false;
  matches:Match[]=[];

  constructor(private matchService: MatchService) { }

  ngOnInit(): void {
    this.refreshMatches();
  }

  refreshMatches(){
    this.matchService.getMatches().subscribe({next:
      data =>{
        this.matches = data;
      },
      error: error =>{
        alert('Error loading matches');
      }
    });
  }
  
}
