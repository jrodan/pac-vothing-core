package com.prodyna.pac.vothing.model.helper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {
	
	@PersistenceContext(unitName = "vothingPU")
	private EntityManager entityManager;
	
	@Produces
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
