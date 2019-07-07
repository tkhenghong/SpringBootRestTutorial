package com.in28minutes.rest.webservices.restfulwebservices.user;

// import ControllerLinkBuilder here
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// DB(Model) -> DAO -> Resource (Lowest to Highest)

@RestController
public class UserResource { // *UserResource may be called as UserController in other developer's term (WE
							// ARE USING IT)
	// If this class called UserResource, other classes will call XXXResouce, be
	// consistent.

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
	public Resource<User> retreiveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}

		// HATEOS: It will give additional links to the browser so you can do some
		// action on it (Not understand)
		// Not commonly used in current applications because the devs haven't found very
		// good reason to use them
		// But very good practice so end users can simply use those links given by
		// server
		// "all-users", SERVER_PATH + "/users"
		// We will add a link to this method (retreiveAllUsers)
		Resource<User> resource = new Resource<User>(user);
		// Create a link from another method in this class
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retreiveAllUsers()); // From import static
		// Attach this link to the server response // ControllerLinkBuilder.*
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	// Delete a user
	// Test DELETE http://localhost:8080/users/1000
	// DELETE http://localhost:8080/users/1
	// GET http://localhost:8080/users/
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		// If success, it will return status 200, if not return UserNotFoundException
	}

	// Remember, in Postman set POST, write the correct URL, send the body like this
	// and set it to JSON format (no need id property):
	// {
	// "name": "Nick"
	// "birthDate": "6346-07-01T15:04:10.474+0000"
	// }
	// Create a user
	// Input - Details of the user
	// Output - return created user and return create URI
	// This is a good example to follow HTTP best practices when returning something
	// Remember to add validations
	// Validation libraries that are commonly used in Spring:
	// 1. Hibernate validator
	// 2. Validator API
	@PostMapping("/users")
	// RequestBody is the data when we sent using Postman
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
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

//	// Retrieve all post for a User - GET /users/{id}/posts
//	@GetMapping("/users/{id}/posts")
//	public User getUserPosts(@PathVariable int id) {
//		User user = service.findOne(id);
//		if (user == null) {
//			throw new UserNotFoundException("id-" + id);
//		}
//		return user;
//	}

//	// Create a post for a User - POST /users/{id}/posts
//	@PostMapping("/users/{id}/posts")
//	public void createUserPost(@PathVariable String id, @ResponseBody Post post) {
//
//	}
//
//	// Retrieve details of a post - GET /users/{id}/posts/{post_id}
//	public void getPostDetails(@PathVariable int id, @PathVariable String post_id) {
//
//	}

}
