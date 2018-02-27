package com.qa.cinema.models;

public enum ScreenType {
	THREE_D(1),
	TWO_D(0),
	GOLD(15),
	IMAX(3);
	
	private final double price;

	private ScreenType(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
}
