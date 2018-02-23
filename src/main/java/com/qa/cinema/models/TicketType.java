package com.qa.cinema.models;

public enum TicketType {
	STANDARD(10), CHILD(11), OAP(69), STUDENT(250);

	private final double price;

	private TicketType(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

}
