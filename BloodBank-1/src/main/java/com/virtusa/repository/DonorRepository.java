package com.virtusa.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.model.Donor;

public interface DonorRepository extends JpaRepository<Donor,String> {

	ArrayList<Donor> findByBloodGroup(String bloodGroup);
	//boolean existsByUserName(String userName);
	//boolean existsByAadharNumber(String aadharNumber);
	//boolean existsByMobileNumber(String mobileNumber);
	
}
