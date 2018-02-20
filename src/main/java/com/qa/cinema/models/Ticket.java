package com.qa.cinema.models;

public class Ticket {
	
	private Integer id;
	private String type;
	private Double price;
	
	public Ticket() {}

	public Ticket(Integer id, String type, Double price) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
	
}
