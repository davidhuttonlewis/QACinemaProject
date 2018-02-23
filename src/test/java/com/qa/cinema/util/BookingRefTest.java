package com.qa.cinema.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookingRefTest {

	@Test
	public void testToBookingRef() {
		BookingRefCreator create = new BookingRefCreator();
		
		assertEquals("000001", create.toBookingRef(1));
		assertEquals("577AF5", create.toBookingRef(5733109));
		assertEquals("345678", create.toBookingRef(305419896));
	}

}
