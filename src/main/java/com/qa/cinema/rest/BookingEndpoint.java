package com.qa.cinema.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.inject.Inject;
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
import com.qa.cinema.services.BookingService;

@Path("/booking")
public class BookingEndpoint {

	@Inject
	private BookingService bookingService;

	@GET
	@Path("/{id : \\d+}")
	@Produces(APPLICATION_JSON)
	public Response getBooking(@PathParam("id") @Min(1) Integer id) {
		return bookingService.getBooking(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBookings() {
		return bookingService.getAllBookings();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBooking(Booking booking, @Context UriInfo uriInfo) {
		return bookingService.createBooking(booking, uriInfo);
	}

	@DELETE
	@Path("/{id : \\d+}")
	public Response deleteBooking(@PathParam("id") @Min(1) Integer id) {
		return bookingService.deleteBooking(id);
	}

}
