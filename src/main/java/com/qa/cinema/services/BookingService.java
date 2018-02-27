package com.qa.cinema.services;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.qa.cinema.models.Booking;
import com.qa.cinema.models.Ticket;
import com.qa.cinema.repositories.BookingRepository;
import com.qa.cinema.rest.BookingEndpoint;
import com.qa.cinema.util.JSONCreator;

@Stateless
@Default
public class BookingService {

	private static final Logger LOGGER = Logger.getLogger(BookingEndpoint.class.getName()); 
	
	@PersistenceContext(unitName = "cinemaPU")
	private EntityManager em;
	
	@Inject
	private BookingRepository bookingRepository;
	
	@Inject
	private JSONCreator json;
	
	public Response getBooking(Integer id) {
		LOGGER.info("in get book");
		Booking booking = bookingRepository.find(id);
		LOGGER.info("got book");
		if (booking == null) {
			LOGGER.info("no book");
			return Response.status(NOT_FOUND).build();
		}
		LOGGER.info("return book");
		return Response.ok(json.toJSON(booking)).build();
	}

	public Response getAllBookings() {

		LOGGER.info("in get books");
		List<Booking> bookings = bookingRepository.findAll();
		LOGGER.info("got books");
		if (bookings.isEmpty()) {
			LOGGER.info("no books");
			return Response.status(NO_CONTENT).build();
		}
		LOGGER.info("returning books");
		return Response.ok(json.toJSON(bookings)).build();
	}

	public Response createBooking(Booking booking,  UriInfo uriInfo) {
		LOGGER.info("in make book");
		booking = bookingRepository.create(booking);
		LOGGER.info("book in table");
		URI createdURI = uriInfo.getBaseUriBuilder().path(booking.getId().toString()).build();
		LOGGER.info("find book and return");
		return Response.created(createdURI).build();
	}

	public Response deleteBooking(Integer id) {
		bookingRepository.destroy(id);
		return Response.noContent().build();
	}
	
	public Double bookingTotal(Integer id){
		
		List<Ticket> tickets = bookingRepository.find(id).getTicket();
		Double total = 0.0;
		for(Ticket ticket : tickets){
			total += ticket.getPrice();
		}
		
		return total;
	
	}
	
}
