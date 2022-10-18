import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/models/team.model';
import { TeamService } from 'src/app/services/team.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { TournamentService } from 'src/app/services/tournament.service';

@Component({
  selector: 'app-team-creation',
  templateUrl: './team-creation.component.html',
  styleUrls: ['./team-creation.component.css']
})
export class TeamCreationComponent implements OnInit {

  tournamentName: string = "";
  newTeam: Team;
  addedTeams: Team[] = []
  allTeams: Team[] = []
  exists: boolean = false;

  constructor(private router: Router, private teamService: TeamService, private tournamentService: TournamentService) { 
    this.newTeam = {id: 0, abbr: "", name: "", winAmount: 0}
  }

  ngOnInit(): void {
    this.refreshTeams();
  }

  onSubmit(teamForm: NgForm){
    if(teamForm.valid)
      this.teamService.saveTeam(this.newTeam).subscribe({next:
        data => {
          this.select({id: this.newTeam.id, name: this.newTeam.name, abbr: this.newTeam.abbr.toUpperCase(), winAmount: this.newTeam.winAmount});
          this.newTeam.abbr = "";
          this.newTeam.name = "";
          this.refreshTeams();
        },
        error: error => {
          alert('Speichern fehlgeschlagen');
        }
      });
  }

  refreshTeams(){
    this.teamService.getTeams().subscribe({next:
      data =>{
        this.allTeams = data;
        this.allTeams = this.allTeams.filter((team) => this.addedTeams.find((obj) => {return obj.abbr === team.abbr})== null)
      },
      error: error =>{
        alert('Error loading teams');
      }
    });

  }

  select(selected: Team){
    this.addedTeams.push(selected);
    this.refreshTeams();
  }

  deselect(selected: Team){
    this.addedTeams = this.addedTeams.filter((team) => team.id !== selected.id)
    this.refreshTeams();
  }

  create(){
    this.tournamentService.exists(this.tournamentName).subscribe({next:
      data => {
        this.exists = data;
        
        if(this.exists == false){
          this.tournamentService.saveTournament(this.tournamentName, this.addedTeams).subscribe({next:
            data => {
              this.router.navigate(['play-tournament', this.tournamentName])
            },
            error: error => {
              alert('Speichern fehlgeschlagen');
            }
          });
        }
        else{
          alert('Turnier mit diesem Namen existiert bereits');
        }
      }
    });
  }
}
