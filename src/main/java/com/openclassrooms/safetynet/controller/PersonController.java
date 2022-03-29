package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.person.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

	@Autowired
	DataStorage dataStorage;
	@Autowired
	IPerson iPerson;

	@PostMapping("/person")
	public Person addPerson(Person person) {
		return iPerson.addPerson(person);
	}

	@PutMapping("/person")
	public Person updatePerson(@RequestParam String firstName, String lastName) {
		return iPerson.updatePerson(firstName, lastName);
	}

	/*@DeleteMapping("/person")
	public Person deletePerson(Person person, String firstName, String lastName) {
		return iPerson.deletePerson(person);
	}*/

	@GetMapping("/personInfo")
	public List<Person> getAllPersons(@RequestParam String firstName, String lastName) {
		return iPerson.getAllPersons(firstName, lastName);
	}

}
