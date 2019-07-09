package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

// Customized exception steps: (Sample)
// 1. UserNotFoundException class
// 2. ExceptionResponse class
// 3. CustomizedResponseEntityExceptionHandler class

// Step 3: Define your exception handler
// This is the one that handles all general errors of your backend
// You always can go to ResponseEntityExceptionHandler class to see what kind of exception that you can override so you can write your own error response
@ControllerAdvice // Making this applicable to other controllers/resources classes, so they use
					// this for error returning
@RestController // This handler becomes a REST controller because it will response something
				// back to the user.
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		// When exception happens, create a new instance of our specific bean
		// Don't expose anything that security sensitive
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		// Question: ResponseEntity is a raw type. References to generic type ResponseEntity<T> should be parameterized
		// Answer: https://stackoverflow.com/questions/20451096/why-am-i-getting-the-warning-class-is-a-raw-type-references-to-generic-type-cl
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request)
			throws Exception {
		// When exception happens, create a new instance of our specific bean
		// Don't expose anything that security sensitive
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		// ex.getMessage() will return a bunch of program error to the user, but
		// sometimes user don't have to see this error mess.
		// Return something that is simpler like the next method
		// handleMethodArgumentNotValid()

		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);

	}

	// You can continue to customize the return message so it can get as accurate as
	// possible
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed",
				ex.getBindingResult().toString());
		// ex.getBindingResult().toString() will give the details of what has went wrong
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
