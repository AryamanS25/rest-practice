package com.rest.restfulwebservices.charity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.rest.restfulwebservices.user.User;

@Entity
public class Charity {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String description;
	private BigDecimal donationPool;
	private List<User> supporters;
	
	public Charity(Integer id, String name, String description, BigDecimal donationPool) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.donationPool = donationPool;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPool() {
		return donationPool;
	}
	public void setPool(BigDecimal pool) {
		this.donationPool = pool;
	}
	public List<User> getPosts() {
		return supporters;
	}
	public void setPosts(List<User> posts) {
		this.supporters = posts;
	}
	
	@Override
	public String toString() {
		return "Charity [id=" + id + ", name=" + name + ", description=" + description + ", donationPool="
				+ donationPool + "]";
	}
	
}
