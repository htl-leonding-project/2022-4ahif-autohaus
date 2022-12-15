import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Match } from 'src/app/models/match.model';
import { TournamentService } from 'src/app/services/tournament.service';
import { MatchService } from 'src/app/services/match.service';
import { NotifierService } from 'angular-notifier';

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
    team1: {id: 0, name:"", abbr:""},
    team2: {id: 0, name:"", abbr:""},
    pointsTeam1: 0,
    pointsTeam2: 0,
    finished: false,
    phase: 0
  }

  constructor(
    private route: ActivatedRoute,
    private router:Router, 
    private tournamentService: TournamentService,
    private matchService: MatchService,
    private notifier: NotifierService) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.tournamentName = params['name'];
        this.checkForTournamentCompletion();
        this.refreshMatches(this.tournamentName);
      }
    )
    
  }

  refreshMatches(name: String){
    this.tournamentService.getMatchesForTournament(name).subscribe(
      {next:
        data =>{
          this.matches = data
          this.matches.sort((a,b) => b.phase - a.phase)
        },
        error: error =>{
          this.notifier.notify( 'error','Matches konnten nicht geladen werden!');
        }
      });
  }

  finishTournament():void{
    this.tournamentService.createDiagram(this.tournamentName).subscribe({
      next:
        data =>{
          console.log("Generated Diagram");
          this.router.navigate(['/result/'+this.tournamentName.replace(' ', '_')]);
        },
        error: error =>{
          this.notifier.notify( 'error','Diagramm konnten nicht geladen werden!');
        }
    })
  }

  clicked(){

    if(this.selected.pointsTeam1 != this.selected.pointsTeam2)
      if(this.selected.pointsTeam1 >= 0 && this.selected.pointsTeam2 >= 0){
        this.matchService.updateMatch(this.selected).subscribe({
          next:
            data =>{   
              this.refreshMatches(this.tournamentName);
              this.checkForTournamentCompletion();
            },
            error: error =>{
              this.notifier.notify( 'error','Matches konnten nicht geladen werden!');
            }
        })

        this.selected={
          id: 0,
          team1: {id: 0, name:"", abbr:""},
          team2: {id: 0, name:"", abbr:""},
          pointsTeam1: 0,
          pointsTeam2: 0,
          finished: false,
          phase: 0
        }
      
      }else{
        this.notifier.notify('info', 'Nur positive Punkte erlaubt!')
      }
    else{
      this.notifier.notify( 'info','Gleichstand ist nicht erlaubt!');
    }
  }

  select(match:Match){
    this.selected=match;
  }

  checkForTournamentCompletion(){
    
    this.tournamentService.isLastMatchDone(this.tournamentName).subscribe({
      next:
        data =>{
          this.finishedAllMatches = data;
      },
      error: error =>{
        this.notifier.notify( 'error','Fehler beim checken ob Turnier fertig gespielt wurde!');
      }
    });
  }
}
