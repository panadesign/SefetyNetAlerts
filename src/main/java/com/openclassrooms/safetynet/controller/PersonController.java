package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.ParseJSON;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class PersonController {

	Data data;

	@PostMapping("/person")
	public void addPerson(Person person) {
	}

	@PutMapping("/person")
	public String updatePerson() {
		return "mise à jour une personne";
	}

	@DeleteMapping("/person")
	public String deletePerson() {
		return "supprimer une personne";
	}



}
