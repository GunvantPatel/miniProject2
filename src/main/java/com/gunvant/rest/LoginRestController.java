package com.gunvant.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gunvant.binding.LoginForm;
import com.gunvant.service.UserMgmtService;

@RestController
public class LoginRestController {
		@Autowired
	private UserMgmtService service;
	
		
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		return service.signIn(loginForm);
		
	}
}
