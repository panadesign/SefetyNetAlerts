package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.FirestationDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.firestation.IFirestation;
import com.openclassrooms.safetynet.service.person.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {

	@Autowired
	DataStorage dataStorage;
	@Autowired
	IFirestation iFirestation;
	@Autowired
	IPerson iPerson;

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

	public List<FirestationDto> getPeopleByFirestationNumber(@RequestParam int stationNumber) {
		return iFirestation.getPeopleByFirestationNumber(stationNumber);

	}

}
