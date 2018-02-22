package com.qa.cinema.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Booking {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 6, max = 6)
	private String bookingRef;
	
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Ticket> ticket;
	

	public Booking() {}

	public Booking(Integer id, String bookingref) {
		super();
		this.id = id;
		this.bookingRef = bookingref;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingRef=" + bookingRef + "]";
	}

	public String getBookingRef() {
		return bookingRef;
	}

	public void setBookingRef(String bookingRef) {
		this.bookingRef = bookingRef;
	}
	
	public List<Ticket> getTicket() {
		return ticket;
	}
	
	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}
}
