import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { KOTournamentTeamSelectionComponent } from './components/ko-tournament-team-selection/ko-tournament-team-selection.component';
import { TeamCreationComponent } from './components/team-creation/team-creation.component';
import { TeamListComponent } from './components/team-list/team-list.component';
import { TournamentListComponent } from './components/tournament-list/tournament-list.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full' },
  {path: 'home', component: HomeComponent},
  {path: 'teams', component: TeamListComponent},
  {path: 'new-team', component: TeamCreationComponent},
  {path: 'tournaments', component: TournamentListComponent},
  {path: 'start-tournament', component: KOTournamentTeamSelectionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
