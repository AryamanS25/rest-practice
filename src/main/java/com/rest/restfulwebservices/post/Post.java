package com.rest.restfulwebservices.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.restfulwebservices.user.User;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	
	
	//We will have to ignore 'user' data member while getting Post data because that will 
	//lead to recursion as User has a Post as a data member as well. We use @JsonIgnore.
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user; 
	
	//note: earlier we had to create a default constructor (if overwritten) for post request, 
	//		but it isn't required since update.
	
	public Post() {
		//JPA expects a no argument constructor in our entity/class
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
	
	
}
