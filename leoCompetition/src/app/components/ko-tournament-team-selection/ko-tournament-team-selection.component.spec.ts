import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KOTournamentTeamSelectionComponent } from './ko-tournament-team-selection.component';

describe('KOTournamentTeamSelectionComponent', () => {
  let component: KOTournamentTeamSelectionComponent;
  let fixture: ComponentFixture<KOTournamentTeamSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KOTournamentTeamSelectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KOTournamentTeamSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
