package com.rest.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.restfulwebservices.exception.UserNotFoundException;
import com.rest.restfulwebservices.user.User;
import com.rest.restfulwebservices.user.UserDaoService;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService daoService;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return daoService.findAll();
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User newUser = daoService.save(user);
		
		// In order to adhere to the HTTP standards we need to respond back 
		// with the status of CREATED, so a status of 201 instead of 200.
		// Also client doesn't know the ID of the newly created user.
		
		URI uri = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(newUser.getId()).toUri();

		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping("/users/{id}")
	public User retrieveOneUser(@PathVariable Integer id) {
		User user = daoService.findOne(id);
		
		//Without this if statement, which checks if user was found or not, we would
		//simply get a blank page, no error, no indication of what happened. It is good 
		//practice to display some kind of message on the screen for the users sake.
		if(user==null) throw new UserNotFoundException("id: "+id);
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User user = daoService.deleteById(id);
		if(user==null) throw new UserNotFoundException("id: "+id);
	}
}