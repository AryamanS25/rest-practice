package com.rest.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.restfulwebservices.charity.Charity;
import com.rest.restfulwebservices.charity.CharityRepository;
import com.rest.restfulwebservices.exception.UserNotFoundException;
import com.rest.restfulwebservices.post.Post;
import com.rest.restfulwebservices.post.PostRepository;
import com.rest.restfulwebservices.user.User;
import com.rest.restfulwebservices.user.UserRepository;

@RestController
public class CharityController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CharityRepository charityRepository;
	
	
	@GetMapping("/jpa/charities")
	public List<Charity> retrieveAllCharity() {
		return charityRepository.findAll();
	}
	
	@PostMapping("/jpa/charities")
	public ResponseEntity<Object> createUser(@RequestBody Charity charity) {
		Charity newCharity = charityRepository.save(charity);
		
		// In order to adhere to the HTTP standards we need to respond back 
		// with the status of CREATED, so a status of 201 instead of 200.
		// Also client doesn't know the ID of the newly created charity.
		
		URI uri = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(newCharity.getId()).toUri();

		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping("/jpa/charities/{id}")
	public Optional<Charity> retrieveOneUser(@PathVariable Integer id) {
		Optional<Charity> charity = charityRepository.findById(id);
		if(charity.isEmpty()) throw new UserNotFoundException("id: "+id);
		return charity;
	}
	
	@DeleteMapping("/jpa/charities/{id}")
	public void deleteCharity(@PathVariable Integer id) {
		charityRepository.deleteById(id);
	}
	
	//post handler methods:
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllPostsForUsers(@PathVariable Integer id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(userOptional.isEmpty()) throw new UserNotFoundException("id: "+id);
		User user = userOptional.get();
		return user.getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable Integer id, 
			@RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(userOptional.isEmpty()) throw new UserNotFoundException("id: "+id);
		
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);
		
		// In order to adhere to the HTTP standards we need to respond back 
		// with the status of CREATED, so a status of 201 instead of 200.
		// Also user doesn't know the ID of the newly created user.
		
		URI uri = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{post_id}")
		.buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(uri).build();
		
	}
}
