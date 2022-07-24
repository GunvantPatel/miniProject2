package com.gunvant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gunvant.entity.CityMaster;

public interface CityRepository extends JpaRepository<CityMaster, Integer> {

	

	

	List<CityMaster> findByStateId(Integer stateId);

}
