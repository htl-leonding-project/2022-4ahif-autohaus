package at.htl.controller;

import at.htl.entity.Tournament;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TournamentRepository implements PanacheRepository<Tournament> {

}
