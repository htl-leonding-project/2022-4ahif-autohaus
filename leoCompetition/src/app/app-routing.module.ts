import { NgModule, OnInit } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PlayTournamentComponent } from './components/play-tournament/play-tournament.component';
import { TeamCreationComponent } from './components/team-creation/team-creation.component';
import { TournamentResultComponent } from './components/tournament-result/tournament-result.component';
import { PrepareTournamentComponent } from './components/prepare-tournament/prepare-tournament.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full' },
  {path: 'home', component: HomeComponent},
  {path: 'new-team', component: TeamCreationComponent},
  {path: 'play-tournament/:name', component: PlayTournamentComponent},
  {path: 'result/:name', component: TournamentResultComponent},
  {path: 'preparation/:name', component: PrepareTournamentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule{ 

}
