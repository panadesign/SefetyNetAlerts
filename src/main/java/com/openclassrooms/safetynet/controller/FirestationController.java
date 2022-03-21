package com.openclassrooms.safetynet.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FirestationController {

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

	@GetMapping("/firestation/stationNumber/{station}")
	public List firestationNumber(@PathVariable int station) {
		List list = null;
		return list;
	}

}
