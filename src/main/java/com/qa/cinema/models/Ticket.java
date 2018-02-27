package com.qa.cinema.models;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(scope = Ticket.class, generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Ticket {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TicketType type;
	
	@NotNull
	@Min(4)
	private Double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "showing_id")
	@NotNull
	private Showing showing;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "booking_id")
	@NotNull
	private Booking booking;

	public Ticket() {}

	public Ticket(TicketType type, Showing showing, Booking booking) {
		super();
		this.type = type;
		this.price = type.getPrice() + showing.getScreen().getType().getPrice();
		this.showing = showing;
		this.booking = booking;
		
		// 1 is sunday so 4 is wednesday!
		Calendar c = Calendar.getInstance();
		c.setTime(showing.getTime());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 4){
			this.price = this.price / 2;
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", type=" + type + ", price=" + price + "]";
	}

	public Showing getShowing() {
		return showing;
	}

	public void setShowing(Showing showing) {
		this.showing = showing;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}
