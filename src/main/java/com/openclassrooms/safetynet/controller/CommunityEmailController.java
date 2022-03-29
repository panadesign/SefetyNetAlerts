package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.person.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class CommunityEmailController {

	@Autowired
	DataStorage dataStorage;
	@Autowired
	IPerson iPerson;

	@GetMapping("/communityEmail")
	public Set<Person> getAllMailsByCity(@RequestParam String city) {
		return iPerson.getAllMailsByCity(city);
	}
}
