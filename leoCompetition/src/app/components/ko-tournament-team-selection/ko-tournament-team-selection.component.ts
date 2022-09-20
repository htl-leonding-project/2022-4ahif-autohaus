import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Team } from 'src/app/models/team.model';
import { Tournament } from 'src/app/models/tournament.model';
import { TeamService } from 'src/app/services/team.service';
import { TournamentService } from 'src/app/services/tournament.service';

@Component({
  selector: 'app-ko-tournament-team-selection',
  templateUrl: './ko-tournament-team-selection.component.html',
  styleUrls: ['./ko-tournament-team-selection.component.css']
})
export class KOTournamentTeamSelectionComponent implements OnInit {

  teams: Team[] = []
  newTournament: Tournament;

  constructor(private router: Router, private teamService: TeamService, private tournamentService: TournamentService) { 
    this.newTournament = {id: 0, name: "", startDate: null}
  }

  ngOnInit(): void {
    this.refreshData();
  }

  refreshData(){
    this.teamService.getTeams().subscribe({next:
      data =>{
        this.teams = data;
      },
      error: error =>{
        alert('Error loading teams');
      }
    });
  }

  onSubmit(teamForm: NgForm){
    if(teamForm.valid)
      this.tournamentService.save(this.newTournament).subscribe({next:
        data => {
          this.router.navigate(['/tournaments']);
        },
        error: error => {
          alert('Speichern fehlgeschlagen');
        }
      });
  
  }

}
