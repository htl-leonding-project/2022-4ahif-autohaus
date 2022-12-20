import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { Team } from 'src/app/models/team.model';
import { MatchService } from 'src/app/services/match.service';
import { TournamentService } from 'src/app/services/tournament.service';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-prepare-tournament',
  templateUrl: './prepare-tournament.component.html',
  styleUrls: ['./prepare-tournament.component.css']
})
export class PrepareTournamentComponent implements OnInit {

  todo = ['Get to work', 'Pick up groceries', 'Go home', 'Fall asleep'];

  done = ['Get up', 'Brush teeth', 'Take a shower', 'Check e-mail', 'Walk dog'];

  teams: Team[] = [];
  tournamentName: string = "";
  teamsH1: Team[] = [];
  teamsH2: Team[] = [];

  constructor(private route: ActivatedRoute,
    private router:Router, 
    private tournamentService: TournamentService,
    private notifier: NotifierService) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.tournamentName = params['name'];
        this.loadData()
      }
    )
  }

  loadData(){
    this.tournamentService.getTeams(this.tournamentName).subscribe({next:
      data =>{
        this.teams = data;
        this.teamsH1 = this.teams.splice(0, this.teams.length/2)
        this.teamsH2 = this.teams
      },
      error: error =>{
        this.notifier.notify( 'error','Teams konnten nicht geladen werden!');
      }
    })
  }
  
  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }

  startMatches(){

  }

}
