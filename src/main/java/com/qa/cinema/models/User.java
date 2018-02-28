package com.qa.cinema.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	
//	`id` INT NOT NULL AUTO_INCREMENT,
//	  `email` VARCHAR(45) NOT NULL,
//	  `password` VARCHAR(45) NOT NULL,
//	  `name` VARCHAR(45) NOT NULL,
//	  PRIMARY KEY (`id`),
//	  UNIQUE INDEX `id_UNIQUE` (`id` ASC))

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@Size(min = 2, max = 45)
	private String email;
	

	@NotNull
	@Size(min = 2, max = 45)
	private String password;
	

	@NotNull
	@Size(min = 2, max = 45)
	private String name;

	public User(Integer id, String email, String password, String name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + "]";
	}

}
