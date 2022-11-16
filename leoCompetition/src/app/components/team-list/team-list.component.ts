import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/models/team.model';
import { TeamService } from 'src/app/services/team.service';

@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.css']
})
export class TeamListComponent implements OnInit {

  teams: Team[] = []

  constructor(private teamService: TeamService) { }

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

}
