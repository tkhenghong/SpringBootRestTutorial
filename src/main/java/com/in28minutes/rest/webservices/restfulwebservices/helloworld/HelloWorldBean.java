package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

// Step 3: Create a bean (An plain object)
public class HelloWorldBean {
	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
	}

	// If you're returning this object from REST controller, you must have getter
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
