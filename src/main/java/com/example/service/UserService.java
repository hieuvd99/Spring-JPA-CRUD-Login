package com.example.service;

import java.util.List;

import com.example.model.User;

public interface UserService {
	List<User> findAll();
	User save(User user);
	User findByUsername(String username);
	Boolean existsByUsername(String username);
}
