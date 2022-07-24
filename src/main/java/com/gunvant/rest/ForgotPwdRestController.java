package com.gunvant.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gunvant.service.UserMgmtService;

@RestController
public class ForgotPwdRestController {

	@Autowired
	private UserMgmtService service;
	
	@GetMapping("/forgot/{email}")
	public String forgotpwd(@PathVariable("email") String email) {
		
		return service.forgotPwd(email);
	}
}
