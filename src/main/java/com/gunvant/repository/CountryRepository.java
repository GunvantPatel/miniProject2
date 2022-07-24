package com.gunvant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gunvant.entity.CountryMaster;

public interface CountryRepository extends JpaRepository<CountryMaster, Integer> {

}
