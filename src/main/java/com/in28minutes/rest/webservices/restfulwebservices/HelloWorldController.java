package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Step 4: Run.
import com.in28minutes.rest.webservices.restfulwebservices.helloworld.HelloWorldBean;

// A lot of magic has happened. (Because of Spring Boot auto configuration)
// Who made the object that you return to become JSON string? Jackson2ObjectMapperBuilderCustomerConfiguration
// Who made and return those error code to the end user?
// So, you'll need dispatcher servlet.
// What is dispatcher servlet?
// Who configuring the dispatcher servlet?
// What does dispatcher servlet do?
// How does the HelloWorldBean object get converted to JSON? HttpMessageConvertersAutoConfiguration with Jackson2ObjectMapperBuilderCustomerConfiguration
// How's configuring the error mapping? Spring Boot auto configuration

// Spring Boot auto configuration includes:
// Dispatcher Servlet
// HttpMessage ConverterAutoConfiguration
// spring.boot.starter.web that has dependency on spring.web.MVC framework
// ErrorMvcAutoConfiguration = Configuration on making your error page
// ErrorMvcAutoConfiguration#basicErrorController and ErrorMvcAutoConfiguration#errorAttributes 
// ErrorMvcAutoConfiguration.WhitelabelErrorViewConfiguration is the one that shows your error page on the web

// Every time your enter your URL into the browser and sent to the server, 
// DispatcherServlet will be the one that read that URL and decide which controller and method to run.
// DispatcherServlet is the front controller for the Spring Boot Web MVC framework
// So it knows that "this" URL is mapped into which method in the Controller classes. (If you look at your log properly) 

// @RestController is an annotation that contains a lot of other annotations, including @Controller

//Step 1 : Tell Spring that this is a RestController to handle REST request
@RestController
public class HelloWorldController {

	// Internationalization
	// Step 3: Bring messageSource
	@Autowired
	private MessageSource messageSource;

	// @Step 2: Create a method for processing the requests
	// Use URI to mark the method so outside can access to different method using
	// different URI
	// Use Get to get user data
	// A method that returns Hello World
	// Use GET to get the value
	// URI set as /hello-world
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	// You can straight call GetMapping for @RequestMapping with GET
	@GetMapping(path = "/hello-world2")
	public String helloWorld2() {
		return "Hello World2";
	}

	// Internationalization
	// Step 4: Create an API to accept request.
	// You need Locale object and annotate it as RequestHeader
	// At this point, the messageSource object is configured to those files
	// Then use that locale to return the message based on request's preferences
	// Inside RequestHeader, name set to Accept-Language to tell SPRING to find that
	// property,
	// but required = false means if you can't find it, no problem
	// Test in Postman
	// http://localhost:8080/hello-world-internationalized
	// Go to Headers and add a field Accept-Language, with value en or fr or nl
	// Add Content-Type field with value, application/json (So Postman won't say
	// unexpected response)
	// You'll get different return response (Success)
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, locale);
	}

	// The above method is too cumbersome, you have to add RequestHeader... into
	// that thing,
	// So, there's an alternative method like below.
	// You don't have to mention Content-Type field too in Postman!
	@GetMapping(path = "/hello-world-internationalized2")
	public String helloWorldInternationalized2() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

	// Create a method that returns a bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	// Create a method that accepts a parameter
	// When you write like this: /hello-world/path-variable/in28minutes,
	// It will return: {"message":"Hello World, in28minutes"}
	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

	// There's a class that writes toString() effortlessly,
	// it doesn't need hard coding (View it's implementation)
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
