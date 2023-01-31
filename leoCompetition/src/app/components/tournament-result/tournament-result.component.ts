import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Team } from 'src/app/models/team.model';
import { TournamentService } from 'src/app/services/tournament.service';
import { NotifierService } from 'angular-notifier';
import { Tournament } from 'src/app/models/tournament.model';
import { faCrown } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-tournament-result',
  templateUrl: './tournament-result.component.html',
  styleUrls: ['./tournament-result.component.css']
})
export class TournamentResultComponent implements OnInit {

  tournamentName:string="";
  id: number=-1;
  teams: Team[]=[];
  state: string = "";
  tournamentWinner: string = "";
  private notifier: NotifierService;

  faCrown = faCrown;

  constructor(
    private route: ActivatedRoute, 
    private router: Router,
    private tournamentService: TournamentService,
    notifier: NotifierService){
      this.notifier = notifier;
    }

  ngOnInit(): void {
    
    this.route.params.subscribe(
      params => {
        this.tournamentName = params['name'];

        this.tournamentService.getTournaments().subscribe({next:
          data =>{
            this.state = data.find(t => t.name == this.tournamentName)!.status.toString()
            console.log(this.state)
            this.tournamentWinner = data.find(t => t.name == this.tournamentName)!.abbrOfWinnerTeam.toString()
          },
          error: e =>{
            this.notifier.notify("error", "Turnier konnte nicht geladen werden!")
          }
        });
      }
    )
    this.refreshData();
  }

  refreshData(){
    this.tournamentService.getTeams(this.tournamentName).subscribe({next:
      data =>{
        this.teams = data;
      },
      error: e =>{
        this.notifier.notify("error", "Teams konnten nicht geladen werden!")
      }
    });

    this.teams.filter(t => t.name.match(this.tournamentWinner));
  }

  backToStart(){
      this.router.navigate(['/home']);
  }

  continuePlaying(){
    this.router.navigate(['/play-tournament/'+this.tournamentName]);
  }

  continuePlanning(){
    this.router.navigate(['/preparation/'+this.tournamentName]);
  }
}
