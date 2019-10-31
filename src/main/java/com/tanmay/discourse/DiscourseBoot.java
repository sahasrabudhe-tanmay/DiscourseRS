package com.tanmay.discourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tanmay.discourse.model.User;
import com.tanmay.discourse.repository.UserRepository;

@SpringBootApplication
public class DiscourseBoot implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DiscourseBoot.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		
		userRepository.save(new User("tanmay", "qfvADbZLTjTpYbR2huE/bg==", "tanmay@tanmay.com", "Tanmay Sahasrabudhe"));
		
		System.out.println("Current records in repository:");
		userRepository.findAll().forEach(u -> System.out.println(u.getUsername()));
	}

}
