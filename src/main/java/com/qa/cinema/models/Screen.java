package com.qa.cinema.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
//@Table(name = "screen")
public class Screen {
	
	@Id
	private Integer id;
	private ScreenType type;
	private Integer numberOfSeats;
	private Boolean accessiblity;
	

	@OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
	private List<Showing> showings;
	
	public Screen() {
		
	}

	public Screen(Integer id, ScreenType type, Integer numberOfSeats, Boolean accessiblity) {
		super();
		this.id = id;
		this.type = type;
		this.numberOfSeats = numberOfSeats;
		this.accessiblity = accessiblity;
	}

	public List<Showing> getShowings() {
		return showings;
	}

	public void setScreens(List<Showing> showings) {
		this.showings = showings;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ScreenType getType() {
		return type;
	}

	public void setType(ScreenType type) {
		this.type = type;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Boolean getAccessiblity() {
		return accessiblity;
	}

	public void setAccessiblity(Boolean accessiblity) {
		this.accessiblity = accessiblity;
	}

	@Override
	public String toString() {
		return "Screen [id=" + id + ", type=" + type + ", numberOfSeats=" + numberOfSeats + ", accessiblity="
				+ accessiblity + "]";
	}
	
}