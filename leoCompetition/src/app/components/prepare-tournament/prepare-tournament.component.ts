import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { Team } from 'src/app/models/team.model';
import { MatchService } from 'src/app/services/match.service';
import { TournamentService } from 'src/app/services/tournament.service';

@Component({
  selector: 'app-prepare-tournament',
  templateUrl: './prepare-tournament.component.html',
  styleUrls: ['./prepare-tournament.component.css']
})
export class PrepareTournamentComponent implements OnInit {

  teams: Team[] = [];
  tournamentName: string = "";
  teamsH1: Team[] = [];
  teamsH2: Team[] = [];

  constructor(private route: ActivatedRoute,
    private router:Router, 
    private tournamentService: TournamentService,
    private notifier: NotifierService) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.tournamentName = params['name'];
        this.loadData()
      }
    )

    
  }

  loadData(){
    this.tournamentService.getTeams(this.tournamentName).subscribe({next:
      data =>{
        this.teams = data;

        this.teams.sort((a:Team,b:Team) => a.id - b.id)

        this.teamsH1 = this.teams.splice(0, this.teams.length/2)
        this.teamsH2 = this.teams.splice(this.teams.length/2-1, this.teams.length)
        this.teams = [];
      },
      error: error =>{
        this.notifier.notify( 'error','Teams konnten nicht geladen werden!');
      }
    })
  }

  startMatches(){
    if(this.teamsH1.length == this.teamsH2.length){
      for (let i = 0; i < this.teamsH1.length; i++) {
        this.teams.push(this.teamsH1[i])
        this.teams.push(this.teamsH2[i])
      }

      this.tournamentService.setUpMatchesForTournament(this.tournamentName, this.teams).subscribe({next:
        data =>{
          this.router.navigate(['play-tournament', this.tournamentName])
        },
        error: error =>{
          this.notifier.notify( 'error','Teams konnten nicht in Matches gespeichert werden!');
        }
      });
    }
  }

}
