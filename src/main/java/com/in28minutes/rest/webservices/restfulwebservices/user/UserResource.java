package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// DB(Model) -> DAO -> Resource (Lowest to Highest)

@RestController
public class UserResource {

	// We created a UserDaoService in another place with @Component, now we can call
	// the component here and tell Spring to
	// instantiate the component using @Autowired without us having to do
	// UserDaoService service = new UserDaoService();
	@Autowired
	private UserDaoService service;

	// GET /users
	// retreiveAllUsers
	@GetMapping("/users")
	public List<User> retreiveAllUsers() {
		return service.findAll();
	}

	// GET/users/{id}
	// Good example practice of handling when user is not found
	@GetMapping("/users/{id}")
	public User retreiveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		return user;
	}

	// Remember, in Postman set POST, write the correct URL, send the body like this
	// and set it to JSON format (no need id property):
	// {
	// "name": "Nick",
	// "birthDate": "6346-07-01T15:04:10.474+0000"
	// }
	// Create a user
	// Input - Details of the user
	// Output - return created user and return create URI
	// This is a good example to follow HTTP best practices when returning something
	@PostMapping("/users")
	// RequestBody is the data when we sent using Postman
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = service.save(user);

		// How to response the user's request formally? Giving the user back the current
		// state of the DB resource
		// The below line means Using ServletUriComponentsBuilder to get the above
		// URI(/users/{id}),
		// with "path" of /{id}
		// Created users
		// /user/{id} savedUser.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		// Using ResponseEntity object provided by Spring
		return ResponseEntity.created(location).build();
		// The server returns 201 Created status code, with a Location of
		// http://localhost:8080/users/4 <-- new ID of the user
	}

}
