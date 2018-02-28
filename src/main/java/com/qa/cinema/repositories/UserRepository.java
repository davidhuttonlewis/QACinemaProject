package com.qa.cinema.repositories;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.qa.cinema.models.Screen;
import com.qa.cinema.models.User;

public class UserRepository {

	@PersistenceContext(unitName = "cinemaPU")
	private EntityManager em;
	
	public User find(Integer id) {
		return em.find(User.class, id);
	}
	
	public List<User> findAll(){
		return em.createQuery("SELECT user FROM User user ORDER BY user.id", User.class).getResultList();
	}
	
	@Transactional(REQUIRED)
	public User create(@NotNull User user) {
		em.persist(user);
		return user;
	}
	
	@Transactional(REQUIRED)
	public void destroy(@NotNull Integer id) {
        em.remove(em.getReference(User.class, id));
	}
	
	@Transactional(REQUIRED)
	public void update(User user) {
        em.merge(user);
	}
		
}
