package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;

// Create an object to be the model of the database
// Step 1: Create the Bean
// Remember to auto generate all these methods using Alt + Shift + S**
// Step 2: Create a DAO of the User. DAO is something that is created from the bean 
// to talk to the database and get the details back.
public class User {

	private Integer id;
	private String name;
	private Date birthDate;

	// Latest Spring doesn't show 500 Internal Server error, older version will show
	// if you don't mention this empty constructor
	protected User() {
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
