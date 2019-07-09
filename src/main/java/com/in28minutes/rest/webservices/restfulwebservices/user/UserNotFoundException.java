package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Example of returning HTTP NOT FOUND status
// DON'T extends normalException
// You don't want to return 500 Internal Error. You need to add the below response status to indicate it is not found
// Step 1: Define your Exception class \
// Warning about class not serialized? See here: https://stackoverflow.com/questions/7683739/why-my-exception-class-needs-to-be-serialized
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

// Advice: you have to standardize the way of returning error response. Don't let all API return exceptions differently.
// Make sure you are returning the correct HTTP error codes back to the users, and must be consistent at all times.
