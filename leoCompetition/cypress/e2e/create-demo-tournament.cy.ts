import { timeout } from "rxjs";

var DATE = Date.now().toString();

describe('... Demo Test', () => {
    beforeEach(() => {
        // Navigate Start Page and create new Tournament
      });

      it('... Create new Tournament', () => {
        cy.visit('http://localhost:4200/home')
        cy.get('.btn')
        cy.contains("Neues Turnier erstellen").click()
        cy.url().should('include', '/new-team')
      });
    
      it('... Create new Team ...', () => {
        cy.visit('http://localhost:4200/new-team')
        cy.url().should('include', '/new-team')
        
        //Enter new Team name
        cy.get('#nameElement')
          .type('DemoTeam{enter}')

        //Enter new Team abbr
        cy.get('#abbrElement')
          .type('DT{enter}')

        //Press the save button
        cy.get('.btn')
        cy.contains('Speichern').click()
        
        //Enter new Team name
        cy.get('#nameElement')
          .type('Schüler{enter}')

        //Enter new Team abbr
        cy.get('#abbrElement')
          .type('S{enter}')

        //Press the save button
        cy.get('.btn')
        cy.contains('Speichern').click()
        cy.url().should('include', '/new-team')
        
        //Enter new Team name
        cy.get('#nameElement')
          .type('Christoph{enter}')

        //Enter new Team abbr
        cy.get('#abbrElement')
          .type('CH{enter}')

        //Press the save button
        cy.get('.btn')
        cy.contains('Speichern').click()
        cy.url().should('include', '/new-team')
        
        //Enter new Team name
        cy.get('#nameElement')
          .type('Leondinger Schwammerl{enter}')

        //Enter new Team abbr
        cy.get('#abbrElement')
          .type('LS{enter}')

        //Press the save button
        cy.get('.btn')
        cy.contains('Speichern').click()

        //Enter Tournament Name
        cy.get('#tournamentNameElement')
          .type(DATE, '{enter}')

        //Start Tournament
        cy.get('.btn')
        cy.contains('Turnier starten').click()
        cy.url().should('include', '/preparation')
      });

      it('... Set Matches ...', () => {
        cy.visit('http://localhost:4200/preparation/'+DATE)
        cy.get('.btn')
        cy.contains('Turnier starten').click()
        cy.url().should('include', '/play-tournament')
      })

      it('... Enter Results ...', () => {
        cy.visit('http://localhost:4200/play-tournament/'+DATE)
        //Enter Result of First Match
        cy.get('.btn')
        cy.contains('Ergebnis bearbeiten').click()

        cy.get('#team1Points')
          .type('{backspace}2{enter}')

        cy.get('#team2Points')
          .type('{backspace}1{enter}')

        cy.get('.btn')
        cy.contains('Speichern').click()
        timeout: 100

        //Enter Result of 2nd Match
        cy.get('.btn').eq(1).click()

        cy.get('#team1Points')
          .type('{backspace}1{enter}')

        cy.get('#team2Points')
          .type('{backspace}3{enter}')

        cy.get('.btn')
        cy.contains('Speichern').click()
        timeout: 100

        //Enter Result of Final Match
        cy.get('.btn').eq(2).click()

        cy.get('#team1Points')
          .type('{backspace}3{enter}')

        cy.get('#team2Points')
          .type('{backspace}2{enter}')

        cy.get('.btn')
        cy.contains('Speichern').click()

        cy.get('.btn')
        cy.contains('Turnier beenden').click()
      });
});