import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TournamentResultComponent } from './tournament-result.component';

describe('TournamentResultComponent', () => {
  let component: TournamentResultComponent;
  let fixture: ComponentFixture<TournamentResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TournamentResultComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TournamentResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
