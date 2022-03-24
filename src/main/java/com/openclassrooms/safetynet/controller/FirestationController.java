package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.firestation.IFirestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {

	@Autowired
	DataStorage dataStorage;
	@Autowired
	IFirestation iFirestation;

	@PostMapping("/firestation")
	public String addMapping() {
		return "ajouter un mapping caserne/adresse";
	}

	@PutMapping("/firestation")
	public String updateFirestation() {
		return "mise à jour du numéro d'une caserne";
	}

	@DeleteMapping("/firestation")
	public String deletePerson() {
		return "supprimer le mapping d'une caserne ou d'une adresse";
	}

	@GetMapping("/firestation")

	public List<Person> getPeopleByFirestationNumber(@RequestParam int station) {
		return iFirestation.getPeopleByFirestationNumber(station);

	}

}
