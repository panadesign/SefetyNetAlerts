package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.getPersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
	
	@Autowired
	private PersonManager personManager;
	
	@PostMapping("/person")
	public void addPerson(Person person) {
		personManager.addPerson(person);
	}
	
	@PutMapping("/person")
	public void updatePerson(@RequestParam String firstName, @RequestParam String lastName) {
		personManager.updatePerson(firstName, lastName);
	}
	
	
	@GetMapping("/personInfo")
	ResponseEntity<List<getPersonByFirstNameAndLastNameDto>> getPersonsWithMedicalrecords(@RequestParam String firstName, @RequestParam String lastName) {
		return new ResponseEntity<>(personManager.getPersonsByAddressWithMedicalrecords(firstName, lastName), HttpStatus.OK);
	}
	
}
