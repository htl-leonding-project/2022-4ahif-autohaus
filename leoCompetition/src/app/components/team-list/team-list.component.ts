import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { Team } from 'src/app/models/team.model';
import { TeamService } from 'src/app/services/team.service';

@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.css']
})
export class TeamListComponent implements OnInit {

  teams: Team[] = []

  constructor(
    private teamService: TeamService,
    private notifier: NotifierService) { }

  ngOnInit(): void {
    this.refreshData();
  }

  refreshData(){
    this.teamService.getTeams().subscribe({next:
      data =>{
        this.teams = data;
      },
      error: error =>{
        this.notifier.notify( 'error','Teams konnten nicht geladen werden!');
      }
    });
  }
}
