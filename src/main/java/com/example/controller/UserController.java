package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class UserController {

	@Autowired 
	private UserService userService;
	
	@RequestMapping("/")
	public String home(Model model) {
		String username = "admin";
		String password = "admin";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(password);
	    
	    if(!userService.existsByUsername(username))  {
		    
		    User user = new User();
		    user.setUsername(username);
		    user.setPassword(encodedPassword);
		    user.setEmail("admin@gmail.com");
		    userService.save(user);
	    }
		return "home";
	}
	
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String listUser(Model model){
        List<User> user = userService.findAll();
        model.addAttribute("user", user);
        return "adminListUser";
    }
	
	@RequestMapping("/admin/add")
	public String add(Model model) {
	    User user = new User();
	    model.addAttribute("user", user);
	     
	    return "adminAddUser";
	}
	
	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    userService.save(user);
	     
	    return "redirect:/admin/list";
	}
}
