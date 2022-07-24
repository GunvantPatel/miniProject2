package com.gunvant.service;

import java.util.Map;

import com.gunvant.binding.LoginForm;
import com.gunvant.binding.UnlockForm;
import com.gunvant.binding.UserForm;

public interface UserMgmtService {
	
	
	public String signIn(LoginForm login);
	public String forgotPwd(String email);
	public String accUnlock(UnlockForm unlock);
	
	public String emailCheck(String email);
	public Map<Integer,String> getCountry();
	public Map<Integer,String> getState(Integer countryId);
	public Map<Integer,String> getCity(Integer stateId);
	public String signUp(UserForm user);
	
}
