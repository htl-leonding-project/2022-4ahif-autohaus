import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/models/team.model';
import { TeamService } from 'src/app/services/team.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

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

  constructor(private router: Router, private teamService: TeamService) { 
    this.newTeam = {id: 0, abbr: "", name: "", winAmount: 0}
  }

  ngOnInit(): void {
    this.refreshTeams();
  }

  onSubmit(teamForm: NgForm){
    if(teamForm.valid)
      this.teamService.saveTeam(this.newTeam).subscribe({next:
        data => {
          this.refreshTeams();
          this.newTeam.abbr = ""
          this.newTeam.name = ""
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
}
