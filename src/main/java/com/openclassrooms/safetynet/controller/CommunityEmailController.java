package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.service.person.PersonManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class CommunityEmailController {
	
	private final PersonManager personManager;
	
	CommunityEmailController(PersonManager personManager) {
		this.personManager = personManager;
	}

	@GetMapping("/communityEmail")
	ResponseEntity<Set<String>> getAllMailsByCity(@RequestParam String city) {
		return new ResponseEntity<>(personManager.getAllMailsByCity(city), HttpStatus.OK);
	}
}
