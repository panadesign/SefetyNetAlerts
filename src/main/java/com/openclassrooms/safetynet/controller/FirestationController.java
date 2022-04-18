package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DataStorage;
import com.openclassrooms.safetynet.dto.getPersonsByStationDto;
import com.openclassrooms.safetynet.service.firestation.FirestationManager;
import com.openclassrooms.safetynet.service.person.PersonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {

	@Autowired
	private DataStorage dataStorage;
	@Autowired
	private FirestationManager firestationManager;
	@Autowired
	private PersonManager personManager;

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

	ResponseEntity<List<getPersonsByStationDto>> getPeopleByFirestationNumber(@RequestParam int stationNumber) {
		return new ResponseEntity<>(firestationManager.getPeopleByFirestationNumber(stationNumber), HttpStatus.OK);

	}

}
