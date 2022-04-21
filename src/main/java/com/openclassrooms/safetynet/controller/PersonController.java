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
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {
		personManager.addPerson(person);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/person")
	public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
		personManager.updatePerson(person);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/person")
	public ResponseEntity<Void> deletePerson(@RequestBody Person person) {
		personManager.updatePerson(person);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/personInfo")
	ResponseEntity<List<getPersonByFirstNameAndLastNameDto>> getPersonsWithMedicalrecords(@RequestParam String firstName, @RequestParam String lastName) {
		return new ResponseEntity<>(personManager.getPersonsByAddressWithMedicalrecords(firstName, lastName), HttpStatus.OK);
	}
	
}
