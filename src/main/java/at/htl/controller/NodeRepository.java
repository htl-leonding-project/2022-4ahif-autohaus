package at.htl.controller;

import at.htl.entity.Node;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class NodeRepository {

    @Inject
    EntityManager entityManager;

    public Node save(Node node){
        return this.entityManager.merge(node);
    }
}
