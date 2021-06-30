package com.virtusa.controller;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.model.AuthRequest;
import com.virtusa.model.Donor;
import com.virtusa.model.User;
import com.virtusa.repository.DonorRepository;
import com.virtusa.repository.UserRepository;
import com.virtusa.util.JwtUtil;

@RestController
public class HomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    DonorRepository donorRepo;

    
    public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String currentDate = dateFormat.format(new Date());
		return currentDate;
	}

    @PostMapping("/signUp")
	public String Signup(@RequestBody User user) {
		if(userRepo.existsByUserName(user.getEmail())||userRepo.existsByUserName(user.getName())||userRepo.existsByMobileNumber(user.getMobileNumber())||userRepo.existsByAadharNumber(user.getAadharNumber())) 
			{return("User Name or Mobile Number or Email or Aadhar Number already in use");}
		user.setRole("USER");
		userRepo.save(user);
		return("User Registered Successfully");
	}
    
    @PostMapping("/viewProfile")
	@ResponseBody
	public String viewProfile(@RequestBody String token) {

		User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
		return (user.toString());
	}

    @PostMapping("/donateBlood")
	@ResponseBody
	public String donateBlood(@RequestBody String token) {
    	
		User user = userRepo.findByUserName(jwtUtil.extractUsername(token));
		Donor donor = new Donor();
		donor.setName(user.getName());
		donor.setAadharNumber(user.getAadharNumber());
		donor.setBloodGroup(user.getBloodGroup());
		donor.setDate(getDate());
		donor.setMobileNumber(user.getMobileNumber());
		donorRepo.save(donor);
		return ("Data updated. Thank you");
	}

	@RequestMapping("/requestBlood")
	@ResponseBody
	public String requestBlood(@RequestBody String bloodgroup) {
		System.out.println(bloodgroup);
		ArrayList<Donor> donorList = donorRepo.findByBloodGroup(bloodgroup);
		LocalDate today = LocalDate.now();
		for (Donor i : donorList) {
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate ith = LocalDate.parse(i.getDate(), dt);
			System.out.println(i.getDate());
			long difference = ChronoUnit.DAYS.between(ith,today);
			System.out.println(difference);
			if (difference > 90 && donorRepo.existsById(i.getAadharNumber())) {
				donorRepo.delete(i);
			}
		}
		return(donorRepo.findByBloodGroup(bloodgroup).toString());
	}



    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
    	
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}