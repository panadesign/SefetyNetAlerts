package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonByFirstNameAndLastNameDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.dataStorage.DataStorage;
import com.openclassrooms.safetynet.service.person.PersonManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
public class PersonController {

	private final PersonManager personManager;
	private final DataStorage dataStorage;
	
	public PersonController(DataStorage dataStorage, PersonManager personManager) {
		this.dataStorage = dataStorage;
		this.personManager = personManager;
	}
	
	@GetMapping("/person")
	public List<Person> personList() {
		return dataStorage.getPersons();
	}
	
	
	@PostMapping("/person")
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {
		log.debug("Add a new person");
		personManager.addPerson(person);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/person")
	public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
		log.debug("Update a person");
		personManager.updatePerson(person);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/person")
	public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
		log.debug("Delete a person");
		personManager.deletePerson(person);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/personInfo")
	ResponseEntity<List<PersonByFirstNameAndLastNameDto>> getPersonsByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
		log.debug("Get persons by firstname and lastname");
		return new ResponseEntity<>(personManager.getPersonsByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
	}
}
