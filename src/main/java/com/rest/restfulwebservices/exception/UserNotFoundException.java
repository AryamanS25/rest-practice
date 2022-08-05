package com.rest.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//This annotation is important as without it error code would be 500. We can and should
//be more specific, the error in particular is 'not found' with error code 404, using
//annotation @ResponseStatus and appropriate parameters we can specify that.
//We extend RuntimeExcpetion and not Exception because RuntimeException and all its 
//subclass are 'unchecked'

//@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
	}
}
