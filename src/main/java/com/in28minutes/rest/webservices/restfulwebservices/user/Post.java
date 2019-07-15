package com.in28minutes.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Create Relationship in Spring JPA (with H2 Database)
@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer id;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY) // Tell Spring JPA to auto get User object/tell Spring it's mapped to User
										// object
	@JsonIgnore // Prevent outsiders to get info about the User from the Post object
	private User user; // Many Posts have One User (@ManyToOne)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + ", user=" + user + "]";
	}

}
