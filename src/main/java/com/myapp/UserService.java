package com.myapp;

import java.util.List;
import java.util.ArrayList;

public class UserService {

	private static List<User> users = new ArrayList<User>();

	public List<User> findAll() {
		return users;
	}

	public User findById(Long id) {
		if (id == null) {
			return null;
		}

		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}

		return null;
	}

	public User update(Long id, User newUser) {
		User oldUser = findById(id);

		if (oldUser == null) {
			return null;
		}

		return oldUser.updateWith(newUser);
	}

	public User create(User newUser) {
		newUser.setId(users.size() + 1l);

		users.add(newUser);

		return newUser;
	}

	public boolean delete(Long id) {
		return users.remove(findById(id));
	}
}
