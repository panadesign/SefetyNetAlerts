package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.ParseJSON;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.Data;
import com.openclassrooms.safetynet.service.person.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class CommunityEmailController {

	@Autowired
	ParseJSON parseJSON;
	@Autowired
	IPerson iPerson;

	@GetMapping("/communityEmail")
	public Set<String> getAllMailsByCity(@RequestParam String city) {
		return iPerson.getAllMailsByCity(city);
	}
}
