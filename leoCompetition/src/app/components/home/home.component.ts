import { Component, OnInit } from '@angular/core';
import { GroupService } from 'src/app/services/group.service';
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

  constructor(
    private teamService: TeamService,
    private matchService: MatchService,
    private tournamentService: TournamentService,
    private groupService: GroupService 
  ) { }

  ngOnInit(): void {
    this.refreshValues();
  }

  refreshValues(){
    this.teamService.getTeamsAmount().subscribe({next:
      data => {
        this.teamAmount = data;
      },
      error: error => {
        alert('Error loading teams!');
      }
    });

    this.matchService.getMatchesAmount().subscribe({next:
      data => {
        this.matchesAmount = data;
      },
      error: error => {
        alert('Error loading matches!');
      }
    });

    this.tournamentService.getTournamentsAmount().subscribe({next:
      data => {
        this.tournamentAmount = data;
      },
      error: error => {
        alert('Error loading tournaments!');
      }
    });

    this.groupService.getGroupsAmount().subscribe({next:
      data => {
        this.groupsAmount = data;
      },
      error: error => {
        alert('Error loading groups!');
      }
    });
  }

}
