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
import { PlayTournamentComponent } from './components/play-tournament/play-tournament.component';
import { TournamentResultComponent } from './components/tournament-result/tournament-result.component';
import { TournamentListComponent } from './components/tournament-list/tournament-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TeamListComponent,
    TeamCreationComponent,
    PlayTournamentComponent,
    TournamentResultComponent,
    TournamentListComponent
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
