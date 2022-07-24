package com.gunvant.binding;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.gunvant.entity.CityMaster;
import com.gunvant.entity.CountryMaster;
import com.gunvant.entity.StateMaster;

import lombok.Data;

@Data
public class UserForm {


	private String firstName;
	private String lastName;
	private String email;
	private Long  phno;
	
	private LocalDate dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	
	
}
