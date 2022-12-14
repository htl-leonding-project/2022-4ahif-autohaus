import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Team } from 'src/app/models/team.model';
import { TournamentService } from 'src/app/services/tournament.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-tournament-result',
  templateUrl: './tournament-result.component.html',
  styleUrls: ['./tournament-result.component.css']
})
export class TournamentResultComponent implements OnInit {

  tournamentName:string="";
  id: number=-1;
  teams: Team[]=[];
  display:boolean = false;
  private notifier: NotifierService;

  constructor(
    private route: ActivatedRoute, 
    private router: Router,
    private http: HttpClient,
    private tournamentService: TournamentService,
    notifier: NotifierService){
      this.notifier = notifier;
    }

  ngOnInit(): void {
    
    this.route.params.subscribe(
      params => {
        this.tournamentName = params['name'];

        this.tournamentService.isLastMatchDone(this.tournamentName).subscribe(
          params => {
            if(params.valueOf() == true)
              this.display = true
          }
        )
      }
    )
    this.refreshData();
  }

  refreshData(){
    this.tournamentService.getTeams(this.tournamentName).subscribe({next:
      data =>{
        this.teams = data;
      },
      error: e =>{
        
      }
    });
  }

  backToStart(){
      this.router.navigate(['/home']);
  }

  continue(){
    this.router.navigate(['/play-tournament/'+this.tournamentName]);
  }
}
