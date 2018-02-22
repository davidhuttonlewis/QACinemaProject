package com.qa.cinema.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.qa.cinema.models.Showing;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;


public class ShowingRepository {

		@PersistenceContext(unitName = "cinemaPU")
		private EntityManager em;
		
		public Showing find(Integer id) {
			return em.find(Showing.class, id);
		}
		
		public List<Showing>findAll(){
			return em.createQuery("SELECT showings FROM Showing showings ORDER BY showings.id", Showing.class).getResultList();
		}
		
		 @Transactional(REQUIRED)
		    public Showing create(@NotNull Showing showing) {
		        em.persist(showing);
		        return showing;
		}
		
		 @Transactional(REQUIRED)
		    public void delete(@NotNull Integer id) {
		        em.remove(em.getReference(Showing.class, id));
		    }
}
