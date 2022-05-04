package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.GetPersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.person.PersonManager;
import com.openclassrooms.safetynet.service.person.PersonManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonManager personManager;
	
	@PostMapping("/person")
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {
		logger.debug("Add a new person");
		personManager.addPerson(person);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/person")
	public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
		logger.debug("Update a person");
		personManager.updatePerson(person);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/person")
	public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
		logger.debug("Delete a person");
		personManager.deletePerson(person);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/personInfo")
	ResponseEntity<List<GetPersonByFirstNameAndLastNameDto>> getPersonsByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
		return new ResponseEntity<>(personManager.getPersonsByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
	}
	
}
