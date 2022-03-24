package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.ParseJSON;
import com.openclassrooms.safetynet.repository.Data;
import com.openclassrooms.safetynet.service.firestation.IFirestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {

	@Autowired
	ParseJSON parseJSON;
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

	public List<String> getPeopleByFirestationNumber(@RequestParam int station) {
		return iFirestation.getPeopleByFirestationNumber(station);

	}

}
