package com.gunvant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gunvant.entity.StateMaster;

public interface StateRepository extends JpaRepository<StateMaster,Integer>{

	List<StateMaster> findByCountryId(Integer countryId);

	

}
