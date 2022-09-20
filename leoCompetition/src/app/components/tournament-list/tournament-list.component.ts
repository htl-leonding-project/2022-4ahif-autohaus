import { Component, OnInit } from '@angular/core';
import { Tournament } from 'src/app/models/tournament.model';
import { TournamentService } from 'src/app/services/tournament.service';

@Component({
  selector: 'app-tournament-list',
  templateUrl: './tournament-list.component.html',
  styleUrls: ['./tournament-list.component.css']
})
export class TournamentListComponent implements OnInit {

  tournaments: Tournament[] = []

  constructor(private tournamentService: TournamentService) { }

  ngOnInit(): void {
    this.refreshData();
  }

  refreshData(){
    this.tournamentService.getTournaments().subscribe({next:
      data =>{
        this.tournaments = data;
      },
      error: error =>{
        alert('Error loading teams');
      }
    });
  }

}
