package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.ParseJSON;
import com.openclassrooms.safetynet.model.Person;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class PersonController {

	@Autowired
	ParseJSON parseJSON;
	CommunityEmailController communityEmailController;

	@PostMapping("/person")
	public Person addPerson() {
		Person persons = new Person("Charpentier", "Jeremy", " 4 ter rue gambetta", "Vigneux", 91270, "0673249045", "charpentier.jeremy@gmail.com");
		return persons;
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
