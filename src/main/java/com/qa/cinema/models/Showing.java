package com.qa.cinema.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Showing {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	private Date time;
	@NotNull
	@Size(min = 2, max = 50)
	private String film;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "screening_id")
	@NotNull
	private Screen screen;
	
	@OneToMany(mappedBy = "showing", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Ticket> ticket;
	
	public Showing() {}
	
	public Showing(Integer id, Date time, String film, Screen screen) {
		super();
		this.id = id;
		this.time = time;
		this.film = film;
		this.screen = screen;
	}
	
	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}
	

	@Override
	public String toString() {
		return "Screening [id=" + id + ", time=" + time + ", film=" + film + "]";
	}
	
}
