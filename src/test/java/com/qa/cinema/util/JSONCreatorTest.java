package com.qa.cinema.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

import com.qa.cinema.models.Booking;
import com.qa.cinema.models.Screen;
import com.qa.cinema.models.ScreenType;
import com.qa.cinema.models.Showing;
import com.qa.cinema.models.Ticket;
import com.qa.cinema.models.TicketType;

public class JSONCreatorTest {

	@Test
	@Ignore
	public void testJSON() {
		JSONCreator creator = new JSONCreator();
		final String JSON_EXPECTED = "{\"id\":1,\"type\":\"STANDARD\",\"price\":7.0,\"showing\":{\"id\":1,\"time\":null,\"film\":\"Film\",\"screen\":{\"id\":1,\"type\":\"TWO_D\",\"numberOfSeats\":12,\"accessibility\":true,\"showings\":[1]}},\"booking\":{\"id\":1,\"bookingRef\":\"000000\",\"ticket\":[1]}}";

		
		Screen scr = new Screen(ScreenType.TWO_D, 12, true);
		
		scr.setId(1);
		
		Showing show = new Showing(null, "Film", scr);
		show.setId(1);
		
		scr.setScreens(Arrays.asList(show));
		
		Booking booking = new Booking();
		booking.setId(1);
		booking.setBookingRef("000000");
		
		Ticket ticket = new Ticket(TicketType.STANDARD, show, booking);
		ticket.setId(1);
		
		booking.setTicket(Arrays.asList(ticket));
		
		String json = creator.toJSON(ticket);
		assertEquals(JSON_EXPECTED, json);
		
		Ticket deserialized = creator.fromJSON(json, Ticket.class);
		assertNotNull("Reading from JSON should not be null", deserialized);
		
	}

}
