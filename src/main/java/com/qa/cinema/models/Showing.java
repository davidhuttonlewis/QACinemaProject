package com.qa.cinema.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Showing {
	
	@Id
	@GeneratedValue()
	private Integer id;
	
	private Date time;
	private String film;
	
	private Screen screen;
	
	public Showing() {}
	
	public Showing(Integer id, Date time, String film, Screen screen) {
		super();
		this.id = id;
		this.time = time;
		this.film = film;
		this.screen = screen;
	}
	
	@ManyToOne
	@JoinColumn(name = "screening_id")
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
