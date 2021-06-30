package com.virtusa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.model.User;

public interface UserRepository extends JpaRepository<User,String> {

	User findByUserName(String userName);
	boolean existsByUserName(String userName);
	boolean existsByAadharNumber(String aadharNumber);
	boolean existsByMobileNumber(String mobileNumber);
	boolean existsByEmail(String email);
	
}
