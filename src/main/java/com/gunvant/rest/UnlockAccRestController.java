package com.gunvant.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gunvant.binding.UnlockForm;
import com.gunvant.service.UserMgmtService;

@RestController
public class UnlockAccRestController {

	@Autowired
	private UserMgmtService service;
	@PostMapping("/unlock")
	public String unlockAcc(@RequestBody UnlockForm unlock) {
		return service.accUnlock(unlock);
		
	}
}
