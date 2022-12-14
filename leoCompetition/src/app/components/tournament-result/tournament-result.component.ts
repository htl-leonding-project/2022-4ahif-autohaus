import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Team } from 'src/app/models/team.model';
import { TournamentService } from 'src/app/services/tournament.service';

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

  constructor(
    private route: ActivatedRoute, 
    private router: Router,
    private http: HttpClient,
    private tournamentService: TournamentService){}

  ngOnInit(): void {
    
    this.route.params.subscribe(
      params => {
        this.tournamentName = params['name'];
        this.id = params['id'];

        this.tournamentService.isLastMatchDone(this.tournamentName).subscribe(
          params => {
            if(params.valueOf() == true)
              this.display = true
            else
              alert('Turnier wurde nicht beendet!')
          }
        )
      }
    )
    this.refreshData(this.id);
  }

  refreshData(id: number){
    this.tournamentService.getTeams(id).subscribe({next:
      data =>{
        this.teams = data;
      },
      error: e =>{
        alert('Error loading Teams');
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
