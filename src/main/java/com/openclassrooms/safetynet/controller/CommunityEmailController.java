package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.service.person.PersonManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * The type Community email controller.
 */
@RestController
public class CommunityEmailController {
	
	private final PersonManager personManager;

	/**
	 * Instantiates a new Community email controller.
	 *
	 * @param personManager the person manager
	 */
	CommunityEmailController(PersonManager personManager) {
		this.personManager = personManager;
	}

	/**
	 * Gets all mails by city.
	 *
	 * @param city the city
	 * @return the all mails by city
	 */
	@GetMapping("/communityEmail")
	ResponseEntity<Set<String>> getAllMailsByCity(@RequestParam String city) {
		return new ResponseEntity<>(personManager.getAllMailsByCity(city), HttpStatus.OK);
	}
}
