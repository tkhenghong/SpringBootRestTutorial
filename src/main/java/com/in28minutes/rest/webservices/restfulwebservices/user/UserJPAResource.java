package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// JPA Step 6: Create a Controller class for JPA
// We will convert from UserResouce.java file to this file with using JPA method
@RestController
@RequestMapping("/jpa")
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDaoService service;

	// http://localhost:8080/jpa/users
	@GetMapping("/users")
	public List<User> retreiveAllUsers() {
		return userRepository.findAll(); // findAll() doesn't require you to explicitly mentioned in the UserRepository
											// class
	}

	// http://localhost:8080/jpa/users/1
	@GetMapping("/users/{id}")
	public Resource<User> retreiveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id); // It will return Optional Object, to prevent User object
															// null and cause JavaNullExceptions
		// Check is there an user object in Optional object
		// If the User object is NOT present
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id); // throws exception
		}

		Resource<User> resource = new Resource<User>(user.get()); // Get the real User object in Optional object
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retreiveAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}

	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user); // save() doesn't require you to explicitly mentioned in the UserRepository
												// class

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
