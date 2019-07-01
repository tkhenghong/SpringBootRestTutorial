package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
	@GetMapping("/users/{id}")
	public User retreiveUser(@PathVariable int id) {
		return service.findOne(id);
	}
}
