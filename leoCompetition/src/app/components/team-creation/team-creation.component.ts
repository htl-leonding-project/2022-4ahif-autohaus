import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/models/team.model';
import { TeamService } from 'src/app/services/team.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-team-creation',
  templateUrl: './team-creation.component.html',
  styleUrls: ['./team-creation.component.css']
})
export class TeamCreationComponent implements OnInit {

  newTeam: Team;

  constructor(private router: Router, private teamService: TeamService) { 
    this.newTeam = {id: 0, abbr: "", name: "", winAmount: 0}
  }

  ngOnInit(): void {
  }

  onSubmit(teamForm: NgForm){
    if(teamForm.valid)
      this.teamService.saveTeam(this.newTeam).subscribe({next:
        data => {
          this.router.navigate(['/teams']);
        },
        error: error => {
          alert('Speichern fehlgeschlagen');
        }
      });
  
  }
}
