import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
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
    private router: Router,
    private notifier: NotifierService) {}

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
        this.notifier.notify( 'error','Anzahl Teams konnten nicht geladen werden!');
      }
    });

    this.matchService.getMatchesAmount().subscribe({next:
      data => {
        this.matchesAmount = data;
      },
      error: error => {
        this.notifier.notify( 'error','Anzahl Matches konnten nicht geladen werden!');
      }
    });

    this.tournamentService.getTournamentsAmount().subscribe({next:
      data => {
        this.tournamentAmount = data;
      },
      error: error => {
        this.notifier.notify( 'error','Anzahl Turniere konnten nicht geladen werden!');
      }
    });
  }

  refreshTable(){
    this.tournamentService.getTournaments().subscribe({next:
      data =>{
        this.tournaments = data;
      },
      error: error =>{
        this.notifier.notify( 'error','Liste der Turniere konnten nicht geladen werden!');
      }
    });
  }

  redirect(name:String){
    this.router.navigate(['/result/'+name.replace(' ', '_')]);
  }

  remove(t:Tournament){
    console.log(t.id)
    if(Date.now()+(24*60*60*1000) < Date.parse(t.startDate!.toString())+(24*60*60*1000) || t.status == "FINISHED"){
      this.tournamentService.deleteTournament(t.id).subscribe({next:
        data => {
          this.refreshTable()
          this.refreshValues()
          this.notifier.notify('success', 'Turnier wurde erfolgreich gelöscht!')
        },
        error: error =>{
          this.notifier.notify( 'error','Turnier konnte nicht gelöscht werden!');
        }
      })
    } else {
      this.notifier.notify( 'info','Aus Sicherheitsgründen können Turniere erst nach 24 Stunden gelöscht werden.');
    }
  }

}
