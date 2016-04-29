package com.myapp;

public class User {

	private Long id;

	private String name;
	private String lastName;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public User updateWith(User user) {
		this.name = user.name;
		this.lastName = user.lastName;

		return this;
	}

}
