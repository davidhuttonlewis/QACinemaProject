package com.qa.cinema.models;

public class Booking {

	private Integer id;
	
	public Booking() {}

	public Booking(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + "]";
	}
	
}
