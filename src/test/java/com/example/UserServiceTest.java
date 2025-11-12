package com.example;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional
class UserServiceTest {

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private UserRepository userRepository;

  @Test
  void testFindByUsername() {
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("abc123");
    user.setEmail("test@gmail.com");
    userRepository.save(user);

    User result = userService.findByUsername("testUser");
    assertEquals("testUser", result.getUsername());
  }
}
