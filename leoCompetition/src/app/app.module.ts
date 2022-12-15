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
import { NotifierModule, NotifierOptions } from 'angular-notifier';

/**
 * Custom angular notifier options
 */
const customNotifierOptions: NotifierOptions = {
  position: {
    horizontal: {
      position: 'left',
      distance: 12,
    },
    vertical: {
      position: 'bottom',
      distance: 12,
      gap: 10,
    },
  },
  theme: 'material',
  behaviour: {
    autoHide: 6000,
    onClick: 'hide',
    onMouseover: 'pauseAutoHide',
    showDismissButton: true,
    stacking: 4,
  },
  animations: {
    enabled: true,
    show: {
      preset: 'slide',
      speed: 300,
      easing: 'ease',
    },
    hide: {
      preset: 'fade',
      speed: 300,
      easing: 'ease',
      offset: 50,
    },
    shift: {
      speed: 300,
      easing: 'ease',
    },
    overlap: 150,
  },
};

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
    CommonModule,
    NotifierModule.withConfig(customNotifierOptions)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
