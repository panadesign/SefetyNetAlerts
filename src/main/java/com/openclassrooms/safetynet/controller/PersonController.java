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

/**
 * The type Person controller.
 */
@RestController
@Log4j2
public class PersonController {

	private final PersonManager personManager;
	private final DataStorage dataStorage;

	/**
	 * Instantiates a new Person controller.
	 *
	 * @param dataStorage   the data storage
	 * @param personManager the person manager
	 */
	public PersonController(DataStorage dataStorage, PersonManager personManager) {
		this.dataStorage = dataStorage;
		this.personManager = personManager;
	}

	/**
	 * Person list list.
	 *
	 * @return the list
	 */
	@GetMapping("/person")
	public List<Person> personList() {
		return dataStorage.getPersons();
	}


	/**
	 * Add person response entity.
	 *
	 * @param person the person
	 * @return the response entity
	 */
	@PostMapping("/person")
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {
		log.debug("Add a new person");
		personManager.addPerson(person);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Update person response entity.
	 *
	 * @param person the person
	 * @return the response entity
	 */
	@PutMapping("/person")
	public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
		log.debug("Update a person");
		personManager.updatePerson(person);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Delete person response entity.
	 *
	 * @param person the person
	 * @return the response entity
	 */
	@DeleteMapping("/person")
	public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
		log.debug("Delete a person");
		personManager.deletePerson(person);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Gets persons by first name and last name.
	 *
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @return the persons by first name and last name
	 */
	@GetMapping("/personInfo")
	ResponseEntity<List<PersonByFirstNameAndLastNameDto>> getPersonsByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
		log.debug("Get persons by firstname and lastname");
		return new ResponseEntity<>(personManager.getPersonsByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
	}
}
