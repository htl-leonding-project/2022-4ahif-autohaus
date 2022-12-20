import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/models/team.model';
import { TeamService } from 'src/app/services/team.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { TournamentService } from 'src/app/services/tournament.service';
import { NotifierService } from 'angular-notifier';
import * as React from 'react';

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
  lines: any[] = []; //for headings
  linesR: any[] = [];

  constructor(
    private router: Router, 
    private teamService: TeamService, 
    private tournamentService: TournamentService, 
    private notifier: NotifierService) { 
    this.newTeam = {id: 0, abbr: "", name: ""}
  }

  ngOnInit(): void {

    const input: any = document.getElementById('input');
    input.type = 'file';
    input.accept = '.csv';
    
    input.onchange = (event: React.ChangeEvent<HTMLInputElement>) => {
      const file = event.target.files![0];
      const reader = new FileReader();

      console.log(file.type)

      if(file.type == 'text/csv'){

      reader.readAsText(file);
      reader.onload = (e) => {
        let csv: any = reader.result;
        let allTextLines = [];
        allTextLines = csv.split(/\r|\n|\r/);

        console.log("file uploaded: " +file.name)
      
        let headers = allTextLines[0].split(';')
        let data = headers;
        let tarr = [];
        for(let j = 0; j < headers.length; j++){
          tarr.push(data[j]);
        }

        this.lines.push(tarr);

        if(this.lines[0][0] == "name" && this.lines[0][1]=="abbr"){
          let tarrR = [];
            
            let arrl = allTextLines.length;
            let rows = [];
            for(let i = 1; i < arrl; i++){
            rows.push(allTextLines[i].split(';'));
          
            }
            
            for (let j = 0; j < arrl; j++) {
        
                tarrR.push(rows[j]);
                
            }
            this.linesR.push(tarrR);

            console.log(this.linesR);

            for (let i = 1; i < this.linesR[0].length; i+=2) {
              this.select({id: this.newTeam.id, name: this.linesR[0][i][0], abbr: this.linesR[0][i][1].toUpperCase()});
              this.newTeam.abbr = "";
              this.newTeam.name = "";
            }

          }
        }

        this.notifier.notify('success', 'File wurde erfolgreich verarbeitet');

      }
      else{
        this.notifier.notify('info', 'Bitte nur .csv Files hochladen');
      }
    };
    this.lines  = []; //for headings
    this.linesR = [];


  }

  onSubmit(teamForm: NgForm){
    if(teamForm.valid){
      if(this.addedTeams.find((team) => team.abbr.toLocaleUpperCase() == this.newTeam.abbr.toLocaleUpperCase())==null){
          this.select({id: this.newTeam.id, name: this.newTeam.name, abbr: this.newTeam.abbr.toUpperCase()});
          this.newTeam.abbr = "";
          this.newTeam.name = "";
      }
      else{
        this.notifier.notify('info','Teams sollen per Abkürzung unterscheidbar sein');
      }
    }
  }

  select(selected: Team){
    this.addedTeams.push(selected);
  }

  deselect(selected: Team){
    this.addedTeams = this.addedTeams.filter((team) => team.abbr !== selected.abbr)
  }

  create(){
    if(this.tournamentName != ""){
      this.tournamentService.exists(this.tournamentName).subscribe({next:
        data => {
          this.exists = data;
          
          if(this.exists == false){
            if(this.addedTeams.length==4 || this.addedTeams.length==8 || this.addedTeams.length==16){
              this.addedTeams.forEach(team => {
                this.teamService.saveTeam(team).subscribe({
                  error: error => {
                    this.notifier.notify('error','Teams speichern fehlgeschlagen!');
                  }})
              });
              this.tournamentService.saveTournament(this.tournamentName, this.addedTeams).subscribe({next:
                data => {
                  this.router.navigate(['preparation', this.tournamentName])
                },
                error: error => {
                  this.notifier.notify( 'error','Speichern fehlgeschlagen!');
                }
              });
            } else {
              this.notifier.notify('info', 'Teamanzahl muss in den Bereichen 4, 8 oder 16 liegen');
            }
          }
          else{
            this.notifier.notify( 'error','Turnier mit diesem Namen existiert bereits');
          }
        }
      });
    } else {
      this.notifier.notify('info', 'Das Turnier braucht einen Namen');
    }
  }


}
