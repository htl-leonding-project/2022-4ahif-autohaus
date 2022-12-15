import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/models/team.model';
import { TeamService } from 'src/app/services/team.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { TournamentService } from 'src/app/services/tournament.service';
import { NotifierService } from 'angular-notifier';

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
  fileContent: string | ArrayBuffer | null = '';
  lines = []; //for headings
  linesR = [];

  constructor(
    private router: Router, 
    private teamService: TeamService, 
    private tournamentService: TournamentService, 
    private notifier: NotifierService) { 
    this.newTeam = {id: 0, abbr: "", name: ""}
  }

  ngOnInit(): void {
  }

  onSubmit(teamForm: NgForm){
    if(teamForm.valid){
          this.select({id: this.newTeam.id, name: this.newTeam.name, abbr: this.newTeam.abbr.toUpperCase()});
          this.newTeam.abbr = "";
          this.newTeam.name = "";
    }
  }

  select(selected: Team){
    this.addedTeams.push(selected);
  }

  deselect(selected: Team){
    this.addedTeams = this.addedTeams.filter((team) => team.id !== selected.id)
  }

  create(){
    if(this.tournamentName != ""){
      this.tournamentService.exists(this.tournamentName).subscribe({next:
        data => {
          this.exists = data;
          
          if(this.exists == false){
            this.addedTeams.forEach(team => {
              this.teamService.saveTeam(team).subscribe({
                error: error => {
                  this.notifier.notify('error','Teams speichern fehlgeschlagen!');
                }})
            });
            this.tournamentService.saveTournament(this.tournamentName, this.addedTeams).subscribe({next:
              data => {
                this.router.navigate(['play-tournament', this.tournamentName])
              },
              error: error => {
                this.notifier.notify( 'error','Speichern fehlgeschlagen!');
              }
            });
          }
          else{
            this.notifier.notify( 'error','Turnier mit diesem Namen existiert bereits');
          }
        }
      });
    }
  }
}
