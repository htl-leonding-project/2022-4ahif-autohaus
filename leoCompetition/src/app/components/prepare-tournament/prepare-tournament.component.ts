import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { Team } from 'src/app/models/team.model';
import { MatchService } from 'src/app/services/match.service';
import { TournamentService } from 'src/app/services/tournament.service';

@Component({
  selector: 'app-prepare-tournament',
  templateUrl: './prepare-tournament.component.html',
  styleUrls: ['./prepare-tournament.component.css']
})
export class PrepareTournamentComponent implements OnInit {

  teams: Team[] = [];
  tournamentName: string = "";
  teamsH1: Team[] = [];
  teamsH2: Team[] = [];
  private draggedIndex: number = -1;
  public draggedOverIndex: number = -1;

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
  
  onDragStart(index: any): void {
    this.draggedIndex = index;
  }

  allowDrop($event: any, index: any): void {
    this.draggedOverIndex = index;
    $event.preventDefault();
  }

  onDrop($event: any, index: any): void {
    $event.preventDefault();
    console.log(this.draggedOverIndex)
    console.log(this.draggedIndex)
    console.log($event.target)
    const team = this.teams[this.draggedIndex];
    this.teams.splice(this.draggedIndex, 1);
    this.teams.splice(index, 0, team);
    this.teamsH1 = this.teams.splice(0, this.teams.length/2);
    this.teamsH2 = this.teams;
    this.teamsH1.forEach(element => {
      console.log(element.name)
    });
    this.teamsH2.forEach(element => {
      console.log(element.name)
    });
    this.draggedIndex = -1;
    this.draggedOverIndex = -1;
  }

  startMatches(){

  }

}
