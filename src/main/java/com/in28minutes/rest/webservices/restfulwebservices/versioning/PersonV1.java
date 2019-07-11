package com.in28minutes.rest.webservices.restfulwebservices.versioning;

// Versioning
// Multiple types of versioning in different platform
// Media type versioning (GitHub)
// Custom headers versioning (Microsoft)
// URI versioning (Twitter)
// Parameter versioning (Amazon)

// Things to prevent:
// URI Pollution
// Misuse of HTTP Headers
// Caching
// Can we execute the request on the browser?
// API Documentation
// No Perfect Solution

public class PersonV1 {
	private String name;

	public PersonV1() {
		super();
	}

	public PersonV1(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
