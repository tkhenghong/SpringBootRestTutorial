package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// JPA Step 5:  Restart the server, you will found a line that says: 
// create table user (id integer not null, birth_date timestamp, name varchar(255), primary key (id))

// Create an object to be the model of the database
// Step 1: Create the Bean
// Remember to auto generate all these methods using Alt + Shift + S**
// Step 2: Create a DAO of the User. DAO is something that is created from the bean 
// to talk to the database and get the details back.
// Write @ApiModel to tell the devs about the validation about the User model object (Swagger)
@ApiModel(description = "All details about the user.")
// Spring JPA (Like Hibernate, basically it's ORM for Java)
// It can help you create a database based on your object model classes (Like this class below)
// JPA Step 1: Create Entity 
@Entity
public class User {
// Remember to add validation of the DB Model itself

	// Validation libraries that are commonly used in Spring:
	// 1. Hibernate validator
	// 2. Validator API
	// JPA Step 2: Define id with @Id & @GeneratedValue <-- Auto creates an Id for
	// you
	@Id
	@GeneratedValue
	private Integer id;

	// You can mention the limitation WITH the message when the data given is wrong
	// like below
	@Size(min = 2, message = "Name should have at least 2 characters.")
	// ApiModelProperty tells the dev about some notes about this property
	@ApiModelProperty(notes = "Name should have at least 2 characters.")
	private String name;

	@Past // This means the date must be in the past, birthday can't possibly be in future
	@ApiModelProperty(notes = "Birth date should be in the past.")
	private Date birthDate;

	// Latest Spring doesn't show 500 Internal Server error, older version will show
	// error.
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
