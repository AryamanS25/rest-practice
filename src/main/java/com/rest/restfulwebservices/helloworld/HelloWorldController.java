package com.rest.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//We tell spring that this is a Controller, i.e it handles HTTP requests
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	// Define what method? GET,POST,CREATE,DELETE
	// Then Define URI or Resources or End-points (doubt: are they the same thing?)
	
//	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@GetMapping("/hello-world-message")
	public HelloWorldBean helloMessage() {
		return new HelloWorldBean("Hi guys!");
	}
	
	@GetMapping("/hello-world/{name}")
	public HelloWorldBean helloPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello, %s", name));
	}
	
	
	// To internationalize our method we can accept locale from the request header using 
	// @RequestHeader annotation. We can make use of MessageSource interface to avoid
	// having to hard code our logic, by making message_{location identifier}.properties
	// files and getting the message we want by passing in the 'code' as argument in 
	// .getMessage method.
	
	@GetMapping("/hello-world-I18n")
	public String helloWorldI18n(
			@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource
				.getMessage("hello.world.message", null, locale);
	}
	
	// One problem with the above implementation is that for every method we want to
	// internationalize, we would need to accept Locale as a parameter. Adding a parameter
	// everywhere can become cumbersome and tedious. Solution: we can make use of the
	// LocaleContextHolder class.
	
	@GetMapping("/hello-world-I18n-opt")
	public String helloWorldI18nOpt() {
		return messageSource
				.getMessage("hello.world.message", null, LocaleContextHolder.getLocale());
	}
}
