package com.gunvant.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gunvant.binding.UserForm;
import com.gunvant.service.UserMgmtService;

@RestController
public class ResgisterRestController {

	@Autowired
	private UserMgmtService service;
	
	@GetMapping("/email/{email}")
	public String emailCheck(@PathVariable("email") String email) {
		
		return service.emailCheck(email);
	}
	
	@GetMapping("/country")
	public Map<Integer, String > getCountry (){
		
		return service.getCountry();
	}
	
	@GetMapping("/state/{countryId}")
	public Map<Integer, String > getState(@PathVariable("countryId") Integer countryId){
		
		return service.getState(countryId);
	}
	
	@GetMapping("/city/{stateId}")
	public Map<Integer, String > getCity(@PathVariable("stateId") Integer stateId){
		
		return service.getState(stateId);
	}
	
	@PostMapping("/register")
	public String userRegister(@RequestBody UserForm userForm ) {
		return service.signUp(userForm);
		
	}
}
