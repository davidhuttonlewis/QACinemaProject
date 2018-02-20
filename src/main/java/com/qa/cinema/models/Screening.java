package com.qa.cinema.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Screening {
	
	@Id
	private Integer id;
	
	private Date time;
	private String film;
	
	@ManyToOne
	private Screen screen;
	
	public Screening() {}
	
	public Screening(Integer id, Date time, String film) {
		super();
		this.id = id;
		this.time = time;
		this.film = film;
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
