package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findAll();
	User findByUsername(String username);
	Boolean existsByUsername(String username);
}
