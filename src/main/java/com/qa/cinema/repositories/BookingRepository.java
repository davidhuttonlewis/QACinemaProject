package com.qa.cinema.repositories;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.qa.cinema.models.Booking;
import com.qa.cinema.util.BookingRefCreator;

public class BookingRepository {

	private static final Logger LOGGER = Logger.getLogger(BookingRepository.class.getName());
	
	@PersistenceContext(unitName = "cinemaPU")
	private EntityManager em;
	
	@Inject
	private BookingRefCreator bookingRefGen;
	
	public Booking find(Integer id) {
		return em.find(Booking.class, id);
	}
	
	public List<Booking> findAll(){
		return em.createQuery("SELECT booking FROM Booking booking ORDER BY booking.id", Booking.class).getResultList();
	}
	
	@Transactional(REQUIRED)
	public Booking create(@NotNull Booking booking) {
		LOGGER.info("Repo - in make book");
		em.persist(booking);
		
		LOGGER.info("Repo - in make bookinref");
		booking.setBookingRef(bookingRefGen.toBookingRef(booking.getId()));
		
		// UPDATE THE OBJECT IN DB
		LOGGER.info("Repo - save book to table");
		em.merge(booking);
		
		LOGGER.info("Repo - return book");
		return booking;
	}
	
	@Transactional(REQUIRED)
	public void destroy(@NotNull Integer id) {
        em.remove(em.getReference(Booking.class, id));
	}
	
}
