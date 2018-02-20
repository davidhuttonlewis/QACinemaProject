package com.qa.cinema.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qa.cinema.models.Screen;

public class ScreenRepository {
	// find, create, delete, all
	@PersistenceContext(unitName = "cinemaPU")
	private EntityManager em;
	
	public Screen find(Integer id) {
		return em.find(Screen.class, id);
	}
	
	public Screen create(Screen screen) {
		em.persist(screen);
		return screen;
	}
}
