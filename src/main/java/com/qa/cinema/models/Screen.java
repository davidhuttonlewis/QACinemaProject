package com.qa.cinema.models;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Screen {
	
	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	@NotNull
	private ScreenType type;

	@Min(10)
	@Max(100)
	@NotNull
	private Integer numberOfSeats;
	@NotNull
	private Boolean accessibility;
	
	@OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Showing> showings;
	
	public Screen() {
		
	}

	public Screen(Integer id, ScreenType type, Integer numberOfSeats, Boolean accessiblity) {
		super();
		this.id = id;
		this.type = type;
		this.numberOfSeats = numberOfSeats;
		this.accessibility = accessiblity;
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
		return accessibility;
	}

	public void setAccessiblity(Boolean accessiblity) {
		this.accessibility = accessiblity;
	}

	@Override
	public String toString() {
		return "Screen [id=" + id + ", type=" + type + ", numberOfSeats=" + numberOfSeats + ", accessiblity="
				+ accessibility + "]";
	}
	
}