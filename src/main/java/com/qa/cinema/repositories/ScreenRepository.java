package com.qa.cinema.repositories;

import java.util.List;

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
	
	public List<Screen> findAll(){
		return em.createQuery("SELECT screen FROM Screen screen ORDER BY screen.id", Screen.class).getResultList();
	}
	
//	public Screen create(Screen screen) {
//		em.persist(screen);
//		return screen;
//	}
}
