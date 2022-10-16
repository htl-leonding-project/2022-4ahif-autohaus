import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Match } from 'src/app/models/match.model';
import { Team } from 'src/app/models/team.model';
import { TournamentService } from 'src/app/services/tournament.service';
import { FormsModule, NgForm } from '@angular/forms';
import { MatchService } from 'src/app/services/match.service';

@Component({
  selector: 'app-play-tournament',
  templateUrl: './play-tournament.component.html',
  styleUrls: ['./play-tournament.component.css']
})
export class PlayTournamentComponent implements OnInit {

  finishedAllMatches:Boolean=false;
  tournamentName = ""
  matches:Match[]=[];
  selected:Match = {
    id: 0,
    team1: {id: 0, name:"", abbr:"", winAmount:0},
    team2: {id: 0, name:"", abbr:"", winAmount:0},
    pointsTeam1: 0,
    pointsTeam2: 0}

  constructor(
    private route: ActivatedRoute,
    private router:Router, 
    private tournamentService: TournamentService,
    private matchService: MatchService) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.tournamentName = params['name'];
      }
    )
    this.refreshMatches(this.tournamentName);
  }

  refreshMatches(name: String){
    this.tournamentService.getMatchesForTournament(name).subscribe(
      {next:
        data =>{
          this.matches = data
          this.matches.sort((a,b) => a.id - b.id)
        },
        error: error =>{
          alert('Error loading teams');
        }
      });
  }

  finishTournament():void{
    this.router.navigate(['/result']);
  }

  clicked(){

    this.matchService.updateMatch(this.selected).subscribe({
      next:
        data =>{
          console.log("saved");      
          this.refreshMatches(this.tournamentName);
          this.checkForTournamentCompletion();
        },
        error: error =>{
          alert('Error loading teams');
        }
    })

    this.selected={
      id: 0,
      team1: {id: 0, name:"", abbr:"", winAmount:0},
      team2: {id: 0, name:"", abbr:"", winAmount:0},
      pointsTeam1: 0,
      pointsTeam2: 0
    }
  }

  select(match:Match){
    this.selected=match;
  }

  checkForTournamentCompletion(){
    console.log(this.tournamentName);
    
    this.tournamentService.isLastMatchDone(this.tournamentName).subscribe({
      next:
        data =>{
          this.finishedAllMatches = data;
          console.log(data);
      },
      error: error =>{
        alert('Error checking for completion');
      }
    });
  }
}
