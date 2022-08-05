package com.rest.restfulwebservices.helloworld;


//Note: from what I understand getter method is required by Jackson to convert object to
//		json. To see what happens I tried making the message variable public, doing that
//		circumvented the need of creating the getter method. Also the json key is named
//		using the getter method (inferred via testing). Example: For getter method 
//		"getMess", key name will be "mess" regardless of data member name.

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message = " + message + "]";
	}
}
