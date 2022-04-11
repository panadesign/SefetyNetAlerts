package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonInfoDto;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
	
	@Autowired
	private PersonManager iPerson;
	
	@PostMapping("/person")
	public void addPerson(com.openclassrooms.safetynet.model.Person person) {
		iPerson.addPerson(person);
	}
	
	@GetMapping("/personInfo")
	public List<PersonInfoDto> getPersonsWithMedicalrecords(@RequestParam String firstName, @RequestParam String lastName) {
		return iPerson.getPersonsByAddressWithMedicalrecords(firstName, lastName);
	}
	
}
