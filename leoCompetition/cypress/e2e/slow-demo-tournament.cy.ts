import { timeout } from "rxjs";
import { slowCypressDown } from 'cypress-slow-down'

slowCypressDown(500)

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
      });

      it('... Add Teams to Tournament ...', () => {
        //Add pre-created Team to Tournament
        cy.get('.btn')
        cy.contains('+').click()
        timeout: 10000

        cy.get('.btn')
        cy.contains('+').click()
        timeout: 10000

        cy.get('.btn')
        cy.contains('+').click()
      });

      it('... Finish Tournament creation ...', () => {
        //Enter Tournament name
        cy.get('#tournamentNameElement')
          .type(Date.now().toString(), '{enter}')

        //Start Tournament
        cy.get('.btn')
        cy.contains('Turnier starten').click()
        cy.url().should('include', '/play-tournament')
      });

      it('... Enter Result Match 1...', () => {
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
      });

      it('... Enter Result Match 2 ...', () => {
        //Enter Result of Second Match
        cy.get('.btn').eq(1).click()

        cy.get('#team1Points')
          .type('{backspace}1{enter}')

        cy.get('#team2Points')
          .type('{backspace}3{enter}')

        cy.get('.btn')
        cy.contains('Speichern').click()
        timeout: 100
      });

      it('... Enter Result Final ...', () => {
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