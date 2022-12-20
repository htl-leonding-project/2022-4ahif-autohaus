import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrepareTournamentComponent } from './prepare-tournament.component';

describe('PrepareTournamentComponent', () => {
  let component: PrepareTournamentComponent;
  let fixture: ComponentFixture<PrepareTournamentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PrepareTournamentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrepareTournamentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
