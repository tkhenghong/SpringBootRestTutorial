package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

// Instructor said you need at least a general standardize exception class that handles general errors 
public class ExceptionResponse {
	// Every exception log needs:
	// 1. Time stamp
	// 2. Message
	// 3. Detail

	private Date timestamp;

	private String message;

	private String details;

	// Return the exception in this format
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
