package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Id;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.person.PersonManager;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private DataStorage dataStorage;
	
	public PersonController(DataStorage dataStorage) {
		this.dataStorage = dataStorage;
	}
	
	@GetMapping("/person")
	public List<Person> personList() {
		return dataStorage.getPersons();
	}
	
	
	@PostMapping("/person")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		log.debug("Add a new person");
		Person personAdded = dataStorage.getPersons().get(0);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.buildAndExpand(personAdded)
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/person")
	public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
		log.debug("Update a person");
		personManager.updatePerson(person);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/person")
	public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
		log.debug("Delete a person");
		personManager.deletePerson(person);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/personInfo")
	ResponseEntity<List<PersonByFirstNameAndLastNameDto>> getPersonsByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
		log.debug("Get persons by firstname and lastname");
		return new ResponseEntity<>(personManager.getPersonsByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
	}
	
}
