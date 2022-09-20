import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';

import { HttpClientModule } from '@angular/common/http';
import { TeamListComponent } from './components/team-list/team-list.component';
import { TeamCreationComponent } from './components/team-creation/team-creation.component';
import { TournamentListComponent } from './components/tournament-list/tournament-list.component';
import { KOTournamentTeamSelectionComponent } from './components/ko-tournament-team-selection/ko-tournament-team-selection.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TeamListComponent,
    TeamCreationComponent,
    TournamentListComponent,
    KOTournamentTeamSelectionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
