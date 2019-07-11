package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Versioning
//Multiple types of versioning in different platform
//Media type versioning (GitHub)
//Custom headers versioning (Microsoft)
//URI versioning (Twitter)
//Parameter versioning (Amazon)

//Things to prevent:
//URI Pollution
//Misuse of HTTP Headers
//Caching
//Can we execute the request on the browser?
//API Documentation
//No Perfect Solution

@RestController
public class VersioningController {

	// URI versioning: put versions before the real API begins
	// http://localhost:8080/v1/person
	@GetMapping("/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}

	// URI versioning
	// http://localhost:8080/v2/person
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Custom param versioning
	// http://localhost:8080/person/param?version=1
	@GetMapping(value = "person/param", params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");
	}

	// Custom param versioning
	// http://localhost:8080/person/param?version=2
	@GetMapping(value = "person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Custom header versioning
	// http://localhost:8080/person/header
	// Headers -> X-API-VERSION : 1
	@GetMapping(value = "person/header", headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Bob Charlie");
	}

	// Custom header versioning
	// http://localhost:8080/person/header
	// Headers -> X-API-VERSION : 2
	@GetMapping(value = "person/header", headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Produces/mime type versioning
	// http://localhost:8080/person/produces
	// Headers -> Accept : application/vnd.company.app-v1+json
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Bob Charlie");
	}

	// Produces/mime type versioning
	// http://localhost:8080/person/produces
	// Headers -> Accept : application/vnd.company.app-v2+json
	@GetMapping(value = "person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
