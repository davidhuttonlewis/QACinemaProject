package com.qa.cinema.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(scope = Booking.class, generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Booking {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 6, max = 6)
	private String bookingRef;
	
	@OneToMany(mappedBy = "booking", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch=FetchType.EAGER)
	private List<Ticket> ticket;
	

	public Booking() {}

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
