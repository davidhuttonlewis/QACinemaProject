package com.qa.cinema.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qa.cinema.models.Showing;


public class ShowingRepository {

		@PersistenceContext(unitName = "cinemaPU")
		private EntityManager em;
		
		public Showing find(Integer id) {
			return em.find(Showing.class, id);
		}
		
		public List<Showing>findAll(){
			return em.createQuery("SELECT showings FROM Showing showings ORDER BY showings.id", Showing.class).getResultList();
		}
		
}
