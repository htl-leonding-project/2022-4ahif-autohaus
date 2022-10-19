import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-tournament-result',
  templateUrl: './tournament-result.component.html',
  styleUrls: ['./tournament-result.component.css']
})
export class TournamentResultComponent implements OnInit {

  tournamentName:string="";

  constructor(
    private route: ActivatedRoute, 
    private router: Router,
    private http: HttpClient){}

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        this.tournamentName = params['name'];
      }
    )
  }

  backToStart(){
      this.router.navigate(['/home']);
  }
}
