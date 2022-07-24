package com.gunvant.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@Data
@Entity
@Table(name="USER_ACCOUNT")
public class UserAccountEntity {
	@Id
	@GeneratedValue
	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private Long  phno;
	private LocalDate dob;
	private String gender;
	private String country;
	private String state;
	private String city;
	private String password;
	private String status;
	
	@Column(name="CREATED_DATE" , updatable=false)
	@CreationTimestamp
	private LocalDate createdDate;
	@Column(name="UPDATED_DATE" , insertable=false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	
}
