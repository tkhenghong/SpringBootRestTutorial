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

public class PersonV2 {
	private Name name;

	public PersonV2() {
		super();
	}

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

}
