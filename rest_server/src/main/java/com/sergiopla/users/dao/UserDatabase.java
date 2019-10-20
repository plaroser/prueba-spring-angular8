package com.sergiopla.users.dao;

import java.util.LinkedList;
import java.util.List;

import com.sergiopla.users.dto.User;

public class UserDatabase {
	private LinkedList<User> dbUsers;

	public UserDatabase() {
		super();
		this.dbUsers = new LinkedList<>();
	}

	private User getUserByIndex(int index) {
		return this.dbUsers.get(index);
	}

	private int getUserIndex(User user) {
		return dbUsers.indexOf(user);
	}

	private int getUsernameIndex(String username) throws Exception {
		for (User user : this.dbUsers) {
			if (user.getUsername().equals(username))
				return getUserIndex(user);
		}
		return -1;
	}

	public List<User> findAll() {
		return this.dbUsers;
	}

	public User findByUsername(String username) throws Exception {
		for (User user : this.dbUsers) {
			if (user.getUsername().equals(username))
				return user;
		}
		return null;
	}

	public User save(User user) {
		int userIndex = dbUsers.lastIndexOf(user);
		System.out.println("save() - index encontrado: " + userIndex);
		if (userIndex < 0) {
			this.dbUsers.addLast(user);
			userIndex = dbUsers.lastIndexOf(user);
		} else {
			this.dbUsers.set(userIndex, user);
		}

		return dbUsers.get(userIndex);
	}

	public User deleteByUsername(String username) throws Exception {
		User userRemoved = findByUsername(username);
		System.out.println("deleteByUsername " + userRemoved);
		if (userRemoved != null) {
			if (this.dbUsers.remove(userRemoved))
				return userRemoved;
		}
		return null;
	}

}
