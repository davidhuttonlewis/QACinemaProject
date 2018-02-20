package com.qa.cinema.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Screen {
	
	@Id
	private Integer id;
	private String type;
	private Integer numberOfSeats;
	private Boolean accessiblity;
	
	@OneToMany(mappedBy = "screen")
	private List<Screening> screening;
	
	public Screen() {
		
	}

	public Screen(Integer id, String type, Integer numberOfSeats, Boolean accessiblity) {
		super();
		this.id = id;
		this.type = type;
		this.numberOfSeats = numberOfSeats;
		this.accessiblity = accessiblity;
	}

	public List<Screening> getScreening() {
		return screening;
	}

	public void setScreens(List<Screening> screening) {
		this.screening = screening;
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