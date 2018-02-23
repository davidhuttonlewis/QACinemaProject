package com.qa.cinema.util;

public class BookingRefCreator {
	public String toBookingRef(int number) {
		String hex = Integer.toHexString(number).toUpperCase();
		if (hex.length() > 6) hex = hex.substring(hex.length() - 6);
		if (hex.length() < 6) hex = String.format("%1$6s", hex).replace(' ', '0');
		return hex;
	}
}
