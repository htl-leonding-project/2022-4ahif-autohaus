import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tournament } from 'src/app/models/tournament.model';
import { MatchService } from 'src/app/services/match.service';
import { TeamService } from 'src/app/services/team.service';
import { TournamentService } from 'src/app/services/tournament.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  teamAmount: number = 0;
  matchesAmount: number = 0;
  groupsAmount: number = 0;
  tournamentAmount: number = 0;
  tournaments: Tournament[] = [];

  constructor(
    private teamService: TeamService,
    private matchService: MatchService,
    private tournamentService: TournamentService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.refreshValues();
    this.refreshTable();
  }

  refreshValues(){
    this.teamService.getTeamsAmount().subscribe({next:
      data => {
        this.teamAmount = data;
      },
      error: error => {
        alert('Error loading teams!');
      }
    });

    this.matchService.getMatchesAmount().subscribe({next:
      data => {
        this.matchesAmount = data;
      },
      error: error => {
        alert('Error loading matches!');
      }
    });

    this.tournamentService.getTournamentsAmount().subscribe({next:
      data => {
        this.tournamentAmount = data;
      },
      error: error => {
        alert('Error loading tournaments!');
      }
    });
  }

  refreshTable(){
    this.tournamentService.getTournaments().subscribe({next:
      data =>{
        this.tournaments = data;
      },
      error: error =>{
        alert('Error loading tournaments');
      }
    });
  }

  redirect(name:String){
    this.router.navigate(['/result/'+name.replace(' ', '_')]);
  }

  remove(id:number){
    this.tournamentService.deleteTournament(id).subscribe({next:
      data => {
        this.refreshTable()
        this.refreshValues()
      },
      error: error =>{
        alert('Error deleting tournament');
      }
    })
  }

}
