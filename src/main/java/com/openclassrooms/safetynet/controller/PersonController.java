package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.ParseJSON;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.person.PersonManagement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.CallableMethodReturnValueHandler;

import java.io.IOException;
import java.util.List;

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

	@GetMapping("/communityEmail")
	public String getCommunityEmail(@RequestParam(value = "city")String city) throws IOException, ParseException {
		return communityEmailController.getAllEmailAddressByCity();
	}

}
