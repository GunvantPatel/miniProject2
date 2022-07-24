package com.gunvant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data 
@Entity
@Table(name="CITY_MASTER")
public class CityMaster {
	@Id
	@GeneratedValue
	private Integer cityId;
	private String cityName;
	private Integer stateId;

}
