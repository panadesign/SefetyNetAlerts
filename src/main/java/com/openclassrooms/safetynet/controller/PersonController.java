package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.PersonInfoDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.GlobalService;
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
	@Autowired
	GlobalService globalService;
	
	@PostMapping("/person")
	public void addPerson(Person person) {
		iPerson.addPerson(person);
	}
	
	@GetMapping("/personInfo")
	public List<PersonInfoDto> getPersonsWithMedicalrecords(@RequestParam String firstName, String lastName) {
		return iPerson.getPersonsByAddressWithMedicalrecords(firstName, lastName);
	}
	
}
