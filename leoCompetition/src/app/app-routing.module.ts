import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PlayTournamentComponent } from './components/play-tournament/play-tournament.component';
import { TeamCreationComponent } from './components/team-creation/team-creation.component';
import { TeamListComponent } from './components/team-list/team-list.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full' },
  {path: 'home', component: HomeComponent},
  {path: 'teams', component: TeamListComponent},
  {path: 'new-team', component: TeamCreationComponent},
  {path: 'play-tournament', component: PlayTournamentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
