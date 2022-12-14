import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { Tournament } from 'src/app/models/tournament.model';
import { TournamentService } from 'src/app/services/tournament.service';

@Component({
  selector: 'app-tournament-list',
  templateUrl: './tournament-list.component.html',
  styleUrls: ['./tournament-list.component.css']
})
export class TournamentListComponent implements OnInit {

  tournaments: Tournament[] = [];

  constructor(
    private tournamentService: TournamentService,
    private router: Router,
    private notifier: NotifierService) { }

  ngOnInit(): void {
    this.refreshData();
  }

  refreshData(){
    this.tournamentService.getTournaments().subscribe({next:
      data =>{
        this.tournaments = data;
      },
      error: error =>{
        this.notifier.notify( 'error','Turniere konnten nicht geladen werden!');
      }
    });
  }

  redirect(name:String){
    this.router.navigate(['/result/'+name.replace(' ', '_')]);
  }

  remove(id:number){
    this.tournamentService.deleteTournament(id).subscribe({next:
      data => {
        this.refreshData()
      },
      error: error =>{
        this.notifier.notify( 'error','Turniere konnte nicht gelöscht werden!');
      }
    })
  }

}
