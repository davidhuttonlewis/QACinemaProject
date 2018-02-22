package com.qa.cinema.repositories;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

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
	
	@Transactional(REQUIRED)
	public Screen create(@NotNull Screen screen) {
		em.persist(screen);
		return screen;
	}
	
	@Transactional(REQUIRED)
	public void destroy(@NotNull Integer id) {
        em.remove(em.getReference(Screen.class, id));
	}
}
