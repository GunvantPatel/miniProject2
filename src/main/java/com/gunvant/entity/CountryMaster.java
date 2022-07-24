package com.gunvant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="COUNTRY_MASTER")
public class CountryMaster {
	
	@Id
	@GeneratedValue
	private Integer countryId;
	private String countryName;
}
