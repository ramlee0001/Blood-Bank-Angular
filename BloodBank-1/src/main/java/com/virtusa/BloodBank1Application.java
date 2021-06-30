package com.virtusa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins= "*")
@SpringBootApplication
public class BloodBank1Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(BloodBank1Application.class, args);
	}

}
