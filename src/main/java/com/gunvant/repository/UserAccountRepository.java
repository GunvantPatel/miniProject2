package com.gunvant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gunvant.entity.UserAccountEntity;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {

	UserAccountEntity findByEmail(String email);

	UserAccountEntity findByEmailAndPassword(String email, String password);

}
